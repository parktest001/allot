package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class LiveContext implements Serializable{
	private static final long serialVersionUID = 1L;
	   private String parkingLotName;
	   private long liveCarCount;
	   private long liveBikeCount;
	   
	    
	   public LiveContext(String parkingLotName, long liveCarCount, long liveBikeCount){  
	      this.parkingLotName = parkingLotName;
	      this.liveCarCount = liveCarCount;
	      this.liveBikeCount = liveBikeCount;
	      
	   }  
	   public LiveContext() {}
	   public String getParkingLotName() { 
	      return parkingLotName; 
	   } 
	   @JsonSetter
	   public void setParkingLotName(String parkingLotName) { 
	      this.parkingLotName = parkingLotName; 
	   } 

	   public long getLiveCarCount() {
	   	return liveCarCount;
	   }
	   @JsonSetter
	   public void setLiveCarCount(long liveCarCount) {
	   	this.liveCarCount = liveCarCount;
	   }

	   public long getLiveBikeCount() {
	   	return liveBikeCount;
	   }
	   @JsonSetter
	   public void setLiveBikeCount(long liveBikeCount) {
	   	this.liveBikeCount = liveBikeCount;
	   }
}