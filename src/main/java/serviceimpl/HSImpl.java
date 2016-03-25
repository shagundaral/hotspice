package serviceimpl;

import java.util.List;

import pojo.Customer;
import pojo.FoodItem;
import pojo.Order;
import service.HSService;

public class HSImpl implements HSService {
	
	static HSHelper helper;
	
	static{
		helper = new HSHelper();
	}


	@Override
	public List<FoodItem> search(Customer customer) {
		
		List<FoodItem> foodItems = null;
		if(null!=helper){
			foodItems = helper.getFoodItems(customer);
		}
		return foodItems;
	}

	@Override
	public String order(FoodItem item, Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String order(FoodItem item, String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order book(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order cancel(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteOrder(FoodItem item, String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addCategories(String Category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addFoodItem(FoodItem foodItem) {
		String status = "FAILED";
		if(null!=helper){
			status = helper.store(foodItem);
		}
		return status;
	}

	@Override
	public List<Order> getAllOrders(Customer customer) {
		List<Order> orders = null;
		if(null!=helper){
			orders = helper.getOrders(customer);
		}
		return orders;
	}

	@Override
	public String placeOrder(Order order) {
		return helper.store(order);
	}

	@Override
	public String generateFoodCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
