/**
 * 
 *//*
package com.scube.auth.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



*//**
 * @author dell
 *
 *//*
@Entity
@Table(name="AuthClientDetails")
public class AuthClientDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String resourceIds;
	private String client_secret;
	private Set<String> scope;
	private Set<String> grantTypes;
	private String redirectUrl;
	private Set<String> authorities;
	private long access_token_validity;
	private long refresh_token_validity;

	@OneToOne
	@JoinColumn(name="user_Id",referencedColumnName="id")
	private DbUser user;
	
	
	*//**
	 * @return the user
	 *//*
	
	public DbUser getUser() {
		return user;
	}
	*//**
	 * @param user the user to set
	 *//*
	public void setUser(DbUser user) {
		this.user = user;
	}
	*//**
	 * @return the userId
	 *//*
	@JoinColumn
	public DbUser getUserId() {
		return userId;
	}
	*//**
	 * @param userId the userId to set
	 *//*
	public void setUserId(DbUser userId) {
		this.userId = userId;
	}
	private DbUser userId;
	
	*//**
	 * @return the id
	 *//*
	public long getId() {
		return id;
	}
	*//**
	 * @param id the id to set
	 *//*
	public void setId(long id) {
		this.id = id;
	}
	*//**
	 * @return the resourceIds
	 *//*
	public String getResourceIds() {
		return resourceIds;
	}
	*//**
	 * @param resourceIds the resourceIds to set
	 *//*
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	*//**
	 * @return the client_secret
	 *//*
	public String getClient_secret() {
		return client_secret;
	}
	*//**
	 * @param client_secret the client_secret to set
	 *//*
	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}
	
	*//**
	 * @return the redirectUrl
	 *//*
	public String getRedirectUrl() {
		return redirectUrl;
	}
	*//**
	 * @param redirectUrl the redirectUrl to set
	 *//*
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	*//**
	 * @return the access_token_validity
	 *//*
	public long getAccess_token_validity() {
		return access_token_validity;
	}
	*//**
	 * @param access_token_validity the access_token_validity to set
	 *//*
	public void setAccess_token_validity(long access_token_validity) {
		this.access_token_validity = access_token_validity;
	}
	*//**
	 * @return the refresh_token_validity
	 *//*
	public long getRefresh_token_validity() {
		return refresh_token_validity;
	}
	*//**
	 * @param refresh_token_validity the refresh_token_validity to set
	 *//*
	public void setRefresh_token_validity(long refresh_token_validity) {
		this.refresh_token_validity = refresh_token_validity;
	}
	*//**
	 * @return the scope
	 *//*
	public Set<String> getScope() {
		return scope;
	}
	*//**
	 * @param scope the scope to set
	 *//*
	public void setScope(Set<String> scope) {
		this.scope = scope;
	}
	*//**
	 * @return the grantTypes
	 *//*
	public Set<String> getGrantTypes() {
		return grantTypes;
	}
	*//**
	 * @param grantTypes the grantTypes to set
	 *//*
	public void setGrantTypes(Set<String> grantTypes) {
		this.grantTypes = grantTypes;
	}
	*//**
	 * @return the authorities
	 *//*
	public Set<String> getAuthorities() {
		return authorities;
	}
	*//**
	 * @param authorities the authorities to set
	 *//*
	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}
	
	
	
	
	
	
}
*/