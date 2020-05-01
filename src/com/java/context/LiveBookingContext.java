package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class LiveBookingContext implements Serializable{
	private static final long serialVersionUID = 1L;
	   private boolean car;
	   private boolean bike;	   
	    
	   public LiveBookingContext(boolean car, boolean bike){  
	      this.car = car;
	      this.bike = bike;
	      
	   }  
	   public LiveBookingContext() {}
	   public boolean getCar() { 
	      return car; 
	   } 
	   @JsonSetter
	   public void setCar(boolean car) { 
	      this.car = car; 
	   } 

	   public boolean getBike() {
	   	return bike;
	   }
	   @JsonSetter
	   public void setBike(boolean bike) {
	   	this.bike = bike;
	   }
}