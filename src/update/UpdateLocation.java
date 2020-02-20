package update;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import utility.Connection;

public class UpdateLocation 
{
	public static void main(String args[])
	{
		Connection c = new Connection("test","farma");
		MongoCollection<Document> coll = c.getColl();

		ArrayList<Document> cc = coll.find().into(new ArrayList<Document>());

		HashMap<Long,ArrayList<Double>> items = new HashMap<Long,ArrayList<Double>>();

		for(int i=0;i<cc.size();i++)
		{
			ArrayList<Double> d = new ArrayList<Double>();
			d.add(cc.get(i).getDouble("LONGITUDINE"));
			d.add(cc.get(i).getDouble("LATITUDINE"));

			Long app = cc.get(i).getLong("CODICE");
			items.put(app, d);
		}
		
		for(Map.Entry m:items.entrySet())
		{  
			coll.updateOne(eq("CODICE",m.getKey()),set("LOCATION", m.getValue()));
		} 
	}
}