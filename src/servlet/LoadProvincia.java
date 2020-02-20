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
import com.mongodb.client.MongoCursor;

import utility.Connection;
import utility.Provincia;
import static com.mongodb.client.model.Filters.*;

public class LoadProvincia extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
    	Connection c = new Connection("test","comuni");
		MongoCollection<Document> coll = c.getColl();
		
		String regione = (String) request.getParameter("regione");
		
		MongoCursor<String> app = coll.distinct("PROVINCIA", eq("REGIONE",regione), String.class).iterator();
		ArrayList<Provincia> ris = new ArrayList<Provincia>();
		
		while(app.hasNext())
		{
			String a = app.next();
			a = a.replace("-", "");
			ris.add(new Provincia(a));
			if(a.equals(";")) ris.remove(ris.size() -1);
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(ris);
		
		response.setContentType("text/html");
		response.getWriter().write(jsonString);
	}
}