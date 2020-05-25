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

import com.java.context.ConfirmationContext;
import com.java.context.TokenContext;
import com.java.context.VendorTokenContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

@Path("/vendorTokenService")
public class VendorTokenService {
	@POST
	@Path("/setToken")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_JSON})
	public static String updateToken(VendorTokenContext context) {

			Bson filter,document;
			filter = eq("parkingLotName",context.getParkingLotName());
				document = set("token",context.getToken());
				MongoCommands.updateData("Token", "VendorDetails", document, filter);
		return "SUCCESS";
	}
	
	@POST
	@Path("/getToken")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_JSON})
	public static String getToken(VendorTokenContext context) {

		
		BasicDBObject queryGetToken = new BasicDBObject();
		queryGetToken.put("parkingLotName", new BasicDBObject("$eq", context.getParkingLotName()));
		FindIterable<Document> docToken = MongoCommands.retrieveDataWithCondition("Token", "VendorDetails", queryGetToken);
		if(docToken.first() != null)
		{
			return docToken.first().getString("token");
		}
		return "FAILED";
	}
}