<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<img src="${data.profile_img == null ? '/img/default_profile.jpg' : ''}">
		</div>
	
		<div>ID : ${data.user_id }</div>
		<div>이름 : ${data.nm }</div>
		<div>이메일 : ${data.email }</div>
		<div>가입일시 : ${data.r_dt }</div>
	</div>
</body>
</html>