package com.yrazlik.lol.pojo;

import java.util.List;

public class ItemDto {
	
	private String id;
	private String name;
	private String description;
	private String colloq;
	private String plaintext;
	private List<String> into;
	private ImageDto image;
	private GoldDto gold;
	private List<String> tags;
	private List<String> from;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getColloq() {
		return colloq;
	}
	public void setColloq(String colloq) {
		this.colloq = colloq;
	}
	public String getPlaintext() {
		return plaintext;
	}
	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}
	public List<String> getInto() {
		return into;
	}
	public void setInto(List<String> into) {
		this.into = into;
	}
	public ImageDto getImage() {
		return image;
	}
	public void setImage(ImageDto image) {
		this.image = image;
	}
	public GoldDto getGold() {
		return gold;
	}
	public void setGold(GoldDto gold) {
		this.gold = gold;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<String> getFrom() {
		return from;
	}
	public void setFrom(List<String> from) {
		this.from = from;
	}
	
	
	

}
