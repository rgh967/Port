<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 인증 페이지</title>
<script src = "resources/js/jquery-3.5.1.js"></script>
<style>
body {
	background-color: #F9F7E8;
	}
form {
	text-align:center;
	margin-top: 30%;
}
button {
    color: white;
    background-color: #FF8B8B;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer; /* 손가락 커서 모양 */
    width: 30%;
    opacity: 0.9;
}
.email_injeung{
	border: 1px solid #FF8B8B;
	width: 170px;
}
.maingo{
	color: white;
    background-color: #61BFAD;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer; /* 손가락 커서 모양 */
    width: 30%;
    opacity: 0.9;
}
</style>
<script>
$(function() {
	$("input:text(0)").on('keyup',function() {
		var pattern = /^[0-9]/g;
		var num = $("input:text(0)").val();
		if(!pattern.test(num)) {
			alert('숫자만 입력 가능합니다. 다시 입력해 주세요.');
			return;
		}
	});
});
</script>
</head>
<body>

<form action="joinMailAuth.net${dice}" method="post"> <!-- 받아온 인증코드를 컨트롤러로 넘겨서 일치하는지 확인 --> 
<div>
   인증번호 입력 : <input type="text" class="email_injeung" name="email_injeung" placeholder="  인증번호를 입력하세요. ">
</div>
<br>
<button type="submit" name="submit">인증번호 확인</button>
<input type="button" class="maingo" onClick="location.href='main.net'" value="메인으로 가기">              
</form>
                        
</body>
</html>