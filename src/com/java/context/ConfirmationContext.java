package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ConfirmationContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	   private String sessionKey; 
	   private String location;
	   private long requestedTime;
	   private long expectedTime;
	   private String uniqueKey;
	   private long userMobileNumber;
	   private boolean state;

	   public ConfirmationContext(String sessionKey, String location, long requestedTime, long expectedTime, String uniqueKey, long userMobileNumber, boolean state){  
	      this.sessionKey = sessionKey;
	      this.location = location;
	      this.requestedTime = requestedTime;
	      this.expectedTime = expectedTime;
	      this.uniqueKey = uniqueKey;
	      this.userMobileNumber = userMobileNumber;
	      this.state = state;

	   }  
	   public ConfirmationContext() {}
	   public String getSessionKey() { 
	      return sessionKey; 
	   } 
	   @JsonSetter
	   public void setSessionKey(String sessionKey) { 
	      this.sessionKey = sessionKey;
	   } 

	   public String getLocation() {
	   	  return location;
	   }
	   @JsonSetter
	   public void setLocation(String location) {
	   		this.location = location;
	   }

	   public long getRequestedTime() {
	   		return requestedTime;
	   }
	   @JsonSetter
	   public void setRequestedTime(long requestedTime) {
	   		this.requestedTime = requestedTime;
	   }

	   public long getExpectedTime() {
	   		return expectedTime;
	   }
	   @JsonSetter
	   public void setExpectedTime(long expectedTime) {
	   		this.expectedTime = expectedTime;
	   }

	   public String getUniqueKey() { 
	      return uniqueKey; 
	   } 
	   @JsonSetter
	   public void setUniqueKey(String uniqueKey) { 
	      this.uniqueKey = uniqueKey;
	   }

	   public long getUserMobileNumber() { 
	      return userMobileNumber; 
	   } 
	   @JsonSetter
	   public void setUserMobileNumber(long userMobileNumber) { 
	      this.userMobileNumber = userMobileNumber;
	   }

	   public boolean getState() { 
	      return state; 
	   } 
	   @JsonSetter
	   public void setState(boolean state) { 
	      this.state = state;
	   }
}




