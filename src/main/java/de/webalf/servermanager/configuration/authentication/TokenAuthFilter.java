package de.webalf.servermanager.configuration.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alf
 * @since 28.01.2021
 */
@Component
public class TokenAuthFilter extends OncePerRequestFilter {
	@Value("${server-manager.auth.token.name:server-manager-auth-token}")
	private String authTokenName;

	@Override
	protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws IOException, ServletException {
		// check if header contains auth token
		String authToken = request.getHeader(authTokenName);

		// if there is an auth token, create an Authentication object
		if (authToken != null) {
			Authentication auth = new ServerManagerAuthentication(authToken);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		// forward the request
		filterChain.doFilter(request, response);
	}
}