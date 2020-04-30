package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class UserLogInContext implements Serializable{

	private static final long serialVersionUID = 1L; 
	   private String userName ;
	   private String passWord ; 
	    
	   public UserLogInContext(String userName,String passWord){  
		   this.userName=userName;
		   this.passWord=passWord;
	   }  
	   public UserLogInContext() {}
	
	   public String getUserName() { 
	      return userName; 
	   } 
	   @JsonSetter
	   public void setUserName(String userName) { 
	      this.userName = userName; 
	   } 
	   public String getpassWord() { 
		      return passWord; 
		   } 
	   @JsonSetter
	   public void setpassWord(String passWord) { 
		      this.passWord = passWord; 
	   } 
}
