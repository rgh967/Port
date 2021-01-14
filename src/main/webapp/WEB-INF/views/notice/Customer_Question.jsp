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
table {
  border-collapse: collapse;
  width: 65%;
  margin-left: 15%;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
  background-color: gray;
  color: white;
}
.btnwrite{
  background-color: #FF8B8B;
  border: none;
  color: white;
  padding: 10px 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  float: right;
  margin-right: 10%;
}
div a{
	color: black;
	text-decoration: none;
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
					<li class="on">
						<a href="NoticeQuestion.bo">
							<strong>자주찾는질문</strong>
</a>					</li>
					<li>
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

$("button").click(function() {
	location.href = "QuestionWrite.bo";
	});
	
});

</script>

<%-- 자주찾는 질문  --%>
<div class="container">
<c:if test="${listcount>0 }"> 
	
<table class="table table-striped">
<thead>
	<tr>
		<th colspan="3">자주찾는 질문</th>
		<th colspan="2">
			<font size=3>글 개수 : ${listcount }</font>
		</th>
	</tr>
	<tr>
		<th><div>번호</div></th>
		<th><div>질문</div></th>
		<th><div>작성자</div></th>
		<th><div>날짜</div></th>
		<th><div>조회수</div></th>
	</tr>
	</thead>
	<tbody>
	<c:set var="num" value="${listcount-(page-1)*limit }"/>
	<c:forEach var="c" items="${boardQuestion }">
	<tr>
		<td><%--번호 --%>
		<c:out value="${num }"/><%--num 출력 --%>
		<c:set var="num" value="${num-1}"/> <%--num=num=1; 의미 --%>
		</td>
		<td><%--제목 --%>
			<div>		
				<a href="QuestionDetailAction.bo?num=${c.NOTICE_NUM}">
					<c:out value="${c.NOTICE_TITLE}" escapeXml="true"/>
				</a>
			</div>
			</td>
			<td><div>${c.NOTICE_ID}</div></td>
			<td><div>${c.NOTICE_DATE}</div></td>
			<td><div>${c.NOTICE_READCOUNT}</div></td>
	</tr>
	</c:forEach>
	</tbody>
</table>
    
</c:if> <%-- <c:if test="${ listcount>0 }"> end --%>

<%--레코드가 없으면 --%>

<c:if test="${listcount == 0 }">
			<font size =5>등록된 글이 없습니다.</font>
</c:if>

<input id="MEMBER_ID" type="hidden" name="MEMBER_ID" value="${sessionScope.id}">
<c:if test= '${sessionScope.id == "admin"}'>
<button type = "button" class="btnwrite">글 쓰 기</button>
</c:if>

</div>

<div> <!-- 자주찾는질문 -->

</div>

</body>
</html>