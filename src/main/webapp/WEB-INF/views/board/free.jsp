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
		<button onclick="goBoardWR(${login_user == null ? 0 : 1})">글쓰기</button>
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
