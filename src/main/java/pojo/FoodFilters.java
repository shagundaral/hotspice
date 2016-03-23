package pojo;

import java.io.Serializable;
import java.util.List;

import service.FoodFilter;

public class FoodFilters implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 134578753L;
	private List<FoodFilter> foodFilters;

	public List<FoodFilter> getFoodFilters() {
		return foodFilters;
	}

	public void setFoodFilters(List<FoodFilter> foodFilters) {
		this.foodFilters = foodFilters;
	}

}
