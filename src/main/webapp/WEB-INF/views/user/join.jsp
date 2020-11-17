<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<c:forEach items="${css}" var="item">
	<link rel="stylesheet" type="text/css" href="/res/css/${item }.css">
</c:forEach>
<div id="user_join">
	<form id="join_frm" action="/user/join" method="post" onsubmit="return ChkJoin()">
		<fieldset id="join_box">
			<h1>회원가입</h1>
			<ul>
				<li>
					<div id="overlap_res"></div>
					<input type="text" name="user_id" placeholder="아이디" onkeyup="input_reset()">
					<button type="button" onclick="overlapChk()" name="id_Chk">중복확인</button>
				</li>
				<li><input type="password" name="user_pw" placeholder="비밀번호 (영어,숫자,특수문자를 포함한 8~15글자)"></li>
				<li><input type="password" name="user_pwre" placeholder="비밀번호 확인"></li>
				<li><input type="text" name="user_nm" placeholder="이름"></li>
				<li>
					<div id="n_overlap_res"></div>
					<input type="text" name="nick_nm" placeholder="닉네임" onkeyup="input_reset()">
					<button type="button" onclick="overlapChk2()" name="nick_Chk">중복확인</button>	
				</li>
				<li><input type="email" name="user_email" placeholder="이메일">
					<button type="button" onclick="sendEmail()">이메일 인증</button></li>
				<li id="atuhKeyForm" style="visibility:hidden;">
					<input type="text" name="inputAuthKey">
					<button type="button" onclick="emailChk()">확인</button></li>
				<li><input type="submit" value="회원가입"></li>
				<li><input type="hidden" name="authKey"></li>
				<li><input type="hidden" name="emailOK" value="0"></li>
			</ul>					
		</fieldset>
     </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>

	//인증메일 보내기
	function sendEmail(){
		const user_email = join_frm.user_email.value
		
		if(CheckEmail(user_email)){
			axios.post('/user/sendEmail',{
				user_email 
			}).then(function(res){
				console.log(res.data)
				atuhKeyForm.style.visibility ='visible'
				join_frm.authKey.value = res.data
			})		
		}else{
			alert('이메일을 확인해 주세요')
		}
		
	}
	
	//인증키 확인
	function emailChk(){
		console.log(join_frm.inputAuthKey.value)
		if(join_frm.inputAuthKey.value == join_frm.authKey.value){
			alert('인증 성공')
			join_frm.emailOK.value = 1
 		}else{
			alert('인증번호가 다릅니다')
		}
	}
	
	//이메일 형식 체크
	function CheckEmail(str){                                                 
	     var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
	     if(!reg_email.test(str)) {                            
	          return false;         
	     }                            
	     else {                       
	          return true;         
	     }                            
	}
	//아이디 중복체크
	function overlapChk(){
		var idRule = /^[a-z]+[a-z0-9]{5,15}$/g;
		if( !idRule.test( $("input[name=user_id]").val())){
			alert('아이디를 확인해주세요 (영문,숫자를 포함한 6 ~ 15글자)')
			return
		}
		const user_id = join_frm.user_id.value
		axios.post('/user/overlapChk',{
			user_id,
		}).then(function(res){
			if(res.data == 1){
				overlap_res.innerHTML = '중복된 아이디 입니다'
				join_frm.id_Chk.value = res.data
			}else{
				overlap_res.innerHTML = '사용할수 있는 아이디 입니다'
				join_frm.id_Chk.value = res.data
			}
		})
	}
	
	//인풋 초기화
	function input_reset(){
		if(join_frm.id_Chk.value != 0){
			join_frm.id_Chk.value = 0
			overlap_res.innerHTML = '';
		}
		
		if(join_frm.nick_Chk.value != 0){
			join_frm.nick_Chk.value = 0
			n_overlap_res.innerHTML = '';
		}
	}
	
	//닉네임 중복체크
	function overlapChk2(){
		if(join_frm.nick_nm.value.length >10 || join_frm.nick_nm.value.length < 3){
			alert('닉네임을 확인해주세요(3~10글자)')
			return
		}
		
		const nick_nm = join_frm.nick_nm.value
		axios.post('/user/overlapChk',{
			nick_nm
		}).then(function(res){
			if(res.data == 1){
				n_overlap_res.innerHTML = '중복된 닉네임 입니다'
				join_frm.nick_Chk.value = res.data
			}else{
				n_overlap_res.innerHTML = '사용할수 있는 닉네임 입니다'
				join_frm.nick_Chk.value = res.data
			}
		})
	}
	
	//서브밋전 정규화
	function ChkJoin(){
		let passRule = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		let emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		let idRule = /^[a-z]+[a-z0-9]{5,15}$/g;
		let nameRule = /^[가-힣]{2,4}$/;
		
		if( !idRule.test( $("input[name='user_id']").val())){
			alert('아이디를 확인해주세요 (영문,숫자를 포함한 6 ~ 15글자)')
			return
		}else if(!passRule.test($("input[name='user_pw']").val())){
			alert('비밀번호는 영문,숫자,특수문자를 포함한 8~15글자를 적어주세요')
			return false
		}else if(join_frm.user_pw.value != join_frm.user_pwre.value){
			alert('비밀번호를 확인해 주세요')
			return false
		}else if(!nameRule.test($("input[name='user_nm']").val())){
			alert('이름을 확인해 주세요(2~4글자 한글)')
			return false
		}else if(join_frm.nick_nm.value.length >10 || join_frm.nick_nm.value.length < 3){
			alert('닉네임을 확인해주세요(3~10글자)')
			return false
		}else if(!emailRule.test($("input[name='user_email']").val())){
			alert('이메일 인증을 해주세요')
			return false
		}else if(join_frm.id_Chk.value == 1){
			alert('중복된 아이디 입니다')
			return false
		}
		else if(join_frm.nick_Chk.value == 1){
			alert('중복된 닉네임 입니다')
			return false
		}
		return true
	}
</script>