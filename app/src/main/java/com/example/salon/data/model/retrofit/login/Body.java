package com.example.salon.data.model.retrofit.login;

import com.google.gson.annotations.SerializedName;

public class Body{

	@SerializedName("password")
	private String password;

	@SerializedName("username")
	private String username;


	public Body(String password, String username) {
		this.password = password;
		this.username = username;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
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
			",username = '" + username + '\'' + 
			"}";
		}
}