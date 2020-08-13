package com.yrazlik.lol.pojo;

import java.util.List;

public class SingleParticipantMatchListDto {

	private int startIndex;
	private int totalGames;
	private int endIndex;
	private List<SingleParticipantMatchReferenceDto> matches;
	private boolean end = true;
	
	public SingleParticipantMatchListDto() {
		super();
	}
	
	public SingleParticipantMatchListDto(int startIndex, int totalGames, int endIndex,
			List<SingleParticipantMatchReferenceDto> matches, boolean end) {
		super();
		this.startIndex = startIndex;
		this.totalGames = totalGames;
		this.endIndex = endIndex;
		this.matches = matches;
		this.end = end;
	}
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
	public List<SingleParticipantMatchReferenceDto> getMatches() {
		return matches;
	}
	public void setMatches(List<SingleParticipantMatchReferenceDto> matches) {
		this.matches = matches;
	}
	public boolean isEnd() {
		return end;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	
	
	
}
