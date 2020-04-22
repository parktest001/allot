package com.java.context;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSetter;

public class IdContext implements Serializable{
	private static final long serialVersionUID = 1L; 
	   private long id; 

	    
	   public IdContext(long id){  
	      this.id = id; 
	   }  
	   public IdContext() {}
	
	   public long getId() { 
	      return id; 
	   } 
	   @JsonSetter
	   public void setId(long id) { 
	      this.id = id; 
	   } 
}
