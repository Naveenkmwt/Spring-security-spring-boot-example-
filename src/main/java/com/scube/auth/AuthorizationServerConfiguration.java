/**
 * 
 */
package com.scube.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @author dell
 *
 */
@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter implements EnvironmentAware{


	  @Value("${config.oauth2.privateKey}")
      private String privateKey;

     

        private static final String ENV_OAUTH = "authentication.oauth.";
        private static final String PROP_CLIENTID = "clientid";
        private static final String PROP_SECRET = "secret";
        private static final String PROP_TOKEN_VALIDITY_SECONDS = "tokenValidityInSeconds";

        private RelaxedPropertyResolver propertyResolver;
       

        @Autowired
        private DataSource dataSource;
        
   
        @Autowired
        @Qualifier("clientDetails")
        private ClientDetailsService clientDetails;
       
      
        
        @Bean
        @Qualifier("tokenStore")
        public TokenStore tokenStore() {
         //  return new JdbcTokenStore(dataSource);
        	
        	return new JwtTokenStore(accessTokenConverter());
        }

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;
        
     
        @Bean
        public JwtAccessTokenConverter accessTokenConverter() {
        	 JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        	    KeyStoreKeyFactory keyStoreKeyFactory = 
        	      new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
        	    converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
            return converter;
        }
        

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints
            		
            		//.clientDetailsService(clientDetails)
            		.tokenStore(tokenStore())
            		.accessTokenConverter(accessTokenConverter())
                    .authenticationManager(authenticationManager)
                   
            		;
            		
        }

        
       
        
        @Override
    	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    		// @formatter:off
    		clients.withClientDetails(clientDetails);
    		// @formatter:on
    	}
      /*  @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            
			clients
            .withClientDetails(clientDetailsService);
                    .inMemory()
                    .withClient(propertyResolver.getProperty("rajithapp"))
                    .scopes("read", "write")
                    .authorities(Authorities.ROLE_ADMIN.name(), Authorities.ROLE_USER.name())
                    .authorizedGrantTypes("password", "refresh_token")
                    .secret(propertyResolver.getProperty("secret"))
                    .accessTokenValiditySeconds(propertyResolver.getProperty(PROP_TOKEN_VALIDITY_SECONDS, Integer.class, 1800));
        }*/

        @Override
        public void setEnvironment(Environment environment) {
            this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_OAUTH);
        }

    
}
