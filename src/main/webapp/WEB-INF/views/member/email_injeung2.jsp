<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 인증 페이지</title>
<style>
body {
	background-color: #F9F7E8;
	}
form {
	text-align:center;
	margin-top: 10%;
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
</head>
<body>

<form action="find_injeung.do${dice}" method="post"> <!-- 받아온 인증코드를 컨트롤러로 넘겨서 일치하는지 확인 --> 
<div>
   인증번호 입력 : <input type="number" class="email_injeung" name="email_injeung" placeholder="  인증번호를 입력하세요. ">
</div>
<br>
<button type="submit" name="submit">인증번호 확인</button>
<input type="button" class="maingo" onClick="location.href='main.net'" value="메인으로 가기">              
</form>
                        
</body>
</html>