package com.example.salon.data.model.retrofit.home;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("categorys")
	private List<CategorysItem> categorys;

	@SerializedName("posts")
	private List<PostsItem> posts;

	@SerializedName("specialOffersSalon")
	private List<SpecialOffersSalonItem> specialOffersSalon;

	public void setCategorys(List<CategorysItem> categorys){
		this.categorys = categorys;
	}

	public List<CategorysItem> getCategorys(){
		return categorys;
	}

	public void setPosts(List<PostsItem> posts){
		this.posts = posts;
	}

	public List<PostsItem> getPosts(){
		return posts;
	}

	public void setSpecialOffersSalon(List<SpecialOffersSalonItem> specialOffersSalon){
		this.specialOffersSalon = specialOffersSalon;
	}

	public List<SpecialOffersSalonItem> getSpecialOffersSalon(){
		return specialOffersSalon;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"categorys = '" + categorys + '\'' + 
			",posts = '" + posts + '\'' + 
			",specialOffersSalon = '" + specialOffersSalon + '\'' + 
			"}";
		}
}