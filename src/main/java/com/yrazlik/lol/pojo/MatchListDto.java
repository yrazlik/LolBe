package com.yrazlik.lol.pojo;

import java.util.List;

public class MatchListDto {

	private int startIndex;
	private int totalGames;
	private int endIndex;
	private List<MatchReferenceDto> matches;
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getTotalGames() {
		return totalGames;
	}
	public void setTotalGames(int totalGames) {
		this.totalGames = totalGames;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public List<MatchReferenceDto> getMatches() {
		return matches;
	}
	public void setMatches(List<MatchReferenceDto> matches) {
		this.matches = matches;
	}
	
	
}
