<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<div class="main_container">
	<div class="main_borders">
		<div class="left_border">
		<table>
			<c:forEach items="${ranking}" begin="0" end="14" var="item">
				<tr>
					<td>${item.ranking }. </td>
					<td><img alt="이미지없음" src="http://ddragon.leagueoflegends.com/cdn/10.21.1/img/profileicon/${item.profileIconId}.png" width="30px" height="30px"></td>
					<td onclick="goSearch('${item.summonerName}')">${item.summonerName }</td>
					<td><img alt="이미지 없음" src="/res/img/emblems/${item.tier}.png" width="30px" height="30px"></td>
					<td>${item.leaguePoints } LP</td>
				</tr>
			</c:forEach>
			<c:if test="${err != null }">
				${err }
			</c:if>
		</table>
		</div>
		<div class="right_border">
			<div class="right_top">
				<div class="rankTitle">전체 인기글(주간)</div>
				<c:forEach items="${allWeek}" var="allWeek" varStatus="i">
					<div class="allWeek">
						${i.index+1} <a class="cursor" onclick="goFreeDetail(${allWeek.i_board},${allWeek.i_category})">${allWeek.title}</a> 
					</div>
				</c:forEach>
			</div>
			<div class="right_bottom">
				<div class="free_border_rank">
					<div class="rankTitle">자유 인기글(주간)</div>
					<c:forEach items="${freeWeek}" var="freeWeek" varStatus="i">
						<div class="freeWeek">
							${i.index+1} <a class="cursor" onclick="goFreeDetail(${freeWeek.i_board},${freeWeek.i_category})">${freeWeek.title}</a> 
						</div>
					</c:forEach>
				</div>
				<div class="border_rank">
					<div class="rankTitle">유머 인기글(주간)</div>
					<c:forEach items="${humorWeek}" var="humorWeek" varStatus="i">
						<div class="humorWeek">
							${i.index+1} <a class="cursor" onclick="goFreeDetail(${humorWeek.i_board},${humorWeek.i_category})">${humorWeek.title}</a> 
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function goSearch(name){
		location.href = "/board/nameSearch?name="+name
	}
	
	function goFreeDetail(i_board,i_category){
		console.log(i_category)
		location.href = "/board/free_detail?i_board="+i_board+"&i_category="+i_category;
	}
</script>