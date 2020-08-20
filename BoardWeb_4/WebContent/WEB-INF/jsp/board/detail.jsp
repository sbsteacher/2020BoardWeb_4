<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
</head>
<body>
	<div>
		<a href="/board/list">리스트</a>
	</div>
	<div>제목: ${data.title}</div>
	<div>일시: ${data.r_dt}</div>
	<div>작성자: ${data.nm}</div>
	<div>조회수: ${data.hits}</div>
	<hr>
	<div>${data.ctnt}</div>
</body>
</html>