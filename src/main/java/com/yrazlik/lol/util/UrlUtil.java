package com.yrazlik.lol.util;
import static com.yrazlik.lol.util.PlatformConstants.*;

public class UrlUtil {
	
	public static final boolean API_KEY_ENABLED = true;
	
	public static String buildWeeklyRotationsUrl(String regionCode, String servicePath) {
		Region region = Region.createRegionFromRegionCode(regionCode);
		return PROTOCOL + region.getRegionCode() + "." + API_BASE_PATH + API_PLATFORM_PATH + WEEKLY_FREE_API_VERSION + servicePath;
	}
	
	public static String buildDataDragonUrl(String baseUrl, String version, String basePath, String locale, String path) {
		return baseUrl + "/" + version + basePath + locale + path;
	}
	
	public static String buildDataDragonQueueUrl() {
		return "https://static.developer.riotgames.com/docs/lol/queues.json";
	}
	
	
	
	public static String buildDataDragonSpellsUrl(String locale) {
		return "https://ddragon.leagueoflegends.com/cdn/" + ServicePaths.LATEST_VERSION + "/data/" + locale + "/summoner.json";
	}
	
	public static String buildDataDragonItemsUrl(String baseUrl, String locale) {
		return baseUrl + locale + "/item.json";
	}

	public static String buildSummonerSearchUrl(String regionCode, String summonerName) {
		Region region = Region.createRegionFromRegionCode(regionCode);
		return PROTOCOL + region.getRegionCode() + "." + API_BASE_PATH  + "/lol/summoner" + API_VERSION + "/summoners/by-name/" + summonerName;
	}
	
	public static String buildActiveGameInfoUrl(String regionCode, String encryptedSummonerId) {
		Region region = Region.createRegionFromRegionCode(regionCode);
		return PROTOCOL + region.getRegionCode() + "." + API_BASE_PATH  + "/lol/spectator" + API_VERSION + "/active-games/by-summoner/" + encryptedSummonerId;
	}
	
	public static String buildSummonerLeagueUrl(String regionCode, String summonerId) {
		Region region = Region.createRegionFromRegionCode(regionCode);
		return PROTOCOL + region.getRegionCode() + "." + API_BASE_PATH  + "/lol/league" + API_VERSION + "/entries/by-summoner/" + summonerId;
	}

	public static String buildLeagueInfoUrl(String regionCode, String queue, String tier, String division) {
		Region region = Region.createRegionFromRegionCode(regionCode);
		return PROTOCOL + region.getRegionCode() + "." + API_BASE_PATH  + "/lol/league" + API_VERSION + "/entries/" + queue + "/" + tier + "/" + division;
	}

	public static String buildLeagueInfoByLeagueIdUrl(String regionCode, String leagueId) {
		Region region = Region.createRegionFromRegionCode(regionCode);
		return PROTOCOL + region.getRegionCode() + "." + API_BASE_PATH  + "/lol/league" + API_VERSION + "/leagues/" + leagueId;
	}

	public static String buildMatchListByAccountIdUrl(String regionCode, String accountId, int startIndex, int endIndex) {
		Region region = Region.createRegionFromRegionCode(regionCode);
		return PROTOCOL + region.getRegionCode() + "." + API_BASE_PATH  + "/lol/match" + API_VERSION + "/matchlists/by-account/" + accountId + "?startIndex=" + startIndex + "&endIndex=" + endIndex;
	}

	public static String buildMatchDetailUrl(String regionCode, long matchId) {
		Region region = Region.createRegionFromRegionCode(regionCode);
		return PROTOCOL + region.getRegionCode() + "." + API_BASE_PATH  + "/lol/match" + API_VERSION + "/matches/" + matchId;
	}

	public static String buildSummonerChampionMasteriesUrl(String regionCode, String summonerId) {
		Region region = Region.createRegionFromRegionCode(regionCode);
		return PROTOCOL + region.getRegionCode() + "." + API_BASE_PATH  + "/lol/champion-mastery" + API_VERSION + "/champion-masteries/by-summoner/" + summonerId;
	}
	
}
