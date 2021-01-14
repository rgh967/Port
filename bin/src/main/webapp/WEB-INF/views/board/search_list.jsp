<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<jsp:include page="../board/header.jsp" />
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
					<ul class="lst_recipe">
						<c:forEach var="recipe" items="${recipeList}">
							<li>
								<a href="RecipeDetailAction.bo?RECIPE_ID=${recipe.RECIPE_ID}">
									<img id="picture" src="/cook/resources/upload/recipe/${recipe.RECIPE_MAIN_IMG}" />
									<span class="judge">요리조리등급<strong>${recipe.member.MEMBER_RANK}</strong></span>
									<span class="star"> 
										<img id="star1" src="resources/image/icon_star.png">
										<img id="star2" src="resources/image/icon_star.png">
										<img id="star3" src="resources/image/icon_star.png">
										<img id="star4" src="resources/image/icon_star.png">
										<img id="star5" src="resources/image/icon_star.png">
									</span>
									<span class="readcount">조회수:<strong>${recipe.RECIPE_READCOUNT}</strong></span>
								</a>
								<div class="recipe_content">
									<span id="title">${recipe.RECIPE_TITLE}</span><br>
									<span id="intro">${recipe.RECIPE_INTRO}</span><br>
									<span id="name">${recipe.member.MEMBER_NAME}</span>
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
					<!-- 
					<ul class="lst_recipe">
						<c:forEach var="class" items="${classList}">
							<li>
								<img id="picture" src="/cook/resources/upload/recipe/${recipe.RECIPE_MAIN_IMG}" />
								<span class="judge">요리조리등급<strong>${recipe.member.MEMBER_RANK}</strong></span>
								<span class="star"> 
									<img id="star1" src="resources/image/icon_star.png">
									<img id="star2" src="resources/image/icon_star.png">
									<img id="star3" src="resources/image/icon_star.png">
									<img id="star4" src="resources/image/icon_star.png">
									<img id="star5" src="resources/image/icon_star.png">
								</span>
								<span class="readcount">조회수:<strong>${recipe.RECIPE_READCOUNT}</strong></span>
								<div>
									<span>${recipe.RECIPE_TITLE}</span><br>
									<span>${recipe.RECIPE_INTRO}</span><br>
									<span>${recipe.member.MEMBER_NAME}</span>
								</div>
							</li>
						</c:forEach>
					</ul>
					 -->
				</div>
			</c:if>
		
		</div>
	</body>
</html>