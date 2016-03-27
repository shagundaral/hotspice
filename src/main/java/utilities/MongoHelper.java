package utilities;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import pojo.CustomerId;
import pojo.FoodCode;
import pojo.FoodItem;
import pojo.Order;
import pojo.OrderIdNode;

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
	
	public List<Order> getAllOrders(Query query, Class<Order> class1) {
		List<Order> orders = mongoOperation.find(query, class1);
		//mongoOperation.remove(new Query(), Order.class);
		return orders;
		
	}
	
	public int getFoodCode() {
		synchronized (this) {
			FoodCode code = mongoOperation.findOne(new Query(), FoodCode.class);
			if(null!=code){
				Query query = new Query();
				Update update = new Update();
				update.set("code", code.getCode()+1);
				mongoOperation.updateFirst(query, update, FoodCode.class);
				return code.getCode()+1;
			}else{
				//store new code
				save(new FoodCode(1001));
			}
		}
		return 1001;
	}

	public Integer getOrderCode() {
		synchronized (this) {
			OrderIdNode code = mongoOperation.findOne(new Query(), OrderIdNode.class);
			if(null!=code){
				Query query = new Query();
				Update update = new Update();
				update.set("code", code.getCode()+1);
				mongoOperation.updateFirst(query, update, OrderIdNode.class);
				return code.getCode()+1;
			}else{
				//store new code
				save(new OrderIdNode(1));
			}
		}
		return 1;
	}

	public void updateOrderStatus(Query query, Update update,
			Class<Order> class1) {
		
		mongoOperation.updateFirst(query, update, class1);
		
	}

	public void updateFoodItem(Query query, Update update,
			Class<FoodItem> class1) {
		mongoOperation.updateFirst(query, update, class1);
		
	}

	public int getCustomerId() {
		synchronized (this) {
			CustomerId code = mongoOperation.findOne(new Query(), CustomerId.class);
			if(null!=code){
				Query query = new Query();
				Update update = new Update();
				update.set("code", code.getCode()+1);
				mongoOperation.updateFirst(query, update, CustomerId.class);
				return code.getCode()+1;
			}else{
				//store new code
				save(new OrderIdNode(1));
			}
		}
		return 1;
	}

	public Order getCustomerOrder(Query query, Class<Order> class1) {
		Order order = mongoOperation.findOne(query, class1);
		return order;
	}
	

}
