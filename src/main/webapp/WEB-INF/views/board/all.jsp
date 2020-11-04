<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<div class="free_container">
	<div class="free_top">
		<div class="free_left">
			<c:forEach items="${week}" var="week" varStatus="i">
				<div class="weekTitle">
					${i.index+1} <a class="cursor" onclick="goFreeDetail(${week.i_board},${i_category})">${week.title}</a>
				</div>
			</c:forEach>
		</div>
		<div class="free_right">
			<c:forEach items="${month}" var="month" varStatus="i">
				<div class="monthTitle">
					${i.index+1} <a class="cursor" onclick="goFreeDetail(${month.i_board},${i_category})">${month.title}</a>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="free_bottom">
			<c:forEach items="${data}" var="item">
				<article class="contentList" id="contentList1">
					<div class="board_no">${item.i_board}</div>
					<div class="board_cate">[ ${item.category } ]</div>
					<div class="board_title" onclick="goFreeDetail(${item.i_board},${i_category})">
						<c:if test="${searchType == 1 || searchType == 3 }">
							<c:if test="${item.title == searchType }">
								<span style="font-weight: bold">${item.title } <span>[${item.cmtCnt}]</span></span>
							</c:if>
						</c:if>
					${item.title } <span>[${item.cmtCnt}]</span>
					</div>
					<div class="board_r_dt">${item.r_dt }</div>
					<div class="board_nickNm">${item.nick_nm }</div>
				</article>
			</c:forEach>
			<c:if test="${page.curRange != 1}">
				<a href="/board/allBoard?curPage=${page.startIdx-1}&curRange=${page.curRange-1}&i_category=${i_category}">이전</a>
			</c:if>
			<c:forEach var="i" begin="${page.startIdx}" end="${page.endIdx}">
				<c:choose>
					<c:when test="${i == page.curPage}">
						<span class="curPage">${i}</span>
					</c:when>
					<c:otherwise>
						<span><a href="/board/allBoard?curPage=${i}&curRange=${page.curRange}&i_category=${i_category}">${i}</a></span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${page.curRange != page.rangeCnt}">
				<a href="/board/allBoard?curPage=${page.endIdx+1}&curRange=${page.curRange+1}&i_category=${i_category}">다음</a>
			</c:if>
		<div>
			<form action="/board/allBoard">
				<select name="searchType">
					<option value="1">제목</option>
					<option value="2">내용</option>
					<option value="3">제목+내용</option>
				</select>
				<input type="hidden" name="i_category" value="${i_category }">
				<input type="search" name="searchText">
				<input type="submit" value="검색">
			</form>
		</div>
		<div><button onclick="goBoardWR(${login_user == null ? 0 : 1})">글쓰기</button></div>
	</div>
</div>

<script>
	
	function goFreeDetail(i_board,i_category){
		location.href = "/board/free_detail?i_board="+i_board+"&i_category="+i_category;
	}
	
	function goBoardWR(num){
		//0이면 로그인 X, 1이면 로그인O
		if(num == 0){
			alert('로그인을 해주세요')
			return false
		}else{
			location.href = "/board/boardWR";
		}
	}
</script>
