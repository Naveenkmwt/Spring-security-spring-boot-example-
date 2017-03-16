
package com.scube.auth.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.scube.auth.model.DbUser;
import com.scube.auth.model.Role;
import com.scube.auth.repository.UserRepository;

@Component
public class CustomAuthencationProvider implements AuthenticationProvider {
 
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPassword;
	
	
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();
        
        DbUser user = userRepo.findByUsername(name);
        if (bCryptPassword.matches(authentication.getCredentials().toString(),user.getPassword())) {
        	
            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            	   grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            	   grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            
          //  final UserDetails principal = new User(name, password, grantedAuths);
            final Authentication auth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), grantedAuths);
            System.out.println("::::::::::::::::::::::::::::::::"+auth.toString());

            return auth;
            
        } else {
            return null;
        }
    }

    @Override
	public boolean supports(Class<? extends Object> authentication)
	{

		return true;
	}
}