package com.example.salon.data.model.retrofit.allcategorysearch;

import com.google.gson.annotations.SerializedName;

public class ResponseItem{

	@SerializedName("name")
	private String name;

	public String getName(){
		return name;
	}
}