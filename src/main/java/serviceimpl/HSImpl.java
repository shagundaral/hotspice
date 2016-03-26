package serviceimpl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import pojo.Customer;
import pojo.FoodItem;
import pojo.Order;
import pojo.OrderStatus;
import pojo.OrdersResponse;
import pojo.PlaceOrderRequest;
import service.HSService;

public class HSImpl implements HSService {
	
	static HSHelper helper;
	
	static{
		helper = new HSHelper();
	}


	@Override
	public List<FoodItem> retrieveFoodItems() {
		
		List<FoodItem> foodItems = null;
		if(null!=helper){
			foodItems = helper.getFoodItems();
		}
		return foodItems;
	}

	@Override
	public Order cancel(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFoodItem(FoodItem foodItem) {
		if(null!=helper){
			helper.store(foodItem);
		}
	}

	@Override
	public List<Order> retrieveOrders(Customer customer) {
		List<Order> orders = null;
		if(null!=helper){
			if(null!=customer){
				orders = helper.getOrders(customer.getId());
			}else{
				orders = helper.getOrders(0);
			}
		}
		return orders;
	}

	/*@Override
	public String createOrder(Order order) {
		return helper.store(order);
	}*/

	@Override
	public String generateFoodCode() {
		return String.valueOf(helper.generateFoodCode());
	}

	//Will be used when implementing Cart functionality
	@Override
	public void addOrder(Order order) {
		helper.store(order);
		
	}

	@Override
	public Order createOrder(PlaceOrderRequest placeOrderRequest) {
		Order order = new Order();
		order.setOrderId("HS"+helper.generateOrderId());
		order.setAddress(placeOrderRequest.getCustomer().getAddress());
		order.setCity(placeOrderRequest.getCustomer().getCity());
		order.setCustomer(placeOrderRequest.getCustomer());
		order.setStatus(OrderStatus.CONFIRMED);
		
		List<FoodItem> foodItems = helper.getFoodItems();
		Iterator<FoodItem> dishIterator = foodItems.iterator();
		while(dishIterator.hasNext()){
			FoodItem dish = dishIterator.next();
			if(!placeOrderRequest.getFoodItemIds().contains(dish.getCode())){
				dishIterator.remove();
			}
		}
		
		order.setFoodItems(foodItems);
		//order.setTimeOfDelivery(new Date());
		order.setTimeOfOrder(new Date());
		
		double totalAmt = 0.0;
		for(FoodItem food : foodItems){
			totalAmt += food.getPrice();
		}
		
		order.setTotalAmount(totalAmt);
		
		helper.store(order);
		
		return order;
		
	}

	@Override
	public void updateOrderStatus(Order order) {
		helper.updateOrderStatus(order);
	}

	@Override
	public String createCustomer(Customer customer) {
		int id = helper.generateCustomerId();
		return null;
	}

	@Override
	public Customer updateCustoemr(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(String customerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> retrieveCustomer(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateFoodItem(FoodItem dish) {
		helper.updateFoodItem(dish);
		
	}

	@Override
	public Order retrieveOrder(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdersResponse getCustomerOrder(int customer) {
		List<Order> orders = helper.getOrders(customer);
		OrdersResponse orderResp = new OrdersResponse();
		orderResp.setOrders(orders);
		return orderResp;
	}

	@Override
	public Order getCustomerOrderById(int customer, String orderId) {
		Order order = helper.getOrder(customer, orderId);
		return order;
	}

}
