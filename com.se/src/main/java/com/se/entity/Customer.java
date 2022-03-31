/**
 * 
 */
package com.se.entity;

/**
 * @author 48 - Tran Trung Vinh - 18079461
 *
 */
public class Customer {
	private int id;
	private String age;
	private String email;
	private String first_name;
	private String last_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", age=" + age + ", email=" + email + ", first_name=" + first_name
				+ ", last_name=" + last_name + "]";
	}
	public Customer(int id, String age, String email, String first_name, String last_name) {
		super();
		this.id = id;
		this.age = age;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String age, String email, String first_name, String last_name) {
		super();
		this.age = age;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	
	

}
