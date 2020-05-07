package com.java.resources;

import java.sql.Time;
import java.util.Base64;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.java.context.RegisterUserDetailsContext;

import com.java.database.MongoCommands;
import com.java.scheduler.CustomTask;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Path("/test")
public class TestApi {
	@GET
	@Path("/testingTimer")
	@Produces(MediaType.TEXT_PLAIN) 
	public static String schedule(){
//		Timer time = new Timer();
//		CustomTask ct=new CustomTask();
//		Date date=new Date();
//		date.setTime(1588578323000l);
//		System.out.print(date.toLocaleString());
//		time.schedule(ct, date);
		return "EXECUTED";
	}
}