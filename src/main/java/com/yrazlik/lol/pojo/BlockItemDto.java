package com.yrazlik.lol.pojo;


import com.google.gson.annotations.SerializedName;

/**
 * Created by yrazlik on 27/03/17.
 */

public class BlockItemDto {

    @SerializedName("count")
    private int count;
    @SerializedName("id")
    private int id;
    private String imageUrl;
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
    
    
}
