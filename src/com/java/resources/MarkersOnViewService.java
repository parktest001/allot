package com.java.resources;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.json.JSONObject;

import com.java.context.ViewPortContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;

@Path("/MarkersOnViewService")
public class MarkersOnViewService {
	
	@SuppressWarnings("deprecation")
	@POST
	   @Path("/getMarkers")
	   @Produces(MediaType.APPLICATION_JSON) 
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static FindIterable<Document> getMarkersOnView(ViewPortContext context)
	   {
			ArrayList<DBObject> criteria = new ArrayList<DBObject>();
	        Double latitude=context.getLattitude();
	        Double longitude=context.getLongitude();
			criteria.add(new BasicDBObject("lattitude", new BasicDBObject("$gt", latitude - 0.04)));
			criteria.add(new BasicDBObject("longitude", new BasicDBObject("$gt", longitude - 0.04)));
			criteria.add(new BasicDBObject("lattitude", new BasicDBObject("$lt", latitude + 0.04)));
			criteria.add(new BasicDBObject("longitude", new BasicDBObject("$lt", longitude + 0.04)));
			if(context.getParkingOption() == true) {
			if(context.getVehicle() == 1 )
			{
				criteria.add(new BasicDBObject("liveCarCount", new BasicDBObject("$gt", 0)));
			}
			else if(context.getVehicle() == 2)
			{
				criteria.add(new BasicDBObject("liveBikeCount", new BasicDBObject("$gt", 0)));
			}
			}
			else
			{
				if(context.getVehicle() == 1 )
				{
					criteria.add(new BasicDBObject("carCapacity", new BasicDBObject("$gt", 0)));
				}
				else if(context.getVehicle() == 2)
				{
					criteria.add(new BasicDBObject("bikeCapacity", new BasicDBObject("$gt", 0)));
				}
			}
			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", new BasicDBObject("$and", criteria));
			return docs;
	   }
}
