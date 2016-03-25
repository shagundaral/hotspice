package serviceimpl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

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
	
	public List<FoodItem> getFoodItems(Customer customer){
		//customer will be used for search
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
	
	public String store(Object obj){
		mongo.save(obj);
		return "SUCCESS";
	}
	
	public Integer generateFoodCode(){
		int code = 1000;
		
		code = mongo.getFoodCode();
		
		return code;
	}
	

}
