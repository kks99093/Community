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
					<div class="board_title" onclick="goFreeDetail(${item.i_board})">${item.title }</div>
					<div class="board_r_dt">${item.r_dt }</div>
					<div class="board_nickNm">${item.nick_nm }</div>
				</article>
			</c:forEach>
		<a href="/board/boardWR">글쓰기</a>
	</div>
</div>

<script>

	function goFreeDetail(i_board){
		location.href = "/board/free_detail?i_board="+i_board;
	}
</script>
