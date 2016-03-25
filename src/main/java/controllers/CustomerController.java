package controllers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pojo.Customer;
import pojo.FoodItem;
import pojo.MenuResponse;
import pojo.Order;
import pojo.OrdersResponse;
import service.HSService;
import serviceimpl.HSImpl;

import com.google.gson.Gson;

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
		List<FoodItem> foodItems = service.search(null);
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
		List<FoodItem> foodItems = service.search(null);
		
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


	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseBody
	String placeOrder(@RequestBody Order order){
		return service.placeOrder(order);
		
	}
	
	/**
	 * generate new order for customer
	 * request type - post
	 * 
	 * @param item
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseBody
	String order(FoodItem item, Customer customer){
		return service.order(item, customer);
	}
	
	/**
	 * append food item in existing order
	 * request type - patch, delete
	 * 
	 * @param item
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/order", method = RequestMethod.PUT)
	@ResponseBody
	String order(FoodItem item, String orderId){
		return service.order(item, orderId);
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.DELETE)
	@ResponseBody
	String deleteOrder(FoodItem item, String orderId){
		return deleteOrder(item, orderId);
	}

	/**
	 * to make final booking
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	@ResponseBody
	Order book(String orderId){
		return service.book(orderId);
	}
	
	/**
	 * change order status to cancelled
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	@ResponseBody
	Order cancel(String orderId){
		return service.cancel(orderId);
	}
	
	/**
	 * add new category
	 * 
	 * @param Category
	 * @return
	 */
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	@ResponseBody
	String addCategories(String Category){
		return service.addCategories(Category);
	}
	
	
	/**
	 * 
	 * @param foodItem
	 * @return
	 */
	@RequestMapping(value = "/food", method = RequestMethod.GET)
	@ResponseBody
	String addFoodItem(FoodItem foodItem){
		return service.addFoodItem(foodItem);
	}
}
