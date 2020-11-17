<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
      <link rel="stylesheet" type="text/css" href="/res/css/template.css">
<c:forEach items="${css}" var="item">
	<link rel="stylesheet" type="text/css" href="/res/css/${item }.css">
</c:forEach>
</head>
<body>
	<div class="container">
		<div class="header">
			<h1 id="logo"><a href="/">VOLI.GG</a></h1>
			<div class="search_input">
				<form id="name_search_frm" action="/board/nameSearch">
					<input type="text" name="name" placeholder="닉네임을 입려해 주세요">
					<input type="submit" value="검색">
				</form>
			</div>
	    </div>
		<div class="c_container">
	       	<div class="flex_column">
	            <section class="main_section">
	            	<jsp:include page="/WEB-INF/views/${view}.jsp"></jsp:include>
	            </section>
	            <footer>
	            </footer>
	        </div>
			<div class="sidebar">
				<c:choose>
					<c:when test="${login_user.i_user == null }">
						<div class="login_form">
							<form id="login_frm" action="/user/login" method="post" onsubmit="return login_chk()">
								<p>${msg}</p>
					     		<div class="login_input"><input type="text" name="user_id" placeholder="아이디"></div>
					     		<div class="login_input"><input type="password" name="user_pw" placeholder="비밀번호"></div>
					     		<div class="login_submit"><input type="submit" value="로그인"></div>
							</form>
							<div class="join"><a href="/user/join">회원가입</a></div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="login_userInfo">
							<div class="login_userFrofile">
								<div class="profileImg">
									<c:choose>
										<c:when test="${login_user.profile_img == null }">
											<div class="userFrofile"><img src="/res/img/default_img.jpg"></div>
										</c:when>
										<c:otherwise>
											<div class="userFrofile"><img src="/res/img/user/${login_user.i_user}/profileIcon/${login_user.profile_img}"></div>
										</c:otherwise>
									</c:choose>
									<div class="profileImgUpd"><a href="/board/profileImg">이미지 수정</a></div>
								</div>
								<div class="user_nickNm">${login_user.nick_nm}</div>
							</div>
							<div class="login_userMy">
								<button class="user_myBoard" onclick="myBoardSel()">내 글</button>
								<button class="user_myCmt" onclick="myCmtSel()">내 댓글</button>
								<button class="user_myFavorite" onclick="myFavoriteSel()">좋아요</button>
							</div>
							<div class ="logout">
								<a href="/user/logout">로그아웃</a>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="move_menu">
					<div class="menu_div" onclick="home()">홈</div>
					<div class="menu_div" onclick="allBoard()">전체</div>
					<div class="menu_div" onclick="freeBoard()">자유</div>
					<div class="menu_div" onclick="humorBoard()">유머</div>
				</div>
	       	</div>
	    </div>
    </div>
    <script>
	    function login_chk(){
	    	if(login_frm.user_id.value.length < 1){
	    		alert('아이디를 재대로 입력해주세요')
	    		console.log(login_frm.auto_login.value)
	    		return false
	    	}else if(login_frm.user_pw.value.length < 1){
	    		console.log(login_frm.auto_login.checked)
	    		alert('비밀번호를 확인해 주세요')
	    		return false
	    	}
	    	
	    	return true
	    }
	    
	    function myBoardSel(){
	    	location.href= '/board/myBoardSel?i_user='
	    }
	    
		function myCmtSel(){
			location.href= '/board/myCmtSel'
	    }
		
		function myFavoriteSel(){
			location.href= '/board/myFavoriteSel'
	    }
	    
	    function freeBoard(){
	    	location.href= '/board/allBoard?i_category='+1
	    }
	    function allBoard(){
	    	location.href= '/board/allBoard'
	    }
	    function humorBoard(){
	    	location.href= '/board/allBoard?i_category='+2
	    }
	    
	    function home(){
	    	location.href= '/'
	    }
	    
    </script>

</body>
</html>