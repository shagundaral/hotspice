package serviceimpl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import pojo.Customer;
import pojo.FoodFilters;
import pojo.FoodItem;
import service.FoodFilter;
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
	
	public List<FoodItem> search(FoodFilters filters, Customer customer){
		//customer will be used for search
		List<FoodItem> storedFoodItems = mongo.getAllFoodItems(new Query(), FoodItem.class);
		if(null!=storedFoodItems && !storedFoodItems.isEmpty() && null!=filters && null!=filters.getFoodFilters() && !filters.getFoodFilters().isEmpty()){
			for(FoodFilter foodFilter : filters.getFoodFilters()){
				storedFoodItems = foodFilter.filter(storedFoodItems);
			}
		}
		return storedFoodItems;
	}
	
	public String store(Object obj){
		mongo.save(obj);
		return "SUCCESS";
	}
	

}
