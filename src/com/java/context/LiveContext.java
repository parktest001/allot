package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class LiveContext implements Serializable{
	private static final long serialVersionUID = 1L;
	   private String parkingLotName;
	   private int liveCarCount;
	   private int liveBikeCount;
	   
	    
	   public LiveContext(String parkingLotName, int liveCarCount, int liveBikeCount){  
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

	   public int getLiveCarCount() {
	   	return liveCarCount;
	   }
	   @JsonSetter
	   public void setLiveCarCount(int liveCarCount) {
	   	this.liveCarCount = liveCarCount;
	   }

	   public int getLiveBikeCount() {
	   	return liveBikeCount;
	   }
	   @JsonSetter
	   public void setLiveBikeCount(int liveBikeCount) {
	   	this.liveBikeCount = liveBikeCount;
	   }
}