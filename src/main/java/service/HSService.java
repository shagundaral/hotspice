package service;

import java.util.List;

import pojo.Customer;
import pojo.FoodFilters;
import pojo.FoodItem;
import pojo.Order;
import pojo.OrderFilters;

public interface HSService {
	
	List<FoodItem> search(FoodFilters filters,Customer customer);
	
	String order(FoodItem item, Customer customer);
	
	String order(FoodItem item, String orderId);
	
	String deleteOrder(FoodItem item, String orderId);

	Order book(String orderId);
	
	Order cancel(String orderId);
	
	List<Order> getOrders(String sortOrder, OrderFilters filters);

	List<String> getCategories();

	String addCategories(String Category);
	
	String addFoodItem(FoodItem foodItem);


}
