package pojo;

import java.io.Serializable;
import java.util.List;

import service.OrderFilter;

public class OrderFilters implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 123243556L;
	private List<OrderFilter> orders;

	public List<OrderFilter> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderFilter> orders) {
		this.orders = orders;
	}

}
