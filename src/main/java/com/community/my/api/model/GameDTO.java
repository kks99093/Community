package com.community.my.api.model;

import java.util.List;

public class GameDTO {
	private String sumId;
	private int min;
	private int max;
	private SummonerDTO smDTO;
	private List<DetailDTO> dDTOList;
	private LeagueEntryDTO[] searchInfo;
	private MatchlistDTO mtlDTO;
	List<MatchDTO> mtDTO;
	
	
	public String getSumId() {
		return sumId;
	}
	public void setSumId(String sumId) {
		this.sumId = sumId;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public SummonerDTO getSmDTO() {
		return smDTO;
	}
	public void setSmDTO(SummonerDTO smDTO) {
		this.smDTO = smDTO;
	}
	public List<DetailDTO> getdDTOList() {
		return dDTOList;
	}
	public void setdDTOList(List<DetailDTO> dDTOList) {
		this.dDTOList = dDTOList;
	}
	public LeagueEntryDTO[] getSearchInfo() {
		return searchInfo;
	}
	public void setSearchInfo(LeagueEntryDTO[] searchInfo) {
		this.searchInfo = searchInfo;
	}
	public MatchlistDTO getMtlDTO() {
		return mtlDTO;
	}
	public void setMtlDTO(MatchlistDTO mtlDTO) {
		this.mtlDTO = mtlDTO;
	}
	public List<MatchDTO> getMtDTO() {
		return mtDTO;
	}
	public void setMtDTO(List<MatchDTO> mtDTO) {
		this.mtDTO = mtDTO;
	}

	
}
