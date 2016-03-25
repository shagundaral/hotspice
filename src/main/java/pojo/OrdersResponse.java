package pojo;

import java.io.Serializable;
import java.util.List;

public class OrdersResponse implements Serializable{
	
	private static final long serialVersionUID = 137243465445L;
	
	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
