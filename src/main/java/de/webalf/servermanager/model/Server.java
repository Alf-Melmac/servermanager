package de.webalf.servermanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import static de.webalf.servermanager.model.Constants.CMD_EXE;
import static de.webalf.servermanager.model.Constants.EXECUTE_AND_TERMINATE;

/**
 * @author Alf
 * @since 28.01.2021
 */
@Getter
@AllArgsConstructor
public enum Server {
	TEST(new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL \"Test File.lnk\""}, new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL test.bat"}, "test"),
	ARMA(new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL \"Server Autostart.lnk\""}, new String[]{CMD_EXE, EXECUTE_AND_TERMINATE, "CALL \"Server Autostop.lnk\""}, "185.232.71.213:2302");

	private final String[] startFilePath;
	private final String[] stopFilePath;
	private final String ip;

	@Getter
	private static final Map<String, String> ipToEnumMap = new HashMap<>();

	static {
		for (Server server : EnumSet.allOf(Server.class)) {
			ipToEnumMap.put(server.getIp(), server.toString());
		}
	}
}
