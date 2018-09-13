package com.mingli.kafka.pojo;

public class User {
	private String firstName;
	private String lastName;
	private int age;
	private String address;
	
	public User() {
		super();
	}
	public User(String firstName, String lastName, int age, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
	}
	
	/**
	 * @time 2018-09-13 3:52:42 PM
	 * @Title getTimeStamp   
	 * @Description 注意这个方法，将timeStamp映射为属性，值为返回的值  
	 * @return String 
	 */
	public String getTimeStamp() {
		return System.currentTimeMillis()+"";
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
//	@Override
//	public String toString() {
//		return "User [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", address=" + address
//				+ "]";
//	}
	@Override
	public String toString() {
		return firstName + "," + lastName + "," + age + "," + address;
		
	}
	
}	
