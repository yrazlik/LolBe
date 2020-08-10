package com.yrazlik.lol.pojo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ChampionDto {
	
	private String version;
	@SerializedName("id")
	private String champId;
	@SerializedName("nulll")
	private long id;
	private String key;
	private String name;
	private String title;
	private ImageDto image;
	private List<SkinDto> skins;
	private String lore;
	private String blurb;
	private List<String> allytips;
	private List<String> enemytips;
	private List<String> tags;
	private String partype;
	private InfoDto info;
	private StatsDto stats;
	private List<SpellDto> spells;
	private PassiveDto passive;
	//private List<RecommendedDto> recommended;
	private ChampionImageDto images;
	private int championRp;
    private int championIp;
    private String dateInterval = "";
    private List<RecommendedDto> recommended;
    
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getChampId() {
		return champId;
	}
	public void setChampId(String champId) {
		this.champId = champId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ImageDto getImage() {
		return image;
	}
	public void setImage(ImageDto image) {
		this.image = image;
	}
	public List<SkinDto> getSkins() {
		return skins;
	}
	public void setSkins(List<SkinDto> skins) {
		this.skins = skins;
	}
	public String getLore() {
		return lore;
	}
	public void setLore(String lore) {
		this.lore = lore;
	}
	public String getBlurb() {
		return blurb;
	}
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
	public List<String> getAllytips() {
		return allytips;
	}
	public void setAllytips(List<String> allytips) {
		this.allytips = allytips;
	}
	public List<String> getEnemytips() {
		return enemytips;
	}
	public void setEnemytips(List<String> enemytips) {
		this.enemytips = enemytips;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getPartype() {
		return partype;
	}
	public void setPartype(String partype) {
		this.partype = partype;
	}
	public InfoDto getInfo() {
		return info;
	}
	public void setInfo(InfoDto info) {
		this.info = info;
	}
	public StatsDto getStats() {
		return stats;
	}
	public void setStats(StatsDto stats) {
		this.stats = stats;
	}
	public List<SpellDto> getSpells() {
		return spells;
	}
	public void setSpells(List<SpellDto> spells) {
		this.spells = spells;
	}
	public PassiveDto getPassive() {
		return passive;
	}
	public void setPassive(PassiveDto passive) {
		this.passive = passive;
	}
	/*public List<RecommendedDto> getRecommended() {
		return recommended;
	}
	public void setRecommended(List<RecommendedDto> recommended) {
		this.recommended = recommended;
	}*/
	public ChampionImageDto getImages() {
		return images;
	}
	public void setImages(ChampionImageDto images) {
		this.images = images;
	}
	public int getChampionRp() {
		return championRp;
	}
	public void setChampionRp(int championRp) {
		this.championRp = championRp;
	}
	public int getChampionIp() {
		return championIp;
	}
	public void setChampionIp(int championIp) {
		this.championIp = championIp;
	}
	public String getDateInterval() {
		return dateInterval;
	}
	public void setDateInterval(String dateInterval) {
		this.dateInterval = dateInterval;
	}
	public List<RecommendedDto> getRecommended() {
		return recommended;
	}
	public void setRecommended(List<RecommendedDto> recommended) {
		this.recommended = recommended;
	}
	
}
