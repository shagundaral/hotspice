package controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.GapContent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import pojo.Customer;
import pojo.FoodFilters;
import pojo.FoodItem;
import pojo.MenuResponse;
import pojo.Order;
import pojo.OrderFilters;
import service.HSService;
import serviceimpl.HSImpl;


/**
 * 
 * @author Shagun
 *
 */
@Controller
public class HSController {
	
	static HSService service;
	
	static{
		service = new HSImpl();
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	@ResponseBody
	MenuResponse getMenu(){
		
		MenuResponse menuresponse = new MenuResponse();
		List<FoodItem> foodItems = service.search(null, null);
		menuresponse.setMenu(foodItems);
		return menuresponse;
		
	}
	
	
	@RequestMapping(value = "view/menu", method = RequestMethod.GET)
	@ResponseBody
	ModelAndView getMenuView(){
		
		MenuResponse menuresponse = new MenuResponse();
		List<FoodItem> foodItems = service.search(null, null);
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
		
		Gson gson = new Gson();
		ModelAndView model = new ModelAndView();
		model.setViewName("hs_home");
		model.addObject("menu", gson.toJson(menuresponse));

		return model;
		
	}
	
	
	/**
	 * filter menu item by category
	 * 
	 * @return
	 */
	@RequestMapping(value = "/menu/{category}", method = RequestMethod.GET)
	@ResponseBody
	MenuResponse getMenu(@PathVariable String category){
		
		MenuResponse menuresponse = new MenuResponse();
		List<FoodItem> foodItems = service.search(null, null);
		menuresponse.setMenu(foodItems);
		return menuresponse;
		
	}
	
	/**
	 * add food item
	 * 
	 * @return
	 */
	@RequestMapping(value = "/menu/item", method = RequestMethod.POST)
	@ResponseBody
	String getMenu(@RequestBody FoodItem foodItem){
		foodItem.getCategory().add("All");
		service.addFoodItem(foodItem);
		return "SUCCESS";
		
	}
	
	
	
	/**
	 * returns all food items available
	 * 
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	List<FoodItem> search(){
		System.out.println("shagun");
		return service.search(null, null);
	}
	
	/**
	 * returns a filtered list of food items
	 * 
	 * @param filters
	 * @return
	 */
	@RequestMapping(value = "/search/filter", method = RequestMethod.GET)
	@ResponseBody
	List<FoodItem> search(@RequestBody FoodFilters filters){
		return service.search(filters, null);
	}
	
	/**
	 * personalized list of food items
	 * 
	 * @param filters
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/search/filter/customer", method = RequestMethod.GET)
	@ResponseBody
	List<FoodItem> search(@RequestBody FoodFilters filters,@RequestBody Customer customer){
		return service.search(filters, customer);
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
	 * get list of orders decorated through sorter &/or filter
	 * 
	 * @param sortOrder
	 * @param filters
	 * @return
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	@ResponseBody
	List<Order> getOrders(String sortOrder,@RequestBody OrderFilters filters){
		return service.getOrders(sortOrder, filters);
	}
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	@ResponseBody
	List<String> getCategories(){
		return service.getCategories();
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
