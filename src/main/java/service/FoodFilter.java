package service;

import java.util.List;

import pojo.FoodItem;

public interface FoodFilter {
	
	public List<FoodItem> filter(List<FoodItem> foodItems);

}
