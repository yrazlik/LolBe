package com.yrazlik.lol.pojo;

public class ChampionImageDto {

	private String square;
	private String splash;
	private String loading;
	
	public ChampionImageDto() {
		super();
	}
	public ChampionImageDto(String square, String splash, String loading) {
		super();
		this.square = square;
		this.splash = splash;
		this.loading = loading;
	}
	public String getSquare() {
		return square;
	}
	public void setSquare(String square) {
		this.square = square;
	}
	public String getSplash() {
		return splash;
	}
	public void setSplash(String splash) {
		this.splash = splash;
	}
	public String getLoading() {
		return loading;
	}
	public void setLoading(String loading) {
		this.loading = loading;
	}
	
	
	
}
