package pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class OrdersResponse implements Serializable{
	
	private static final long serialVersionUID = 137243465445L;
	
	private List<Order> orders;
	private Map<String, Integer> orderByStatusCount;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Map<String, Integer> getOrderByStatusCount() {
		return orderByStatusCount;
	}

	public void setOrderByStatusCount(Map<String, Integer> orderByStatusCount) {
		this.orderByStatusCount = orderByStatusCount;
	}

}
