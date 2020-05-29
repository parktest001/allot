package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class HistoryContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	   
	   private long userMobileNumber;
	   private boolean isActive;
	   int pageNumber;
	   

	   public HistoryContext(long userMobileNumber,boolean isActive,int pageNumber){  
	    
	      this.userMobileNumber = userMobileNumber;
	      this.isActive = isActive;
	      this.pageNumber = pageNumber;

	   }  
	   public boolean getIsActive() {
		return isActive;
	}
	   @JsonSetter
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public HistoryContext() {}
	   public long getUserMobileNumber() { 
	      return userMobileNumber; 
	   } 
	   @JsonSetter
	   public void setUserMobileNumber(long userMobileNumber) { 
	      this.userMobileNumber = userMobileNumber;
	   }
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
}
