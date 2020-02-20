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

import utility.Comune;
import utility.Connection;

import static com.mongodb.client.model.Filters.*;

public class LoadComune extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public LoadComune() 
    {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection c = new Connection("test","comuni");
		MongoCollection<Document> coll = c.getColl();
		
		String regione = (String) request.getParameter("regione");
		String provincia = (String) request.getParameter("provincia");
		
		MongoCursor<String> app = coll.distinct("COMUNE", and(eq("REGIONE",regione),
				or(eq("PROVINCIA","-" + provincia),eq("PROVINCIA",provincia + "-"))), String.class).iterator();
		ArrayList<Comune> ris = new ArrayList<Comune>();
		
		while(app.hasNext())
		{
			ris.add(new Comune(app.next()));
		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(ris);
		
		response.setContentType("text/html");
		response.getWriter().write(jsonString);
	}
}