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
import com.java.context.signUpContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
@Path("/ParkingFeatures") 
public class FeaturesService {
	//Message userDao = new Message(); 

	@POST
	   @Path("/getFeatures") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static List<String> getFeaturesSignUp(signUpContext context)
	   {
			BasicDBObject inQuery = new BasicDBObject();	
			inQuery.put("parkingLotName", new BasicDBObject("$eq", context.getParkingLotName()));
			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("ParkingLotDetailSignUp", "Parking", inQuery);
		    return (List<String>) docs.first().get("features"); 
	   }

}