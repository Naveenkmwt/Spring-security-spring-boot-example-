/**
 * 
 */
package com.scube.auth.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author dell
 *
 */
@Table(name="oauth_refresh_token")
@Entity
public class AuthRefreshToken {

	
	@Id
	String token_id; 
	@Lob
	private byte[] token ;
	@Lob
	private byte[] authentication ;
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
	
}
