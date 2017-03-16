package com.scube.auth;
/**
 * 
 */

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.scube.auth.serviceImpl.CustomAuthenticationEntryPoint;

/**
 * @author dell
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfigure extends ResourceServerConfigurerAdapter {

	@Autowired
    TokenStore tokenStore;

	/* @Value("${config.oauth2.publicKey}")
     private String publicKey;*/
	 
    @Autowired
    JwtAccessTokenConverter tokenConverter;
    
	@Autowired
	private CustomAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
	    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	    Resource resource = new ClassPathResource("public.txt");
	    String publicKey = null;
	    try {
	        publicKey = IOUtils.toString(resource.getInputStream());
	    } catch (final IOException e) {
	        throw new RuntimeException(e);
	    }
	    converter.setVerifierKey(publicKey);
	    return converter;
	}
	
    @Override
	public void configure(HttpSecurity http) throws Exception {
    	
      // @formatter:off
		http
		.antMatcher("/auth/**").authorizeRequests().anyRequest().authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

    	
    	
    }
    
  
}
