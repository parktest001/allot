package com.java.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.types.Binary;


import com.java.context.ImageContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

@Path("/ImageService") 
public class ImageService {
	@POST
	@Path("/InsertImage")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_JSON})
	public String insertImageService(ImageContext context){
		MongoClientURI uri = new MongoClientURI("mongodb://channel:stream@cluster0-shard-00-00-gbif3.mongodb.net:27017,cluster0-shard-00-01-gbif3.mongodb.net:27017,cluster0-shard-00-02-gbif3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
		MongoClient mongo = new MongoClient(uri);
		DB database = mongo.getDB("Images");
		MongoCommands.createCollection(context.getParkingName(), "Images");
        DBCollection collection = database.getCollection(context.getParkingName());
        String source = "C:\\Users\\Anonymous\\Pictures\\img";
        String fileName = "IMG";
		for(int i=1; i<=4; i++)
		{
			insert(fileName + i, source + i + ".png", collection);
		}
		return "SUCCESS";
	}
	@POST
	@Path("/InsertImage")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_JSON})
	public String retrieveImageService(ImageContext context){
		MongoClientURI uri = new MongoClientURI("mongodb://channel:stream@cluster0-shard-00-00-gbif3.mongodb.net:27017,cluster0-shard-00-01-gbif3.mongodb.net:27017,cluster0-shard-00-02-gbif3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
		MongoClient mongo = new MongoClient(uri);
		DB database = mongo.getDB("Images");
		MongoCommands.createCollection(context.getParkingName(), "Images");
        DBCollection collection = database.getCollection(context.getParkingName());
        String source = "C:\\Users\\Anonymous\\Pictures\\img";
        String fileName = "IMG";
		for(int i=1; i<=4; i++)
		{
			retrieve(fileName + i, source + i + ".png", collection);
		}
		return "SUCCESS";
	}
	void insert(String fileName, String source, DBCollection collection)
    {
        try
        {
            File imageFile = new File(source);
            FileInputStream fs = new FileInputStream(imageFile);
 
            byte byteStream[] = new byte[fs.available()];
            fs.read(byteStream);
 
            Binary data = new Binary(byteStream);
            BasicDBObject obj = new BasicDBObject();
            obj.append("fileName",fileName).append("image",data);
            collection.insert(obj);
            System.out.println("Inserted record.");
 
            fs.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    void retrieve(String fileName, String destination, DBCollection collection)
    {
        byte byteStream[];
        try
        {
            DBObject obj = collection.findOne(new BasicDBObject("fileName", fileName));
            byteStream = (byte[])obj.get("image");
            FileOutputStream fout = new FileOutputStream(destination);
            fout.write(byteStream);
            fout.flush();
            System.out.println("Photo of "+fileName+" retrieved and stored at "+destination);
            fout.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}