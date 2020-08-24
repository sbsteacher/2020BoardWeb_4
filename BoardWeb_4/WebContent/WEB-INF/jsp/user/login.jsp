<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	* {
		font-family: 'Noto Sans KR', sans-serif;
	}
	*:focus { 
		outline:none; 
	}
	.container {
		width: 300px;
		margin: 80px auto; 
		border-radius: 7%;
		background-color: #faf9f7;
		padding: 20px;
	}
	h1 {
		margin-bottom: 10px;
		color: #58585a;
		font-size: 1.8em;
		text-align: center;
	}
	#frm{
		margin : 20px;
		margin-top: 40px;
	}
	#join {
		text-decoration: none;
		color: #58585a;
		margin: 20px;
		font-weight: bold;
	}
	#frm input {
		width: 200px;
		padding: 7px;
		border: 0;
		border-bottom: 2px solid #58585a;
		color: #58585a;
		text-indent: 10px;
		background: #faf9f7;
		font-weight: bold;'
	}
	#frm div {
		margin : 20px;
	}
	#frm button {
		width: 100px;
		background-color: #f5d1ca;
		text-align: center;
		border: none;
		padding: 8px;
		color: #58585a;
		border-radius: 10px;
		margin-top: 20px;
		margin-left: 138px;
		font-weight : bolder;
	}
	.err {
		color: #ff6f69;
		text-align: center;
		font-size: 0.9em;
		font-weight : bold;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>Login</h1>
		<div>
			<form id="frm" action="/login" method="post">
				<div><input type="text" name="user_id" placeholder="아이디" autofocus value="${user_id }"></div>
				<div><input type="password" name="user_pw" placeholder="비밀번호"></div>
				<div><button type="submit">로그인</button></div>
			</form>
			<div class="err">${msg }</div>
			<a href="/join" id="join">JOIN</a>
		</div>
	</div>
	<script>
	</script>
</body>
</html>