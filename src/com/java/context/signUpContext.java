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
	   private List<String> features = new ArrayList<String>();
	    
	   public signUpContext(String parkingLotName, double lattitude, double longitude, long carCapacity, long bikeCapacity, ArrayList<String> features){  
	      this.parkingLotName = parkingLotName;
	      this.lattitude = lattitude;
	      this.longitude = longitude;
	      this.carCapacity = carCapacity;
	      this.bikeCapacity = bikeCapacity;
	      this.features = features;
	   }  
	   public signUpContext() {}
	   public String getParkingLotName() { 
	      return parkingLotName; 
	   } 
	   @JsonSetter
	   public void setParkingLotName(String parkingLotName) { 
	      this.parkingLotName = parkingLotName; 
	   } 
	   public double getLattitude() { 
	      return lattitude; 
	   } 
	   @JsonSetter
	   public void setLattitude(double lattitude) { 
	      this.lattitude = lattitude; 
	   } 
	   public double getLongitude() { 
	      return longitude; 
	   } 
	   @JsonSetter
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

	   public ArrayList<String> getFeatures() {
	   		return features;
	   }
	   @JsonSetter
	   public void setFeatures(ArrayList<String> features) {
	   		this.features = features;
	   }
	   
}		

