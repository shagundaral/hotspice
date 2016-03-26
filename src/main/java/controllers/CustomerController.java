package controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.Customer;
import pojo.FoodItem;
import pojo.MenuResponse;
import pojo.Order;
import pojo.OrdersResponse;
import pojo.PlaceOrderRequest;
import service.HSService;
import serviceimpl.HSImpl;

import com.google.gson.Gson;

@Controller
public class CustomerController {
	
	static HSService service;
	static Gson gson = null;
	
	static{
		service = new HSImpl();
		gson = new Gson();
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	@ResponseBody
	MenuResponse getMenu(){
		
		
		
		MenuResponse menuresponse = new MenuResponse();
		List<FoodItem> foodItems = service.retrieveFoodItems();
		
		/*OrdersResponse orderResp = new OrdersResponse();
		
		Order order = new Order();
		order.setOrderId("order 1");
		order.setAddress("312/32 gurgaon");
		order.setCity("Gurgaon");
		Customer customer = new Customer();
		customer.setEmailId("shagun.daral@gmail.com");
		customer.setPhoneNumber("9818578118");
		order.setCustomer(customer);
		order.setStatus(OrderStatus.CONFIRMED);
		order.setFoodItems(foodItems);
		order.setTimeOfDelivery(new Date());
		order.setTimeOfOrder(new Date());
		order.setTotalAmount(500);
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		orderResp.setOrders(orders);*/
		
		/*Customer customer = new Customer();
		customer.setEmailId("shagun.daral@gmail.com");
		customer.setPhoneNumber("9818578118");
		customer.setAddress("312/32 gurgaon");
		customer.setCity("Gurgaon");
		customer.setName("Shagun Daral");
		PlaceOrderRequest req = new PlaceOrderRequest();
		req.setCustomer(customer);
		List<String> foodIds = new ArrayList<String>();
		for(FoodItem food : foodItems){
			foodIds.add(food.getCode());
		}
		req.setFoodItemIds(foodIds);
		
		System.out.println(gson.toJson(req));*/
		
		menuresponse.setMenu(foodItems);
		return menuresponse;
		
	}
		
	
	/**
	 * filter menu item by category
	 * for the app
	 * 
	 * @return
	 */
	@RequestMapping(value = "/menu/{category}", method = RequestMethod.GET)
	@ResponseBody
	MenuResponse getMenu(@PathVariable String category){
		
		MenuResponse menuresponse = new MenuResponse();
		List<FoodItem> foodItems = service.retrieveFoodItems();
		
		Iterator<FoodItem> foodItemsIterator = foodItems.iterator();

		if(foodItemsIterator.hasNext()){
			FoodItem foodItem = foodItemsIterator.next();
			if(!category.equalsIgnoreCase("All") && !foodItem.getCategory().contains(category)){
				foodItemsIterator.remove();
			}
		}
		
		menuresponse.setMenu(foodItems);
		return menuresponse;
		
	}

	/**
	 * 
	 * @param placeOrderRequest
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/customer/{customer}", method = RequestMethod.POST)
	@ResponseBody
	Order placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest, @PathVariable int customer){
		return service.createOrder(placeOrderRequest);
	}
	
	/**
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	@ResponseBody
	String addCustomer(@RequestBody Customer customer){
		return service.createCustomer(customer);
	}
	
	/**
	 * 
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/customer/{customer}/orders", method = RequestMethod.GET)
	@ResponseBody
	OrdersResponse getCustomerOrders(@PathVariable int customer){
		return service.getCustomerOrder(customer);
	}
	
	/**
	 * 
	 * @param customer
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/customer/{customer}/orders/{orderId}", method = RequestMethod.GET)
	@ResponseBody
	Order getCustomerOrders(@PathVariable int customer, @PathVariable String orderId){
		return service.getCustomerOrderById(customer, orderId);
	}
	
	
}
