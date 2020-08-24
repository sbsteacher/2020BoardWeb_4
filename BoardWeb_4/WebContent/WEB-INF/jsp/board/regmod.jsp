<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록/수정</title>
<style>
	* {
		font-family: 'Noto Sans KR', sans-serif;
		background-color: #faf9f7;
	}
	*:focus { 
		outline:none; 
	}
	.container {
		width: 700px;
		margin: 50PX auto; 
		padding: 20px;
	}
	#frm input {
		width: 500px;
		padding: 7px;
		border: 0;
		color: #58585a;
		border-bottom:1px solid black;
	}
	#frm textarea {
		margin-top: 20px;
		width: 500px;
		padding: 7px;
		border: 1px solid #58585a;
		color: #58585a;
	}
	#frm button {
		width: 100px;
		background-color: #f5d1ca;
		text-align: center;
		border: none;
		padding: 8px;
		color: #58585a;
		border-radius: 10px;
		margin: 20px 0px 0px 200px;
		font-weight : bold;
	}
	 
</style>
</head>
<body>
	<div class="container">
		<form id="frm" action="regmod" method="post">
			<div><input type="hidden" name="i_board" value="${data.i_board }"></div>
			<div><input id="title" type="text" name="title" placeholder="제목을 입력하세요" value="${data.title }"></div>
			<div><textarea name="ctnt" placeholder="내용을 입력하세요">${data.ctnt }</textarea></div>
			<div><button type="submit">${data.i_board == null ? '글등록' : '글수정'}</button></div>
		</form>
	</div>
</body>
</html>