package utilities;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import pojo.FoodItem;

public class MongoHelper {
	
	MongoOperations mongoOperation = null;
	static MongoHelper mongoHelper;
	
	private MongoHelper(){
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
		mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
	}
	
	public static MongoHelper getInstance(){
		if(null==mongoHelper){
			synchronized (MongoHelper.class) {
				if(null==mongoHelper){
					mongoHelper = new MongoHelper();
				}
			}
		}
		return mongoHelper;
	}
	
	public void save(Object obj){
		if(null!=mongoOperation){
			mongoOperation.save(obj);
		}
	}

	public List<FoodItem> getAllFoodItems(Query query, Class<FoodItem> class1) {
		//return null;
		
		List<FoodItem> foodItems = mongoOperation.find(query, class1);
		return foodItems;
		
	}
	

}
