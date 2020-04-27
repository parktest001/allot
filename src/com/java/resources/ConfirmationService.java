package com.java.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.java.context.IdContext;
import com.java.context.MessageInput;
import com.java.context.ParkingLocationDetailsContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

@Path("/ParkingService") 
public class ConfirmationService {
	
   
	   
	   @POST
	   @Path("/setConfirmation")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static String setConfirmationDetail(ConfirmationContext context)
	   {
	   	long currentTime = System.currentTimeMillis()/1000;

		   Document document = new Document("sessionKey",context.getUserMobileNumber() + currentTime)
	    			.append("location", context.getLocation())
	    			.append("requestedTime", context.getRequestedTime())
	    			.append("expectedTime", context.getExpectedTime())
	    			.append("uniqueKey", context.getSessionKey()+context.getLocation())
	    			.append("userMobileNumber", context.getUserMobileNumber())
	    			.append("state", context.getState());

		   MongoCommands.insertData("Confirmation", "Parking", document);
		   return "SUCCESS"; 
	   }

	   @POST
	   @Path("/getConfirmation")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static FindIterable<Document> getConfirmationDetail(MobileContext context)
	   {
	   	

		   Document document = new Document("sessionKey",context.getUserMobileNumber());
			
			BasicDBObject criteria=BasicDBObject("UserMobileNumber", new BasicDBObject("$eq",context.getUserMobileNumber() ));
			
			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", criteria);
		   //MongoCommands.insertData("Confirmation", "Parking", document);
		   return docs; 
	   }
}