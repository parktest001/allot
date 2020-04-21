package com.java.resources;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.java.context.MessageInput;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.sun.jersey.api.core.ResourceConfig;

@Path("/UserService") 
public class MessageService extends ResourceConfig{  
   Message userDao = new Message();  
   @GET 
   @Path("/users") 
   @Produces(MediaType.APPLICATION_JSON) 
   public List<MessageInput> getMessages(){ 
      return Message.getSingleChannelMessages(); 
   }   
   @POST
   @Path("/users")
   @Produces(MediaType.APPLICATION_JSON) 
   @Consumes({MediaType.APPLICATION_JSON})
   public List<MessageInput> getMessages(MessageInput msg) throws JsonParseException, JsonMappingException, IOException{

	   //context.createCollection(msg.getChannelId()+"","Messages");
	   Document document = new Document("messageTime",msg.getMessageTime())
	    			.append("message", msg.getMessage());
	   MongoCommands.insertData("s"+msg.getChannelId()+"s","Messages",document);
	   
	 
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
@Override
public boolean getFeature(String featureName) {
	// TODO Auto-generated method stub
	return false;
}
@Override
public Map<String, Boolean> getFeatures() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Map<String, Object> getProperties() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Object getProperty(String propertyName) {
	// TODO Auto-generated method stub
	return null;
}  

}