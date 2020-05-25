package com.java.context;

import java.io.Serializable;

public class ManualParkingContext implements Serializable{
	private static final long serialVersionUID = 1L;
	private String slot;
	private String vehicleNumber;
	private long requestedTime;
	private boolean state;
	private String parkingLotName;
	private long finishTime;
	private int vehicleType;
	
	
	
	
	public ManualParkingContext(String slot, String vehicleNumber, long requestedTime, boolean state,
			String parkingLotName, long finishTime, int vehicleType) {
		this.slot = slot;
		this.vehicleNumber = vehicleNumber;
		this.requestedTime = requestedTime;
		this.state = state;
		this.parkingLotName = parkingLotName;
		this.finishTime = finishTime;
		this.vehicleType = vehicleType;
	}




	ManualParkingContext()
	{}




	public String getSlot() {
		return slot;
	}




	public void setSlot(String slot) {
		this.slot = slot;
	}




	public String getVehicleNumber() {
		return vehicleNumber;
	}




	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}




	public long getRequestedTime() {
		return requestedTime;
	}




	public void setRequestedTime(long requestedTime) {
		this.requestedTime = requestedTime;
	}




	public boolean getState() {
		return state;
	}




	public void setState(boolean state) {
		this.state = state;
	}




	public String getParkingLotName() {
		return parkingLotName;
	}




	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}




	public long getFinishTime() {
		return finishTime;
	}




	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}




	public int getVehicleType() {
		return vehicleType;
	}




	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}
	
}
