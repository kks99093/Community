<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="free_Detail">
	<div class="detail_header">
		<div class="detail_title">${content[0].title }</div>
			<div class="detail_info">
				<div class="header_left">
					<div class="detail_r_dt">${content[0].r_dt}</div>
					<div class="detail_nickNm">${content[0].nick_nm}</div>
				</div>
				<div class="header_right">
					<div>조회수</div>
					<div>댓글수</div>
				</div>
			</div>
			<c:if test="${login_user.i_user == content[0].i_user}">
				<div class="writeReg">
					<button onclick="boardReg(${content[0].i_board})">수정</button> <button onclick="boardDel(${content[0].i_board})">삭제</button>
				</div>
			</c:if>
			<div class="detail_content">
				${content[0].content }
			</div>
			<div class="vote">
				<button id="likeCnt" onclick="insDelLike(${content[0].i_board},${login_user.i_user })" style="background: ${likeCk == null ? '#ffffff' : '#fafd86'};"></button>
			</div>
	</div>
	
	<div class="cmtScreen">
		<div class="cmt_title">댓글</div>
		<c:forEach items="${cmt}" var="item">
			<div class="cmt">
				<div class="recommned">
					추천수
				</div>
				<div class="cmtMain">
					<div class="cmtNm">${item.nick_nm} <span class="cmt_r_dt">${item.r_dt }</span></div>
					<div class="cmtContent"> ${item.c_content }</div>
					<div class="l_cmtIns">답글달기</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
	function boardReg(i_board){
		location.href = "/board/boardWR?"+i_board;
	}
	
	//좋아요 ins Del
	function insDelLike(i_board,i_user){
		axios.get('/board/insLike',{
			params:{
				i_board : i_board,
				i_user : i_user
			}
		}).then(function(res){
			if(res.data == 1){
				console.log('1')
				likeCnt.style.background = '#fafd86'
			}else{
				console.log('0')
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
			likeCnt.innerHTML = res.data.cnt
		})
	}
	
	selLikeCnt(${content[0].i_board})
</script>