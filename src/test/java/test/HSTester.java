package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import pojo.Customer;
import pojo.FoodItem;
import pojo.Order;
import service.HSService;
import serviceimpl.HSImpl;
import controllers.HSController;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class HSTester {

	@Autowired
    private HSService hotspiceHelper;

	@Test
    public void testHSService() {
        assertEquals("class serviceimpl.HSImpl", this.hotspiceHelper.getClass().toString());
    }
	
	/**
	 * test case covering CRUD operations on food item
	 */
	@Test
	public void testFoodItem(){
		FoodItem foodItem = new FoodItem();
		foodItem.setAvailable(true);
		foodItem.setCategory(new ArrayList<String>());
		foodItem.setCode("123");
		foodItem.setCurrency("INR");
		foodItem.setDescription("test food Item");
		foodItem.setLocations(new ArrayList<String>());
		foodItem.setName("test food");
		foodItem.setPrice(999);
		foodItem.setType("VEG");
		
		hotspiceHelper.addFoodItem(foodItem);
		List<FoodItem> foodItems = hotspiceHelper.retrieveFoodItems();
		assertTrue(isFoodItemInResp(foodItem, foodItems));
		assertEquals("1",hotspiceHelper.removeItem(foodItem));
		
	}

	private boolean isFoodItemInResp(FoodItem foodItem, List<FoodItem> foodItems) {
		
		for (FoodItem foodItem2 : foodItems) {
			if(foodItem2.getName().equalsIgnoreCase(foodItem.getName()) && foodItem2.getCode().equalsIgnoreCase(foodItem.getCode())){
				return true;
			}
		}
		return false;
	}
	
	@Test
	public void testOrders(){
		
		Order order = new Order();
		Customer customer = new Customer();
		order.setCustomer(customer);
		order.setCity("Delhi");
		order.setAddress("address");
		order.setFoodItems(new ArrayList<FoodItem>());
		order.setOrderId("orderid");
		order.setStatus(null);
		order.setTimeOfDelivery(null);
		order.setTimeOfOrder(null);
		order.setTotalAmount(222);
		
		hotspiceHelper.addOrder(order);
		List<Order> orders = hotspiceHelper.retrieveOrders(null);
		assertTrue(isOrderPresentInResp(order, orders));
		assertEquals("1",hotspiceHelper.removeItem(order));
	}

	private boolean isOrderPresentInResp(Order order, List<Order> orders) {
		for (Order order2 : orders) {
			if(order2.getOrderId().equalsIgnoreCase(order.getOrderId()) && order2.getAddress().equalsIgnoreCase(order.getAddress())){
				return true;
			}
		}
		return false;
	}
	
	
}
