<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>상세페이지</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <style>
        * {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #faf9f7;
        }

        *:focus {
            outline: none;
        }


        .container {
            width: 700px;
            margin: 30px auto;
        }

        table {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th,
        td {
            /* border: 1px solid black; */
            padding: 8px;
        }

        #title {
            border-bottom: 1px solid #58585a;
        }

        /* .boardInfo {
            border-bottom: 1px solid #58585a;
        } */

        #nm {
            width: 10%;
        }

        #nm-1 {
            width: 33%;
        }

        #date {
            width: 12%;
        }

        #date-1 {
            width: 25%;
        }

        #hits {
            width: 10%;
        }

        #hits-1 {
            width: 10%;
        }

        .ctnt {
            border-right: 1px solid #58585a;
            border-left: 1px solid #58585a;
            border-bottom: 1px solid #58585a;
            height: 200px;
            padding: 10px;
        }

        .btn a {
            text-decoration: none;
            color: #58585a;
            background-color: #f5d1ca;
        }

        .btn button {
            width: 100px;
            background-color: #f5d1ca;
            text-align: center;
            border: none;
            padding: 8px;
            color: #58585a;
            border-radius: 10px;
            margin-top: 20px;
            margin-right: 20px;
            font-weight: bold;
            font-size: 0.9em;
        }

        #delFrm {
            display: inline-block;
        }
        
    </style>
</head>

<body>
    <div class="container">
        <table>
            <tr id="title">
                <th>제목</th>
                <th colspan="6">${data.title}</th>
            </tr>
            <tr class="boardInfo">
                <th id="nm">작성자</th>
                <td id="nm-1">${data.nm }</td>
                <th id="date">작성일시</th>
                <td id="date-1"> ${data.r_dt } <small>${data == null ? '' : '수정' }</small> </td>
                <th id="hits">조회수</th>
                <td id="hits-1">${data.hits }</td>                
                <td>
                	<c:if test="${data.yn_like == 0 }">
						<span class="material-icons">favorite_border</span>                	
                	</c:if>
                	<c:if test="${data.yn_like == 1}">
                		<span class="material-icons" style="color: red;">favorite</span>
                	</c:if>
                </td>
            </tr>
        </table>
        <div class="ctnt">
            ${data.ctnt }
        </div>
        <div class="btn">
             <button type="button"><a href="/board/list">목록</a></button>
             <c:if test="${loginUser.i_user == data.i_user }">
                <button type="submit">
                    <a href="/board/regmod?i_board=${data.i_board}">수정</a>
                </button>
                <form id="delFrm" action="/board/del" method="post">
                    <input type="hidden" name="i_board" value="${data.i_board}">
                    <button type="submit"><a href="#" onclick="submitDel()">삭제</a></button>
                </form>
            </c:if>
        </div>
    </div>

    <script>
        function submitDel() {
            delFrm.submit()
        }
    </script>
</body>

</html>