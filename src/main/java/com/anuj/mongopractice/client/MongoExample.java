package com.anuj.mongopractice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anuj.mongopractice.constant.StringConstants;
import com.anuj.mongopractice.model.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class MongoExample {
	private static Logger logger = LoggerFactory.getLogger(MongoExample.class.getName());

	public static void main(String[] args) {
		User user = createUser();
		DBObject doc = createDBObject(user); 
		
		
		try(MongoClient mongo = new MongoClient("localhost", 27017)) {
			DB db = mongo.getDB("anujtest");
			DBCollection col = db.getCollection("users");
			
			//create user in mongo db
			WriteResult result = col.insert(doc);
			logger.info("======CREATE======");
			logger.info(StringConstants.ID, result.getUpsertedId());
			logger.info(StringConstants.AFFECTED, result.getN());
			logger.info(StringConstants.EXISTING, result.isUpdateOfExisting());
			
			//read example
			DBObject query = BasicDBObjectBuilder.start().add("_id", user.getId()).get();
			DBCursor cursor = col.find(query);
			logger.info("======READ======");
			while(cursor.hasNext()){
				logger.info("Result : {}", cursor.next());
			}
			
			//update example
			user.setName("Pankaj Kumar");
			doc = createDBObject(user);
			result = col.update(query, doc);
			logger.info("======UPDATE======");
			logger.info(StringConstants.ID, result.getUpsertedId());
			logger.info(StringConstants.AFFECTED, result.getN());
			logger.info(StringConstants.EXISTING, result.isUpdateOfExisting());
			
			//delete example
			result = col.remove(query);
			logger.info("======DELETE======");
			logger.info(StringConstants.ID, result.getUpsertedId());
			logger.info(StringConstants.AFFECTED, result.getN());
			logger.info(StringConstants.EXISTING, result.isUpdateOfExisting());
		}
		
	}


	private static DBObject createDBObject(User user) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		
		docBuilder.append("_id", user.getId());
		docBuilder.append("name", user.getName());
		docBuilder.append("role", user.getRole());
		docBuilder.append("isEmployee", user.isEmployee());
		return docBuilder.get();
	}


	private static User createUser() {
		User user = new User();
		user.setId(2);
		user.setName("Anuj");
		user.setEmployee(true);
		user.setRole("CEO");
		return user;
	}

}
