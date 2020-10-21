package com.community.my.api.model;

import java.util.List;

public class MatchlistDTO {
	
	private int startIndex;	
	private int totalGames;
	private int endIndex;	
	private List<MatchReferenceDTO> matches;
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
	public List<MatchReferenceDTO> getMatches() {
		return matches;
	}
	public void setMatches(List<MatchReferenceDTO> matches) {
		this.matches = matches;
	}
	
	

}
