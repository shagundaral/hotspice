package serviceimpl;

import java.util.List;

import pojo.Customer;
import pojo.FoodFilters;
import pojo.FoodItem;
import pojo.Order;
import pojo.OrderFilters;
import service.HSService;

public class HSImpl implements HSService {
	
	static HSHelper helper;
	
	static{
		helper = new HSHelper();
	}


	@Override
	public List<FoodItem> search(FoodFilters filters, Customer customer) {
		
		List<FoodItem> foodItems = null;
		if(null!=helper){
			foodItems = helper.search(filters, customer);
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
	public List<Order> getOrders(String sortOrder, OrderFilters filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCategories() {
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

}
