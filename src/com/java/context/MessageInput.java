package com.java.context;

import java.io.Serializable;  
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "user") 

public class MessageInput implements Serializable {  
   private static final long serialVersionUID = 1L; 
   private long userId; 
   private long channelId;
   private long messageTime;
   private String message;

    
   public MessageInput(long userId, long channelId,long messageTime,String message){  
      this.userId = userId; 
      this.channelId = channelId; 
      this.messageTime = messageTime;
      this.message = message;

   }  
   public MessageInput() {}
   public long getUserId() { 
      return userId; 
   } 
   @XmlElement
   public void setName(long userId) { 
      this.userId = userId; 
   } 
   public long getChannelId() { 
      return channelId; 
   } 
   @XmlElement 
   public void setChannelId(long channelId) { 
      this.channelId = channelId; 
   }  
   public long getMessageTime() { 
	      return messageTime; 
	   } 
   @XmlElement 
   public void setMessageTime(long messageTime) { 
	   this.messageTime = messageTime; 
   } 
   public String getMessage() { 
	      return message; 
	   } 
	@XmlElement 
	public void setMessage(String message) { 
		   this.message = message; 
	}   
} 