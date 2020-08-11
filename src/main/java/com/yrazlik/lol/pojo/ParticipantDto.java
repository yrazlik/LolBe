package com.yrazlik.lol.pojo;

import java.util.List;

public class ParticipantDto {

	private int participantId;
	private int championId;
	private String championImageUrl;
	private List<RuneDto> runes;
	private ParticipantStatsDto stats;
	private int teamId;
	private ParticipantTimelineDto timeline;
	private int spell1Id;
	private int spell2Id;
	private String spell1Url;
	private String spell2Url;
	private int highestAchievedSeasonTier;
	private List<MasteryDto> masteries;
	public int getParticipantId() {
		return participantId;
	}
	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}
	public int getChampionId() {
		return championId;
	}
	public void setChampionId(int championId) {
		this.championId = championId;
	}
	public List<RuneDto> getRunes() {
		return runes;
	}
	public void setRunes(List<RuneDto> runes) {
		this.runes = runes;
	}
	public ParticipantStatsDto getStats() {
		return stats;
	}
	public void setStats(ParticipantStatsDto stats) {
		this.stats = stats;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public ParticipantTimelineDto getTimeline() {
		return timeline;
	}
	public void setTimeline(ParticipantTimelineDto timeline) {
		this.timeline = timeline;
	}
	public int getSpell1Id() {
		return spell1Id;
	}
	public void setSpell1Id(int spell1Id) {
		this.spell1Id = spell1Id;
	}
	public int getSpell2Id() {
		return spell2Id;
	}
	public void setSpell2Id(int spell2Id) {
		this.spell2Id = spell2Id;
	}
	public int getHighestAchievedSeasonTier() {
		return highestAchievedSeasonTier;
	}
	public void setHighestAchievedSeasonTier(int highestAchievedSeasonTier) {
		this.highestAchievedSeasonTier = highestAchievedSeasonTier;
	}
	public List<MasteryDto> getMasteries() {
		return masteries;
	}
	public void setMasteries(List<MasteryDto> masteries) {
		this.masteries = masteries;
	}
	public String getChampionImageUrl() {
		return championImageUrl;
	}
	public void setChampionImageUrl(String championImageUrl) {
		this.championImageUrl = championImageUrl;
	}
	public String getSpell1Url() {
		return spell1Url;
	}
	public void setSpell1Url(String spell1Url) {
		this.spell1Url = spell1Url;
	}
	public String getSpell2Url() {
		return spell2Url;
	}
	public void setSpell2Url(String spell2Url) {
		this.spell2Url = spell2Url;
	}
	
	
}
