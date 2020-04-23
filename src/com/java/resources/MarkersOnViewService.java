package com.java.resources;

import java.awt.List;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.java.context.ViewPortContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;

public class MarkersOnViewService {
	
	@POST
	   @Path("/getMarkers")
	   @Produces(MediaType.APPLICATION_JSON) 
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static FindIterable<Document> getMarkersOnView(ViewPortContext context)
	   {
			ArrayList<DBObject> criteria = new ArrayList<DBObject>();
			criteria.add(new BasicDBObject("lattitude", new BasicDBObject("$gt", context.getLeft_Down_Lattitude())));
			criteria.add(new BasicDBObject("longitude", new BasicDBObject("$gt", context.getLeft_Down_Longitude())));
			criteria.add(new BasicDBObject("lattitude", new BasicDBObject("$gt", context.getRight_Top_Lattitude())));
			criteria.add(new BasicDBObject("longitude", new BasicDBObject("$gt", context.getRight_Top_Longitude())));
			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", new BasicDBObject("$and", criteria));
			
			return docs; 
	   }
	
	
}
