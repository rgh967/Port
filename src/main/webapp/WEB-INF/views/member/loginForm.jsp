<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="resources/js/jquery-3.5.1.js"></script>
<link href="resources/css/loginForm.css" type="text/css" rel="stylesheet">
<title>로그인</title>

<script>
	$(function () {
		$(".join").click(function(){
			location.href="join.net";
		});
		$(".find").click(function(){
			location.href="find.net";
		});
		
	})
</script>

</head>
<body>

<form name="loginform" action="loginProcess.net" method="post">
	
		<a href="main.net">
			<img src="resources/image/logo.png">
		</a>
		<br>
		<input type="text" name="id" id="id" placeholder="아이디" required 
			<c:if test="${!empty saveid}">
				  value="${saveid}"
			</c:if>
		>
		<input type="password" name="password" placeholder="비밀번호" required><br>
		

		<div class="clearfix">
			<button type="submit" class="submitbtn">로그인</button>
		</div>
		
		<input type="checkbox" id="remember" name="remember" style="margin-bottom:15px"
			<c:if test="${!empty saveid}">
				checked
			</c:if>
		>로그인 상태 유지
		
		<br>
		<button type="button" class="find">비밀번호 찾기</button>
		<button type="button" class="join">회원가입</button>
		
		
		
	</form>
</body>
</html>