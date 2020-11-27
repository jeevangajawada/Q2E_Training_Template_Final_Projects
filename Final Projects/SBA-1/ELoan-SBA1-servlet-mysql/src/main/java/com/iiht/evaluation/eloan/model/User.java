package com.iiht.evaluation.eloan.model;

public class User {
	
	private String username;
	private String password;
	private String EmailID;
	private String MobileNumber;
	
	public String getEmailID() {
		return EmailID;
	}
	public void setEmailID(String emailID) {
		EmailID = emailID;
	}
	public String getMobileNumber() {
		return MobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}
	public User() {
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(String username, String password, String emailID, String mobileNumber) {
		super();
		this.username = username;
		this.password = password;
		EmailID = emailID;
		MobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", EmailID=" + EmailID + ", MobileNumber="
				+ MobileNumber + "]";
	}
	

}
