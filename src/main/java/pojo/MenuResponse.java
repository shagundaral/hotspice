package pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class MenuResponse implements Serializable{
	
	private static final long serialVersionUID = 1378765445L;
	
	private List<FoodItem> menu;
	private Set<String> categories;

	public List<FoodItem> getMenu() {
		return menu;
	}

	public void setMenu(List<FoodItem> menu) {
		this.menu = menu;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

}
