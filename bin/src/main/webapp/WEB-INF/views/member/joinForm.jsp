<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/joinForm.css" />
<meta charset="UTF-8">
<title>��ȿ�� �˻�</title>
<script src = "resources/js/jquery-3.5.1.js"></script>
<script>
$(function() {
	$("input:eq(0)").on('keyup',function() {
		$("#message").empty();
		var pattern = /^\w{5,12}$/;
		var id = $('input:eq(0)').val();
		if(!pattern.test(id)){
			$("#message").css("color", "red")
							.html("������ ���� _�� 5~12�� �����մϴ�.");
			checkid=false;
			return;
		}
		
		$.ajax({
			url: "idcheck.net",
			data:{"id" : id},
			success : function(resp) {
				if(resp == -1){
					$("#message").css("color", "green")
							.html("��� ������ ���̵� �Դϴ�.");
					checkid=true;
				}else{
					$("#message").css("color", "blue")
							.html("������� ���̵� �Դϴ�.");
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
			.html("�г����� 4���� �̻��Դϴ�.");
			checkname=false;
			return;
		}
		$.ajax({
			url: "namecheck.net",
			data:{"name" : name},
			success : function(resp) {
				if(resp == -1){
					$("#message_name").css("color", "green")
							.html("��� ������ �г��� �Դϴ�.");
					checkname=true;
				}else{
					$("#message_name").css("color", "blue")
							.html("������� �г��� �Դϴ�.");
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
			alert("��밡���� id�� �Է��ϼ���.");
			$("input:eq(0)").val('').focus();
			return false;
		}
		
		if(!checkname){
			alert("��밡���� �г����� �Է��ϼ���.");
			$("input:eq(3)").val('').focus();
			return false;
		}
		
		if(!checkpsw){
			alert("��밡���� ��й�ȣ�� �Է��ϼ���.");
			$("input:eq(1)").val('').focus();
			return false;
		}
		
		if(!checkemail){
			alert("email ������ Ȯ���ϼ���");
			$("input:eq(4)").focus();
			return false;
		}
		
		if(!checkphone){
			alert("�޴��� ������ Ȯ���ϼ���");
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
			.html("��й�ȣ�� ���ڿ� ���� ���� 6~12�ڸ� �Դϴ�.");
			checkpsw=false;
		}else{
			$("#message_psw").css("color", "green")
			.html("��й�ȣ ���Ŀ� �½��ϴ�.");
			checkpsw=true;
		}
	}); //psw keyup end
	
	$('input:eq(2)').on('keyup',function() {
		$("#message_psw").empty();
		if ($('input:eq(1)').val() != $('input:eq(2)').val()) {
			$("#message_psw").css("color", "red")
			.html("�н����尡 ��ġ���� �ʽ��ϴ�.");
			checkpsw=false;
		}else{
			$("#message_psw").css("color", "green")
			.html("�н����尡 ��ġ�մϴ�.");
			checkpsw=true;
		}
	}); //pswchk keyup end
	
	
	$("input:eq(4)").on('keyup',function() {
		
		$("#message_email").empty();
		//[A-Za-z0-9_]�� ������ ���� \w
		//+�� 1ȸ �̻� �ݺ� �ǹ�. {1,}�� ������
		// \w �� [A-Za-z0-9_]�� 1���̻� ����϶�� ���Դϴ�.
		var pattern = /^\w+@\w+[.]\w{3}$/;
		var email = $("input:eq(4)").val();
		if(!pattern.test(email)) {
			$("#message_email").css("color", "red")
			.html("�̸��� ���Ŀ� ���� �ʽ��ϴ�.");
		checkemail=false;
		}else{
			$("#message_email").css("color", "green")
			.html("�̸��� ���Ŀ� �½��ϴ�.");
		checkemail=true;
		}
	}); //email keyup end
		
	$("input:eq(5)").on('keyup',function() {
		
		$("#message_phone").empty();
		var pattern = /^\d{3}-\d{3,4}-\d{4}$/;;
		var phone = $("input:eq(5)").val();
		if(!pattern.test(phone)) {
			$("#message_phone").css("color", "red")
			.html("�޴��� ���Ŀ� ���� �ʽ��ϴ�.");
			checkphone=false;
		}else{
			$("#message_phone").css("color", "green")
			.html("�޴��� ���Ŀ� �½��ϴ�.");
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
		<b>���̵�</b>
		<input type="text" class="join" name="MEMBER_ID" placeholder="���̵�" required maxLength="10">
		<span id="message"></span>
		
		<b>��й�ȣ</b>
		<input type="password" class="join" name="MEMBER_PASSWORD" placeholder="��й�ȣ" required>
		
		<b>��й�ȣ Ȯ��</b>
		<input type="password" class="join" name="passwordchk" placeholder="��й�ȣ Ȯ��" required>
		<span id="message_psw"></span>
		
		<b>�г���</b>
		<input type="text" class="join" name="MEMBER_NAME" placeholder="�г���" maxLength="15" required> 
		<span id="message_name"></span>
		
		<b>�̸��� </b>
		<input type="text" class="join" name="MEMBER_EMAIL" placeholder="�̸���(***@***.com)" required>
		<span id="message_email"></span>
		
		
		<b>�޴�����ȣ</b>
		<input type="text" class="join" name="MEMBER_PHONE" placeholder="010-0000-0000" maxLength="15" required> 
		<span id="message_phone"></span>
		
		<div class="clearfix">
			<button type="submit" id="submitbtn" class="submitbtn">�̸��� �����ޱ�</button>

			<button type="reset" class="cancelbtn">�ٽ��ۼ�</button>
		</div>
	</FORM>
</body>
</html>