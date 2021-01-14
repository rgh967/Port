<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<jsp:include page="../home/header.jsp" />
		<link rel="stylesheet" type="text/css" href="resources/css/search_list.css" />
		<script src="resources/js/jquery-3.5.1.js"></script>
		<script src="resources/js/search_list.js"></script>
		<title>search_list</title>
	</head>
	<body>
		<div id="container">
			<h2>조건에 맞는 정보가 <span id="recipeCount">${recipeCount + classCount}</span> 개 있습니다.</h2>
			
			<c:if test="${recipeCount > 0}">
				<!-- 검색된 레시피 출력 -->
				<div class="contentListView">
					<h1>검색 레시피 <span class="listCount">(${recipeCount}개)</span></h1>
					<ul class="lst_ul">
						<c:forEach var="recipe" items="${recipeList}">
							<li>
								<a href="recipeDetailProcess.net?RECIPE_ID=${recipe.RECIPE_ID}">
									<img id="picture" src="/cook/resources/upload/recipe/${recipe.RECIPE_MAIN_IMG}" />
									<span class="judge">요리조리등급<strong>${recipe.member.MEMBER_RANK}</strong></span>
									<span class="star">
 										<c:forEach var="i" begin="1" end="${recipe.recipeReview.REVIEW_STAR}">
 											<img id="stars" src="resources/image/filed_star.png">
 										</c:forEach>
									</span>
									<span class="readcount">조회수:<strong>${recipe.RECIPE_READCOUNT}</strong></span>
								</a>
								<div class="content">
									<span id="title">${recipe.RECIPE_TITLE}</span><br>
									<span id="intro">${recipe.RECIPE_INTRO}</span><br>
									<span id="name">작성자 : ${recipe.member.MEMBER_NAME}</span>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<c:if test="${classCount > 0}">
				<!-- 검색된 클래스 출력 -->
				<div class="contentListView">
					<h1>검색 클래스 <span class="listCount">(${classCount}개)</span></h1>
					<ul id="lst_ul_class" class="lst_ul">
						<c:forEach var="cookClass" items="${classList}">
							<li>
								<a href="classDetailProcess.net?CLASS_ID=${cookClass.CLASS_ID}">
									<img id="picture" src="/cook/resources/upload/class/${cookClass.CLASS_MAIN_IMG}" />
								</a>
								<span class="people">${cookClass.CLASS_REG_PEOPLE} / ${cookClass.CLASS_PEOPLE}</span>
								<div class="content">
									<span id="title">${cookClass.CLASS_TITLE}</span><br>
									<span id="app">신청 기간 : ${fn:substring(cookClass.CLASS_APP_STARTDATE,0,10)} ~ ${fn:substring(cookClass.CLASS_APP_ENDDATE,0,10)}</span><br>
									<span id="date">강습 기간 : ${fn:substring(cookClass.CLASS_STARTDATE,0,10)} ~ ${fn:substring(cookClass.CLASS_ENDDATE,0,10)}</span><br>
									<span id="time">강습 시간 : ${fn:substring(cookClass.CLASS_STARTTIME,11,16)} ~ ${fn:substring(cookClass.CLASS_ENDTIME,11,16)}</span><br>
									<span id="time">강습 비용  : ${cookClass.CLASS_COST}</span><br>
									<span id="lecturer">강사 이름 : ${cookClass.member.MEMBER_NAME}</span><br>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>			
		</div>
	</body>
</html>