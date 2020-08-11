package com.yrazlik.lol.pojo;

import java.util.List;

public class SpellDto {

	private String id;
	private String name;
	private String description;
	private String tooltip;
	private LevelTipDto leveltip;
	private double maxrank;
	private List<Double> cooldown;
	private String cooldownBurn;
	private List<Double> cost;
	private String costBurn;
	private String key;
	//datavalues: { },
	//effect: [],
	//effectBurn: [],
	//vars: [ ],
	private String costType;
	private String maxammo;
	private List<Double> range;
	private String rangeBurn;
	private ImageDto image;
	private String resource;
	private String videoUrl = "";
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
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public LevelTipDto getLeveltip() {
		return leveltip;
	}
	public void setLeveltip(LevelTipDto leveltip) {
		this.leveltip = leveltip;
	}
	public double getMaxrank() {
		return maxrank;
	}
	public void setMaxrank(double maxrank) {
		this.maxrank = maxrank;
	}
	public List<Double> getCooldown() {
		return cooldown;
	}
	public void setCooldown(List<Double> cooldown) {
		this.cooldown = cooldown;
	}
	public String getCooldownBurn() {
		return cooldownBurn;
	}
	public void setCooldownBurn(String cooldownBurn) {
		this.cooldownBurn = cooldownBurn;
	}
	public List<Double> getCost() {
		return cost;
	}
	public void setCost(List<Double> cost) {
		this.cost = cost;
	}
	public String getCostBurn() {
		return costBurn;
	}
	public void setCostBurn(String costBurn) {
		this.costBurn = costBurn;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public String getMaxammo() {
		return maxammo;
	}
	public void setMaxammo(String maxammo) {
		this.maxammo = maxammo;
	}
	public List<Double> getRange() {
		return range;
	}
	public void setRange(List<Double> range) {
		this.range = range;
	}
	public String getRangeBurn() {
		return rangeBurn;
	}
	public void setRangeBurn(String rangeBurn) {
		this.rangeBurn = rangeBurn;
	}
	public ImageDto getImage() {
		return image;
	}
	public void setImage(ImageDto image) {
		this.image = image;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
