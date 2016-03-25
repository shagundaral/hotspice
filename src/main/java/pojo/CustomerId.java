package pojo;

import java.io.Serializable;

public class CustomerId implements Serializable{

	private static final long serialVersionUID = 2837754371L;
	
	public CustomerId(int code) {
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
