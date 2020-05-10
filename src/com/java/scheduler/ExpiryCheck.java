package com.java.scheduler;

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

public class ExpiryCheck extends TimerTask  {
	String uniqueKey;
	public ExpiryCheck(String uniqueKey){
		  
		 this.uniqueKey = uniqueKey;
	     System.out.println("Executed Now");

	   }

	   public void run() {
	    	   BasicDBObject query = new BasicDBObject();
		   		query.put("uniqueKey", new BasicDBObject("$eq", uniqueKey));
		   		FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", query);

		   		
		   		if(doc.first().getBoolean("state") == true && doc.first().getBoolean("isParked") == false &&  doc.first().getBoolean("isFinished")== false)
		   		{
		   			
	   				Bson filter,document;
   					filter = eq("uniqueKey",uniqueKey);
   					document = set("state",false);
   					MongoCommands.updateData("Confirmation", "Parking", document, filter);
		   		}
	       
	    }
	}