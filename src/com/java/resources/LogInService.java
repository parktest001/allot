package com.java.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.java.context.UserLogInContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;


@Path("/user")
public class LogInService {
	@POST
	@Path("/logIn")
	@Produces(MediaType.TEXT_PLAIN) 
	@Consumes({MediaType.APPLICATION_JSON})
	public static String verifyMobile(UserLogInContext context){
		BasicDBObject query = new BasicDBObject();
		query.put("userName", new BasicDBObject("$eq", context.getUserName()));
		FindIterable<Document> doc = MongoCommands.retrieveDataWithCondition("User", "UserDetails", query);
		if(doc.first() == null) {
			return "NoUser";
		}
		else if(doc.first().get("passWord").equals(context.getpassWord()))
		{
			return "Success";
		}
		return "Failed";
	}

}
