<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>회원관리 시스템 관리자모드(회원 정보 보기)</title>
<jsp:include page = "../board/header.jsp" />
<style>
	tr>td:nth-child(odd){font-weight:bold}
	.container{width:50%}
	td{text-align: center}
</style>
</head>
<body>
<div class="container">
	<table class="table table-bordered">
		<tbody>
			<tr>
				<td>아이디</td>
				<td>${memberinfo.MEMBER_ID}</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>${memberinfo.MEMBER_PASSWORD}</td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td>${memberinfo.MEMBER_NAME}</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${memberinfo.MEMBER_EMAIL}</td>
			</tr>
			<tr>
				<td>휴대폰번호</td>
				<td>${memberinfo.MEMBER_PHONE}</td>
			</tr>
			<tr>
				<td colspan="2"><a href="member_list.net">리스트로 돌아가기</a></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>