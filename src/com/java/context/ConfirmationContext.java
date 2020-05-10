package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ConfirmationContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	   private String sessionKey; 
	   private String parkingLotName;
	   private long requestedTime;
	   private long tTime;
	   private String uniqueKey;
	   private long userMobileNumber;
	   private boolean state;
	   private boolean isConfirmed;
	   private boolean isParked;
	   private boolean isFinished;
	   private boolean isCancelled;
	   private int vehicleType;
	   private long price;
	   private long finishTime;

	   public ConfirmationContext(String sessionKey, String parkingLotName, long requestedTime, long tTime,String uniqueKey, long userMobileNumber, boolean state,boolean isConfirmed,int vehicleType,boolean isParked,boolean isFinished,long price,long finishTime,boolean isCancelled){  
	      this.sessionKey = sessionKey;
	      this.parkingLotName = parkingLotName;
	      this.requestedTime = requestedTime;
	      this.tTime = tTime;
	      this.uniqueKey = uniqueKey;
	      this.userMobileNumber = userMobileNumber;
	      this.state = state;
	      this.isConfirmed = isConfirmed;
	      this.vehicleType = vehicleType;
	      this.isParked =isParked;
	      this.isFinished = isFinished;
	      this.price = price;
	      this.finishTime = finishTime;
	      this.isCancelled = isCancelled;

	   }  
	   public boolean getIsCancelled() {
		return isCancelled;
	}
	   @JsonSetter
	public void setIsCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	public long getPrice() {
		return price;
	}
	   @JsonSetter
	public void setPrice(long price) {
		this.price = price;
	}
	public long getFinishTime() {
		return finishTime;
	}
	@JsonSetter
	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}
	public boolean isParked() {
		return isParked;
	}
	@JsonSetter
	public void setParked(boolean isParked) {
		this.isParked = isParked;
	}
	public boolean getIsFinished() {
		return isFinished;
	}
	@JsonSetter
	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
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
		   public long gettTime() {
			return tTime;
		}
		@JsonSetter
		public void settTime(long tTime) {
			this.tTime = tTime;
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
	public boolean getIsConfirmed() {
		return isConfirmed;
	}
	public void setIsConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	public int getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}
	
}




