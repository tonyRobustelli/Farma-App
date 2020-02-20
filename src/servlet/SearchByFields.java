package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.*;

import utility.Connection;
import utility.Item;



public class SearchByFields extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection c = new Connection("test","farma");
		MongoCollection<Document> coll = c.getColl();
		
		String comune = (String) request.getParameter("comune");
		String radio = (String) request.getParameter("linguaggio");
		
		ArrayList<Document> employees = null;
				
		if(radio.equals("-1"))
		{
			employees = (ArrayList<Document>)
					coll.find().filter(and(or(eq("DESCRIZIONECOMUNE",comune),eq("DESCRIZIONECOMUNE",comune.toUpperCase()))
					,lt("CODICE",0))).into(new ArrayList<Document>());
		}
		else if(radio.equals("1"))
		{
			employees = (ArrayList<Document>)
					coll.find().filter(and(or(eq("DESCRIZIONECOMUNE",comune),eq("DESCRIZIONECOMUNE",comune.toUpperCase()))
					,gte("CODICE",0))).into(new ArrayList<Document>());
		}
		else
		{
			employees = (ArrayList<Document>) 
					coll.find().filter(or
				(eq("DESCRIZIONECOMUNE",comune),eq("DESCRIZIONECOMUNE",comune.toUpperCase()))).into(new ArrayList<Document>());
		}
		
		ArrayList<Item> ris = new ArrayList<Item>();
		
		for(int i=0;i<employees.size();i++)
		{
			Item a = new Item();
			a.setDenominazione((String) employees.get(i).get("DENOMINAZIONE"));
			a.setIndirizzo((String) employees.get(i).get("INDIRIZZO"));
			a.setLatitudine(employees.get(i).getDouble("LATITUDINE"));
			a.setLongitudine(employees.get(i).getDouble("LONGITUDINE"));
			ris.add(a);
		}
		
		request.setAttribute("ris", ris);
		RequestDispatcher view = request.getRequestDispatcher("./Risultati.jsp");
		view.forward(request, response);	
	}
}