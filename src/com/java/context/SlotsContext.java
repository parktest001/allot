package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class SlotsContext implements Serializable{
		private static final long serialVersionUID = 1L; 
		   private String parkingName;		    
		   private int vehicleType;

		   public SlotsContext(String parkingName,int vehicleType){  
		      this.parkingName = parkingName; 
		      this.vehicleType = vehicleType;
		   }  
		   public SlotsContext() {}
		   
		   public String getParkingName() { 
		      return parkingName; 
		   } 
		   @JsonSetter 
		   public void setParkingName(String parkingName) { 
		      this.parkingName = parkingName; 
		   }  
		   public int getVehicleType() {
				return vehicleType;
			}
			public void setVehicleType(int vehicleType) {
				this.vehicleType = vehicleType;
			}
}
