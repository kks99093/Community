<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
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
		<div class="game_info">
			<div></div>
			<div></div>
			<div></div>
		</div>
		<div class="sum_info"></div>
		<div class="detail_info"></div>
		<div class="item_info"></div>
		<div class="team_info"></div>
	</div>
	
	
	
</div>