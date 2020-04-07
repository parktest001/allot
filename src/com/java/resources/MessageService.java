package com.java.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.java.context.MessageInput;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
@Path("/UserService") 

public class MessageService {  
   Message userDao = new Message();  
//   @GET 
//   @Path("/users") 
//   @Produces(MediaType.APPLICATION_JSON) 
//   public List<MessageInput> getMessages(){ 
//      return Message.getSingleChannelMessages(); 
//   } 
   @POST
   @Path("/users")
   @Produces(MediaType.APPLICATION_JSON) 
   @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON})
   public List<MessageInput> getMessages(MessageInput msg){

	   //context.createCollection(msg.getChannelId()+"","Messages");
	   Document document = new Document("messageTime",msg.getMessageTime())
	    			.append("message", msg.getMessage());
	   //MongoCommands.insertData("s"+msg.getChannelId()+"s","Messages",document);
	   long time=msg.getMessageTime();
	   System.out.println(time);
	   userChannel.createUserChannel(9999999999l);
	   
	   
	   BasicDBObject query = new BasicDBObject();
	   query.put("mobilenumber", 9999999999l);
	   FindIterable<Document> res= MongoCommands.retrieveDataWithCondition("userToChannelMap", "UserInfo", query);
	   for(Document ss:res)
	   {
		   System.out.println(ss);

	   }
	   return Message.getSingleChannelMessages(); 
   }  
}