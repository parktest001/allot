package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;


public class MobileContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	   
	   private long userMobileNumber;
	   

	   public MobileContext(long userMobileNumber){  
	    
	      this.userMobileNumber = userMobileNumber;
	      

	   }  
	   public MobileContext() {}
	   public long getUserMobileNumber() { 
	      return userMobileNumber; 
	   } 
	   @JsonSetter
	   public void setUserMobileNumber(long userMobileNumber) { 
	      this.userMobileNumber = userMobileNumber;
	   }
}