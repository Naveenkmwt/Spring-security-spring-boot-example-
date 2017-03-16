package com.scube.auth.serviceImpl;
/*package com.hellokoding.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.stereotype.Component;

import com.hellokoding.auth.repository.UserRepository;

*//**
 * 
 *//*

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hellokoding.auth.model.DbUser;
import com.hellokoding.auth.model.Role;
import com.hellokoding.auth.repository.UserRepository;

*//**
 * @author dell
 *
 *//*
@Component
public class ClientAndUserDetailsService implements UserDetailsService,ClientDetailsService {
	
 	final ClientDetailsService clients;
	  

    final UserDetailsService users;

    final ClientDetailsUserDetailsService clientDetailsWrapper;
  
    
 public ClientAndUserDetailsService(ClientDetailsService clients,
                                      UserDetailsService users) {
	  
       super();
       this.clients = clients;
       this.users = users;
       clientDetailsWrapper = new ClientDetailsUserDetailsService(this.clients);
   }
 
	

	@Autowired
    private UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	
        UserDetails user = null;
        try{
            user = users.loadUserByUsername(username);
        }catch(UsernameNotFoundException e){
            user = clientDetailsWrapper.loadUserByUsername(username);
        }
        return user;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId)
            throws ClientRegistrationException {
        return clients.loadClientByClientId(clientId);
    }



}
*/