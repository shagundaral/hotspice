package pojo;

import java.util.List;

public class PlaceOrderRequest {

	private List<String> foodItemIds;
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<String> getFoodItemIds() {
		return foodItemIds;
	}
	public void setFoodItemIds(List<String> foodItemIds) {
		this.foodItemIds = foodItemIds;
	}
	
}
