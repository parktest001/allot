package com.java.resources;

import java.util.ArrayList;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.java.context.VehicleParkingContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;

@Path("/VehicleParkingService")
public class VehicleParkingService {
	@POST
	@Path("/getMarkers")
	@Produces(MediaType.APPLICATION_JSON) 
	@Consumes({MediaType.APPLICATION_JSON})
	public static FindIterable<Document> getVehicleBasedParking(VehicleParkingContext context){
		BasicDBObject inQuery = new BasicDBObject();
		if(context.getVehicle() == 0) {
			inQuery.put("bikeCapacity", new BasicDBObject("$gt", 0));
		}
		else{
			inQuery.put("carCapacity", new BasicDBObject("$gt", 0));
		}
		FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("ParkingLotDetailSignUp", "Parking", inQuery);
		return docs;
	}
}
