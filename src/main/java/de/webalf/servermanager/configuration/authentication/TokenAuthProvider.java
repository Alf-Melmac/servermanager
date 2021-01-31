package de.webalf.servermanager.configuration.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author Alf
 * @since 28.01.2021
 */
@Component
@Slf4j
public class TokenAuthProvider implements AuthenticationProvider {
	@Value("${server-manager.auth.token}")
	private String authToken;

	@Override
	public Authentication authenticate(Authentication auth) throws BadCredentialsException {
		// get the token from the authentication object
		String token = auth.getCredentials().toString();

		ServerManagerAuthentication serverManagerAuth = new ServerManagerAuthentication(token);

		if (token.equals(authToken)) {
			serverManagerAuth.setAuthenticated(true);
		} else {
			log.warn("Invalid token " + token);
			throw new BadCredentialsException("Invalid token " + token);
		}

		return serverManagerAuth;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return (ServerManagerAuthentication.class.isAssignableFrom(aClass));
	}
}
