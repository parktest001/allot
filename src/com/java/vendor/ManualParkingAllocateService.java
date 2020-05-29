package com.java.vendor;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.java.context.ManualParkingContext;
import com.java.context.VendorLoginContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

@Path("/ManualParkingAllocateService")
public class ManualParkingAllocateService {
	@POST
	@Path("/AllocateSlot")
	@Produces(MediaType.TEXT_PLAIN) 
	@Consumes({MediaType.APPLICATION_JSON})
	public String allocateSlot(ManualParkingContext context){
		BasicDBObject query = new BasicDBObject();
		query.put("parkingLotName", new BasicDBObject("$eq", context.getParkingLotName()));
		query.put("vehicleNumber", new BasicDBObject("$eq", context.getVehicleNumber()));
		query.put("state", new BasicDBObject("$eq", true));
		FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("ManualParking", "Parking", query);
		if(doc.first() == null) {
			System.out.println("HELOOOOOOOOO");
			long currentTime = System.currentTimeMillis();
			Document document = new Document("slot", context.getSlot())
   	    			.append("vehicleNumber", context.getVehicleNumber())
   	    			.append("requestedTime", currentTime)
   	    			.append("state", true)
   	    			.append("parkingLotName", context.getParkingLotName())
   	    			.append("vehicleType", context.getVehicleType())
   	    			.append("uniqueKey", context.getVehicleNumber() + context.getParkingLotName() + currentTime);
			MongoCommands.insertData("ManualParking", "Parking", document);
			
			
			
			BasicDBObject queryCount = new BasicDBObject();
   			queryCount.put("parkingName", new BasicDBObject("$eq", context.getParkingLotName()));
	   		FindIterable<Document> docCount = MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", queryCount);
	   		Bson filterNew,documentNew;
	   		filterNew = eq("parkingName",context.getParkingLotName());
			if(context.getVehicleType() == 1)
			{
   				documentNew = set("liveCarCount",(docCount.first().get("liveCarCount") instanceof Integer ? docCount.first().getInteger("liveCarCount"): docCount.first().getLong("liveCarCount")) - 1);
			}
			else
			{
				documentNew = set("liveBikeCount",(docCount.first().get("liveBikeCount") instanceof Integer ? docCount.first().getInteger("liveBikeCount"): docCount.first().getLong("liveBikeCount")) - 1);
			}
			MongoCommands.updateData("ParkingLotDetails", "Parking", documentNew, filterNew);
			return "SUCCESS";
		}
		return "FAILED";
	}
	
}
