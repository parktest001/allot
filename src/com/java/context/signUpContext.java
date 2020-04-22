package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class signUpContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	   private String parkingLotName;
	   private double lattitude;
	   private double longitude;
	   private long carCapacity;
	   private long bikeCapacity;

	    
	   public signUpContext(String parkingLotName, double lattitude, double longitude, long carCapacity, long bikeCapacity){  
	      this.parkingLotName = parkingLotName;
	      this.lattitude = lattitude;
	      this.longitude = longitude;
	      this.carCapacity = carCapacity;
	      this.bikeCapacity = bikeCapacity;
	   }  
	   public signUpContext() {}
	   @JsonSetter
	   public String getParkingLotName() { 
	      return parkingLotName; 
	   } 
	   @JsonSetter
	   public void setParkingLotName(String parkingLotName) { 
	      this.parkingLotName = parkingLotName; 
	   } 
	   @JsonSetter
	   public double getLattitude() { 
	      return lattitude; 
	   } 
	   @JsonSetter
	   public void setLattitude(double lattitude) { 
	      this.lattitude = lattitude; 
	   } 
	   @JsonSetter
	   public double getLongitude() { 
	      return longitude; 
	   } 
	   @JsonSetter
	   public void setLongitude(double longitude) { 
	      this.longitude = longitude; 
	   }
	   @JsonSetter
	   public long getCarCapacity() { 
	      return carCapacity; 
	   } 
	   @JsonSetter
	   public void setCarCapacity(long carCapacity) { 
	      this.carCapacity = carCapacity; 
	   }
	   @JsonSetter
	   public long getBikeCapacity() { 
	      return bikeCapacity; 
	   } 
	   @JsonSetter
	   public void setBikeCapacity(long bikeCapacity) { 
	      this.bikeCapacity = bikeCapacity; 
	   }
	   
}

