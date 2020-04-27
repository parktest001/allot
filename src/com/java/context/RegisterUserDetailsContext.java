package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class RegisterUserDetailsContext implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;
	private String passWord;
	private long mobile;

	public RegisterUserDetailsContext(String userName, String passWord, long number){
		this.userName = userName;
		this.passWord = passWord;
		this.mobile = mobile;
	}
	
	public RegisterUserDetailsContext() {}

	public String getUserName(){
		return userName;
	}
	@JsonSetter
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getPassWord(){
		return passWord;
	}
	@JsonSetter
	public void setPassWord(String passWord){
		this.passWord = passWord;
	}
	public long getMobile(){
		return mobile;
	}
	@JsonSetter
	public void setMobile(long mobile){
		this.mobile = mobile;
	}

}