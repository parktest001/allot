package com.java.resources;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.bson.Document;
import com.java.context.HistoryContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

@Path("/HistoricalTransaction")
public class HistoryParkingService {
	@SuppressWarnings("deprecation")
	@POST
	   @Path("/getHistoryDetails") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static ArrayList<HashMap<String, Object>> getHistoryTransaction(HistoryContext context)
	   {
        	ArrayList<HashMap<String,Object>> res = new ArrayList<>(); 
			List<String> parkingName = new ArrayList<String>();
			BasicDBObject inQuery = new BasicDBObject();	
			BasicDBObject inQueryAddressJoin = new BasicDBObject();	
			inQuery.put("userMobileNumber", new BasicDBObject("$eq", context.getUserMobileNumber()));
			
			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", inQuery);
			docs.forEach(new Block<Document>() {

				@Override
				public void apply(Document u) {
					parkingName.add(u.getString("parkingLotName"));					
				}
			});
			inQueryAddressJoin.put("parkingName",new BasicDBObject("$in",parkingName));
			FindIterable<Document> docsAddress= MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", inQueryAddressJoin);
			docsAddress.forEach(new Block<Document>() {

				@Override
				public void apply(Document t) {
					System.out.println(t);
					docs.forEach(new Block<Document>() {

						@Override
						public void apply(Document u) {
							if(t.getString("parkingName").equals( u.getString("parkingLotName")))
							{
								HashMap<String,Object> result = new HashMap<>();
								if(!u.getBoolean("isConfirmed")&& !u.getBoolean("isParked") && !u.getBoolean("isFinished") && !u.getBoolean("isCancelled") && !u.getBoolean("state"))
								{
									result.put("parkingLotName", u.getString("parkingLotName"));
									result.put("requestedTime", u.getLong("requestedTime"));
									result.put("address", t.getString("address"));
									result.put("onGoing", false);
									result.put("uniqueKey", u.getString("uniqueKey"));
									res.add(result);
								}
								else if(u.getBoolean("state")){
									result.put("parkingLotName", u.getString("parkingLotName"));
									result.put("requestedTime", u.getLong("requestedTime"));
									result.put("address", t.getString("address"));
									result.put("onGoing", true);
									result.put("isPaid", u.getBoolean("isPaid"));
									result.put("isFinished", u.getBoolean("isFinished"));
									result.put("isParked", u.getBoolean("isParked"));
									result.put("uniqueKey", u.getString("uniqueKey"));
									res.add(result);
								}
//								result.put("parkingLotName", value);
//								result.put("parkingLotName", value);
							}
						}
					});				
					}
				
			});
			return res;
	   }
	
	
	@SuppressWarnings("deprecation")
	@POST
	   @Path("/getHistoryDetailsCompleted") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static ArrayList<HashMap<String, Object>> getHistoryTransactionCompleted(HistoryContext context)
	   {
     	ArrayList<HashMap<String,Object>> res = new ArrayList<>(); 
			List<String> parkingName = new ArrayList<String>();
			BasicDBObject inQuery = new BasicDBObject();	
			BasicDBObject inQueryAddressJoin = new BasicDBObject();	
			inQuery.put("userMobileNumber", new BasicDBObject("$eq", context.getUserMobileNumber()));
			
			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("Confirmation", "Parking", inQuery);
			docs.forEach(new Block<Document>() {

				@Override
				public void apply(Document u) {
					parkingName.add(u.getString("parkingLotName"));					
				}
			});
			inQueryAddressJoin.put("parkingName",new BasicDBObject("$in",parkingName));
			FindIterable<Document> docsAddress= MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", inQueryAddressJoin);
			docs.forEach(new Block<Document>() {

				@SuppressWarnings("deprecation")
				@Override
				public void apply(Document u) {
					System.out.println(u);
					docsAddress.forEach(new Block<Document>() {

						@Override
						public void apply(Document t) {
							if(t.getString("parkingName").equals( u.getString("parkingLotName")))
							{
								HashMap<String,Object> result = new HashMap<>();
								if(u.getBoolean("isFinished") && !u.getBoolean("state"))
								{
									result.put("parkingLotName", u.getString("parkingLotName"));
									result.put("requestedTime", u.getLong("requestedTime"));
									result.put("address", t.getString("address"));
									result.put("isCancelled",false);
									result.put("price",u.getLong("price"));
									result.put("finishTime",u.getLong("finishTime"));
									res.add(0,result);
								}
								else if(!u.getBoolean("state") && u.getBoolean("isCancelled")){
									result.put("parkingLotName", u.getString("parkingLotName"));
									result.put("requestedTime", u.getLong("requestedTime"));
									result.put("address", t.getString("address"));
									result.put("isCancelled", true);
									res.add(0,result);
								}
//								result.put("parkingLotName", value);
//								result.put("parkingLotName", value);
							}
						}
					});				
					}
				
			});
			return res;
	   }
}