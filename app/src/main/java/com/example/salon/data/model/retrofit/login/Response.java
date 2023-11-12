package com.example.salon.data.model.retrofit.login;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("statos")
	private String statos;

	public void setStatos(String statos){
		this.statos = statos;
	}

	public String getStatos(){
		return statos;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"statos = '" + statos + '\'' + 
			"}";
		}
}