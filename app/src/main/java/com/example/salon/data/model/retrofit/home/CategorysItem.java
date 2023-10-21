package com.example.salon.data.model.retrofit.home;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CategorysItem{

	@SerializedName("image")
	private String image;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("salon")
	private List<SalonItem> salon;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSalon(List<SalonItem> salon){
		this.salon = salon;
	}

	public List<SalonItem> getSalon(){
		return salon;
	}

	@Override
 	public String toString(){
		return 
			"CategorysItem{" + 
			"image = '" + image + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",salon = '" + salon + '\'' + 
			"}";
		}
}