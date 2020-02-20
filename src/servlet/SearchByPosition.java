package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

import utility.Connection;
import utility.Item;


public class SearchByPosition extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection c = new Connection("test","farma");
		MongoCollection<Document> coll = c.getColl();
		
		coll.createIndex(Indexes.geo2dsphere("LOCATION"));
		
		String longitudine = request.getParameter("lon");
		String latitudine = request.getParameter("lat");
		
		Point refPoint = new Point(new Position(Double.parseDouble(longitudine),Double.parseDouble(latitudine)));
		ArrayList<Document> app = coll.find(Filters.near("LOCATION", refPoint, 4000.0, 0.0)).into(new ArrayList<Document>());
		
		ArrayList<Item> ris = new ArrayList<Item>();
		
		for(int i=0;i<app.size();i++)
		{
			Item a = new Item();
			a.setDenominazione((String) app.get(i).get("DENOMINAZIONE"));
			a.setIndirizzo((String) app.get(i).get("INDIRIZZO"));
			a.setLatitudine(app.get(i).getDouble("LATITUDINE"));
			a.setLongitudine(app.get(i).getDouble("LONGITUDINE"));
			ris.add(a);
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(ris);
		
		response.setContentType("text/html");
		response.getWriter().write(jsonString);
	}
}