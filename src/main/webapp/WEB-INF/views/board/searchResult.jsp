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
				<div class="rank_tier">${rankData.tier} ${rankData.rank}</div>
				<div class="rank_lp">${rankData.leaguePoints} LP</div>
			</div>
			<div class="rank_info">
				<div class="rank_wins">${rankData.wins + rankData.losses}전 ${rankData.wins }승 ${rankData.losses } 패</div>				
				<div class="reank_winrate"><fmt:formatNumber value="${rankData.wins/(rankData.wins +rankData.losses)*100}" pattern=".00"/> %</div>
			</div>
		</div>
	</div>
	<div class="search_bottom">
		<div id="game" style="background-color:${dDTO.win == 1 ? '#a3cfec' : '#e2b6b3' }">
			<div class="game_info">
				<div id="que_type">${dDTO.que }</div>
				<div>${dDTO.win == 1 ? '승리' : '패배' }</div>
				<div>${dDTO.time[0]}분 ${dDTO.time[1]}초</div>
			</div>
			<div class="sum_info">
				<div class="sumTB">
					<img alt="" src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/${dDTO.champId }.png" width="50px" height="50px">
					<div class="spell">
						<img alt="" src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/spell/${dDTO.spell1Id }.png" width="25px" height="25px">
						<img alt="" src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/spell/${dDTO.spell2Id }.png" width="25px" height="25px">
					</div>
					<div class="perk">
						<img alt="" src="http://ddragon.leagueoflegends.com/cdn/img/${dDTO.perk0Icon }" width="25px" height="25px">
						<img alt="" src="http://ddragon.leagueoflegends.com/cdn/img/${dDTO.perkSubStyleIcon }" width="25px" height="25px">
					</div>
				</div>
				<div>
					${dDTO.champNm }
				</div>
			</div>
			<div class="detail_info">
				<div class="kda">
					<div>${dDTO.kills } / ${dDTO.deaths } / ${dDTO.assists }</div>
					<div>${(dDTO.kills + dDTO.assists)/dDTO.deaths} 평점</div>
				</div>
				<div class="sub_info">
					<div>레벨${dDTO.champLevel}</div>
					<div>${dDTO.totalMinionsKilled} CS</div>
					<div>킬 관여율</div>
					<div>${(dDTO.kills+dDTO.assists/dDTO.totalKill)*10} %</div> 
					<div>시야점수 </div>
					<div>${dDTO.visionScroe}</div>
				</div>
			</div>
			<div class="item_info"></div>
			<div class="team_info"></div>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
