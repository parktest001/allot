package com.java.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.java.context.IdContext;
import com.java.context.LiveBookingContext;
import com.java.context.MessageInput;
import com.java.context.ParkingLocationDetailsContext;
import com.java.context.signUpContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
@Path("/ParkingServiceNew") 
public class BookingService {

//	@POST
//	   @Path("/setConfirmVehicle") 
//	   @Produces(MediaType.TEXT_PLAIN) 
//	   @Consumes({MediaType.APPLICATION_JSON})
//	   public static String setBookingVehicle(LiveBookingContext context)
//	   {
//		   
//	   		
//		   MongoCommands.insertData("VehicleCount", "Parking", document);
//		   return "SUCCESS"; 
//	   }
}