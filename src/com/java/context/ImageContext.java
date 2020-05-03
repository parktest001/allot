package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ImageContext implements Serializable{
	private static final long serialVersionUID = 1L;
	private String parkingName;
	private String imageBase64;
	public ImageContext() {}
	public ImageContext(String parkingName) {
		this.parkingName = parkingName;
	}
	public String getParkingName() {
		return parkingName;
	}
	@JsonSetter
	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}
	public String getImageBase64() {
		return imageBase64;
	}
	@JsonSetter
	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

}
