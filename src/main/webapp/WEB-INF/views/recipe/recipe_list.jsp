<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../home/header.jsp" />
<link rel="stylesheet" type="text/css"
	href="resources/css/recipe_list.css" />
<script src="resources/js/jquery-3.5.1.js"></script>
<script src="resources/js/recipe_list.js"></script>
<title>recipe_list</title>
</head>
<body>
	<div id="wrap">
		<div id="container">
			<div id="content">
				<div class="title">
					<h1>레시피 검색</h1>
					<h2>원하는 카테고리를 선택하세요.</h2>
					<input type="hidden" name="RECIPE_ID"
						value="${recipedata.RECIPE_ID}">
					<!-- 					<div class="btn"> -->
					<!-- 						<button type="button" class="btn_chef">*전문가 레시피 보기</button> -->
					<!-- 					</div> -->
				</div>
				<form class="result-filter" action="recipeChkList.net" method="post">
					<!-- 카테고리 시작 -->
					<div class="finder">
						<dl class="dl_first">
							<dt>
								<h3>쉐프별</h3>
							</dt>
							<dd>
								<ul class="lst_check">
									<c:forEach var="chef" items="${cheflist}">
										<li><label><img
												src="resources/image/checkbox_uncheck.png" width="25px">${chef.CHEF_NAME}
												<input type="checkbox" name="CHEF" class="depth_one"
												sub-name="${chef.CHEF_NAME}" value="${chef.CHEF_ID}"></label></li>
										<input type="hidden" name="CHEF_ID" value="${chef.CHEF_ID}" />
									</c:forEach>
								</ul>
							</dd>
						</dl>
						<dl class="dl_second">
							<dt>
								<h3>나라별</h3>
							</dt>
							<dd>
								<!-- value + 4 -->
								<ul class="lst_check">
									<c:forEach var="nation" items="${nationlist}">
										<li><label><img
												src="resources/image/checkbox_uncheck.png" width="25px">${nation.NATION_NAME}
												<input type="checkbox" name="NATION" class="depth_one"
												sub-name="${nation.NATION_NAME}" value="${nation.NATION_ID}"></label></li>
									</c:forEach>
								</ul>
							</dd>
						</dl>
						<dl class="dl_third">
							<dt>
								<h3>재료별</h3>
							</dt>
							<dd>
								<!-- value + 8 -->
								<ul class="lst_check">
									<c:forEach var="part" items="${partlist}">
										<li><label><img
												src="resources/image/checkbox_uncheck.png" width="25px">${part.PART_NAME}
												<input type="checkbox" name="PART" class="depth_one"
												sub-name="${part.PART_NAME}" value="${part.PART_ID}"></label></li>
									</c:forEach>
								</ul>
							</dd>
						</dl>
					</div>
					<!-- 카테고리 끝 -->
					<table class="bar_result">
						<tbody>
							<tr>
								<th scope="row">결과</th>
								<td id="sub-result"></td>
							</tr>
						</tbody>
					</table>
				</form>

				<div class="recipes">
					<div class="inner_result">
						<div class="tit_area">
							<input type="hidden" id="filter-sort" name="sort" value="rct">
							<h2>
								조건에 맞는 레시피는 ? <strong>${listcount}</strong>
							</h2>
							<div class="sort_area">
								<select class="sort-selector" name="sort">
									<option value="rct">별점순 정렬</option>
									<option value="rnk">최신순 정렬</option>
									<option value="rlv">등급순 정렬</option>
								</select>
							</div>
						</div>
						<ul class="lst_recipe">
							<c:forEach var="recipe" items="${recipelist}">
								<li><img id="picture"
									src="/cook/resources/upload/recipe/${recipe.RECIPE_MAIN_IMG}" />
									<!-- RECIPE_PICTURE --> <span class="judge">요리조리등급<strong>${recipe.member.MEMBER_RANK}</strong></span>
									<span class="star"> <img id="star1"
										src="resources/image/icon_star.png"> <img id="star2"
										src="resources/image/icon_star.png"> <img id="star3"
										src="resources/image/icon_star.png"> <img id="star4"
										src="resources/image/icon_star.png"> <img id="star5"
										src="resources/image/icon_star.png">
								</span> <span class="readcount">조회수:<strong>${recipe.RECIPE_READCOUNT}</strong></span>
									<div class="recipe_content">
										<span id="title">${recipe.RECIPE_TITLE}</span><br> <span
											id="intro">${recipe.RECIPE_INTRO}</span><br><span
											id="name">작성자 : ${recipe.member.MEMBER_NAME}</span>
									</div></li>
							</c:forEach>
						</ul>
					</div>
					<input type="hidden" name="page" id="page" value="${page}">
					<input type="hidden" name="limit" id="limit" value="${limit}">
					<div class="paging">
						<c:if test="${page <= 1}">
							<a class="prev">이전</a>
						</c:if>
						<c:if test="${page > 1}">
							<a class="prev" href="recipePaging.net?page=${page-1}">이전</a>
						</c:if>
						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page}">
								<a class="current">${a}</a>
							</c:if>
							<c:if test="${a != page}">
								<a class="current" href="recipePaging.net?page=${a}">${a}</a>
							</c:if>
						</c:forEach>

						<c:if test="${page >= maxpage}">
							<a class="next">다음</a>
						</c:if>
						<c:if test="${page < maxpage}">
							<a class="next" href="recipePaging.net?page=${page+1}">다음</a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>