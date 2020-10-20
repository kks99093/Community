<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<div class="main_container">
	<div class="main_borders">
		<div class="left_border">
		<table>
			<c:forEach items="${ranking}" begin="0" end="19" var="item">
				<tr>
					<td><img alt="이미지없음" src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/profileicon/${item.profileIconId}.png" width="30px" height="30px"></td>
					<td onclick="goSearch('${item.summonerName}')">${item.summonerName }</td>
					<td><img alt="이미지 없음" src="/res/img/emblems/${item.tier}.png" width="30px" height="30px"></td>
					<td>${item.leaguePoints } LP</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<div class="right_border">
			<div class="right_top">
				공지
			</div>
			<div class="right_bottom">
				<div class="free_border_rank">
					아직
				</div>
				<div class="border_rank">
					미정
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function goSearch(name){
		location.href = "/board/nameSearch?name="+name
	}
</script>