package com.java.context;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ViewPortContext implements Serializable{
	private static final long serialVersionUID = 1L;
	private double left_Down_Lattitude;
	private double left_Down_Longitude;
	private double right_Top_Lattitude;
	private double right_Top_Longitude;

	public ViewPortContext(double left_Down_Lattitude, double left_Down_Longitude, double right_Top_Lattitude, double right_Top_Longitude) {
		this.left_Down_Lattitude = left_Down_Lattitude;
		this.left_Down_Longitude = left_Down_Longitude;
		this.right_Top_Lattitude = right_Top_Lattitude;
		this.right_Top_Longitude = right_Top_Longitude;
	}
	
	public ViewPortContext() {}
	
	public double getLeft_Down_Lattitude() {
		return left_Down_Lattitude;
	}
	
	@JsonSetter
	public void setLeft_Down_Lattitude(double left_Down_Lattitude) {
		this.left_Down_Lattitude = left_Down_Lattitude;
	}
	
	public double getLeft_Down_Longitude() {
		return left_Down_Longitude;
	}
	
	@JsonSetter
	public void setLeft_Down_Longitude(double left_Down_Longitude) {
		this.left_Down_Longitude = left_Down_Longitude;
	}
	public double getRight_Top_Lattitude() {
		return right_Top_Lattitude;
	}
	
	@JsonSetter
	public void setRight_Top_Lattitude(double right_Top_Lattitude) {
		this.right_Top_Lattitude = right_Top_Lattitude;
	}
	public double getRight_Top_Longitude() {
		return right_Top_Longitude;
	}
	
	@JsonSetter
	public void setRight_Top_Longitude(double right_Top_Longitude) {
		this.right_Top_Longitude = right_Top_Longitude;
	}
	
	
}
