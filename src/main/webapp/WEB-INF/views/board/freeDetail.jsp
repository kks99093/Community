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
					<div>추천수</div>
				</div>
			</div>
	</div>
	<div class="detail_content">
		${content[0].content }
	</div>
	<div class="vote">
		<button>추천 :23</button>
	</div>
	<div>
		<c:forEach items="${cmt}" var="item">
			<div>글쓴이 : ${item.nick_nm}, 코멘트: ${item.c_content} 날짜${item.r_dt} </div>
		</c:forEach>
	</div>
</div>