<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<jsp:include page="../home/header.jsp" />
<title>고객지원</title>
<style>
.csmain{
    width: 100%;
    height: 60px;
    border-top: 1px solid #e8e9e9;
    border-bottom: 1px solid #e8e9e9;
    margin-bottom: 55px;
    margin-top: 50px;
    }
.csmain li a {
    display: block;
    width: 25%;
    height: 60px;
    padding-top: 15px;
    font-family: Microsoft YaHei,'NST';
    font-size: 18px;
    color: #b7b7b7;
    box-sizing: border-box;
    text-align: center;
    float: left;
   }
.csmain li{
    list-style: none;
}
.csmain li.on a {
    color: white;
    background: #61BFAD;
    height: auto;
}
div a{
	color: black;
	text-decoration: none;
}
button.submitbtn {
	background-color: #FF8B8B;
}
button.submitbtn {
 	float: left;
    color: white;
    padding: 14px 20px;
    margin: 8px 0 0 20px;
    border: none;
    cursor: pointer; /* 손가락 커서 모양 */
    width: 30%;
    opacity: 0.9;
}
input{border-radius:3px;border:1px solid lightgray}

input.join {
    width: 30%;
	padding: 15px;
	margin: 5px 0 22px 20px;
	display: inline-block;
	border: 1px solid lightgray;
	background: #F9F7E8;
	border-radius: 2px;  
}
b {
	margin: 0 0 0 20px;
}
</style>

<!-- <script src = "resources/js/list.js"></script> -->
</head>
<body>
<div class="csmain">
			<div class="inner">
				<ul>
					<li >
						<a href="NoticeList.bo">
							<strong>공지사항</strong>
</a>					</li>
					<li >
						<a href="NoticeQuestion.bo">
							<strong>자주찾는질문</strong>
</a>					</li>
					<li class="on">
						<a href="NoticeOno.bo">
							<strong>1:1문의</strong>
</a>					</li>
				</ul>
			</div>
		</div>
<script>
$(function() {
$('li').click(function() {
	 $('li').removeClass('on');
	 $(this).addClass('on');
	});

});

</script>
<%-- 1대1문의  --%>

<form name="joinform" action="Onomail.bo" method="post">

<b>1대1문의를 받을 이메일을 적어주세요.</b><br><br>
<input type="text" class="join" name="MEMBER_EMAIL" placeholder="이메일(***@***.com)" required>      
<br>
<button type="submit" id="submitbtn" class="submitbtn">1대1문의 이메일 받기</button>

</form>

</body>
</html>