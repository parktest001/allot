package com.java.vendor;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.conversions.Bson;


import com.java.context.VendorLoginContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;


@Path("/Vendor")
public class vendorService {
	@POST
	@Path("/VendorLogin")
	@Produces(MediaType.APPLICATION_JSON) 
	@Consumes({MediaType.APPLICATION_JSON})
	public static HashMap<String,Object> vendorLogin(VendorLoginContext context){
		HashMap<String,Object> res = new HashMap<>();
		BasicDBObject query = new BasicDBObject();
		query.put("userName", new BasicDBObject("$eq", context.getUserName()));
		FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("ParkingLotDetailSignUp", "Parking", query);
		if(doc.first() == null) {
			res.put("status", "NoUserID");
			return res;
		}
		else if(doc.first().get("passWord").equals(context.getpassWord()))
		{
			res.put("status", "SUCCESS");
			res.put("parkingLotName", doc.first().getString("parkingLotName"));
			return res;
		}
		res.put("status", "FAILED");
		return res;
	}
	
//		@POST
//	   @Path("/forgotPassword")
//	   @Produces(MediaType.TEXT_PLAIN)
//	   @Consumes({MediaType.APPLICATION_JSON})
//	    public static FindIterable<Document> getPassword(VendorLoginService context)
//	   {
//	   				
//			BasicDBObject criteria=new BasicDBObject("UserId", new BasicDBObject("$eq",context.getUserId() ));
//			
//			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("VendorLoginCredentials", "Vendor", criteria);
//		   return docs; 
//	   }

	
}
