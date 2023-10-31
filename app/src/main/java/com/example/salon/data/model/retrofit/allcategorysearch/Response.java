package com.example.salon.data.model.retrofit.allcategorysearch;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("Response")
	private List<ResponseItem> response;

	public List<ResponseItem> getResponse(){
		return response;
	}
}