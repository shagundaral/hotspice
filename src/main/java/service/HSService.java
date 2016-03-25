package service;

import java.util.List;

import pojo.Customer;
import pojo.FoodItem;
import pojo.Order;
import pojo.PlaceOrderRequest;

/**
 * 
 * defining all CRUD operations on all out data objects
 * 
 * @author MMT3760
 *
 */
public interface HSService {
	
	//String order(FoodItem item, String orderId);
	
	//Order book(String orderId);
	
	//String createOrder(Order order);
	
	//ORDER
	Order createOrder(PlaceOrderRequest placeOrderRequest);
	List<Order> retrieveOrders(Customer customer);
	Order retrieveOrder(String orderId);
	void updateOrderStatus(Order order);
	Order cancel(String orderId);
	
	//FOOD
	List<FoodItem> retrieveFoodItems();
	void addFoodItem(FoodItem foodItem);
	void updateFoodItem(FoodItem dish);
	
	//CUSTOMER
	String createCustomer(Customer customer);
	List<Customer> retrieveCustomer(int customerId);
	Customer updateCustoemr(Customer customer);
	void deleteCustomer(String customerId);
	
	
	String generateFoodCode();
	
	
	//will be used for cart functionality
	void addOrder(Order order);

	


	

}
