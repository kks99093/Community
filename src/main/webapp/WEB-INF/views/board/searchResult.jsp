<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="search_result">
	<div class="search_top">
		<div class="search_tleft">
			<div class="profile_img">
				<img alt="이미지없음"
					src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/profileicon/${game.smDTO.profileIconId}.png">
				<div class="profile_level">${game.smDTO.summonerLevel}</div>
			</div>
			<div class="profile_info">
				<div class="profile_nm">${game.searchInfo[0].summonerName}</div>
				<button class="search_reflesh">전적 갱신</button>
			</div>
		</div>
		<div class="search_tright">
			<div class="rank_img">
				<img alt="이미지없음"
					src="/res/img/emblems/${game.searchInfo[0].tier}.png" width="120px"
					height="120px">
				<div class="rank_tier">${game.searchInfo[0].tier}
					${game.searchInfo[0].rank}</div>
				<div class="rank_lp">${game.searchInfo[0].leaguePoints}LP</div>
			</div>
			<div class="rank_info">
				<div class="rank_wins">${game.searchInfo[0].wins + game.searchInfo[0].losses}전
					${game.searchInfo[0].wins }승 ${game.searchInfo[0].losses } 패</div>
				<div class="reank_winrate">
					<fmt:formatNumber
						value="${game.searchInfo[0].wins/(game.searchInfo[0].wins +game.searchInfo[0].losses)*100}"
						pattern=".00" />
					%
				</div>
			</div>
		</div>
	</div>
	<div id="search_bottom">
		<c:forEach items="${game.dDTOList}" var="dDTO">
			<div id="game"
				style="background-color:${dDTO.win ? '#a3cfec' : '#e2b6b3' }; border-color:${dDTO.win ? '#99b9cf' : '#cea7a7'};">
				<div class="game_info">
					<div id="que_type">${dDTO.que }</div>
					<div style="color:${dDTO.win ? '#1a78ae' : '#c6443e'}">${dDTO.win ? '승리' : '패배' }</div>
					<div>${dDTO.time[0]}분${dDTO.time[1]}초</div>
					<div>${dDTO.timeDate }</div>
				</div>
				<div class="sum_info">
					<div class="sumTB">
						<img class="icon"
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/${dDTO.champId }.png">
						<div class="spell">
							<img
								src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/spell/${dDTO.spell1Id }.png">
							<img
								src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/spell/${dDTO.spell2Id }.png">
						</div>
						<div class="perk">
							<img
								src="http://ddragon.leagueoflegends.com/cdn/img/${dDTO.perk0Icon }">
							<img
								src="http://ddragon.leagueoflegends.com/cdn/img/${dDTO.perkSubStyleIcon }">
						</div>
					</div>
					<div>${dDTO.champNm }</div>
				</div>
				<div class="detail_info">
					<div class="kda">
						<div>${dDTO.kills }
							/<span id='deathSc'> ${dDTO.deaths } </span>/ ${dDTO.assists }
						</div>
						<div>
							<fmt:formatNumber
								value="${(dDTO.kills + dDTO.assists)/dDTO.deaths}" pattern=".00" />
							평점
						</div>
					</div>
					<div class="sub_info">
						<div>레벨${dDTO.champLevel}</div>
						<div>${dDTO.totalMinionsKilled} (<fmt:formatNumber
								value="${dDTO.totalMinionsKilled/dDTO.time[0]}" pattern=".0" />
							) CS
						</div>
						<div>
							<span id="killPer">킬 관여 <fmt:formatNumber
									value="${(dDTO.kills+dDTO.assists)/dDTO.totalKill}"
									type="percent" /></span>
						</div>
						<div>시야점수</div>
						<div>${dDTO.visionScore}</div>
					</div>
				</div>
				<div class="item_info">
					<div class="line_first">
						<img onerror="this.src='/res/img/opacity1.png';"
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/item/${dDTO.item0}.png">
						<img onerror="this.src='/res/img/opacity1.png';"
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/item/${dDTO.item1}.png">
						<img onerror="this.src='/res/img/opacity1.png';"
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/item/${dDTO.item2}.png">
						<img onerror="this.src='/res/img/opacity1.png';"
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/item/${dDTO.item6}.png">
					</div>
					<div class="line_second">
						<img onerror="this.src='/res/img/opacity1.png';"
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/item/${dDTO.item3}.png">
						<img onerror="this.src='/res/img/opacity1.png';"
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/item/${dDTO.item4}.png">
						<img onerror="this.src='/res/img/opacity1.png';"
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/item/${dDTO.item5}.png">
					</div>
				</div>
				<div class="team_info">
					<div class="blueTeam">
						<img alt=""
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/${dDTO.blueTeam.champList[0]}.png">
						<img alt=""
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/${dDTO.blueTeam.champList[1]}.png">
						<img alt=""
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/${dDTO.blueTeam.champList[2]}.png">
						<img alt=""
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/${dDTO.blueTeam.champList[3]}.png">
						<img alt=""
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/${dDTO.blueTeam.champList[4]}.png">
					</div>
					<div class="redTeam">
						<img alt=""
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/${dDTO.redTeam.champList[0]}.png">
						<img alt=""
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/${dDTO.redTeam.champList[1]}.png">
						<img alt=""
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/${dDTO.redTeam.champList[2]}.png">
						<img alt=""
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/${dDTO.redTeam.champList[3]}.png">
						<img alt=""
							src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/${dDTO.redTeam.champList[4]}.png">
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="moreButton">
		<a href="javascript:void(0);" onclick="moreGame('${game.smDTO.id}')">더 보기</a>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="/res/js/moreGame.js"></script>
