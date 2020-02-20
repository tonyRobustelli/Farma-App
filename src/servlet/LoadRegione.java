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
import utility.Regione;

public class LoadRegione extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection c = new Connection("test","comuni");
		MongoCollection<Document> coll = c.getColl();
		
		MongoCursor<String> app = coll.distinct("REGIONE", String.class).iterator();
		ArrayList<Regione> ris = new ArrayList<Regione>();
		
		while(app.hasNext())
		{
			ris.add(new Regione(app.next()));
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(ris);
		
		response.setContentType("text/html");
		response.getWriter().write(jsonString);
	}
}