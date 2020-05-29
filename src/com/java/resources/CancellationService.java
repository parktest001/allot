package com.java.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.java.context.ConfirmationContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

@Path("/ParkingCancellation") 

public class CancellationService {
		
		   @POST
		   @Path("/cancelService")
		   @Produces(MediaType.TEXT_PLAIN)
		   @Consumes({MediaType.APPLICATION_JSON})
		   public static String setCancellationService(ConfirmationContext context){
		   	Bson filter, document;
		   	filter = eq("uniqueKey", context.getUniqueKey());
		   	document =  set("state", false);
		    MongoCommands.updateData("Confirmation","Parking",document, filter);
		    document =  set("isParked", false);
		    MongoCommands.updateData("Confirmation","Parking",document, filter);
		   	document = set("isCancelled",true);
		   	MongoCommands.updateData("Confirmation","Parking",document, filter);
		    document =  set("isFinished", false);
		    MongoCommands.updateData("Confirmation","Parking",document, filter);
		    setCancelConfirmedSlot(context.getUniqueKey());	
		    return "SUCCESS";
		   }
		   
		   public static void setCancelConfirmedSlot(String uniqueKey)
		   {
		   		BasicDBObject queryCount = new BasicDBObject();
		   		queryCount.put("uniqueKey", new BasicDBObject("$eq", uniqueKey));
			   	FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", queryCount);
			   	Bson filter,document;
			   	BasicDBObject queryCountNew = new BasicDBObject();
			   	queryCountNew.put("parkingName", new BasicDBObject("$eq", doc.first().getString("parkingLotName")));
			   	FindIterable<Document> docCount = MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", queryCountNew);

				filter = eq("parkingName",doc.first().getString("parkingLotName"));
		   		if(doc.first().getInteger("vehicleType") == 1)
		   		{
		   			document = set("liveCarCount",(docCount.first().get("liveCarCount") instanceof Integer ? docCount.first().getInteger("liveCarCount"): docCount.first().getLong("liveCarCount")) + 1);
				}
	  			else
				{
		   			document = set("liveBikeCount",(docCount.first().get("liveBikeCount") instanceof Integer ? docCount.first().getInteger("liveBikeCount"): docCount.first().getLong("liveBikeCount")) + 1);
		   		}
				MongoCommands.updateData("ParkingLotDetails", "Parking", document, filter);
		   }
		   
		   @POST
		   @Path("/cancelPrebookedService")
		   @Produces(MediaType.TEXT_PLAIN)
		   @Consumes({MediaType.APPLICATION_JSON})
		   public static String setPrebookedCancellationService(ConfirmationContext context){
		   	Bson filter, document;
		   	filter = eq("uniqueKey", context.getUniqueKey());
		   	
		   	document = set("isCancelled",true);
		   	MongoCommands.updateData("Confirmation","Parking",document, filter);
		    return "SUCCESS";
		   }
}
