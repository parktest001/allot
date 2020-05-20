package com.java.resources;

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
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

@Path("/UpdateTokenService")
public class UpdateTokenService {
	@POST
	@Path("/setToken")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_JSON})
	public static String updateToken(TokenContext context) {

			Bson filter,document;
			filter = eq("mobile",context.getUserMobileNumber());
				document = set("token",context.getToken());
				MongoCommands.updateData("Token", "UserDetails", document, filter);
		return "SUCCESS";
	}
	
	@POST
	@Path("/getToken")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_JSON})
	public static String getToken(ConfirmationContext context) {

		BasicDBObject queryGetMobile = new BasicDBObject();
		queryGetMobile.put("uniqueKey", new BasicDBObject("$eq", context.getUniqueKey()));
		FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", queryGetMobile);
		if(context.getParkingLotName().equals(doc.first().getString("parkingLotName")))
		{
			BasicDBObject queryGetToken = new BasicDBObject();
			queryGetToken.put("mobile", new BasicDBObject("$eq", doc.first().getLong("userMobileNumber")));
			FindIterable<Document> docToken = MongoCommands.retrieveDataWithCondition("Token", "UserDetails", queryGetToken);
			return docToken.first().getString("token");
		}
		return "FAILED";
		
	}
}