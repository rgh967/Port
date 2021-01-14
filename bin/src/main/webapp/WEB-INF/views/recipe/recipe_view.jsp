<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page = "../board/header.jsp" />
		<title>레시피 상세정보 페이지</title>
		<link rel="stylesheet" type="text/css" href="resources/css/recipe_view.css" />
		<script src="resources/js/jquery-3.5.1.js"></script>
		<script type="text/javascript" src="resources/js/recipe_view.js"></script>
	</head>
	<!-- 전체 화면 -->
	<body>
		<input id="RECIPE_ID" type="hidden" name="RECIPE_ID" value="${recipe.RECIPE_ID}">
		<div class="recipe_view">
			<!-- 상세정보 화면 -->
			<div class="view_area">
				<!-- 우측 영역 -->
				<aside class="aside" id="aside">
					<!-- 우측 상단 영역 -->
					<div class="top">
						<!-- 작성자 정보 -->
						<div class="user">
							<img class="img_user" src="resources/image/login_unlock.png">
							<br>
							<strong>
								${recipeWriter}
							</strong>
						</div>
								
						<!-- 요리 기본 정보 -->
						<div class="basic_info">
							<h1><strong>${recipe.RECIPE_TITLE}</strong></h1>
							<table class="basic_info_table">
								<tr>
									<th>인원</th>
									<th>조리시간</th>
									<th>난이도</th>
								</tr>
								<tr>
									<td>${recipe.RECIPE_PEOPLE}(인분)</td>
									<td>${recipe.RECIPE_TIME}(분)</td>
									<td>${recipe.RECIPE_DEGREE}</td>
								</tr>
							</table>
						</div>
								
						<div class="btn-area">
							<form id="scrapForm" action ="RecipeScrapAction.bo" method="post">
								<input type="hidden" name="RECIPE_ID" value="${recipe.RECIPE_ID}">
								<button id="scrap" class="scrap">스크랩</button>
		      				</form>
	      					<form id="subscribeForm" action="MemberSubscribeAction.bo" method="post">
	      						<!-- 레시피 작성자 id -->
	      						<input type="hidden" name="MEMBER_ID_REG" value="${recipe.MEMBER_ID}">
								<button id="subscribe" class="subscribe">구독</button>
							</form>
						</div>
					</div>
							
					<!-- 우측 하단 영역 -->
					<div class="btm">
						<!-- 재료 리스트 -->
						<div class="ing_list">
							<h4>재료 리스트</h4>
							<table class="ing_list_table">
								<c:forEach var="ing" items="${recipeIng}">
									<tr>
										<td>${ing.ING_NAME}</td>
										<td>${ing.ING_AMOUNT}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</aside>
					
				<!-- 좌측 영역 -->
				<section class="section" id="section">
					<div class="rego_line">
						
					</div>
					<!-- 좌측상단 영역 -->
					<!-- 레시피 대표 사진 -->
					<div class="top">
						<div class="top_main">
							<img class="recipe_picture" alt="레시피 대표 사진" src="resources/upload/recipe/${recipe.RECIPE_MAIN_IMG}">
							<p class="recipe_content">
								<br>
								${recipe.RECIPE_INTRO}
							</p>
						</div>
					</div>
						
					<!-- 로고 경계선 -->
					<div class="rego_line">
						
					</div>
					<img id="logo" alt="로고" src="resources/image/logo.png">
						
						
					<div class="btm">
						<!-- 좌측중간 영역 -->
						<div class="mid_main">
							<h3>동영상</h3>
							<video controls autoplay>
								<source src="${recipe.RECIPE_URL}" type="video/mp4">
							</video>
						</div>
					</div>
							
					<!-- 로고 경계선 -->
					<div class="rego_line">
						
					</div>
					<img id="logo" alt="로고" src="resources/image/logo.png">
							
					<!-- 좌측하단 영역 -->
					<div class="btm_main">
						<h3>레시피</h3>
						<ul>
							<c:forEach var="step" items="${recipeStep}">
								<div>
									<h4>STEP ${step.STEP_NUM}.</h4>
									<c:if test="${step.STEP_IMAGE != null}">
										<img alt="사진" src="resources/upload/recipe/${step.STEP_IMAGE}">
									</c:if>
									<p>${step.STEP_CONTENT}</p>
								</div>
							</c:forEach>
						</ul>
					</div>
							
					<!-- 로고 경계선 -->
					<div class="rego_line">
							
					</div>
					<img id="logo" alt="로고" src="resources/image/logo.png">
							
					<!-- 후기 -->
					<div class="sec_review">
						<h3>후기</h3>
						<div class="review">
							<div id="ajaxForm">
								<input type="hidden" name="RECIPE_ID" value="${recipe.RECIPE_ID}">
								<input id="REVIEW_ID" type="hidden" name="REVIEW_ID" value="0">
								<!-- 별점이 -1이면, 후기 등록 불가/ '별점을 선택하세요' alert 출력  -->
								<input id="REVIEW_STAR" name = "REVIEW_STAR" type="hidden" value="-1">
								<img class="star" id="star1" alt="별점" src="resources/image/unfiled_star.png">
								<img class="star" id="star2" alt="별점" src="resources/image/unfiled_star.png">
								<img class="star" id="star3" alt="별점" src="resources/image/unfiled_star.png">
								<img class="star" id="star4" alt="별점" src="resources/image/unfiled_star.png">
								<img class="star" id="star5" alt="별점" src="resources/image/unfiled_star.png">
								<button id="review_btn" class="review_btn" name="button">등록</button>
								<br>
								<textarea placeholder="요리 후기를 남겨주세요." name="REVIEW_CONTENT"></textarea>
							</div>
							
							<!-- 후기 출력 테이블 -->
							<input type="hidden" id="count" value="${count}">
							<table class="review_table" class="table table_striped">
								<tbody>
								</tbody>
							</table>
							<label id="moreview">더보기</label>
							<!-- 후기가 존재하지 않으면 메시지 출력 -->
							<div id="message">
								<h5 style='color:#FF8B8B'>해당 레시피에는 아직 후기가 없습니다.<br>맛있는 경험을 남겨주세요!</h5>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
	</body>
</html>