package controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

	
	
	@RequestMapping(value = "view/menu", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView getMenuView(){
		
		MenuResponse menuresponse = new MenuResponse();
		List<FoodItem> foodItems = service.retrieveFoodItems();
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
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "view/menu/{category}", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView getMenuView(@PathVariable String category) {

		MenuResponse menuresponse = new MenuResponse();
		List<FoodItem> foodItems = service.retrieveFoodItems();
		

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
	void addFood(@RequestBody FoodItem foodItem){
		//set auto generated code
		service.addFoodItem(foodItem);
	}
	
	/**
	 * view all orders
	 * 
	 * @return
	 */
	@RequestMapping(value = "/view/orders", method = RequestMethod.GET)
	@ResponseBody
	String getAllOrders(){
		List<Order> orders = service.retrieveOrders(null);
		Map<String, Integer> orderStatusCount = new HashMap<String, Integer>();
		for (Order order : orders) {
			if(orderStatusCount.containsKey(order.getStatus())){
				orderStatusCount.put(String.valueOf(order.getStatus()), orderStatusCount.get(order.getStatus())+1);
			}else{
				orderStatusCount.put(String.valueOf(order.getStatus()), 1);
			}
		}
		
		OrdersResponse orderResp = new OrdersResponse();
		orderResp.setOrders(orders);
		orderResp.setOrderByStatusCount(orderStatusCount);
		
		return gson.toJson(orderResp);
	}
	
	@RequestMapping(value = "/order/status", method = RequestMethod.POST)
	@ResponseBody
	void updateOrderStatus(@RequestBody Order order){
		service.updateOrderStatus(order);
	}
	
	
	/**
	 * get all order's json
	 * used only for developer
	 * 
	 * @return
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	@ResponseBody
	OrdersResponse getOrders(){
		List<Order> orders = service.retrieveOrders(null);
		OrdersResponse orderResp = new OrdersResponse();
		orderResp.setOrders(orders);
		return orderResp;
	}
	
	/**
	 * not used in any ui screen
	 * only for testing
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/storeOrder", method = RequestMethod.POST)
	@ResponseBody
	String storeOrder(@RequestBody Order order){
		service.addOrder(order);
		return null;
	}
	
}
