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
@Path("/ParkingService") 
public class FeaturesService {
	Message userDao = new Message(); 

	@POST
	   @Path("/setFeatures") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static String setFeaturesSignUp(signUpContext context)
	   {
		   Document document = new Document("features", context.getFeatures());

		   MongoCommands.insertData("ParkingLotDetailSignUp", "Parking", document);
		   return "SUCCESS"; 
	   }
	   
	   public static ArrayList<String> getFeaturesSignUp(ArrayList<String> context)
	   {
			List<Integer> features = new ArrayList<Integer>(); 
		   	for(IdContext idobj:context)
		   	{
		   		features.add((int) idobj.getFeatures());
		   	}
			BasicDBObject inQuery = new BasicDBObject();	
			inQuery.put("features", new BasicDBObject("$in", features));
			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", inQuery);
		    return docs; 
	   }
	   public static ArrayList<String> getParkingLotDetailSignUp()
	   {
		ArrayList<String> docs= MongoCommands.retrieveDataWithCondition("ParkingLotDetails","Parking",inQuery)
		return docs;
		   
	   }
}