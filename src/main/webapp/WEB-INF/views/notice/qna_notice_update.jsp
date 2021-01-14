<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<jsp:include page="../home/header.jsp" />
<style>
.container {
	width: 65%;
  	margin-left: 25%;
}
h1{
	font-size: 25px;
	color: #61BFAD;
	margin-bottom: 20px;
}
form label{
	font-size: 18px;
	color: gray;
	font-weight: bold;
	padding: 15px;
}
.form-control1 {
	color: #FF8B8B;
	font-weight: bold;
	border: none;
	margin-bottom: 20px;
}
.form-control2{
	color: gray;
	font-weight: bold;
	border: none;
	margin-bottom: 30px;
}
textarea{
	resize:none;
	border: 2px solid #61BFAD;
	border-radius: 10px;
	color: gray;
	font-weight: bold;
	margin-left: 60px;
}
.sucbtn, .canbtn{
	color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    opacity: 0.9;
}
button.canbtn {
    background-color: #61BFAD;
}
button.sucbtn {
	background-color: #FF8B8B;
}
.form-group1{
	margin-left: 60%;
    margin-top: 80px;
}
</style>
<title>공지사항 글수정</title>
</head>
<body>

<div class="container">
 <form action="BoardModifyAction.bo" method="post" enctype="multipart/form-data"
 		name="boardform">
 	<input type="hidden" name="NOTICE_NUM" value="${boarddata.NOTICE_NUM}">	
 		
 	<h1>공지사항 글수정</h1>	
 	<div class="form-group">
 		<label for="board name">글쓴이</label>
 		<input name="NOTICE_ID" id="NOTICE_ID" value="Admin"
 				type="text" readonly class="form-control1" >
 	</div>	
 	
 	<div class="form-group">
 	 <label for="board_subject">제목</label>
 	 <input name="NOTICE_TITLE" id="NOTICE_TITLE"
 				type="text" maxlength="100"	class="form-control2"
 				value="${boarddata.NOTICE_TITLE}"> 	
 	</div>
 	
 	<div class="form-group">
 	 <label for="NOTICE_CONTENT">내용</label><br>
 	 <textarea name="NOTICE_CONTENT" id="NOTICE_CONTENT"
 				cols="67" rows="10"	class="form-control">${boarddata.NOTICE_CONTENT}</textarea>
 	</div>
 	
 	<div class="form-group1">
 	 <button type=submit class="sucbtn">수정</button>
 	 <button type=reset class="canbtn" onClick="history.go(-1)">취소</button>
 	</div>		
 </form>
</div>
</body>
</html>