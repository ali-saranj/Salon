package com.example.salon.data.model.retrofit.regester;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("statos")
	private String statos;

	public String getStatos(){
		return statos;
	}
}