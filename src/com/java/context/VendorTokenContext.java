package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class VendorTokenContext implements Serializable{
	private static final long serialVersionUID = 1L;
	private String parkingLotName;
	private String token;
	public VendorTokenContext(String parkingLotName, String token) {
		this.parkingLotName = parkingLotName;
		this.token = token;
	}
	public VendorTokenContext() {}
	public String getParkingLotName() {
		return parkingLotName;
	}
	@JsonSetter
	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}
	public String getToken() {
		return token;
	}
	@JsonSetter
	public void setToken(String token) {
		this.token = token;
	}
}
