package pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1982345678L;
	private Enum<OrderStatus> status;
	private Date timeOfOrder;
	private Date timeOfDelivery;
	private double totalAmount;
	private List<FoodItem> foodItems;
	private String city;
	private String address;
	private String orderId;
	private Customer customer;
	public Enum<OrderStatus> getStatus() {
		return status;
	}
	public void setStatus(Enum<OrderStatus> status) {
		this.status = status;
	}
	public Date getTimeOfOrder() {
		return timeOfOrder;
	}
	public void setTimeOfOrder(Date timeOfOrder) {
		this.timeOfOrder = timeOfOrder;
	}
	public Date getTimeOfDelivery() {
		return timeOfDelivery;
	}
	public void setTimeOfDelivery(Date timeOfDelivery) {
		this.timeOfDelivery = timeOfDelivery;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<FoodItem> getFoodItems() {
		return foodItems;
	}
	public void setFoodItems(List<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
