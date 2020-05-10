package com.java.scheduler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.java.context.ConfirmationContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class CustomTask extends TimerTask  {
	ConfirmationContext context;
	String uniqueKey;
	public CustomTask(ConfirmationContext context,String uniqueKey){
		  
		 this.context = context;
		 this.uniqueKey = uniqueKey;
	     System.out.println("Executed Now");

	   }

	   public void run() {
	    	   BasicDBObject query = new BasicDBObject();
		   		query.put("uniqueKey", new BasicDBObject("$eq", uniqueKey));
		   		FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", query);
		   		if(doc.first().getBoolean("state") == false && doc.first().getBoolean("isConfirmed") == false)
		   		{
		   			BasicDBObject queryCount = new BasicDBObject();
		   			queryCount.put("parkingName", new BasicDBObject("$eq", context.getParkingLotName()));
			   		FindIterable<Document> docCount = MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", queryCount);

		   			if(context.getVehicleType() == 1 && docCount.first().getLong("liveCarCount") <= 0)
		   			{
		   				System.out.println("No car slot available");
		   			}
		   			
		   			else if(context.getVehicleType() == 2 && docCount.first().getLong("liveBikeCount") <= 0)
		   			{
		   				System.out.println("No Bike slot available");
		   			}
		   			else 
		   			{
		   		
		   				Bson filter,document,filter1,document1;
	   					filter = eq("parkingName",context.getParkingLotName());
		   				filter1 = eq("uniqueKey",uniqueKey);
		   				if(context.getVehicleType() == 1)
		   				{
			   				System.out.println("Car booking confirmed");
		   					document = set("liveCarCount",docCount.first().getLong("liveCarCount") - 1);

		   				}
		   				else
		   				{
		   					document = set("liveBikeCount",docCount.first().getLong("liveBikeCount") - 1);
			   				System.out.println("Bike booking confirmed");
		   				}
	   					MongoCommands.updateData("ParkingLotDetails", "Parking", document, filter);
	   					document1 = set("isConfirmed",true);
	   					MongoCommands.updateData("Confirmation", "Parking", document1, filter1);
	   					document1 = set("state",true);
	   					MongoCommands.updateData("Confirmation", "Parking", document1, filter1);
	   					
	   					
	   					
	   					Date dateExpiry=new Date();
	   					dateExpiry.setTime(context.getRequestedTime() + 900000);
	   					ExpiryCheck ec = new ExpiryCheck(uniqueKey);
	   					Timer time = new Timer();
	   					time.schedule(ec, dateExpiry);

		   			}
		   		}
		   		else if(doc.first().getBoolean("isCancelled") == true) {
		   			System.out.println("Cancelled");
		   		}
		   		
	    	   	System.out.println("TIMER EXECUTED");
	       
	    }
	}