package de.webalf.servermanager.service;

import de.webalf.servermanager.model.Result;
import de.webalf.servermanager.model.Server;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumMap;

/**
 * @author Alf
 * @since 28.01.2021
 */
@UtilityClass
@Slf4j
public class ServerService {
	private static final String BASE_PATH = "C:\\Users\\Administrator\\Desktop";

	private static final ProcessBuilder PROCESS_BUILDER = new ProcessBuilder();
	private static final EnumMap<Server, LocalDateTime> lastExecution = new EnumMap<>(Server.class);

	static {
		PROCESS_BUILDER.redirectErrorStream(true); //Redirects stderr to stdout
		PROCESS_BUILDER.directory(new File(BASE_PATH));
	}

	/**
	 * Restarts the given {@link Server}.
	 * A command can only be executed, if 15 minutes passed since the last execution.
	 *
	 * @param server server that should be toggled
	 */
	public static Result restartServer(Server server) {
		if (lastExecution.containsKey(server)) {
			if (LocalDateTime.now().minusMinutes(15).isBefore(lastExecution.get(server))) {
				return Result.builder().status(HttpStatus.TOO_EARLY).exitCode(false).build();
			}
		} else {
			lastExecution.put(server, LocalDateTime.now());
		}

		PROCESS_BUILDER.command(server.getRestartFilePath());
		return execute();
	}

	private static Result execute() {
		Process process;

		log.trace(Arrays.toString(PROCESS_BUILDER.command().toArray()));

		try {
			process = PROCESS_BUILDER.start();
		} catch (IOException e) {
			log.error("Error starting shell command {}", Arrays.toString(PROCESS_BUILDER.command().toArray()), e);
			return Result.builder().status(HttpStatus.SERVICE_UNAVAILABLE).exitCode(false).build();
		}

		try (final InputStreamReader in = new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8);
			 final BufferedReader bufferedReader = new BufferedReader(in)) {
			int linesRead = 0;
			while (true) {
				final String line = bufferedReader.readLine();
				log.info(line);
				linesRead++;
				if (line.contains(" beendet!")) {
					log.info("Finished command");
					return Result.builder().status(HttpStatus.OK).exitCode(true).build();
				}
				if (linesRead > 65) {
					return Result.builder().status(HttpStatus.GATEWAY_TIMEOUT).exitCode(false).build();
				}
			}
		} catch (IOException e) {
			log.error("Closed or failed to open bufferedReader", e);
			return Result.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).exitCode(false).build();
		}
	}

	/**
	 * Resets the last execution time for the given {@link Server}
	 *
	 * @param server to reset cooldown for
	 */
	public static void resetCooldown(Server server) {
		lastExecution.remove(server);
	}
}
