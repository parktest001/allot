package com.java.resources;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.java.context.ConfirmationContext;
import com.java.context.MobileContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.lang.Math; 

@Path("/QrService") 
public class QrCodeService {


	@POST
	   @Path("/getQr") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static Document getQrStringUser(MobileContext context)
	   {
			BasicDBObject inQuery = new BasicDBObject();	
			inQuery.put("userMobileNumber", new BasicDBObject("$eq", context.getUserMobileNumber()));
			inQuery.put("state", new BasicDBObject("$eq", true));
			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", inQuery);
		    return docs.first();
	   }

		@POST
	   @Path("/setConfirmation") 
	   @Produces(MediaType.TEXT_PLAIN) 
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static String setParkedStatus(ConfirmationContext context)
	   {
			  	BasicDBObject query = new BasicDBObject();
		   		query.put("uniqueKey", new BasicDBObject("$eq", context.getUniqueKey()));
		   		FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", query);
		   		if(doc.first() == null)
		   		{
		   			return "FAILED";
		   		}
		   		else if(doc.first().getBoolean("state") == true && doc.first().getBoolean("isParked") == false &&  doc.first().getBoolean("isFinished")== false)
		   		{
		   			Bson filter,document;
 					filter = eq("uniqueKey",context.getUniqueKey());
 					document = set("isParked",true);
 					MongoCommands.updateData("Confirmation", "Parking", document, filter);
 					System.out.println("HELLOOOOOOOOOOOOOOO Karthikaaaaa loosuuuiiu");
 					Bson filter1,document1;
 					filter1 = eq("uniqueKey",context.getUniqueKey());
 					document1 = set("requestedTime",System.currentTimeMillis());
 					MongoCommands.updateData("Confirmation", "Parking", document1, filter1);		   		
 					return "PARKED";
		   		}
		   		else if(doc.first().getBoolean("state") == true && doc.first().getBoolean("isParked") == true &&  doc.first().getBoolean("isFinished")== false)
		   		{
		   			
		   			
		   			Bson filter,documentParked,documentFinished,documentState;
 					filter = eq("uniqueKey",context.getUniqueKey());
 					documentParked = set("isParked",false);
 					MongoCommands.updateData("Confirmation", "Parking", documentParked, filter);
 					documentFinished = set("isFinished",true);
 					MongoCommands.updateData("Confirmation", "Parking", documentFinished, filter);
 					documentState = set("state",false);
 					MongoCommands.updateData("Confirmation", "Parking", documentState, filter);
 					
 					BasicDBObject queryName = new BasicDBObject();
 					queryName.put("uniqueKey", new BasicDBObject("$eq",context.getUniqueKey()));
 					FindIterable<Document> docName = MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", queryName);
 					
 					BasicDBObject queryPrice = new BasicDBObject();
 					queryPrice.put("parkingName", new BasicDBObject("$eq",docName.first().getString("parkingLotName")));
 					FindIterable<Document> docPrice = MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", queryPrice);

 					BasicDBObject queryStart = new BasicDBObject();
 					queryStart.put("uniqueKey", new BasicDBObject("$eq",context.getUniqueKey()));
 					FindIterable<Document> docStart = MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", queryStart);
 					
 					long finishTime = System.currentTimeMillis();
 					documentState = set("finishTime",finishTime);
 					MongoCommands.updateData("Confirmation", "Parking", documentState, filter);

 					long totalPrice = (long) (docPrice.first().getLong("price") * Math.ceil((double)((long)(finishTime - (long)docStart.first().getLong("requestedTime")) / 3600000.0f)));
 					
 					documentState = set("price",totalPrice);
 					
 					MongoCommands.updateData("Confirmation", "Parking", documentState, filter);
 					
		   			return "FINISHED";
		   		}
		   		else if(doc.first().getBoolean("state") == false)
		   		{
		   			return "EXPIRED";
		   		}
		   		return "SUCCESS";
	       
	    }
		@POST
		   @Path("/finishParking") 
		   @Produces(MediaType.APPLICATION_JSON) 
		   @Consumes({MediaType.APPLICATION_JSON})
		   public static Document getParkingDetails(ConfirmationContext context)
		   {
				BasicDBObject inQuery = new BasicDBObject();	
				inQuery.put("uniqueKey", new BasicDBObject("$eq", context.getUniqueKey()));
				FindIterable<Document> docs = MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", inQuery);
			    return docs.first();
		   }
	   
		
		   
	   }

