package com.java.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.java.context.RegisterUserDetailsContext;

import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Path("/RegisterUserDetailsService")
public class RegisterUserDetailsService {
	@POST
	@Path("/verifyMobile")
	@Produces(MediaType.TEXT_PLAIN) 
	@Consumes({MediaType.APPLICATION_JSON})
	public static String verifyMobile(RegisterUserDetailsContext context){
		BasicDBObject query = new BasicDBObject();
		query.put("mobile", new BasicDBObject("$eq", context.getMobile()));
		FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("User", "UserDetails", query);
		if(doc.first() == null) {
			return "Success";
		}
		return "Failed";
	}
	
	@POST
	@Path("/registerUser")
	@Produces(MediaType.TEXT_PLAIN) 
	@Consumes({MediaType.APPLICATION_JSON})
	public static String registerUser(RegisterUserDetailsContext context){
		Document document = new Document("userName", context.getUserName())
				   .append("passWord", context.getPassWord())
				   .append("mobile", context.getMobile());
		
		MongoCommands.insertData("User", "UserDetails", document);
		return "Success";
	}
}