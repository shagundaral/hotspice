package pojo;

import java.io.Serializable;

public class FoodCode implements Serializable{

	private static final long serialVersionUID = 2837482371L;
	
	public FoodCode(int code) {
		this.code = code;
	}
	
	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
