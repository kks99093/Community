<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="profileUpdatePage">
	<div class="profileUpload">
		<c:choose>
			<c:when test="${login_user.profile_img == null }">
				<div><img src="/res/img/default_img.jpg"></div>
			</c:when>
			<c:otherwise>
				<div><img  src="/res/img/user/${login_user.i_user}/profileIcon/${login_user.profile_img}"></div>
			</c:otherwise>
		</c:choose>
		
		<div>
			<form action="/user/updProfile" method="post" enctype="multipart/form-data">
				<input type="hidden" name="i_user" value="${login_user.i_user}">
				<input type="file" name="profile_img">
				<input type="submit" value="등록">
			</form>
		</div>
	</div>
	<div class="p_userInfo">
		<div>아이디 : ${login_user.user_id } </div>
		<div>닉네임 : ${login_user.nick_nm }</div>
		<div>가입일: ${login_user.r_dt }</div>
	</div>
</div>