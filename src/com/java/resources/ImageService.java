package com.java.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.types.Binary;
import org.json.JSONObject;

import com.java.context.ImageContext;
import com.java.database.MongoCommands;
import com.java.database.MongoServerConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
@Path("/ImageService") 
public class ImageService {
	@POST
	@Path("/InsertImage")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_JSON})
	public String insertImageService(ImageContext context) throws IOException{
		byte byteStream[] = Base64.getDecoder().decode(context.getImageBase64());
        Binary data = new Binary(byteStream);
        BasicDBObject obj = new BasicDBObject();
        obj.append("parkingName",context.getParkingName())
           .append("image",data);
        
        
        MongoClientURI uri = new MongoClientURI("mongodb://channel:stream@cluster0-shard-00-00-gbif3.mongodb.net:27017,cluster0-shard-00-01-gbif3.mongodb.net:27017,cluster0-shard-00-02-gbif3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
		MongoClient mongo = new MongoClient(uri);
		DB database = mongo.getDB("Images");
        DBCollection collection = database.getCollection("New");
        collection.insert(obj);
        System.out.println("Inserted record.");
		return "SUCCESS";
	}
	
	
	
	
	@POST
	@Path("/getImage")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON})
	public List<String> retrieveImageService(ImageContext context) throws IOException{
	
		List<String> imageList =  new ArrayList<String>();
		DB database = MongoServerConnection.mongo.getDB("Images");
		DBCollection collection = database.getCollection("New");
		DBCursor obj = collection.find(new BasicDBObject("parkingName", context.getParkingName()));
		while(obj.hasNext())
		{
			
		    imageList.add(obj.next().get("image").toString());
		}
		return imageList;
	}
}
