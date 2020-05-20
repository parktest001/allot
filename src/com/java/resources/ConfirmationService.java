package com.java.resources;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.util.Date;
import java.util.Timer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.java.context.ConfirmationContext;
import com.java.context.MobileContext;
import com.java.database.MongoCommands;
import com.java.scheduler.CustomTask;
import com.java.scheduler.ExpiryCheck;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

@Path("/ParkingServiceConfirmation") 
public class ConfirmationService {
	
   
	 	@POST
	   @Path("/setConfirmationParkLater")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static String verifySlotAvailabilityParkLater(ConfirmationContext context)
	   {
		  long currentTime = System.currentTimeMillis();
	   	   	String uniqueKey  = String.valueOf(context.getUserMobileNumber()) + String.valueOf(currentTime)+context.getParkingLotName();
	   		   Document document = new Document("sessionKey",String.valueOf(context.getUserMobileNumber()) + String.valueOf(currentTime) )
	   	    			.append("parkingLotName", context.getParkingLotName())
	   	    			.append("requestedTime", context.getRequestedTime() )
	   	    			.append("tTime", context.gettTime())
	   	    			.append("uniqueKey", uniqueKey)
	   	    			.append("userMobileNumber", context.getUserMobileNumber())
	   	    			.append("state", context.getState())
	   		   			.append("isConfirmed", context.getIsConfirmed())
	   	    			.append("vehicleType", context.getVehicleType())
	   	    			.append("isParked", false)
	   	    			.append("isPaid", false)
	   	    			.append("isCancelled", false)
	   	    			.append("isFinished", false);
	   		   MongoCommands.insertData("Confirmation", "Parking", document);
	   		   schedule(context,uniqueKey);
	   		   return "SUCCESS";
	   }
	 	@POST
	   @Path("/setConfirmationParkNow")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static String verifySlotAvailabilityParkNow(ConfirmationContext context)
	   {
		  long currentTime = System.currentTimeMillis();
	   	   	String uniqueKey  = String.valueOf(context.getUserMobileNumber()) + String.valueOf(currentTime)+context.getParkingLotName();
	   		
	   			BasicDBObject queryCount = new BasicDBObject();
	   			queryCount.put("parkingName", new BasicDBObject("$eq", context.getParkingLotName()));
		   		FindIterable<Document> docCount = MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", queryCount);
		   		//System.out.println(docCount.first().getInteger("liveCarCount"));
	   			if(context.getVehicleType() == 1 && (docCount.first().get("liveCarCount") instanceof Integer ? docCount.first().getInteger("liveCarCount"): docCount.first().getLong("liveCarCount")) <= 0)
	   			{
	   				System.out.println("No car slot available");
	   				return "FAILED";
	   			}
	   			
	   			else if(context.getVehicleType() == 2 && (docCount.first().get("liveBikeCount") instanceof Integer ? docCount.first().getInteger("liveBikeCount"): docCount.first().getLong("liveBikeCount")) <= 0)
	   			{
	   				System.out.println("No Bike slot available");
	   				return "FAILED";
	   			}
	   			else 
	   			{
	   				System.out.print(context.getState());
	   				Bson filter,document;
					filter = eq("parkingName",context.getParkingLotName());
	   				if(context.getVehicleType() == 1)
	   				{
		   				System.out.println("Car booking confirmed");
	   					document = set("liveCarCount",(docCount.first().get("liveCarCount") instanceof Integer ? docCount.first().getInteger("liveCarCount"): docCount.first().getLong("liveCarCount")) - 1);

	   				}
	   				else
	   				{
	   					document = set("liveBikeCount",(docCount.first().get("liveBikeCount") instanceof Integer ? docCount.first().getInteger("liveBikeCount"): docCount.first().getLong("liveBikeCount")) - 1);
		   				System.out.println("Bike booking confirmed");
	   				}
					MongoCommands.updateData("ParkingLotDetails", "Parking", document, filter);
			   		   Document documentNew = new Document("sessionKey",String.valueOf(context.getUserMobileNumber()) + String.valueOf(currentTime) )
			   	    			.append("parkingLotName", context.getParkingLotName())
			   	    			.append("requestedTime", context.getRequestedTime() + 900000)
			   	    			.append("tTime", context.gettTime())
			   	    			.append("uniqueKey", uniqueKey)
			   	    			.append("userMobileNumber", context.getUserMobileNumber())
			   	    			.append("state", context.getState())
			   		   			.append("isConfirmed", context.getIsConfirmed())
			   	    			.append("vehicleType", context.getVehicleType())
			   	    			.append("isParked", false)
			   	    			.append("isCancelled", false)
			   	    			.append("isPaid", false)
			   	    			.append("isFinished", false);
			   		   MongoCommands.insertData("Confirmation", "Parking", documentNew);
			   		Timer time = new Timer();
					Date dateExpiry=new Date();
					dateExpiry.setTime(context.getRequestedTime()+ 900000);
					ExpiryCheck ec = new ExpiryCheck(uniqueKey);
					time.schedule(ec, dateExpiry);
	   			}
	   			
	   			
				

 	   	//System.out.println("TIMER EXECUTED");
 	   	return "SUCCESS";
	   }
	   
	   @POST
	   @Path("/getConfirmation")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static FindIterable<Document> getConfirmationDetail(MobileContext context)
	   {
	   				
			BasicDBObject criteria=new BasicDBObject("UserMobileNumber", new BasicDBObject("$eq",context.getUserMobileNumber() ));
			
			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", criteria);
		   return docs; 
	   }
	   
	   
	   
	   
	   public static void schedule(ConfirmationContext context,String uniqueKey){
			Timer time = new Timer();
			CustomTask ct=new CustomTask(context,uniqueKey);
			Date dateConfirm=new Date();
			dateConfirm.setTime(context.getRequestedTime() - 900000);
			time.schedule(ct, dateConfirm);

		
		}
	   @POST
	   @Path("/setVerifyAlreadyBooked")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static String verifyBooked(MobileContext context)
	   {
	   		BasicDBObject inQuery = new BasicDBObject();
	   		inQuery.put("userMobileNumber", new BasicDBObject("$eq", context.getUserMobileNumber()));
	   		inQuery.put("state", new BasicDBObject("$eq", true));
	   		inQuery.put("isCancelled", new BasicDBObject("$eq", false));
	   		inQuery.put("isFinished", new BasicDBObject("$eq", false));	
	   		inQuery.put("isConfirmed", new BasicDBObject("$eq", true));	
	   		
	   		
			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", inQuery);
			if(docs.first() == null)
			{
				BasicDBObject inQueryNew = new BasicDBObject();
				inQueryNew.put("state", new BasicDBObject("$eq", false));
				inQueryNew.put("isCancelled", new BasicDBObject("$eq", false));
				inQueryNew.put("isFinished", new BasicDBObject("$eq", false));
				inQueryNew.put("isConfirmed", new BasicDBObject("$eq", false));
				FindIterable<Document> docsNew= MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", inQueryNew);
				if(docsNew.first() == null)
				{
					return "SUCCESS";
				}
				return "FAILED";
			}
			return "FAILED";
	   }
}