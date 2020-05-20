package com.java.resources;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.conversions.Bson;

import com.java.context.ConfirmationContext;
import com.java.database.MongoCommands;


@Path("/payment") 
public class PaymentService {
	   @POST
	   @Path("/setStatus")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static String setPaymentStatus(ConfirmationContext context){
		   
		   	Bson filter,document;
			filter = eq("uniqueKey",context.getUniqueKey());
			document = set("isPaid",true);
			MongoCommands.updateData("Confirmation", "Parking", document, filter);
		   
		   
		   return "SUCCESS";
	   }
			   
}