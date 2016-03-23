package service;

import java.util.List;

import pojo.Order;

public interface OrderFilter {
	
	List<Order> filter(List<Order> orders);

}
