package com.community.my.api.model;

public class DetailDTO {
	private String que;
	private int teamId;
	private int totalKill;
	private int kills;
	private int deaths;
	private int assists;
	private int win; //승: 1 / 패: 0
	private long visionScroe;
	private int champLevel;
	private int totalMinionsKilled;
	private long[] time;
	private String champNm;
	private String spell1Nm;
	private String spell1Id;
	private String spell2Nm;
	private String spell2Id;
	private String perk0Icon;
	private String perkSubStyleIcon;
	private String champId;
	
	
	
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public int getAssists() {
		return assists;
	}
	public void setAssists(int assists) {
		this.assists = assists;
	}
	public long getVisionScroe() {
		return visionScroe;
	}
	public void setVisionScroe(long visionScroe) {
		this.visionScroe = visionScroe;
	}
	public int getChampLevel() {
		return champLevel;
	}
	public void setChampLevel(int champLevel) {
		this.champLevel = champLevel;
	}

	public int getTotalMinionsKilled() {
		return totalMinionsKilled;
	}
	public void setTotalMinionsKilled(int totalMinionsKilled) {
		this.totalMinionsKilled = totalMinionsKilled;
	}
	public int getTotalKill() {
		return totalKill;
	}
	public void setTotalKill(int totalKill) {
		this.totalKill = totalKill;
	}
	public String getChampId() {
		return champId;
	}
	public void setChampId(String champId) {
		this.champId = champId;
	}
	public String getSpell1Id() {
		return spell1Id;
	}
	public void setSpell1Id(String spell1Id) {
		this.spell1Id = spell1Id;
	}
	public String getSpell2Id() {
		return spell2Id;
	}
	public void setSpell2Id(String spell2Id) {
		this.spell2Id = spell2Id;
	}
	public String getPerk0Icon() {
		return perk0Icon;
	}
	public void setPerk0Icon(String perk0Icon) {
		this.perk0Icon = perk0Icon;
	}
	public String getPerkSubStyleIcon() {
		return perkSubStyleIcon;
	}
	public void setPerkSubStyleIcon(String perkSubStyleIcon) {
		this.perkSubStyleIcon = perkSubStyleIcon;
	}
	public String getChampNm() {
		return champNm;
	}
	public void setChampNm(String champNm) {
		this.champNm = champNm;
	}
	public String getSpell1Nm() {
		return spell1Nm;
	}
	public void setSpell1Nm(String spell1Nm) {
		this.spell1Nm = spell1Nm;
	}
	public String getSpell2Nm() {
		return spell2Nm;
	}
	public void setSpell2Nm(String spell2Nm) {
		this.spell2Nm = spell2Nm;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getQue() {
		return que;
	}
	public void setQue(String que) {
		this.que = que;
	}
	public long[] getTime() {
		return time;
	}
	public void setTime(long[] time) {
		this.time = time;
	}
	
	
	
}
