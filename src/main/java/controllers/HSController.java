package controllers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
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


/**
 * 
 * @author Shagun
 *
 */
@Controller
public class HSController {
	
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
	
	
	@RequestMapping(value = "view/menu", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView getMenuView(){
		
		MenuResponse menuresponse = new MenuResponse();
		List<FoodItem> foodItems = service.search(null);
		menuresponse.setMenu(foodItems);
		
		Set<String> categories = new HashSet<String>();
		if(null!=foodItems && !foodItems.isEmpty()){
			for(FoodItem dish : foodItems){
				for(String category : dish.getCategory()){
					categories.add(category);
				}
			}
		}
		categories.add("All");
		menuresponse.setCategories(categories);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("hs_home");
		model.addObject("menu", gson.toJson(menuresponse));

		return model;
		
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

	/**
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "view/menu/{category}", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView getMenuView(@PathVariable String category) {

		MenuResponse menuresponse = new MenuResponse();
		List<FoodItem> foodItems = service.search(null);
		

		Set<String> categories = new HashSet<String>();
		Iterator<FoodItem> foodItemsIterator = foodItems.iterator();

		if(foodItemsIterator.hasNext()){
			FoodItem foodItem = foodItemsIterator.next();
			for(String categ : foodItem.getCategory()){
				categories.add(categ);
			}
			if(!category.equalsIgnoreCase("All") && !foodItem.getCategory().contains(category)){
				foodItemsIterator.remove();
			}
			
		}
		menuresponse.setMenu(foodItems);
		categories.add("All");
		menuresponse.setCategories(categories);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("hs_home");
		model.addObject("menu", gson.toJson(menuresponse));

		return model;
		
	}

	/**
	 * add food item
	 * 
	 * @return
	 */
	@RequestMapping(value = "/menu/item", method = RequestMethod.POST)
	@ResponseBody
	String getMenu(@RequestBody FoodItem foodItem){
		//set auto generated code
		service.addFoodItem(foodItem);
		return "SUCCESS";
		
	}
	
	/**
	 * view all orders
	 * 
	 * @return
	 */
	@RequestMapping(value = "/view/orders", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView getAllOrders(){
		List<Order> orders = service.getAllOrders(null);
		OrdersResponse orderResp = new OrdersResponse();
		orderResp.setOrders(orders);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("hs_home");
		model.addObject("orders", gson.toJson(orderResp));
		
		return model;
	}
	
	
	/**
	 * get all orders
	 * 
	 * @return
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	@ResponseBody
	OrdersResponse getOrders(){
		List<Order> orders = service.getAllOrders(null);
		OrdersResponse orderResp = new OrdersResponse();
		orderResp.setOrders(orders);
		return orderResp;
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
