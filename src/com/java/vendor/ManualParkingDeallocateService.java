package com.java.vendor;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.java.context.ManualParkingContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

@Path("/deallocateManual")
public class ManualParkingDeallocateService {
	@POST
	@Path("/dealloc")
	@Produces(MediaType.APPLICATION_JSON) 
	@Consumes({MediaType.APPLICATION_JSON})
	   public static Document deallocateParking(ManualParkingContext context)
	   {
		
		Bson filter,document;
		Document d;
		int vehicleType;
		long reqTime;
		String uniqueKey = "";
		long finishTime = System.currentTimeMillis();
		BasicDBObject query = new BasicDBObject();
	   	query.put("parkingLotName", new BasicDBObject("$eq",context.getParkingLotName()));
	   	query.put("vehicleNumber", new BasicDBObject("$eq",context.getVehicleNumber()));
	   	query.put("state", new BasicDBObject("$eq",true));
	   	FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("ManualParking", "Parking", query);
	   	d = doc.first();
	   	uniqueKey = doc.first().getString("uniqueKey");
	 	filter = eq("uniqueKey",uniqueKey);
	 	vehicleType = d.getInteger("vehicleType");
	 	reqTime = d.getLong("requestedTime");
		document = set("state",false);
		MongoCommands.updateData("ManualParking", "Parking", document, filter);
		document = set("finishTime",finishTime);
		MongoCommands.updateData("ManualParking", "Parking", document, filter);
		
		
		
		
		
		BasicDBObject queryPrice = new BasicDBObject();
		queryPrice.put("parkingName", new BasicDBObject("$eq",context.getParkingLotName()));
		FindIterable<Document> docPrice = MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", queryPrice);
		long totalPrice;
		if(context.getVehicleType() == 1) {
			totalPrice = (long) ((docPrice.first().get("carPrice") instanceof Integer ? docPrice.first().getInteger("carPrice"): docPrice.first().getLong("carPrice")) * Math.ceil((double)((long)(finishTime - reqTime) / 3600000.0f)));

		}
		else  {
			totalPrice = (long) ((docPrice.first().get("bikePrice") instanceof Integer ? docPrice.first().getInteger("bikePrice"): docPrice.first().getLong("bikePrice")) * Math.ceil((double)((long)(finishTime - reqTime) / 3600000.0f)));

		}
		document = set("price",totalPrice);
		MongoCommands.updateData("ManualParking", "Parking", document, filter);
		
		
		
		
		
		BasicDBObject queryCount = new BasicDBObject();
		queryCount.put("parkingName", new BasicDBObject("$eq", context.getParkingLotName()));
   		FindIterable<Document> docCount = MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", queryCount);
   		Bson filterNew,documentNew;
   		filterNew = eq("parkingName",context.getParkingLotName());
		if(context.getVehicleType() == 1)
		{
			documentNew = set("liveCarCount",(docCount.first().get("liveCarCount") instanceof Integer ? docCount.first().getInteger("liveCarCount"): docCount.first().getLong("liveCarCount")) + 1);
		}
		else
		{
			documentNew = set("liveBikeCount",(docCount.first().get("liveBikeCount") instanceof Integer ? docCount.first().getInteger("liveBikeCount"): docCount.first().getLong("liveBikeCount")) + 1);
		}
		MongoCommands.updateData("ParkingLotDetails", "Parking", documentNew, filterNew);
		 Document documentReturn = new Document("status", "SUCCESS")
				   .append("uniqueKey", uniqueKey);
		return documentReturn;
	   }
	@POST
	@Path("/searchRes")
	@Produces(MediaType.APPLICATION_JSON) 
	@Consumes({MediaType.APPLICATION_JSON})
	   public static FindIterable<Document> listParking(ManualParkingContext context)
	   {
		BasicDBObject query = new BasicDBObject();
	   	query.put("parkingLotName", new BasicDBObject("$eq",context.getParkingLotName()));
	   	query.put("vehicleNumber", new BasicDBObject("$regex",".*"+context.getVehicleNumber()+".*"));
	   	query.put("state", new BasicDBObject("$eq",true));
	   	FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("ManualParking", "Parking", query).limit(10);
		return doc;
	   }
	@POST
	@Path("/getBill")
	@Produces(MediaType.APPLICATION_JSON) 
	@Consumes({MediaType.APPLICATION_JSON})
	   public static Document getBill(ManualParkingContext context)
	   {
		BasicDBObject query = new BasicDBObject();
	   	query.put("uniqueKey", new BasicDBObject("$eq",context.getUniqueKey()));
	   	FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("ManualParking", "Parking", query);
	   	System.out.println(doc.first());
		return doc.first();
	   }
}
