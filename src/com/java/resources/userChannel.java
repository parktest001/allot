package com.java.resources;

import java.util.Random;

import org.bson.Document;

import com.java.database.MongoCommands;
import com.java.standardAPI.InitConstants;

public class userChannel {
	public static void createUserChannel(long mobileNumber)
	{
		Random rand=new Random();
		Document document = new Document("mobilenumber",mobileNumber)
				.append("key",rand.nextInt(1000))
				.append("channelid",InitConstants.channelid++);
		MongoCommands.insertData("userToChannelMap", "UserInfo",document );
	}
	public void getUserChannel(long mobileNumber)
	{
		
	}
	
}
