package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class VehicleParkingContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	private int vehicle;
	public VehicleParkingContext(int vehicle) {
		this.vehicle = vehicle;
	}
	public VehicleParkingContext() {}
	
	public int getVehicle() {
		return vehicle;
	}
	@JsonSetter
	public void setVehicle(int vehicle) {
		this.vehicle = vehicle;
	}
}
