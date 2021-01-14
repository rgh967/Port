<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<style>
body {
	background-color: #F9F7E8;
	}
form {
	margin-top: 8%;
}
button {
    color: white;
    background-color: #FF8B8B;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer; /* 손가락 커서 모양 */
    float: left;
    width: 50%;
    opacity: 0.9;
}
input.join {
    width: 90%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: 1px solid lightgray;
	background: #F9F7E8;
	border-radius: 2px;
   
}
input[type=text]:focus{
  box-shadow: 0 0 0 0.2rem rgba(0,123,255,.25);
    outline: none;
}
hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
}
 
b {
	width: 100%;
    display: block;
    font-weight: bold;
    }
    
.maingo{
	color: white;
    background-color: #61BFAD;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer; /* 손가락 커서 모양 */
    float: left;
    width: 50%;
    opacity: 0.9;
}
h3{text-align:center}

</style>
</head>
<body>

<form action="find_password.net" method="post"> 
<h3>비밀번호 찾기</h3>
<hr>
<b>아이디</b>
		<input type="text" class="join" name="MEMBER_ID" placeholder="가입한 아이디" required maxLength="10" required>

<b>이메일 </b>
		<input type="text" class="join" name="MEMBER_EMAIL" placeholder="이메일(***@***.com)" required>
<button type="submit" name="submit">비밀번호 찾기</button>
<input type="button" class="maingo" onClick="location.href='main.net'" value="메인으로 가기">              
</form>
                        
</body>
</html>