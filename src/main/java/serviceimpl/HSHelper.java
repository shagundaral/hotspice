package serviceimpl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import pojo.Customer;
import pojo.FoodItem;
import pojo.Order;
import utilities.MongoHelper;

/**
 * 
 * DAO layer - interacts with Mongo DB
 * 
 * @author Shagun
 *
 */
public class HSHelper {
	
	static MongoHelper mongo;
	
	static{
		mongo = MongoHelper.getInstance();  
	}
	
	public List<FoodItem> getFoodItems(){
		List<FoodItem> storedFoodItems = mongo.getAllFoodItems(new Query(), FoodItem.class);
		/*if(null!=storedFoodItems && !storedFoodItems.isEmpty() && null!=filters && null!=filters.getFoodFilters() && !filters.getFoodFilters().isEmpty()){
			for(FoodFilter foodFilter : filters.getFoodFilters()){
				storedFoodItems = foodFilter.filter(storedFoodItems);
			}
		}*/
		return storedFoodItems;
	}
	
	public List<Order> getOrders(Customer customer){
		List<Order> storedOrders = mongo.getAllOrders(new Query(), Order.class);
		return storedOrders;
	}
	
	public void store(Object obj){
		mongo.save(obj);
	}
	
	public Integer generateFoodCode(){
		return mongo.getFoodCode();
	}
	
	public Integer generateOrderId(){
		return mongo.getOrderCode();
	}

	public void updateOrderStatus(Order order) {
		Query query = new Query();
		query.addCriteria(Criteria.where("orderId").is(order.getOrderId()));
		query.fields().include("orderId");
		
		Update update = new Update();
		update.set("status", order.getStatus());
		
		mongo.updateOrderStatus(query, update, Order.class);
				
	}
	

}
