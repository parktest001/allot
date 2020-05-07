package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ParkingLocationDetailsContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	   private String parkingLotId; 
	   private String parkingName;
	   private String address;
	   private float rating = -1;
	   private long price;
	   private int liveCarCount;
	   private int liveBikeCount;
	   

	    
	   public ParkingLocationDetailsContext(String parkingLotId, String parkingName,String address,float rating,long price,int liveCarCount,int liveBikeCount){  
	      this.parkingLotId = parkingLotId; 
	      this.parkingName = parkingName; 
	      this.address = address;
	      this.rating = rating;
	      this.price = price;
	      this.liveCarCount = liveCarCount;
	      this.liveBikeCount = liveBikeCount;

	   }  
	   public ParkingLocationDetailsContext() {}
	   public String getParkingLotId() { 
	      return parkingLotId; 
	   } 
	   @JsonSetter
	   public void setParkingLotId(String parkingLotId) { 
	      this.parkingLotId = parkingLotId; 
	   } 
	   public String getParkingName() { 
	      return parkingName; 
	   } 
	   @JsonSetter 
	   public void setParkingName(String parkingName) { 
	      this.parkingName = parkingName; 
	   }  
	   public String getAddress() { 
		      return address; 
		   } 
	   @JsonSetter 
	   public void setAddress(String address) { 
		   this.address = address; 
	   } 
	   public float getRating() { 
		      return rating; 
		   } 
	   @JsonSetter 
		public void setRating(float rating) { 
			   this.rating = rating; 
		}  
	   public long getPrice() { 
		      return price; 
		   } 
	   @JsonSetter 
		public void setPrice(long price) { 
			   this.price = price; 
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
