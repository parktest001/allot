package com.java.resources;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.conversions.Bson;

import com.java.context.RegisterUserDetailsContext;
import com.java.database.MongoCommands;



@Path("/password")
public class ForgotPasswordService {
	@POST
	@Path("/reset")
	@Produces(MediaType.TEXT_PLAIN) 
	@Consumes({MediaType.APPLICATION_JSON})
	public static String resetPassword(RegisterUserDetailsContext context) {
		Bson filter,document;

		filter = eq("mobile",context.getMobile());

		document = set("passWord",context.getPassWord());

	
		MongoCommands.updateData("User", "UserDetails", document, filter);
		return "SUCCESS";
	}

}
