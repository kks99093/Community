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
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
	function boardReg(i_board){
		location.href = "/board/boardWR?"+i_board;
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

</script>