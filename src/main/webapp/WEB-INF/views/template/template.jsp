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
			<h1 id="logo"><a href="/">다성이 바보</a></h1>
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
				<c:if test="${login_user.i_user == null }">
					<div class="login_form">
						<form id="login_frm" action="/user/login" method="post" onsubmit="return login_chk()">
							<p>${msg}</p>
				     		<div class="login_input"><input type="text" name="user_id" placeholder="아이디"></div>
				     		<div class="login_input"><input type="password" name="user_pw" placeholder="비밀번호"></div>
				     		<div class="login_submit"><input type="submit" value="로그인"></div>
						</form>
					</div>
				</c:if>
				<div class="move_menu">
					<div class="menu_div" onclick="home()">홈</div>
					<div class="menu_div" onclick="freeBoard()">자유</div>
					<div class="menu_div">세번째</div>
					<div class="menu_div">네번째</div>
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
	    
	    function freeBoard(){
	    	location.href= '/board/free'
	    }
	    
	    function home(){
	    	location.href= '/'
	    }
	    
    </script>

</body>
</html>