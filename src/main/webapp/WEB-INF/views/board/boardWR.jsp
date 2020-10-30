<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="board_WR">
	<form id="wR_frm" action="boardWR" method="post" onsubmit="return wRChk()">
		<input type="hidden" name="i_user" value="${login_user.i_user }">
		<div class="top">
			<div class="title">
				<div><label>제목</label></div>
				<input type="text" name="title" value="${content[0].title == null ? '' : content[0].title }">
			</div>
			<div class="group">
				<select name="i_category" id="category">
                    <option value="0">카테고리</option>
                    <c:forEach items="${category}" var="cg">
                		<option value="${cg.i_category}">${cg.category}</option>
                	</c:forEach>
                </select>
                  
			</div>
		</div>
		<div class="mid">
			<textarea name="content" id="content">
				<c:if test="${content[0].content != null}">
					${content[0].content}
				</c:if>
			</textarea>
		</div>
		<div class="bottom">
			<input type="submit" value="등  록">
		</div>
	</form>
</div>
<script src="/res/ckeditor/ckeditor.js"></script>
<script>
 CKEDITOR.replace('content', {
		 				height: 500,
		 				filebrowserUploadUrl:'/imageUpload'}
 );
</script>
<script>

	function wRChk(){
		if(wR_frm.i_category.value == 0){
			alert('카테고리를 선택해주세요')
			return false;
		}else if(wR_from.title.length < 1){
			alert('제목을 적어주세요')
			return false;
		}else if(wR_from.content.length < 1){
			alert('내용을 적어주세요')
			return false;
		}
		
		return true
	}

	
</script>