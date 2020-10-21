<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="search_result">
	<div class="search_top">
		<div class="search_tleft">
			<div class="profile_img">
				<img alt="이미지없음" src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/profileicon/${sumData.profileIconId}.png" width="120px" height="120px">
				<div class="profile_level">${sumData.summonerLevel}</div>
			</div>
			<div class="profile_info">
				<div class="profile_nm">${rankData.summonerName}</div>
				<button class="search_reflesh" >전적 갱신</button>
			</div>
		</div>
		<div class="search_tright">
			<div class="rank_img">
				<img alt="이미지없음" src="/res/img/emblems/${rankData.tier}.png" width="120px" height="120px">
				<div class="rank_tier">${rankData.tier}</div>
				<div class="rank_lp">${rankData.leaguePoints} LP</div>
			</div>
			<div class="rank_info">
				<div class="rank_wins">${rankData.wins + rankData.losses}전 ${rankData.wins }승 ${rankData.losses } 패</div>				
				<div class="reank_winrate"><fmt:formatNumber value="${rankData.wins/(rankData.wins +rankData.losses)*100}" pattern=".00"/> %</div>
			</div>
		</div>
	</div>
	<div class="search_bottom">
		<div id="game" style="background-color:${dDTO.teamId == 100 ? '#a3cfec' : '#e2b6b3' }">
			<div class="game_info">
				<div id="que_type">${dDTO.que }</div>
				<div>${dDTO.teamId == 100 ? '승리' : '패배' }</div>
				<div>${dDTO.time[0]}분 ${dDTO.time[1]}초</div>
			</div>
			<div class="sum_info"></div>
			<div class="detail_info"></div>
			<div class="item_info"></div>
			<div class="team_info"></div>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
	
	function champ(id){
		axios.get('http://ddragon.leagueoflegends.com/cdn/10.21.1/data/en_US/champion.json').then(function(res){
			championData = res.data
			console.log(res.data.data.length)
			 for (var i in championData) {
		      if (championData[i].key == id) {
		        console.log(championData[i].id)
		      }
			 }
		})
	}
	champ(142)
</script>
