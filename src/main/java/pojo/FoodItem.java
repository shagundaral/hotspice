package pojo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;

//@Document(collection = "FoodItems")
public class FoodItem implements Serializable{
	
	@Override
	public String toString() {
		return "FoodItem [name=" + name + ", type=" + type + ", category="
				+ category + ", description=" + description + ", code=" + code
				+ ", available=" + available + ", price=" + price
				+ ", currency=" + currency + ", imagePath=" + imagePath
				+ ", locations=" + locations + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 123432345L;
	@Id
	private String id;
	private String name;
	private String type;
	private List<String> category;
	private String description;
	private String code;
	private boolean available;
	private double price;
	private String currency;
	private String imagePath;
	private List<String> locations;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public List<String> getLocations() {
		return locations;
	}
	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

}
