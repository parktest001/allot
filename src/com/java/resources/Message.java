package com.java.resources;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.util.ArrayList; 
import java.util.List;

import com.java.context.MessageInput;

public class Message { 
   public static List<MessageInput> getSingleChannelMessages(){ 
      
      List<MessageInput> messageList = null; 
      try { 
         if (true) { 
        	 MessageInput message = new MessageInput(1,2,3,""); 
        	 messageList = new ArrayList<MessageInput>(); 
        	 messageList.add(message); 
         } 
      }
      catch(Exception e) {};
      return messageList; 
   }     
}