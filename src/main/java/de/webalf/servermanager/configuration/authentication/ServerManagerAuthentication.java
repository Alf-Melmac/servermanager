package de.webalf.servermanager.configuration.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Alf
 * @since 28.01.2021
 */
@Component
@NoArgsConstructor
public class ServerManagerAuthentication implements Authentication {
	@Getter
	private String credentials;
	@Getter
	private Collection<? extends GrantedAuthority> authorities;

	public ServerManagerAuthentication(String token) {
		credentials = token;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return false;
	}

	@Override
	public void setAuthenticated(boolean b) {

	}

	@Override
	public String getName() {
		return null;
	}
}
