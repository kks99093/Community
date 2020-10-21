package com.community.my.api.model;

public class DetailDTO {
	private String que;
	private String match;
	private int teamId;
	private long[] time;
	
	
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
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	public long[] getTime() {
		return time;
	}
	public void setTime(long[] time) {
		this.time = time;
	}
	
	
	
}
