/**
 * 
 */
package com.scube.auth.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.mysql.jdbc.Blob;

/**
 * @author dell
 *
 */
@Entity
@Table(name = "oauth_access_token")
public class AuthenticationAccessToken {

	
	@Id
	private String token_id;
	 
	@Lob
	private byte[] token;
	private long authentication_id;
	private String username;
	private String client_id;
	
	@Lob
	private byte[] authentication;
	private String refresh_token;
	
	
	

	

	/**
	 * @return the token_id
	 */
	public String getToken_id() {
		return token_id;
	}
	/**
	 * @param token_id the token_id to set
	 */
	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}
	/**
	 * @return the token
	 */
	public byte[] getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(byte[] token) {
		this.token = token;
	}
	/**
	 * @return the authentication
	 */
	public byte[] getAuthentication() {
		return authentication;
	}
	/**
	 * @param authentication the authentication to set
	 */
	public void setAuthentication(byte[] authentication) {
		this.authentication = authentication;
	}
	/**
	 * @return the authentication_id
	 */
	public long getAuthentication_id() {
		return authentication_id;
	}
	/**
	 * @param authentication_id the authentication_id to set
	 */
	public void setAuthentication_id(long authentication_id) {
		this.authentication_id = authentication_id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the client_id
	 */
	public String getClient_id() {
		return client_id;
	}
	/**
	 * @param client_id the client_id to set
	 */
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	/**
	 * @return the refresh_token
	 */
	public String getRefresh_token() {
		return refresh_token;
	}
	/**
	 * @param refresh_token the refresh_token to set
	 */
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	
	
	
	
}
