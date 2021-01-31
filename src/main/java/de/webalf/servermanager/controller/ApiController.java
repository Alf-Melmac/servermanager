package de.webalf.servermanager.controller;

import de.webalf.servermanager.model.Result;
import de.webalf.servermanager.model.Server;
import de.webalf.servermanager.service.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static de.webalf.servermanager.controller.Urls.API;

/**
 * @author Alf
 * @since 28.01.2021
 */
@RequestMapping(API)
@RestController
@Slf4j
public class ApiController {

	@PutMapping("/{server}/{start}")
	public ResponseEntity<Boolean> putServerToggle(@PathVariable Server server,
												   @PathVariable boolean start) {
		log.trace("putServerToggle: " + server + " " + start);

		final Result result = ServerService.toggleServer(server, start);
		return new ResponseEntity<>(result.isExitCode(), result.getStatus());
	}

	@PutMapping("/{server}/resetCooldown")
	public void putResetCooldown(@PathVariable Server server) {
		log.trace("putResetCooldown: server: " + server);

		ServerService.resetCooldown(server);
	}
}
