<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>공지사항</title>
 <jsp:include page = "../board/header.jsp" />
 <script>

 </script>
 <style>
table {
  border-collapse: collapse;
  width: 65%;
  margin-left: 15%;
}

th, td {
  text-align: left;
  padding: 15px;
}
body > div > table > tbody > tr:nth-child(1){
	font-size: 25px;
	color: #61BFAD;
}
div .title{
	font-size: 18px;
	color: gray;
	font-weight: bold;
}
textarea{
	resize:none;
	border: 2px solid #61BFAD;
	border-radius: 10px;
	color: gray;
	font-weight: bold;
}
div .writer{
	color: #FF8B8B;
	font-weight: bold;
}
div .nor{
	color: gray;
	font-weight: bold;
}
 </style>
</head>
<body>


<input type="hidden" id="loginid" value="${NOTICE_ID}" name="loginid">
 <div class="container">
  <table class="table table-striped">
   <tr><th colspan="2">공지사항</th></tr>
   <tr>
   		<td><div class="title">글쓴이</div></td>
   		<td><div class="writer">${boarddata.NOTICE_ID}</div></td>
  </tr>
  <tr>
   		<td><div class="title">제목</div></td>
   		<td><div class="nor"><c:out value="${boarddata.NOTICE_TITLE}" escapeXml="true"/></div></td>
  </tr>
  <tr>
   		<td><div class="title">내용</div></td>
   		<td><textarea class="form-control" rows="5" readOnly style="width:130%"
   		><c:out value="${boarddata.NOTICE_CONTENT}" escapeXml="true"/></textarea></td>
  </tr>
  </table>
 <%--게시판 view end --%>

 
 </div> <!-- class="container" end-->

</body>
</html>