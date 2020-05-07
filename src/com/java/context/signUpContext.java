package com.java.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

public class signUpContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	   private String parkingLotName;
	   private String address;
	   private double lattitude;
	   private double longitude;
	   private int carCapacity;
	   private int bikeCapacity;
	   private List<String> features;
	   private long price;
	    
	   public signUpContext(String parkingLotName, String address,double lattitude, double longitude, int carCapacity, int bikeCapacity, List<String> features,long price){  
	      this.parkingLotName = parkingLotName;
	      this.address = address;
	      this.lattitude = lattitude;
	      this.longitude = longitude;
	      this.carCapacity = carCapacity;
	      this.bikeCapacity = bikeCapacity;
	      this.features = features;
	      this.price = price;
	   }  
	   public signUpContext() {}
	   public String getParkingLotName() { 
	      return parkingLotName; 
	   } 
	   @JsonSetter
	   public void setAddress(String address) { 
	      this.address = address; 
	   } 
	   public String getAddress() { 
		      return address; 
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
	   public int getCarCapacity() { 
	      return carCapacity; 
	   } 
	   @JsonSetter
	   public void setCarCapacity(int carCapacity) { 
	      this.carCapacity = carCapacity; 
	   }
	   public int getBikeCapacity() { 
	      return bikeCapacity; 
	   } 
	   @JsonSetter
	   public void setBikeCapacity(int bikeCapacity) { 
	      this.bikeCapacity = bikeCapacity; 
	   }

	   public List<String> getFeatures() {
	   		return features;
	   }
	   @JsonSetter
	   public void setFeatures(List<String> features) {
	   		this.features = features;
	   }
	   @JsonSetter 
		public void setRating(long price) { 
			   this.price = price; 
		}  
	   public long getPrice() { 
		      return price; 
		   } 
	   
}		

