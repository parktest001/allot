package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ParkingLocationDetailsContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	   private long parkingLotId; 
	   private String parkingName;
	   private String address;
	   private float rating = -1;
	   private long price;

	    
	   public ParkingLocationDetailsContext(long parkingLotId, String parkingName,String address,float rating,long price){  
	      this.parkingLotId = parkingLotId; 
	      this.parkingName = parkingName; 
	      this.address = address;
	      this.rating = rating;
	      this.price = price;

	   }  
	   public ParkingLocationDetailsContext() {}
	   public long getParkingLotId() { 
	      return parkingLotId; 
	   } 
	   @JsonSetter
	   public void setParkingLotId(long parkingLotId) { 
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
}
