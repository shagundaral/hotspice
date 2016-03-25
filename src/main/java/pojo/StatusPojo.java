package pojo;

import java.io.Serializable;

public class StatusPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 145798L;
	
	public StatusPojo(String status) {
		this.status = status;
	}
	
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
