package de.webalf.servermanager.configuration.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author Alf
 * @since 28.01.2021
 */
@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EndpointConfig extends WebSecurityConfigurerAdapter {
	private final TokenAuthFilter tokenAuthFilter;
	private final TokenAuthProvider tokenAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// no session management required
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()

				.authorizeRequests()

				// all other requests must be authenticated
				.anyRequest().fullyAuthenticated()
				.and()

				// disable Cross Site Request Forgery token
				// we do not rely on cookie based auth and are completely stateless so we are safe
				.csrf().disable()

				// authentication for token based authentication
				.authenticationProvider(tokenAuthProvider)
				.addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class);
	}
}
