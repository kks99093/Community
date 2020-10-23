package com.community.my.api.model;

import java.util.List;

public class MatchDTO {
	
	private long gameid;
	private int queueId;
	private String gameType;
	private long gameDuration;
	private List<TeamStatsDTO>teams;
	private String platformId;
	private long gameCreation;
	private int seasonId;
	private String gameVersion;
	private int mapId;
	private String gameMode;
	private List<ParticipantDTO> participants;
	
	public long getGameid() {
		return gameid;
	}
	public void setGameid(long gameid) {
		this.gameid = gameid;
	}
	public int getQueueId() {
		return queueId;
	}
	public void setQueueId(int queueId) {
		this.queueId = queueId;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public long getGameDuration() {
		return gameDuration;
	}
	public void setGameDuration(long gameDuration) {
		this.gameDuration = gameDuration;
	}
	public List<TeamStatsDTO> getTeams() {
		return teams;
	}
	public void setTeams(List<TeamStatsDTO> teams) {
		this.teams = teams;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public long getGameCreation() {
		return gameCreation;
	}
	public void setGameCreation(long gameCreation) {
		this.gameCreation = gameCreation;
	}
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	public String getGameVersion() {
		return gameVersion;
	}
	public void setGameVersion(String gameVersion) {
		this.gameVersion = gameVersion;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public String getGameMode() {
		return gameMode;
	}
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	public List<ParticipantDTO> getParticipants() {
		return participants;
	}
	public void setParticipants(List<ParticipantDTO> participants) {
		this.participants = participants;
	}
	
	
	

}
