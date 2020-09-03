<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필</title>
</head>
<body>
	<h1>프로필</h1>
	<div>
		<div>
			<a href="/changePw"><button>비밀번호 변경</button></a>
		</div>
		<div>
			<c:choose>
				<c:when test="${data.profile_img != null}">
					<img src="/img/user/${loginUser.i_user}/${data.profile_img}">
				</c:when>
				<c:otherwise>
					<img src="/img/default_profile.jpg">
				</c:otherwise>
			</c:choose>
		</div>
		<div>
			<form action="/profile" method="post" enctype="multipart/form-data">
				<div>
					<label>프로필 이미지 : <input type="file" name="profile_img" accept="image/*"></label>
					<input type="submit" value="업로드">
				</div>				
			</form>
		</div>
		<div>ID : ${data.user_id }</div>
		<div>이름 : ${data.nm }</div>
		<div>이메일 : ${data.email }</div>
		<div>가입일시 : ${data.r_dt }</div>
	</div>
	<script>
		var proc = '${param.proc}'
		switch(proc) {
		case '1':
			alert('비밀번호를 변경하였습니다.')
			break
		}
	</script>
</body>
</html>