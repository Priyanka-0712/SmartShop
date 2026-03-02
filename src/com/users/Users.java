package com.users;

public class Users {

	private int user_id;
	private String fisrst_name;
	private String last_name;
	private String username;
	private String password;
	private String city;
	private String email;
	private String mobile;
	private String role;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFisrst_name() {
		return fisrst_name;
	}
	public void setFisrst_name(String fisrst_name) {
		this.fisrst_name = fisrst_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public Users()
	{
		
	}
	
	public Users(int user_id, String fisrst_name, String last_name, String username, String password, String city,
			String email, String mobile, String role) {
		super();
		this.user_id = user_id;
		this.fisrst_name = fisrst_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.city = city;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
	}
	
	
}
