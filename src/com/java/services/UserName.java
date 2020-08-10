package com.java.services;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.java.context.LoginContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
 
@Path("/user")
public class UserName {

	@POST
	@Path("/adminlogin")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)

	public static String adminLogin(LoginContext context) {
		   BasicDBObject query = new BasicDBObject();
		   query.put("mobile", new BasicDBObject("$eq", context.getMobile()));
		   query.put("passWord", new BasicDBObject("$eq", context.getpassWord()));
		   FindIterable<Document> docs = MongoCommands.retrieveDataWithCondition("adminlogin", "products", query);
		   if(docs.first() != null)
		   {
			   return "SUCCESS";
		   }
		   else
		   {
				return "FAILED";
   
		   }
	}
	   
	@POST
	@Path("/userlogin")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)

	public static String userLogin(LoginContext context) {
		   BasicDBObject query = new BasicDBObject();
		   query.put("mobile", new BasicDBObject("$eq", context.getMobile()));
		   query.put("passWord", new BasicDBObject("$eq", context.getpassWord()));
		   FindIterable<Document> docs = MongoCommands.retrieveDataWithCondition("userlogin", "products", query);
		   if(docs.first() != null)
		   {
			   return "SUCCESS";
		   }
		   else
		   {
				return "FAILED";

		   }
	}


}