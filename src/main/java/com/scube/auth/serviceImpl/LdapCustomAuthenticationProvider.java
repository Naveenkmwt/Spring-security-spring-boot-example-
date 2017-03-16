/**
 * 
 */
package com.scube.auth.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * @author dell
 *
 */
@Service
public class LdapCustomAuthenticationProvider  implements AuthenticationProvider{

	@Autowired
	private LdapTemplate ldapTemplate;
	
	
	
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		 AndFilter filter = new AndFilter();
 filter.and(new EqualsFilter("objectclass", "top")).and(
			new EqualsFilter("uid", authentication.getPrincipal()
					.toString()));
 boolean authenticated = ldapTemplate.authenticate(DistinguishedName.EMPTY_PATH, filter.toString(), authentication.getCredentials().toString());
 
		if (authenticated)
		{
			  List<GrantedAuthority> grantedAuths = new ArrayList<>();
	            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
	          //  final UserDetails principal = new User(name, password, grantedAuths);
	          Authentication auth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), grantedAuths);
	            return auth;
			
		}
		throw new InternalAuthenticationServiceException(
				"Bad User Credentials.");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

	
}
