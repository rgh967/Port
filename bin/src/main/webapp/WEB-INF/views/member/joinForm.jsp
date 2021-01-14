<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/joinForm.css" />
<meta charset="UTF-8">
<title>유효성 검사</title>
<script src = "resources/js/jquery-3.5.1.js"></script>
<script>
$(function() {
	$("input:eq(0)").on('keyup',function() {
		$("#message").empty();
		var pattern = /^\w{5,12}$/;
		var id = $('input:eq(0)').val();
		if(!pattern.test(id)){
			$("#message").css("color", "red")
							.html("영문자 숫자 _로 5~12자 가능합니다.");
			checkid=false;
			return;
		}
		
		$.ajax({
			url: "idcheck.net",
			data:{"id" : id},
			success : function(resp) {
				if(resp == -1){
					$("#message").css("color", "green")
							.html("사용 가능한 아이디 입니다.");
					checkid=true;
				}else{
					$("#message").css("color", "blue")
							.html("사용중인 아이디 입니다.");
					checkid=false;
				}
			}		
		})
		
	});
	
	$("input:eq(3)").on('keyup',function() {
		$("#message_name").empty();
		var name = $('input:eq(3)').val();
		
		if ($('input:eq(3)').val().length < 4) {
			$("#message_name").css("color", "red")
			.html("닉네임은 4글자 이상입니다.");
			checkname=false;
			return;
		}
		$.ajax({
			url: "namecheck.net",
			data:{"name" : name},
			success : function(resp) {
				if(resp == -1){
					$("#message_name").css("color", "green")
							.html("사용 가능한 닉네임 입니다.");
					checkname=true;
				}else{
					$("#message_name").css("color", "blue")
							.html("사용중인 닉네임 입니다.");
					checkname=false;
				}
			}		
		})
		
	});
/* 
	$("#email_check").click(function() {
		var MEMBER_EMAIL = $('input:eq(4)').val();
		$.ajax({
			url: "auth.do",
			type : "POST",
			data:{"MEMBER_EMAIL" : MEMBER_EMAIL},
			datatype:"JSON",
			async: true,
			success : function(data){
			
			}
		});
		return result;
		
	}); */

		var checkid=false;
		var checkname=false;
		var checkpsw=false;
		var checkemail=false;
		var checkphone=false;
	
	$('#submitbtn').click(function(){

		
		if(!checkid){
			alert("사용가능한 id로 입력하세요.");
			$("input:eq(0)").val('').focus();
			return false;
		}
		
		if(!checkname){
			alert("사용가능한 닉네임을 입력하세요.");
			$("input:eq(3)").val('').focus();
			return false;
		}
		
		if(!checkpsw){
			alert("사용가능한 비밀번호로 입력하세요.");
			$("input:eq(1)").val('').focus();
			return false;
		}
		
		if(!checkemail){
			alert("email 형식을 확인하세요");
			$("input:eq(4)").focus();
			return false;
		}
		
		if(!checkphone){
			alert("휴대폰 형식을 확인하세요");
			$("input:eq(5)").focus();
			return false;
		}
		
		
	})//submit end
	
	$('input:eq(1)').on('keyup',function() {
		$("#message_psw").empty();
		var pattern = /^[A-Za-z0-9]{6,12}$/;
		var psw = $("input:eq(1)").val();
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
	
	$('input:eq(2)').on('keyup',function() {
		$("#message_psw").empty();
		if ($('input:eq(1)').val() != $('input:eq(2)').val()) {
			$("#message_psw").css("color", "red")
			.html("패스워드가 일치하지 않습니다.");
			checkpsw=false;
		}else{
			$("#message_psw").css("color", "green")
			.html("패스워드가 일치합니다.");
			checkpsw=true;
		}
	}); //pswchk keyup end
	
	
	$("input:eq(4)").on('keyup',function() {
		
		$("#message_email").empty();
		//[A-Za-z0-9_]와 동일한 것이 \w
		//+는 1회 이상 반복 의미. {1,}와 동일함
		// \w 는 [A-Za-z0-9_]를 1개이상 사용하라는 말입니다.
		var pattern = /^\w+@\w+[.]\w{3}$/;
		var email = $("input:eq(4)").val();
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
		
	$("input:eq(5)").on('keyup',function() {
		
		$("#message_phone").empty();
		var pattern = /^\d{3}-\d{3,4}-\d{4}$/;;
		var phone = $("input:eq(5)").val();
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
</head>
<body>
	 <form name="joinform" action="auth.do" method="post">
	<!-- <FORM name="joinform" action="joinProcess.net" method="post"> -->
		<a href="/index.html">
			<img src="resources/image/logo.png">
		</a>
		<b>아이디</b>
		<input type="text" class="join" name="MEMBER_ID" placeholder="아이디" required maxLength="10">
		<span id="message"></span>
		
		<b>비밀번호</b>
		<input type="password" class="join" name="MEMBER_PASSWORD" placeholder="비밀번호" required>
		
		<b>비밀번호 확인</b>
		<input type="password" class="join" name="passwordchk" placeholder="비밀번호 확인" required>
		<span id="message_psw"></span>
		
		<b>닉네임</b>
		<input type="text" class="join" name="MEMBER_NAME" placeholder="닉네임" maxLength="15" required> 
		<span id="message_name"></span>
		
		<b>이메일 </b>
		<input type="text" class="join" name="MEMBER_EMAIL" placeholder="이메일(***@***.com)" required>
		<span id="message_email"></span>
		
		
		<b>휴대폰번호</b>
		<input type="text" class="join" name="MEMBER_PHONE" placeholder="010-0000-0000" maxLength="15" required> 
		<span id="message_phone"></span>
		
		<div class="clearfix">
			<button type="submit" id="submitbtn" class="submitbtn">이메일 인증받기</button>

			<button type="reset" class="cancelbtn">다시작성</button>
		</div>
	</FORM>
</body>
</html>