package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class UserLogInContext implements Serializable{

	private static final long serialVersionUID = 1L; 
	   private long mobile ;
	   private String passWord ; 
	    
	   public UserLogInContext(long mobile,String passWord){  
		   this.mobile=mobile;
		   this.passWord=passWord;
	   }  
	   public UserLogInContext() {}
	
	   public long getMobile() { 
	      return mobile; 
	   } 
	   @JsonSetter
	   public void setMobile(long mobile) { 
	      this.mobile = mobile; 
	   } 
	   public String getpassWord() { 
		      return passWord; 
		   } 
	   @JsonSetter
	   public void setpassWord(String passWord) { 
		      this.passWord = passWord; 
	   } 
}
