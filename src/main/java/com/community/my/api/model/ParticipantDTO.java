package com.community.my.api.model;

import java.util.List;

public class ParticipantDTO {
	
	private int participantId;
	private int championId;
	private List<RuneDTO>runes;
	private ParticipantStatsDTO stats;
	private int teamId;
	private int spell1Id;
	private int spell2Id;
	private String highestAchievedSeasonTier;
	private List<MasteryDTO> masteries;
	
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
	public List<RuneDTO> getRunes() {
		return runes;
	}
	public void setRunes(List<RuneDTO> runes) {
		this.runes = runes;
	}
	public ParticipantStatsDTO getStats() {
		return stats;
	}
	public void setStats(ParticipantStatsDTO stats) {
		this.stats = stats;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
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
	public String getHighestAchievedSeasonTier() {
		return highestAchievedSeasonTier;
	}
	public void setHighestAchievedSeasonTier(String highestAchievedSeasonTier) {
		this.highestAchievedSeasonTier = highestAchievedSeasonTier;
	}
	public List<MasteryDTO> getMasteries() {
		return masteries;
	}
	public void setMasteries(List<MasteryDTO> masteries) {
		this.masteries = masteries;
	}
	
	
	
}
