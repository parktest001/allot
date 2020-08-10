package com.java.database;


import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoCommands {
	public static MongoClientURI uri = new MongoClientURI("mongodb://admin:Password@cluster0-shard-00-00.rmoje.mongodb.net:27017,cluster0-shard-00-01.rmoje.mongodb.net:27017,cluster0-shard-00-02.rmoje.mongodb.net:27017/<dbname>?ssl=true&replicaSet=atlas-v7op1p-shard-0&authSource=admin&retryWrites=true&w=majority");
	public static MongoClient mongo = new MongoClient(uri); 
	
	public static void insertData(String collectionName,String databaseName,Document document)
	{
		 
	     MongoDatabase database = mongo.getDatabase(databaseName); 
		 MongoCollection<Document> collection = database.getCollection(collectionName); 
	  	collection.insertOne(document);
		
	}
	public static void updateData(String collectionName,String databaseName, Bson document,Bson query)
	{
		 
	     MongoDatabase database = mongo.getDatabase(databaseName); 
		 MongoCollection<Document> collection = database.getCollection(collectionName); 
	  	collection.updateOne(query, document);
		
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
	public static void deleteDataWithCondition(String collectionName,String databaseName,BasicDBObject query )
	{
		MongoDatabase database = mongo.getDatabase(databaseName);
		MongoCollection<Document> collection = database.getCollection(collectionName); 
		collection.findOneAndDelete(query);
	}

}

