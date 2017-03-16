package com.scube.auth.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.auth.model.DbUser;
import com.scube.auth.model.Role;
import com.scube.auth.repository.UserRepository;

@Service
@Qualifier("clientDetails")
public class CustomClientDetailsService implements ClientDetailsService {
    @Autowired
    private UserRepository userRepository;

  /* @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DbUser user = userRepository.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
*/   

   @Override
   @Transactional
   public ClientDetails loadClientByClientId (String clientId) throws ClientRegistrationException {

    
	   DbUser user = userRepository.findByClientId(clientId);
	  /* if(user==null){
        user = userRepository.findByUsername(clientId);

	   	}*/
	  
       
       if (user!=null) {
       	
			BaseClientDetails details = new BaseClientDetails();
			
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	        for (Role role : user.getRoles()){
	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
	        }

            
            details.setClientId(user.getClientId());
            details.setClientSecret("clientSecret");
            Collection<String> authorizedGrant = new ArrayList<String>();
            authorizedGrant.add("password");
            authorizedGrant.add("refresh_token");
            authorizedGrant.add("authorization_code");
            authorizedGrant.add("implicit");
            authorizedGrant.add("client_credentials");
 
            
            Collection<String> resourcIds = new ArrayList<String>();
            resourcIds.add("naveen");
            resourcIds.add("kumawat");
            
            details.setResourceIds(resourcIds);
            details.setAuthorizedGrantTypes(authorizedGrant);
            
            Collection<String> scope = new ArrayList<String>();
            scope.add("read");
            scope.add("write");
            
            details.setScope(scope);
            details.setAuthorities(grantedAuthorities);
            details.setAccessTokenValiditySeconds(60);
            
            
            return details;
       }
            
       return null;
      

   }
    
    
    
}
