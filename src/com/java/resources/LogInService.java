package com.java.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.java.context.RegisterUserDetailsContext;
import com.java.context.UserLogInContext;
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

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;


@Path("/user")
public class LogInService {
	@POST
	@Path("/logIn")
	@Produces(MediaType.TEXT_PLAIN) 
	@Consumes({MediaType.APPLICATION_JSON})
	public static String verifyMobile(UserLogInContext context){
//		Filter doc;
//				doc.eq("userName","KAMALAKANNAN");
		Bson filter = eq("userName","kamal");
		Bson doc = set("userName","KAMALAKANNAN");
//		filter =
//		filter.eq("userName", "kamal")
		
		MongoCommands.updateData("User", "UserDetails", filter, filter);
//		BasicDBObject query = new BasicDBObject();
//		query.put("userName", new BasicDBObject("$eq", context.getUserName()));
//		FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("User", "UserDetails", query);
//		if(doc.first() == null) {
//			return "NoUser";
//		}
//		else if(doc.first().get("passWord").equals(context.getpassWord()))
//		{
//			return doc.first().getString("userId");
//		}
		return "Failed";
	}

	@POST
	@Path("/getLogInDetailes")
	@Produces(MediaType.APPLICATION_JSON) 
	@Consumes({MediaType.APPLICATION_JSON})
	public static Document get(RegisterUserDetailsContext context){
		BasicDBObject query = new BasicDBObject();
		query.put("userId", new BasicDBObject("$eq", context.getUserId()));
		FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("User", "UserDetails", query);
		return doc.first();
	}
}
