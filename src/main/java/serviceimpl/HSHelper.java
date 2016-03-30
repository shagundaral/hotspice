package serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import pojo.Customer;
import pojo.FoodItem;
import pojo.Order;
import pojo.OrderStatus;
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
	
	public List<Order> getOrders(int  customerid){
		Query query = new Query();
		if(0!=customerid){
			query.addCriteria(Criteria.where("customer.id").is(customerid));
		}
		List<Order> storedOrders = mongo.getAllOrders(query, Order.class);
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
		if(order.getStatus().equals(OrderStatus.DELIVERED)){
			update.set("timeOfDelivery", new Date());
		}
		
		mongo.updateOrderStatus(query, update, Order.class);
				
	}

	public void updateFoodItem(FoodItem dish) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(dish.getCode()));
		query.fields().include("code");
		
		Update update = new Update();
		update.set("name", dish.getName());
		update.set("category", dish.getCategory());
		update.set("currency", dish.getCurrency());
		update.set("description", dish.getDescription());
		update.set("available", dish.isAvailable());
		update.set("price", dish.getPrice());
		update.set("imagePath", dish.getImagePath());
		update.set("locations", dish.getLocations());
		
		mongo.updateFoodItem(query, update, FoodItem.class);
	}

	public int generateCustomerId() {
		return mongo.getCustomerId();
	}

	public Order getOrder(int customer, String orderId) {
		Query query = new Query();
		if(0!=customer && null!=orderId){
			query.addCriteria(Criteria.where("customer.id").is(customer));
			query.addCriteria(Criteria.where("orderId").is(orderId));
		}
		Order order = mongo.getCustomerOrder(query, Order.class);
		return order;
	}
	
	public String deleteItem(Object object){
		
		return mongo.removeObject(object);
		
	}

	public List<Customer> getCustomers(int customerId) {
		Query query = new Query();
		if(customerId>0){
			query.addCriteria(Criteria.where("id").is(customerId));
		}
		return mongo.getCustomers(query, Customer.class);
	}

	public List<Order> getPaginatedOrders(int pageNumber) {
		return mongo.getPaginatedOrders(pageNumber);
		
	}
	

}
