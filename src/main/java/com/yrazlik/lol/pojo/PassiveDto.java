package com.yrazlik.lol.pojo;

public class PassiveDto {
	
	private String name;
	private String description;
	private ImageDto image;
	private String videoUrl = "";
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
	public ImageDto getImage() {
		return image;
	}
	public void setImage(ImageDto image) {
		this.image = image;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	
}
