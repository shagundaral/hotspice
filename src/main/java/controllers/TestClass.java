package controllers;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import pojo.FoodItem;
import utilities.MongoConfig;

public class TestClass {
	
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
		
		mongoOperation.save(new FoodItem());
		Query searchUserQuery = new Query(Criteria.where("name").is("chicken"));
		List<FoodItem> savedUser = mongoOperation.find(searchUserQuery, FoodItem.class);
		System.out.println("2. find - savedUser : " + savedUser);
		
	}

}
