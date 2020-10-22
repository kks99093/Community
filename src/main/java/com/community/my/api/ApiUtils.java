package com.community.my.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.community.my.api.model.DetailDTO;
import com.community.my.api.model.LeagueEntryDTO;
import com.community.my.api.model.MatchDTO;
import com.community.my.api.model.MatchlistDTO;
import com.community.my.api.model.PerksDTO;
import com.community.my.api.model.SummonerDTO;
import com.community.my.api.model.ChamSpell;

public class ApiUtils {
	
	private static String apiKey = "RGAPI-f2f5ee67-1d04-44fb-b206-f002bac58ad4";
	
	
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
	
	//최근전적 상세조회 정리 - que_type
	public static DetailDTO detailInfo(MatchDTO mtDTO,MatchlistDTO mtlDTO) {
		
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
		
		//챔프이름
				int champId = mtlDTO.getMatches().get(0).getChampion();
				String chamSpell = "champion"; //챔피언 서치
				String[] champ = detailInfo2(champId,chamSpell);
				dDTO.setChampId(champ[0]); //영
				dDTO.setChampNm(champ[1]);; //한
				
		//내정보 조회
		for(int i=0; i<mtDTO.getParticipants().size(); i++) {
			
			if(mtDTO.getParticipants().get(i).getChampionId() == champId) {
				if(mtDTO.getParticipants().get(i).getStats().isWin()) {	dDTO.setWin(1);	}
				else {dDTO.setWin(0);}
				dDTO.setTeamId(mtDTO.getParticipants().get(i).getTeamId());
				dDTO.setKills(mtDTO.getParticipants().get(i).getStats().getKills());
				dDTO.setDeaths(mtDTO.getParticipants().get(i).getStats().getDeaths());
				dDTO.setAssists(mtDTO.getParticipants().get(i).getStats().getAssists());
				dDTO.setVisionScroe(mtDTO.getParticipants().get(i).getStats().getVisionScore());//시야점수
				dDTO.setChampLevel(mtDTO.getParticipants().get(i).getStats().getChampLevel());
				dDTO.setTotalMinionsKilled(mtDTO.getParticipants().get(i).getStats().getTotalMinionsKilled());//cs
				for(int j=0; j<mtDTO.getTeams().size(); j++) {
					if(mtDTO.getParticipants().get(i).getTeamId() == mtDTO.getTeams().get(j).getTeamId()) {
						dDTO.setTotalKill(mtDTO.getTeams().get(j).getTowerKills());
					}
				}
			}
		}

		//게임시간
		long min = mtDTO.getGameDuration()/60; //분
		long sec = mtDTO.getGameDuration()%60; //초
		long[] time = {min,sec};
		dDTO.setTime(time);
		
		
		//스펠,룬 이름
		int spell1Id=0;
		int spell2Id=0;
		int perk0=0;
		int perkSubStyle=0;
		for(int i=0; i<mtDTO.getParticipants().size(); i++) {
			if(champId == mtDTO.getParticipants().get(i).getChampionId()) {
				spell1Id = mtDTO.getParticipants().get(i).getSpell1Id();
				spell2Id = mtDTO.getParticipants().get(i).getSpell2Id();
				perk0 = mtDTO.getParticipants().get(i).getStats().getPerk0();
				perkSubStyle = mtDTO.getParticipants().get(i).getStats().getPerkSubStyle();
			}
		}
		chamSpell = "summoner";
		String[] spell1 = detailInfo2(spell1Id,chamSpell);
		String spellId1 = spell1[0];
		String spellNm1 = spell1[1];
		
		String[] spell2 = detailInfo2(spell2Id,chamSpell);
		String spellId2 = spell2[0];
		String spellNm2 = spell2[1];
		
		String perk0Icon = detailPerk(perk0);
		String perkSubStyleIcon = detailPerk(perkSubStyle);
		
		dDTO.setSpell1Id(spellId1); //한
		dDTO.setSpell1Nm(spellNm1);	//영
		dDTO.setSpell2Id(spellId2);
		dDTO.setSpell2Nm(spellNm2);
		dDTO.setPerk0Icon(perk0Icon);
		dDTO.setPerkSubStyleIcon(perkSubStyleIcon);
		
			
		return dDTO;

	}
	
	//최근전적 상세조회 정리 - que_type
	public static String[] detailInfo2(int id, String chamSpell) {
		URI url = URI.create("http://ddragon.leagueoflegends.com/cdn/10.21.1/data/ko_KR/"+chamSpell+".json");
		ChamSpell chamspell = restTemplate.getForObject(url, ChamSpell.class);
		
		for(Object key : chamspell.getData().keySet()) {
			if(chamspell.getData().get(key).getKey() == id) {
				
				return new String[] {chamspell.getData().get(key).getId(),chamspell.getData().get(key).getName()};
			}
		}
		return null;
	}
	
	//최근전적 상세조회 정리 - perk1
	public static String detailPerk(int id) {
		URI url = URI.create("https://ddragon.leagueoflegends.com/cdn/10.6.1/data/en_US/runesReforged.json");
		PerksDTO[] perksDTO = restTemplate.getForObject(url, PerksDTO[].class);
		
		for(int i=0; i<perksDTO.length; i++) {
			if(perksDTO[i].getId() == id) {
				return perksDTO[i].getIcon();
			}
			for(int z=0; z<perksDTO[i].getSlots().size(); z++) {
				for(int j=0;j<perksDTO[i].getSlots().get(z).getRunes().size(); j++) {
					if(perksDTO[i].getSlots().get(z).getRunes().get(j).getId() == id) {
						return perksDTO[i].getSlots().get(z).getRunes().get(j).getIcon();
					}
				}
			}
		}
		
		return "못받아옴";
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
