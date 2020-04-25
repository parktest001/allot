package com.java.resources;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.json.JSONObject;

import com.java.context.ViewPortContext;
import com.java.database.MongoCommands;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;

@Path("/MarkersOnViewService")
public class MarkersOnViewService {
	
	@SuppressWarnings("deprecation")
	@POST
	   @Path("/getMarkers")
	   @Produces(MediaType.APPLICATION_JSON) 
	   @Consumes({MediaType.APPLICATION_JSON})
	   public static ArrayList<HashMap<String, Object>> getMarkersOnView(ViewPortContext context)
	   {
			ArrayList<String> parkingName= new ArrayList<>();
			ArrayList<DBObject> criteria = new ArrayList<DBObject>();
			BasicDBObject inQuery = new BasicDBObject();
	        ArrayList<HashMap<String,Object>> ja = new ArrayList<>(); 
	        Double latitude=context.getLattitude();
	        Double longitude=context.getLongitude();
			criteria.add(new BasicDBObject("lattitude", new BasicDBObject("$gt", latitude - 0.01)));
			criteria.add(new BasicDBObject("longitude", new BasicDBObject("$gt", longitude - 0.01)));
			criteria.add(new BasicDBObject("lattitude", new BasicDBObject("$lt", latitude + 0.01)));
			criteria.add(new BasicDBObject("longitude", new BasicDBObject("$lt", longitude + 0.01)));
			FindIterable<Document> docs= MongoCommands.retrieveDataWithCondition("ParkingLotDetailSignUp", "Parking", new BasicDBObject("$and", criteria));
			
			docs.forEach(new Block<Document>() {

				@Override
				public void apply(Document t) {
					parkingName.add(t.getString("parkingLotName"));	
				};
			
			});
			inQuery.put("parkingName", new BasicDBObject("$in", parkingName));
			FindIterable<Document> docsSpace= MongoCommands.retrieveDataWithCondition("ParkingLotDetails", "Parking", inQuery);

			docs.forEach(new Block<Document>() {

				@Override
				public void apply(Document t) {

					docsSpace.forEach(new Block<Document>() {

						@Override
						public void apply(Document u) {
							if(t.getString("parkingLotName").equals(u.getString("parkingName")))
							{
								try {
							        HashMap<String,Object> jo = new HashMap<>(); 
							        jo.put("parkingName",t.getString("parkingLotName"));
							        jo.put("address", u.getString("address"));
							        jo.put("rating", u.get("rating"));
							        jo.put("price", u.get("price"));
							        jo.put("lattitude", t.get("lattitude"));
							        jo.put("longitude", t.get("longitude"));
							        jo.put("carCapacity", t.get("carCapacity"));
							        jo.put("bikeCapacity", t.get("bikeCapacity"));
							        ja.add(jo);

								}catch (Exception e) {
									System.out.println(e);
								}
							}
						};

					});				
					};

			});
			return ja; 
	
	   }
}
