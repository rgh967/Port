<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page = "../home/header.jsp" />
		<title>클래스 상세정보 페이지</title>
		<link rel="stylesheet" type="text/css" href="resources/css/class_view.css" />
		<script src="resources/js/jquery-3.5.1.js"></script>
		<script type="text/javascript" src="resources/js/class_view.js"></script>
	</head>
	<!-- 전체 화면 -->
	<body>
		<input id="CLASS_ID" type="hidden" name="CLASS_ID" value="${classData.CLASS_ID}">
		<div class="class_view">
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
								${classWriter}
							</strong>
						</div>
								
						<!-- 클래스 기본 정보 -->
						<div class="basic_info">
							<h1><strong>${classData.CLASS_TITLE}</strong></h1>
							<table class="basic_info_table">
								<tr>
									<th>강습 비용</th>
									<td>${classData.CLASS_COST}(원)</td>
								</tr>
								<tr>
									<th>강습 기간</th>
									<td>${classData.CLASS_STARTDATE} ~ ${classData.CLASS_ENDDATE}</td>
								</tr>
								<tr>
									<th>강습 시간</th>
									<td>${classData.CLASS_STARTTIME} ~ ${classData.CLASS_ENDTIME}</td>
								</tr>
							</table>
						</div>
								
						<div class="btn-area">
							<!-- 신청 기간일 경우 -->
							<c:if test="${regiOK == 1}">
								<form id="cloveForm" action ="ClassCloveProcess.net" method="post">
									<input type="hidden" name="CLASS_ID" value="${classData.CLASS_ID}">
									<button id="clove" class="clove">찜하기</button>
		      					</form>
	      						<form id="buyForm" action="ClassBuyProcess.net" method="post">
	      							<!-- 클래스 작성자 id -->
	      							<input type="hidden" name="CLASS_ID" value="${classData.CLASS_ID}">
									<button id="buy" class="buy">수강신청</button>
								</form>
							</c:if>
							<!-- 신청 기간이 지났을 경우, 버튼 비활성화 -->
							<c:if test="${regiOK == 0}"> 
								<span>수강신청 기간이 지났습니다.</span><br>
								<form id="cloveForm" action ="ClassCloveProcess.net" method="post">
									<input type="hidden" name="CLASS_ID" value="${classData.CLASS_ID}">
									<button id="clove" class="clove" disabled>찜하기</button>
		      					</form>
	      						<form id="buyForm" action="ClassBuyProcess.net" method="post">
	      							<!-- 클래스 작성자 id -->
	      							<input type="hidden" name="CLASS_ID" value="${classData.CLASS_ID}">
		      						<button id="buy" class="buy" disabled>수강신청</button>
								</form>
							</c:if>
						</div>
					</div>
							
					<!-- 우측 하단 영역 -->
					<div class="btm">
						 <img class="refund_regu" alt="클래스 환불 규정" src="resources/image/refund_regu.png">
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
							<img class="class_picture" alt="레시피 대표 사진" src="resources/upload/class/${classData.CLASS_MAIN_IMG}">
							<div class="class_content">
								<h3>커리큘럼</h3>
								<ul>
									<c:forEach var="curriculum" items="${classCurriculum}">
										<li> Day ${curriculum.CUR_DAY}. ${curriculum.CUR_CONTENT} </li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
						
					<!-- 로고 경계선 -->
					<div class="rego_line">
						
					</div>
					<img id="logo" alt="로고" src="resources/image/logo.png">
						
						
					<div class="btm">
						<!-- 좌측중간 영역 -->
						<div class="mid_main">
							<h3>기본 정보</h3>
							<table>
								<tr>
									<td>클래스 이름</td>
									<td colspan=3>${classData.CLASS_TITLE}</td>
								</tr>
								<tr>
									<td>강습 비용</td>
									<td>${classData.CLASS_COST} 원</td>
									<td>신청인원/수강가능인원</td>
									<td>${classData.CLASS_REG_PEOPLE}/${classData.CLASS_PEOPLE}</td>
								</tr>
								<tr>
									<td>강습 기간</td>
									<td>${classData.CLASS_STARTDATE} ~ ${classData.CLASS_ENDDATE}</td>
									<td>강습 시간</td>
									<td>${classData.CLASS_STARTTIME} ~ ${classData.CLASS_ENDTIME}</td>
								</tr>
								<tr>
									<td>강습 장소</td>
									<td colspan=3>${classData.CLASS_ADDRESS}</td>
								</tr>
								<tr>
									<td>신청 기간</td>
									<td colspan=3>${classData.CLASS_APP_STARTDATE} ~ ${classData.CLASS_APP_ENDDATE}</td>
								</tr>
								<tr>
									<td>강사 이름</td>
									<td colspan=3>${classWriter}</td>
								</tr>
								<tr>
									<td>강사 소개</td>
									<td colspan=3>${classData.CLASS_LECTURER}</td>
								</tr>
							</table>
						</div>
					</div>
							
					<!-- 로고 경계선 -->
					<div class="rego_line">
						
					</div>
					<img id="logo" alt="로고" src="resources/image/logo.png">
							
					<!-- 좌측하단 영역 -->
					<div class="btm_main">
						<h3>상세 정보</h3>
						<ul>
							<c:forEach var="detail" items="${classDetail}">
								<div>
									<c:if test="${detail.DETAIL_IMAGE != null}">
										<img alt="사진" src="resources/upload/class/${detail.DETAIL_IMAGE}">
									</c:if>
									<p>${detail.DETAIL_CONTENT}</p>
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
								<input type="hidden" name="CLASS_ID" value="${classData.CLASS_ID}">
								<input id="REVIEW_ID" type="hidden" name="REVIEW_ID" value="0">
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