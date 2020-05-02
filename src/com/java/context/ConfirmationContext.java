package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ConfirmationContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	   private String sessionKey; 
	   private String parkingLotName;
	   private long requestedTime;
	   private String uniqueKey;
	   private long userMobileNumber;
	   private boolean state;

	   public ConfirmationContext(String sessionKey, String parkingLotName, long requestedTime, String uniqueKey, long userMobileNumber, boolean state){  
	      this.sessionKey = sessionKey;
	      this.parkingLotName = parkingLotName;
	      this.requestedTime = requestedTime;
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

	   public String getParkingLotName() {
	   	  return parkingLotName;
	   }
	   @JsonSetter
	   public void setParkingLotName(String parkingLotName) {
	   		this.parkingLotName = parkingLotName;
	   }

	   public long getRequestedTime() {
	   		return requestedTime;
	   }
	   @JsonSetter
	   public void setRequestedTime(long requestedTime) {
	   		this.requestedTime = requestedTime;
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




