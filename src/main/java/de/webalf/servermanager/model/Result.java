package de.webalf.servermanager.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

/**
 * @author Alf
 * @since 31.01.2021
 */
@Value
@Builder
public class Result {
	HttpStatus status;
	boolean exitCode;
}
