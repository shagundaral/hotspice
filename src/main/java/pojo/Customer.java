package pojo;

import java.io.Serializable;

public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 13498765L;
	private String emailId;
	private String phoneNumber;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
