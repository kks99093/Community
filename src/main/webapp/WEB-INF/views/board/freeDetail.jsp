<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<div class="free_Detail">
	<div class="detail_header">
		<div class="detail_title">${content.title }</div>
			<div class="detail_info">
				<div class="header_left">
					<div class="detail_r_dt">${content.r_dt}</div>
					<div class="detail_nickNm">${content.nick_nm}</div>
				</div>
				<div class="header_right">
					<div>조회수</div>
					<div>댓글수</div>
				</div>
			</div>
			<c:if test="${login_user.i_user == content.i_user}">
				<div class="writeReg">
					<button onclick="boardReg(${content.i_board})">수정</button> <button onclick="boardDel(${content.i_board})">삭제</button>
				</div>
			</c:if>
			<div class="detail_content">
				${content.content }
			</div>
			<div class="vote">
				<button id="likeCnt" onclick="insDelLike(${content.i_board},${login_user.i_user })" style="background: ${likeCk == null ? '#ffffff' : '#fafd86'};"></button>
			</div>
	</div>
	<div class="cmtScreen">
		<div class="cmt_title">댓글</div>
		<c:forEach items="${cmt}" var="item" varStatus="status">
			<div class="cmt">
				<div class="cmtMain">
					<div class="cmtNm">${item.nick_nm} <span class="cmt_r_dt">${item.r_dt }</span></div>
					<div class="cmtContent"> ${item.c_content }</div>
					<div class="l_cmtIns">답글달기</div>
				</div>
			</div>
		</c:forEach>
		<c:if test="${login_user != null }">
			<div class="ins_cmt">
				<form action="/board/insCmt" method="post">
					<input type="hidden" name="i_user" value="${login_user.i_user }">
					<input type="hidden" name="i_board" value="${content.i_board }">
					<input type="text" name="c_content">
					<input type="submit" value="등록">
				</form>
			</div>
		</c:if>
	</div>
</div>
<div class="free_container">
	<div class="free_bottom">
			<c:forEach items="${data}" var="item">
				<article class="contentList" id="contentList1">
					<div class="board_no">${item.i_board}</div>
					<div class="board_title" onclick="goFreeDetail(${item.i_board})" style="font-weight: ${item.i_board == content.i_board ? 'bold' : ''}">
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

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
	function boardReg(i_board){
		location.href = "/board/boardWR?i_board="+i_board;
	}
	
	//좋아요 ins Del
	function insDelLike(i_board,i_user){
		if(i_user == null){
			alert('로그인을 해주세요')
			return
		}
		axios.get('/board/insLike',{
			params:{
				i_board : i_board,
				i_user : i_user
			}
		}).then(function(res){
			if(res.data == 1){
				likeCnt.style.background = '#fafd86'
			}else{
				likeCnt.style.background = '#ffffff'
			}
			selLikeCnt(i_board)
		})
	}
	
	//좋아요 sel
	function selLikeCnt(i_board){
		axios.get('/board/selLikeCnt',{
			params:{
				i_board : i_board
			}
		}).then(function(res){
			likeCnt.innerHTML = res.data.like_cnt
		})
	}
	selLikeCnt(${content.i_board})
	
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