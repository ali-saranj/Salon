package com.example.salon.data.model.retrofit.regester;

import com.google.gson.annotations.SerializedName;

public class Body{

	@SerializedName("password")
	private String password;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("username")
	private String username;

	public Body(String password, String phone, String name, String username) {
		this.password = password;
		this.phone = phone;
		this.name = name;
		this.username = username;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"Body{" + 
			"password = '" + password + '\'' + 
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}