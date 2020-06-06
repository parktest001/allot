package com.java.resources;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.java.context.ParkingLocationDetailsContext;
import com.java.context.signUpContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

@Path("/ParkingService") 
public class ParkingLotService {
	
	   
	   @POST
	   @Path("/setSpace")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static String setParkingLotDetail(ParkingLocationDetailsContext context)
	   {
		   String parkingNameUnique = Base64.getEncoder() 
		              .encodeToString((context.getParkingName()+context.getLattitude()+context.getLongitude()).getBytes());
		   Document document = new Document("parkingLotId",context.getParkingLotId())
	    			.append("parkingName", parkingNameUnique)
	    			.append("displayName", context.getDisplayName())
	    			.append("address", context.getAddress())
	    			.append("rating", context.getRating())
	    			.append("bikePrice", context.getBikePrice())
	    			.append("carPrice", context.getCarPrice())
	    			.append("liveCarCount", context.getLiveCarCount())
	    			.append("liveBikeCount", context.getLiveBikeCount())
	    			.append("carCapacity", context.getCarCapacity())
	    			.append("bikeCapacity", context.getBikeCapacity())
	    			.append("lattitude", context.getLattitude())
					.append("longitude", context.getLongitude());
		   
		   MongoCommands.insertData("ParkingLotDetails", "Parking", document);
		   Document documentToken = new Document("parkingLotName", context.getParkingName())
				   .append("token", "");
		
		   MongoCommands.insertData("Token", "VendorDetails", documentToken);
		   return "SUCCESS"; 
	   }



	   
	   @POST
	   @Path("/setSpaceSignup") 
	   @Produces(MediaType.TEXT_PLAIN) 
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static String setParkingLotDetailSignUp(signUpContext context)
	   {
		   String parkingNameUnique = Base64.getEncoder() 
		              .encodeToString((context.getDisplayName()+context.getLattitude()+context.getLongitude()).getBytes());
		   Document document = new Document("parkingLotName", parkingNameUnique)
				   .append("displayName", context.getDisplayName())
				   .append("address", context.getAddress())
				   .append("lattitude", context.getLattitude())
				   .append("longitude", context.getLongitude())
				   .append("carCapacity", context.getCarCapacity())
				   .append("bikeCapacity", context.getBikeCapacity())
				   .append("features", context.getFeatures())
				   .append("bikePrice", context.getBikePrice())
				   .append("carPrice", context.getCarPrice())
				   .append("userName", context.getUserName())
		   		   .append("passWord", context.getPassWord());

		   MongoCommands.insertData("ParkingLotDetailSignUp", "Parking", document);
		   String parkingIdGenerated = Base64.getEncoder() 
	              .encodeToString(context.getParkingLotName().getBytes()); 
		   //(long parkingLotId, String parkingName,String address,float rating,long price,long liveCarCount,long liveBikeCount)
		   ParkingLocationDetailsContext parkingLocationDetailsContext=new ParkingLocationDetailsContext(parkingIdGenerated,parkingNameUnique,context.getParkingLotName(),context.getAddress(),3,context.getBikePrice(),context.getCarPrice(),context.getCarCapacity(),context.getBikeCapacity(),context.getLattitude(),context.getLongitude(),context.getCarCapacity(),context.getBikeCapacity());
		   setParkingLotDetail(parkingLocationDetailsContext);		   
		   return "SUCCESS"; 
	   }
	   
	

}
