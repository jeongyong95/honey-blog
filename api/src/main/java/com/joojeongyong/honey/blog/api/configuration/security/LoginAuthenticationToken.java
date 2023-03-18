package com.joojeongyong.honey.blog.api.configuration.security;

import lombok.AccessLevel;
import lombok.Builder;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder(access = AccessLevel.PRIVATE)
public class LoginAuthenticationToken extends AbstractAuthenticationToken {
	private String email;
	private String password;
	
	/**
	 * Creates a token with the supplied array of authorities.
	 *
	 * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
	 *                    represented by this authentication object.
	 */
	private LoginAuthenticationToken(String email, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.email = email;
		setAuthenticated(true);
	}
	
	public static LoginAuthenticationToken unauthenticated(String email, String password) {
		return LoginAuthenticationToken.builder()
			.email(email)
			.password(password)
			.build();
	}
	
	public static LoginAuthenticationToken authenticated(String email, Collection<? extends GrantedAuthority> authorities) {
		if (email == null) throw new AssertionError();
		return new LoginAuthenticationToken(email, authorities);
	}
	
	@Override
	public Object getPrincipal() {
		return email;
	}
	
	@Override
	public Object getCredentials() {
		return password;
	}
}
