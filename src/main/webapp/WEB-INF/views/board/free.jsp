<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<div class="free_container">
	<div class=""></div>
	<div class="free_top">
		<div class="free_left">asd</div>
		<div class="free_right">asd</div>
	</div>
	<div class="free_bottom">
			<c:forEach items="${data}" var="item">
				<article class="contentList" id="contentList1">
					<div class="board_no">${item.i_board}</div>
					<div class="board_title" onclick="goFreeDetail(${item.i_board})">
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
				<a href="/board/free?curPage=${page.startIdx-1}&curRange=${page.curRange-1}">이전</a>
			</c:if>
			<c:forEach var="i" begin="${page.startIdx}" end="${page.endIdx}">
				<c:choose>
					<c:when test="${i == page.curPage}">
						<span class="curPage">${i}</span>
					</c:when>
					<c:otherwise>
						<span><a href="/board/free?curPage=${i}&curRange=${page.curRange}">${i}</a></span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${page.curRange != page.rangeCnt}">
				<a href="/board/free?curPage=${page.endIdx+1}&curRange=${page.curRange+1}">다음</a>
			</c:if>
		<div>
			<form action="/board/free">
				<select name="searchType">
					<option value="1">제목</option>
					<option value="2">내용</option>
					<option value="3">제목+내용</option>
				</select>
				<input type="search" name="searchText">
				<input type="submit" value="검색">
			</form>
		</div>
		<div><button onclick="goBoardWR(${login_user == null ? 0 : 1})">글쓰기</button></div>
	</div>
</div>

<script>
	
	function goFreeDetail(i_board){
		location.href = "/board/free_detail?i_board="+i_board;
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
