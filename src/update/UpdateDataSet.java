package update;

import org.bson.Document;
import com.mongodb.client.MongoCollection;
import utility.Connection;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class UpdateDataSet {

	public static void main(String[] args) 
	{
		Connection c = new Connection("test","farma");
		
		MongoCollection<Document> coll = c.getColl();
		
		coll.updateMany(or(eq("DESCRIZIONEREGIONE","PROV. AUTON. BOLZANO"),eq("DESCRIZIONEREGIONE","PROV. AUTON. TRENTO")
				,eq("DESCRIZIONEREGIONE","Prov. auton. bolzano"),eq("DESCRIZIONEREGIONE","Prov. auton. trento")),
				combine(set("DESCRIZIONEREGIONE","Trentino-Alto Adige")));
		
		coll.updateMany(eq("DESCRIZIONEREGIONE",150),combine(
				set("DESCRIZIONECOMUNE","Bellizzi"),set("DESCRIZIONEREGIONE","Campania"),set("DESCRIZIONEPROVINCIA","Salerno")));
		
		coll.updateMany(eq("DESCRIZIONEREGIONE",190),combine(
				set("DESCRIZIONECOMUNE","Adrano"),set("DESCRIZIONEREGIONE","Sicilia"),set("DESCRIZIONEPROVINCIA","Catania")));
		
		coll.updateMany(or(eq("DESCRIZIONEREGIONE","Valle d`aosta"),eq("DESCRIZIONEREGIONE","VALLE D`AOSTA")),
				combine(set("DESCRIZIONEREGIONE","Valle d'Aosta")));
		
		coll.updateMany(or(eq("DESCRIZIONEREGIONE","FRIULI VENEZIA GIULIA"),eq("DESCRIZIONEREGIONE","Friuli venezia giulia")),
				combine(set("DESCRIZIONEREGIONE","Friuli-Venezia Giulia")));
		
		coll.updateMany(or(eq("DESCRIZIONEREGIONE","EMILIA ROMAGNA"),eq("DESCRIZIONEREGIONE","Emilia romagna")),
				combine(set("DESCRIZIONEREGIONE","Emilia-Romagna")));
		
		coll.updateMany(eq("LATITUDINE",null),
				combine(set("LATITUDINE",0.0)));
		
		coll.updateMany(eq("LATITUDINE","31/10/2014"),
				combine(set("LATITUDINE",0.0)));
		
		c = new Connection("test","comuni");
		
		coll = c.getColl();
		
		coll.updateMany(and(eq("REGIONE","Emilia-Romagna"),eq("PROVINCIA","-Reggio nell'Emilia")),
				combine(set("PROVINCIA","-Reggio Emilia")));
		
		coll.updateMany(and(eq("REGIONE","Emilia-Romagna"),eq("PROVINCIA","-Forlì-Cesena")),
				combine(set("PROVINCIA","-Forlì Cesena")));
		
		coll.updateMany(and(eq("REGIONE","Calabria"),eq("PROVINCIA","-Reggio di Calabria")),
				combine(set("PROVINCIA","-Reggio Calabria")));
		
		coll.updateMany(and(eq("REGIONE","Lombardia"),eq("PROVINCIA","-Monza e della Brianza")),
				combine(set("PROVINCIA","-Monza")));
		
		coll.updateMany(and(eq("REGIONE","Toscana"),eq("PROVINCIA","-Massa-Carrara")),
				combine(set("PROVINCIA","-Massa Carrara")));
		
		coll.updateMany(and(eq("REGIONE","Puglia"),eq("PROVINCIA","-Barletta-Andria-Trani")),
				combine(set("PROVINCIA","-Barletta Andria Trani")));
		
		coll.updateMany(and(eq("REGIONE","Sardegna"),eq("PROVINCIA","-Olbia-Tempio")),
				combine(set("PROVINCIA","-Olbia Tempio")));
		
		coll.updateMany(and(eq("REGIONE","Sardegna"),eq("PROVINCIA","-Carbonia-Iglesias")),
				combine(set("PROVINCIA","-Carbonia Iglesias")));
		
		coll.updateMany(eq("REGIONE","Valle d'Aosta/Vallée d'Aoste"),
				combine(set("REGIONE","Valle d'Aosta")));
		
		coll.updateMany(eq("REGIONE","Valle d'Aosta"),
				combine(set("PROVINCIA","-Aosta")));
		
		coll.updateMany(eq("REGIONE","Trentino-Alto Adige/Südtirol"),
				combine(set("REGIONE","Trentino-Alto Adige")));
		
		coll.updateMany(eq("PROVINCIA","-;Bolzano/Bozen"),
				combine(set("PROVINCIA","-;Bolzano")));
	}
}
