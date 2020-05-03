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
//		File imageFile = new File("/Users/kamal/Desktop/park2.jpg");
//        FileInputStream fs = new FileInputStream(imageFile);
//
//        byte byteStream[] = new byte[fs.available()];
//        fs.read(byteStream);

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

//        fs.close();

//        String source = "/Users/kamal/Desktop/Current_Marker.png";
//        String newFileName = "my-image1";
//    	File imageFile = new File(source);
//    	MongoClientURI uri = new MongoClientURI("mongodb://channel:stream@cluster0-shard-00-00-gbif3.mongodb.net:27017,cluster0-shard-00-01-gbif3.mongodb.net:27017,cluster0-shard-00-02-gbif3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
//    	MongoClient mongo = new MongoClient(uri);
//	    DB database = mongo.getDB("IMAGES1");
//    	GridFS gfsPhoto = new GridFS(database,"IMAGEGRID");
//    	GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
//    	gfsFile.setFilename(newFileName);
//    	gfsFile.save();
		return "SUCCESS";
	}
	
	
	
	
	@POST
	@Path("/getImage")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON})
	public List<String> retrieveImageService(ImageContext context) throws IOException{
		byte byteStream[];
		String encoded;
		List<String> imageList;
		List<String> imageList1 = new ArrayList<String>();

        MongoClientURI uri = new MongoClientURI("mongodb://channel:stream@cluster0-shard-00-00-gbif3.mongodb.net:27017,cluster0-shard-00-01-gbif3.mongodb.net:27017,cluster0-shard-00-02-gbif3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
		MongoClient mongo = new MongoClient(uri);
		DB database = mongo.getDB("Images");
		 DBCollection collection = database.getCollection("New");
		DBCursor obj = collection.find(new BasicDBObject("parkingName", context.getParkingName()));
		while(obj.hasNext())
		{
			byteStream = (byte[])obj.next().get("image");
		    encoded = Base64.getEncoder().encodeToString(byteStream);
		    imageList1.add(encoded);
		    System.out.print(encoded);
		}
	      

//            FileOutputStream fout = new FileOutputStream("/Users/kamal/Desktop/img.png");
//            fout.write(byteStream);
//            fout.flush();
            System.out.println("Photo of  retrieved and stored at ");
//            fout.close();
            imageList = imageList1;
            System.out.print(imageList.size());
		return imageList;
	}
	
	
//	void insert(String source)
//    {
//        try
//        {
//        	
////        	
////            File imageFile = new File(source);
////            FileInputStream fs = new FileInputStream(imageFile);
////            byte byteStream[] = new byte[fs.available()];
////            fs.read(byteStream);
////            Binary data = new Binary(byteStream);
////            
////            
////            Document document = new Document("image",data)
////            .append("parkingName", context.getParkingName());
////            MongoCommands.insertData("parkingImages", "Images", document);
////            System.out.println("Inserted record.");
////            fs.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

   
}
