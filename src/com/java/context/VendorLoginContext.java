package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class VendorLoginContext implements Serializable{

	private static final long serialVersionUID = 1L; 
	   private String userId ;
	   private String passWord ; 
	    
	   public VendorLoginContext(String userId,String passWord){  
		   this.userId=userId;
		   this.passWord=passWord;
	   }  
	   public VendorLoginContext() {}
	
	   public String getUserId() { 
	      return userId; 
	   } 
	   @JsonSetter
	   public void setUserId(String userId) { 
	      this.userId = userId; 
	   } 
	   public String getpassWord() { 
		      return passWord; 
		   } 
	   @JsonSetter
	   public void setpassWord(String passWord) { 
		      this.passWord = passWord; 
	   } 
}