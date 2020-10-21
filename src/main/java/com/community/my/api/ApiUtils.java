package com.community.my.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.community.my.api.model.DetailDTO;
import com.community.my.api.model.LeagueEntryDTO;
import com.community.my.api.model.MatchDTO;
import com.community.my.api.model.MatchlistDTO;
import com.community.my.api.model.SummonerDTO;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;


public class ApiUtils {
	
	private static String apiKey = "RGAPI-a1c6db72-5c2e-4a36-88b0-7abfbf5ef8e5";
	
	
	private static RestTemplate restTemplate = new RestTemplate();
	//id조회
	public static SummonerDTO nameSearch(SummonerDTO smDto) {
		String SummonerName = smDto.getName().replaceAll(" ", "%20"); //공백을 %20으로 바꿈 (api 규칙)
		URI url = URI.create("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+SummonerName+"?api_key=" + apiKey);
		smDto = restTemplate.getForObject(url,SummonerDTO.class);
		return smDto;
	}
	
	//id로 정보 조회
	public static LeagueEntryDTO[] summonerInfo(LeagueEntryDTO leDTO) {
		URI url = URI.create("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+leDTO.getSummonerId()+"?api_key="+apiKey);
		LeagueEntryDTO[] list = restTemplate.getForObject(url, LeagueEntryDTO[].class);
		return list;
	}
	
	//최근전적 조회
	public static MatchlistDTO recentHistory(SummonerDTO smDto) {
		URI url = URI.create("https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/"+ smDto.getAccountId() +"?api_key="+apiKey);
		MatchlistDTO mtlDTO = restTemplate.getForObject(url, MatchlistDTO.class);
		return mtlDTO;
	}
	
	//최근전적 상세조회
	public static List<MatchDTO> recentHistoryDetail(MatchlistDTO mtlDTO) {
		List<MatchDTO> mtDetail = new ArrayList();
		for(int i=0; i<10; i++) {
			URI url = URI.create("https://kr.api.riotgames.com/lol/match/v4/matches/"+mtlDTO.getMatches().get(i).getGameId()+"?api_key="+apiKey);
			MatchDTO mtDTO = restTemplate.getForObject(url, MatchDTO.class);
			mtDetail.add(mtDTO);
		}
		return mtDetail;
	}
	
	//최근전적 상세조회 정리
	public static DetailDTO detailInfo(MatchDTO mtDTO,MatchlistDTO mtlDTO) {
		Orianna.setRiotAPIKey(apiKey);
        Orianna.setDefaultRegion(Region.KOREA);
		
		DetailDTO dDTO = new DetailDTO();
		//1
		switch(mtDTO.getQueueId()) {
			case 420:
				dDTO.setQue("솔로 랭크 게임");
				break;
			case 430:
				dDTO.setQue("일반");
				break;
			case 450:
				dDTO.setQue("칼바람 나락");
				break;
			case 900:
				dDTO.setQue("URF");
				break;
			default:
				dDTO.setQue("알수없는 게임");
		}
		
		if("Win".equals(mtDTO.getTeams().get(0).getWin())) {
			dDTO.setTeamId(mtDTO.getTeams().get(0).getTeamId());
			dDTO.setMatch("승리");
		}else {
			dDTO.setTeamId(mtDTO.getTeams().get(0).getTeamId());
			dDTO.setMatch("패배");
		}
		
		long min = mtDTO.getGameDuration()/60; //분
		long sec = mtDTO.getGameDuration()%60; //초
		long[] time = {min,sec};
		dDTO.setTime(time);

		
		//2


//		for(int z=0; z< mtDTO.getParticipants().size(); z++) {
//			if(mtlDTO.getMatches().get(i).getChampion() == mtDTO.getParticipants().get(z).getChampionId()) {
//				mtDTO.getParticipants().get(z).getSpell1Id();
//				mtDTO.getParticipants().get(z).getSpell2Id();
//				mtDTO.getParticipants().get(z).getRunes().
//			}
//		}
//		
		
		return dDTO;

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
