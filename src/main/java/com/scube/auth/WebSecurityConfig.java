package com.scube.auth;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.server.ApacheDSContainer;

import com.scube.auth.serviceImpl.CustomAuthencationProvider;
import com.scube.auth.serviceImpl.CustomAuthenticationEntryPoint;
import com.scube.auth.serviceImpl.LdapCustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
/*	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;*/
	/*@Autowired
    private ClientDetailsService userDetailsService;
   */

	@Autowired
	private CustomAuthenticationEntryPoint authenticationEntryPoint;
	
	private static final int EMBEDDED_LDAP_SERVER_PORT = 33388;
	
	boolean authServer = true; 
	  
	 @Autowired
    private CustomAuthencationProvider authenticationProvider;
    
	 @Autowired
    private LdapCustomAuthenticationProvider ldapAuthentionProvider;

    @Qualifier("authenticationManagerBean")
    @Override
    @Bean
    public AuthenticationManager authenticationManager() {

    	System.out.println("authServer test :::::::::::::::::::"+authServer);
    	if(authServer){
        return new ProviderManager(Arrays.asList((AuthenticationProvider) authenticationProvider));
    	}
    	else
    	{
        return new ProviderManager(Arrays.asList((AuthenticationProvider) ldapAuthentionProvider));

    	}
    	}
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
   
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
   
    	 http
    	 
    	 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).
 		and()
         .authorizeRequests()
             .anyRequest().authenticated()
             .and()
         .formLogin()
             .loginPage("/login")
             .permitAll()
             .and()
         .logout()                                    
             .permitAll();
    	
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
	  // web.ignoring().antMatchers("/fonts/**");
	   web.ignoring().antMatchers("/resources/**");
	   web.ignoring().antMatchers("/registration**");
	   
	   
	   
	  
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	//	auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
           auth.authenticationProvider(authenticationProvider); 
           auth.ldapAuthentication().contextSource().ldif("classpath:test-server.ldif") ;
           auth.authenticationProvider(ldapAuthentionProvider);
           
           }
    /**
     * This bean starts an embedded LDAP Server. Note that <code>start</code>
     * is not called on the server as the same is done as part of the bean life cycle's
     * afterPropertySet() method.
     *
     * @return The Embedded Ldap Server 
     * @throws Exception
     */
   @Bean(name = "ldap-server")
    public ApacheDSContainer getLdapServer() throws Exception {
      ApacheDSContainer container = new ApacheDSContainer("dc=springframework,dc=org",
          "classpath:test-server.ldif");
      container.setPort(EMBEDDED_LDAP_SERVER_PORT);
      return container;
    }
    
    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
           contextSource.setUrl("ldap://127.0.0.1:" + EMBEDDED_LDAP_SERVER_PORT+"/dc=springframework,dc=org" );
            contextSource.setPassword("naveen");
            contextSource.setUserDn("ou=groups,dc=springframework,dc=org");
            contextSource.setAnonymousReadOnly(true);
        return contextSource;
    }
    
    @Bean
    public LdapTemplate ldapTemplate() {
    	
    	LdapTemplate ldapTemplate = new LdapTemplate(contextSource());
    	ldapTemplate.setIgnorePartialResultException(true);
		ldapTemplate.setContextSource(contextSource());
		return ldapTemplate;
         
    }
    
	}