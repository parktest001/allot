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

//        MongoClientURI uri = new MongoClientURI("mongodb://channel:stream@cluster0-shard-00-00-gbif3.mongodb.net:27017,cluster0-shard-00-01-gbif3.mongodb.net:27017,cluster0-shard-00-02-gbif3.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true&w=majority");
//		MongoClient mongo = new MongoClient(uri);
		DB database = MongoServerConnection.mongo.getDB("Images");
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
            if(imageList1.size() == 0)
            {
            	imageList1.add("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");
            }
            imageList = imageList1;
//            System.out.print(imageList.size());
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
