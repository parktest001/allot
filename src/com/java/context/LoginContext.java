package com.java.context;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonSetter;

public class LoginContext implements Serializable{

	private static final long serialVersionUID = 1L; 
	   private String mobile ;
	   private String passWord ; 
	    
	   public LoginContext(String mobile,String passWord){  
		   this.mobile=mobile;
		   this.passWord=passWord;
	   }  
	   public LoginContext() {}
	
	   public String getMobile() { 
	      return mobile; 
	   } 
	   @JsonSetter
	   public void setMobile(String mobile) { 
	      this.mobile = mobile; 
	   } 
	   public String getpassWord() { 
		      return passWord; 
		   } 
	   @JsonSetter
	   public void setpassWord(String passWord) { 
		      this.passWord = passWord; 
	   } 
}
