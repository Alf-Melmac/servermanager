package de.webalf.servermanager.controller;

import de.webalf.servermanager.model.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static de.webalf.servermanager.controller.Urls.API;

/**
 * @author Alf
 * @since 28.01.2021
 */
@RequestMapping(API + "/status")
@RestController
@Slf4j
public class StatusController {

	@GetMapping
	public ResponseEntity<Void> ping() {
		log.trace("ping");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/mappings")
	public Map<String, String> getMappings() {
		log.trace("getMappings");
		return Server.getIpToEnumMap();
	}
}
