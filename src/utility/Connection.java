package utility;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connection
{
	private MongoClient client;
	private MongoDatabase db;
	private MongoCollection<Document> coll;
	
	public Connection(String db,String co) 
	{
		this.client = new MongoClient("localhost",27017);
		this.db = this.client.getDatabase(db);
		this.coll = this.db.getCollection(co);
	}

	public MongoClient getClient() {
		return client;
	}

	public void setClient(MongoClient client) {
		this.client = client;
	}

	public MongoDatabase getDb() {
		return db;
	}

	public void setDb(MongoDatabase db) {
		this.db = db;
	}

	public MongoCollection<Document> getColl() {
		return coll;
	}

	public void setColl(MongoCollection<Document> coll) {
		this.coll = coll;
	}
}