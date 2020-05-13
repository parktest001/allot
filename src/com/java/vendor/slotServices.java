package com.java.vendor;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.java.context.ParkingLocationDetailsContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

@Path("/getSlotsVendor") 
public class slotServices {
		@POST
	   @Path("/totalSlots")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes({MediaType.APPLICATION_JSON})
		public static Document getTotalCountSlots(ParkingLocationDetailsContext context){
		BasicDBObject inQuery = new BasicDBObject();	
		inQuery.put("parkingLotName", new BasicDBObject("$eq", context.getParkingName()));
		FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("ParkingLotDetailSignUp", "Parking", inQuery);	
		return doc.first();
		
	}
		@POST
		   @Path("/getAllocatedFreeSlots")
		   @Produces(MediaType.APPLICATION_JSON)
		   @Consumes({MediaType.APPLICATION_JSON})
		   public static FindIterable<Document> getAllocatedFreeSlotInParking(ParkingLocationDetailsContext context)
		   {
		   		BasicDBObject queryCount = new BasicDBObject();
			   	queryCount.put("parkingLotName", new BasicDBObject("$eq", context.getParkingName()));
			   	queryCount.put("state", new BasicDBObject("$eq", true));
			   	queryCount.put("isParked", new BasicDBObject("$eq", true));
				FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", queryCount);
				return doc;
		   }
}
