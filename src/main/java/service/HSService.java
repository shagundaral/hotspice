package service;

import java.util.List;

import pojo.Customer;
import pojo.FoodItem;
import pojo.Order;

public interface HSService {
	
	/** for customer **/
	String order(FoodItem item, String orderId);
	
	Order book(String orderId);
	
	String placeOrder(Order order);
	
	/** for operations team **/
	
	String addCategories(String Category);
	
	List<Order> getAllOrders(Customer customer);
	
	String addFoodItem(FoodItem foodItem);
	
	String generateFoodCode();
	
	/** for both **/
	List<FoodItem> search(Customer customer);
	
	String order(FoodItem item, Customer customer);
	
	String deleteOrder(FoodItem item, String orderId);

	Order cancel(String orderId);

}
