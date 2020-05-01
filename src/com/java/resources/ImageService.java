package com.java.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.bson.types.Binary;

import com.java.context.ImageContext;
import com.java.database.MongoCommands;
import com.java.database.MongoServerConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
@Path("/ImageService") 
public class ImageService {
	@POST
	@Path("/InsertImage")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_JSON})
	public String insertImageService(ImageContext context){
        String source = "/Users/kamal/Desktop/Logo_White";
		insert(  source +  ".png",context);
		return "SUCCESS";
	}
	
	
	
	
	@POST
	@Path("/getImage")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_JSON})
	public String retrieveImageService(ImageContext context){
		
        String source = "/Users/kamal/Desktop/img";
			retrieve(source + ".png", context);
		return "SUCCESS";
	}
	void insert(String source, ImageContext context)
    {
        try
        {
            File imageFile = new File(source);
            FileInputStream fs = new FileInputStream(imageFile);
            byte byteStream[] = new byte[fs.available()];
            fs.read(byteStream);
            Binary data = new Binary(byteStream);
            
            
            Document document = new Document("image",data)
            .append("parkingName", context.getParkingName());
            MongoCommands.insertData("parkingImages", "Images", document);
            System.out.println("Inserted record.");
            fs.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void retrieve(String destination,ImageContext context)
    {
        byte byteStream[];
        try
        {
        	FindIterable<Document> docs = MongoCommands.retrieveAllData("parkingImages", "Images");
        	Document document = docs.first();
        	System.out.print(document.get("image"));
            Binary bin = document.get("image", org.bson.types.Binary.class);
            byteStream = bin.toString().getBytes();
            FileOutputStream fout = new FileOutputStream(destination);
            fout.write(byteStream);
            fout.flush();
            System.out.println("Photo of "+"image"+" retrieved and stored at "+destination);
            fout.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
