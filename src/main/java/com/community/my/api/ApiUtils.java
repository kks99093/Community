package com.community.my.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.community.my.api.model.LeagueEntryDTO;
import com.community.my.api.model.SummonerDTO;

public class ApiUtils {
	
	private static String apiKey = "RGAPI-f75b7a28-f9e2-4d4e-8075-66a74b301898";
	private static RestTemplate restTemplate = new RestTemplate();
	//id조회
	public static SummonerDTO nameSearch(SummonerDTO dto) {
		String SummonerName = dto.getName().replaceAll(" ", "%20"); //공백을 %20으로 바꿈 (api 규칙)
		URI url = URI.create("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+SummonerName+"?api_key=" + apiKey);
		dto = restTemplate.getForObject(url,SummonerDTO.class);
		return dto;
	}
	
	//id로 정보 조회
	public static LeagueEntryDTO[] summonerInfo(LeagueEntryDTO leDTO) {
		String id = leDTO.getSummonerId();
		URI url = URI.create("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+id+"?api_key="+apiKey);
		LeagueEntryDTO[] list = restTemplate.getForObject(url, LeagueEntryDTO[].class);
		return list;
	}
	
	//랭킹정보 조회
	public static LeagueEntryDTO[] getRanking() {
		URI url = URI.create("https://kr.api.riotgames.com/lol/league-exp/v4/entries/RANKED_SOLO_5x5/CHALLENGER/I?page=1&api_key="+apiKey);
		LeagueEntryDTO[] list = restTemplate.getForObject(url, LeagueEntryDTO[].class);
		SummonerDTO dto = new SummonerDTO();
		for(int i=0; i<20; i++) {
			dto.setName(list[i].getSummonerName());
			dto = nameSearch(dto);
			list[i].setProfileIconId(dto.getProfileIconId());
			list[i].setSummonerLevel(dto.getSummonerLevel());
		}
		return list;
	}
	
}
