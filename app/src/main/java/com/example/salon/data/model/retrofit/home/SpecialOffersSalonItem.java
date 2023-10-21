package com.example.salon.data.model.retrofit.home;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SpecialOffersSalonItem{

	@SerializedName("images")
	private List<ImagesItem> images;

	@SerializedName("address")
	private String address;

	@SerializedName("phone")
	private String phone;

	@SerializedName("isSpecial")
	private boolean isSpecial;

	@SerializedName("person")
	private List<PersonItem> person;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("location")
	private String location;

	@SerializedName("id")
	private int id;

	public void setImages(List<ImagesItem> images){
		this.images = images;
	}

	public List<ImagesItem> getImages(){
		return images;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setIsSpecial(boolean isSpecial){
		this.isSpecial = isSpecial;
	}

	public boolean isIsSpecial(){
		return isSpecial;
	}

	public void setPerson(List<PersonItem> person){
		this.person = person;
	}

	public List<PersonItem> getPerson(){
		return person;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"SpecialOffersSalonItem{" + 
			"images = '" + images + '\'' + 
			",address = '" + address + '\'' + 
			",phone = '" + phone + '\'' + 
			",isSpecial = '" + isSpecial + '\'' + 
			",person = '" + person + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",location = '" + location + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}