package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement(name = "emp")
public class Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 189828323L;
	private String name;
	private int id;
	private List<String> departments;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(String name, int id) {
		this.name = name;
		this.id = id;
		List<String> dep = new ArrayList<String>();
		dep.add("dep1");
		dep.add("dep2");
		this.departments = dep;
	}
	
	
	
	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIgnore
	public int getId() {
		return id;
	}
	
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getDepartments() {
		return departments;
	}
	
	@XmlElement
	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}
	

}
