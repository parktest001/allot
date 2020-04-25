package com.java.context;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ViewPortContext implements Serializable{
	private static final long serialVersionUID = 1L;
	private double lattitude;
	private double longitude;
	private int vehicle;


	public ViewPortContext(double lattitude, double longitude) {
		this.lattitude = lattitude;
		this.longitude = longitude;
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
}
