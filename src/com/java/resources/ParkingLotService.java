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
public class ParkingLotService {
	 Message userDao = new Message();  
	   @POST
	   @Path("/getSpace")
	   @Produces(MediaType.APPLICATION_JSON) 
	   @Consumes({MediaType.APPLICATION_JSON})
	   public FindIterable<Document> getMessages(List<IdContext> context){ 
	      return getParkingLotDetail(context); 
	   }   
	   
	   
	   @POST
	   @Path("/setSpace")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static String setParkingLotDetail(ParkingLocationDetailsContext context)
	   {
		   Document document = new Document("parkingLotId",context.getParkingLotId())
	    			.append("parkingName", context.getParkingName())
	    			.append("address", context.getAddress())
	    			.append("rating", context.getRating())
	    			.append("price", context.getPrice());

		   MongoCommands.insertData("ParkingLotDetails", "Parking", document);
		   return "SUCCESS"; 
	   }



	   @POST
	   @Path("/getSpaceSignup")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes({MediaType.APPLICATION_JSON})
	   public FindIterable<Document> getParkingLotDetailsSignUp(){ 
	      return getParkingLotDetailSignUp(); 
	   } 
	   
	   @POST
	   @Path("/setSpaceSignup") 
	   @Produces(MediaType.TEXT_PLAIN) 
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static String setParkingLotDetailSignUp(signUpContext context)
	   {
		   Document document = new Document("parkingLotName", context.getParkingLotName())
				   .append("lattitude", context.getLattitude())
				   .append("longitude", context.getLongitude())
				   .append("carCapacity", context.getCarCapacity())
				   .append("bikeCapacity", context.getBikeCapacity())
				   .append("features", context.getFeatures());

		   MongoCommands.insertData("ParkingLotDetailSignUp", "Parking", document);
		   LiveContext livecontext=new LiveContext(context.getParkingName(),context.getCarCapacity(),context.getBikeCapacity())
		   setLiveVehicleCount(livecontext);
		   return "SUCCESS"; 
	   }
	   
	   public static FindIterable<Document> getParkingLotDetail(List<IdContext> context)
	   {
			List<Integer> list = new ArrayList<Integer>(); 
		   	for(IdContext idobj:context)
		   	{
		   		list.add((int) idobj.getId());
		   	}
			BasicDBObject inQuery = new BasicDBObject();	
			inQuery.put("parkingLotId", new BasicDBObject("$in", list));
			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", inQuery);
		    return docs; 
	   }
	   public static FindIterable<Document> getParkingLotDetailSignUp()
	   {
		FindIterable<Document> docs= MongoCommands.retrieveAllData("ParkingLotDetails", "Parking");
		return docs;
		   
	   }
	    public static void setLiveVehicleCount(LiveContext context)
	   {
		   Document document = new Document("parkingLotName", context.getParkingLotName())
				   .append("liveCarCount", context.getLiveCarCount())
				   .append("liveBikeCount", context.getLiveBikeCount());

		   MongoCommands.insertData("VehicleCount", "Parking", document);
	   }

}
