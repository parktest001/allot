package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ParkingLocationDetailsContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	   private String parkingLotId; 
	   private String parkingName;
	   private String displayName;
	   private String address;
	   private float rating = -1;
	   private long bikePrice;
	   private long carPrice;
	   private long liveCarCount;
	   private long liveBikeCount;
	   private double lattitude;
	   private double longitude;
	   private long carCapacity;
	   private long bikeCapacity;
	    
	   
	   public ParkingLocationDetailsContext(String parkingLotId, String parkingName,String displayName, String address, float rating,
			long bikePrice, long carPrice, long liveCarCount, long liveBikeCount,double lattitude,double longitude,long carCapacity,long bikeCapacity) {
		this.parkingLotId = parkingLotId;
		this.parkingName = parkingName;
		this.displayName = displayName;
		this.address = address;
		this.rating = rating;
		this.bikePrice = bikePrice;
		this.carPrice = carPrice;
		this.liveCarCount = liveCarCount;
		this.liveBikeCount = liveBikeCount;
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.carCapacity = carCapacity;
		this.bikeCapacity = bikeCapacity;
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
	   public String getDisplayName() { 
		      return displayName; 
		   } 
		   @JsonSetter 
		   public void setDisplayName(String displayName) { 
		      this.displayName = displayName; 
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
		public long getBikePrice() {
			return bikePrice;
		}
		@JsonSetter
		public void setBikePrice(long bikePrice) {
			this.bikePrice = bikePrice;
		}
		public long getCarPrice() {
			return carPrice;
		}
		@JsonSetter
		public void setCarPrice(long carPrice) {
			this.carPrice = carPrice;
		}
		public double getLattitude() {
			return lattitude;
		}
		public void setLattitude(double lattitude) {
			this.lattitude = lattitude;
		}
		public double getLongitude() {
			return longitude;
		}
		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}
		public long getCarCapacity() { 
		      return carCapacity; 
		   } 
		   @JsonSetter
		   public void setCarCapacity(long carCapacity) { 
		      this.carCapacity = carCapacity; 
		   }
		   public long getBikeCapacity() { 
		      return bikeCapacity; 
		   } 
		   @JsonSetter
		   public void setBikeCapacity(long bikeCapacity) { 
		      this.bikeCapacity = bikeCapacity; 
		   }
		   
}
