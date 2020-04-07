package com.java.database;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoCommands {
	static MongoClient mongo = MongoServerConnection.mongo;
	public static void createCollection(String collectionName,String databaseName)
	{
		   
	     MongoCredential  credential = MongoCredential.createCredential("sampleUser", "myDb", 
	        "password".toCharArray());
	     MongoDatabase database = mongo.getDatabase(databaseName); 
	     System.out.println("Credentials ::"+ credential); 
	     database.createCollection(collectionName); 
	}
	public static void insertData(String collectionName,String databaseName,Document document)
	{
	     MongoDatabase database = mongo.getDatabase(databaseName); 
		 MongoCollection<Document> collection = database.getCollection(collectionName); 
//	      Document document = new Document("title", "MongoDB")
//	    			.append("description", "database")
//	    			.append("likes", 100)
//	    			.append("url", "http://www.tutorialspoint.com/mongodb/")
//	    			.append("by", "tutorials point");
	  	collection.insertOne(document);
		
	}
	public void constructData()
	{
		
	}
	public static FindIterable<Document> retrieveAllData(String collectionName,String databaseName)
	{
		MongoDatabase database = mongo.getDatabase(databaseName); 
		MongoCollection<Document> collection = database.getCollection(collectionName); 
		FindIterable<Document> iterDoc = collection.find();
		return iterDoc;
		

	}
	public static FindIterable<Document> retrieveDataWithCondition(String collectionName,String databaseName,BasicDBObject query)
	{
		MongoDatabase database = mongo.getDatabase(databaseName); 
		MongoCollection<Document> collection = database.getCollection(collectionName); 
		FindIterable<Document> iterDoc = collection.find(query);
		return iterDoc;
	}

}
