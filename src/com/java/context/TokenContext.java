package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;


public class TokenContext implements Serializable{
	private static final long serialVersionUID = 1L;
	private long userMobileNumber;
	public TokenContext(long userMobileNumber, String token) {
		this.userMobileNumber = userMobileNumber;
		this.token = token;
	}
	public TokenContext() {}
	private String token;
	public long getUserMobileNumber() {
		return userMobileNumber;
	}
	@JsonSetter
	public void setUserMobileNumber(long userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}
	public String getToken() {
		return token;
	}
	@JsonSetter
	public void setToken(String token) {
		this.token = token;
	}
	

}
