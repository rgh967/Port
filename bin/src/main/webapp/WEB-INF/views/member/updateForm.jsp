<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script src="resources/js/jquery-3.5.1.js"></script>
<title>회원관리 시스템 회원수정 페이지</title>
<link rel="stylesheet" type="text/css" href="resources/css/joinForm.css" />
<style>
	h3{
		text-align: center; color: #1a92b9;
	}
</style>
</head>
<body>
<jsp:include page = "../board/header.jsp" />
	<form name="updateform" action="updateProcess.net" method="post">
		<h3>회원 정보 수정</h3>
		<hr>
		<b>아이디</b>
		<input type="text" class="join" name="MEMBER_ID" value="${memberinfo.MEMBER_ID}" readOnly>
	
		<b>비밀번호</b>
		<input type="password" class="join" name="MEMBER_PASSWORD" placeholder="비밀번호 재설정" required>
		<span id="message_psw"></span>
		
		<b>닉네임</b>
		<input type="text" class="join" name="MEMBER_NAME" value="${memberinfo.MEMBER_NAME}" readOnly> 
		
		<b>이메일 </b>
		<input type="text" class="join" name="MEMBER_EMAIL" value="${memberinfo.MEMBER_EMAIL}"  required>
		<span id="message_email"></span>
		
		<b>휴대폰번호</b>
		<input type="text" class="join" name="MEMBER_PHONE" value="${memberinfo.MEMBER_PHONE}"  required> 
		<span id="message_phone"></span>
		
		<div class="clearfix">
			<button type="submit" class="submitbtn">수정</button>
			<button type="reset" class="cancelbtn">취소</button>
		</div>
	</form>
	<script>
	$(function() {
		
	
	var checkpsw=false;
	var checkemail=false;
	var checkphone=false;

	$('form').submit(function(){
		
		if(!checkpsw){
			alert("사용가능한 비밀번호로 입력하세요.");
			$("input:eq(3)").val('').focus();
			return false;
			}
		
		if(!checkemail){
			alert("email 형식을 확인하세요");
			$("input:eq(5)").focus();
			return false;
		}
		
		if(!checkphone){
			alert("휴대폰 형식을 확인하세요");
			$("input:eq(6)").focus();
			return false;
		}
	}); // submit end
	
	$('input:eq(3)').on('keyup',function() {
		$("#message_psw").empty();
		var pattern = /^[A-Za-z0-9]{6,12}$/;
		var psw = $("input:eq(3)").val();
		if(!pattern.test(psw)) {
			$("#message_psw").css("color", "red")
			.html("비밀번호는 숫자와 문자 포함 6~12자리 입니다.");
			checkpsw=false;
		}else{
			$("#message_psw").css("color", "green")
			.html("비밀번호 형식에 맞습니다.");
			checkpsw=true;
		}
	}); //psw keyup end
	
	$("input:eq(5)").on('keyup',function() {
		
		$("#message_email").empty();
		//[A-Za-z0-9_]와 동일한 것이 \w
		//+는 1회 이상 반복 의미. {1,}와 동일함
		// \w 는 [A-Za-z0-9_]를 1개이상 사용하라는 말입니다.
		var pattern = /^\w+@\w+[.]\w{3}$/;
		var email = $("input:eq(5)").val();
		if(!pattern.test(email)) {
			$("#message_email").css("color", "red")
			.html("이메일 형식에 맞지 않습니다.");
		checkemail=false;
		}else{
			$("#message_email").css("color", "green")
			.html("이메일 형식에 맞습니다.");
		checkemail=true;
		}
	}); //email keyup end
	
	$("input:eq(6)").on('keyup',function() {
		
		$("#message_phone").empty();
		var pattern = /^\d{3}-\d{3,4}-\d{4}$/;;
		var phone = $("input:eq(6)").val();
		if(!pattern.test(phone)) {
			$("#message_phone").css("color", "red")
			.html("휴대폰 형식에 맞지 않습니다.");
			checkphone=false;
		}else{
			$("#message_phone").css("color", "green")
			.html("휴대폰 형식에 맞습니다.");
			checkphone=true;
		}
	}); //phone keyup end
		
});
	</script>
</body>
</html>