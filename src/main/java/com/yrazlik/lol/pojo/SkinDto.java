package com.yrazlik.lol.pojo;

public class SkinDto {
	
	private String id;
	private double num;
	private String name;
	private boolean chromas;
	private String imageUrl = "";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isChromas() {
		return chromas;
	}
	public void setChromas(boolean chromas) {
		this.chromas = chromas;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

}
