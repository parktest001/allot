package com.java.context;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ViewPortContext implements Serializable{
	private static final long serialVersionUID = 1L;
	private double lattitude;
	private double longitude;
	private int vehicle;
	private boolean parkingOption;


	public ViewPortContext(double lattitude, double longitude,int vehicle,boolean parkingOption) {
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.vehicle = vehicle;
		this.parkingOption = parkingOption;
	}
	
	public ViewPortContext() {}
	
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
	public int getVehicle() {
		return vehicle;
	}
	@JsonSetter
	public void setVehicle(int vehicle) {
		this.vehicle = vehicle;
	}

	public boolean getParkingOption() {
		return parkingOption;
	}

	public void setParkingOption(boolean parkingOption) {
		this.parkingOption = parkingOption;
	}
	
}
