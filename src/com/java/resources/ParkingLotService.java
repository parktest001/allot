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
import com.java.context.ConfirmationContext;
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
		   ParkingLocationDetailsContext parkingLocationDetailsContext=new ParkingLocationDetailsContext(parkingIdGenerated,context.getParkingLotName(),context.getAddress(),3,context.getBikePrice(),context.getCarPrice(),context.getCarCapacity(),context.getBikeCapacity(),context.getLattitude(),context.getLongitude(),context.getCarCapacity(),context.getBikeCapacity());
		   setParkingLotDetail(parkingLocationDetailsContext);		   
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
	   
	   @POST
	   @Path("/updateSlot")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Consumes({MediaType.APPLICATION_JSON})
	   public String updateSlot(ConfirmationContext context){
		   Bson filter,document;
		   filter = eq("uniqueKey",context.getUniqueKey());
		   document = set("slot",context.getSlot());
		   MongoCommands.updateData("Confirmation", "Parking", document, filter);
		return "SUCCESS";
	   } 

}
