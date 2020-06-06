package com.java.context;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ImageContext implements Serializable{
	private static final long serialVersionUID = 1L;
	private String parkingName;
	private List<String> url;
	public ImageContext() {}
	public ImageContext(String parkingName,List<String> url) {
		this.parkingName = parkingName;
		this.url = url;
	}
	public String getParkingName() {
		return parkingName;
	}
	@JsonSetter
	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}
	public List<String> getUrl() {
		return url;
	}
	@JsonSetter
	public void setUrl(List<String> url) {
		this.url = url;
	}

}
