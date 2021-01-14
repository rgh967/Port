<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../home/header.jsp" />
<link rel="stylesheet" type="text/css"
	href="resources/css/class_list.css" />
<script src="resources/js/jquery-3.5.1.js"></script>
<script src="resources/js/class_list.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(function() {
	$("#slider-range").slider({
		range : true,
		min : 0,
		max : 200000,
		step : 1000,
		values : [ 0, 200000 ],
		slide : function(event, ui) {
			$("#value1").val(ui.values[0]);
			$("#value2").val(ui.values[1]);
			$("#amount").val(ui.values[0] + "원 ~ " + ui.values[1] + "원");
		},
		stop : function(event, ui) {
			sendQuery();
		}
	});
	$("#amount").val(
			$("#slider-range").slider("values", 0) + "원 ~ "
					+ $("#slider-range").slider("values", 1) + "원");
	
	$.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년'
    });

	from = $("#from").datepicker({
		defaultDate : "+1w",
		changeMonth : true,
		numberOfMonths : 1
	}).on("change", function() {
		to.datepicker("option", "minDate", getDate(this));
		sendQuery();
	}), 
	to = $("#to").datepicker({
		defaultDate : "+1w",
		changeMonth : true,
		numberOfMonths : 1
	}).on("change", function() {
		from.datepicker("option", "maxDate", getDate(this));
		sendQuery();
	});

	function getDate(element) {
		var date;
		try {
			date = $.datepicker.parseDate(dateFormat, element.value);
		} catch (error) {
			date = null;
		}

		return date;
	}
	
	$('#resetbtn').click(function() {
		$("#slider-range").slider({
			range : true,
			min : 0,
			max : 200000,
			step : 1000,
			values : [ 0, 200000 ],
			slide : function(event, ui) {
				$("#value1").val(ui.values[0]);
				$("#value2").val(ui.values[1]);
				$("#amount").val(ui.values[0] + "원 ~ " + ui.values[1] + "원");
			}
		});
		
		$("#amount").val(
				$("#slider-range").slider("values", 0) + "원 ~ "
						+ $("#slider-range").slider("values", 1) + "원");
		
		$("#from").val("");
		$("#to").val("");
		$("#value1").val("0");
		$("#value2").val("200000");
		sendQuery();
	});
});
</script>
<title>class_list</title>
</head>
<body>
	<div id="wrap">
		<div id="container">
			<div id="content">
				<div class="title">
					<h1>클래스 검색</h1>
					<input type="hidden" name="CLASS_ID" value="${classdata.CLASS_ID}">
				</div>
				<form class="result-filter" action="classChkList.net" method="post">
					<input type="hidden" name="page" id="page" value="${page}">
					<input type="hidden" name="limit" id="limit" value="${limit}">
				<!-- 카테고리 시작 -->
					<div class="finder">
						<table>
							<tr>
								<td colspan="2">강습 기간</td>
								<td colspan="3">강습 비용</td>
							</tr>
							<tr>
								<td>
									<label for="from">강습 시작일</label>
									<input type="text" id="from" name="from">
								</td>
								<td>
									<label for="to">강습 종료일</label>
									<input type="text" id="to" name="to">
								</td>
								<td>
									<input type="text" id="amount" readonly style="border: 0; color: #f6931f; font-weight: bold;">
									<input type="hidden" id="value1" name="class_value1">
									<input type="hidden" id="value2" name="class_value2">
								</td>
								<td style="width:200px"><div id="slider-range"></div></td>
							</tr>
						</table>
					</div>
					<!-- 초기화 버튼 클릭 시 기본화면으로 나오게 작성 -->
					<div class="btn">
						<button type="button" id="resetbtn">초기화</button>
					</div>
				<!-- 카테고리 끝 -->
				</form>
				<div class="recipes">
					<c:if test="${listcount > 0}">
					<div class="inner_result">	
						<div class="tit_area">
							<h2>
								조건에 맞는 클래스는 ? <strong>${listcount}</strong>
							</h2>
						</div>
						<ul class="lst_recipe">
							<c:forEach var="cla" items="${classlist}">
								<li>
									<a href="classDetailProcess.net?CLASS_ID=${cla.CLASS_ID}">
									<img id="picture" src="/cook/resources/upload/class/${cla.CLASS_MAIN_IMG}"/></a>
									<span class="people">${cla.CLASS_REG_PEOPLE} / ${cla.CLASS_PEOPLE}</span>
									<div class="recipe_content">
										<span id="title">${cla.CLASS_TITLE}</span><br> 
										<span id="app"> 신청 기간 : ${cla.CLASS_APP_STARTDATE} ~ ${cla.CLASS_APP_ENDDATE}</span><br> 
										<span id="date"> 강습 기간 : ${cla.CLASS_STARTDATE} ~ ${cla.CLASS_ENDDATE}</span><br>
										<span id="time"> 강습 시간 : ${cla.CLASS_STARTTIME} ~ ${cla.CLASS_ENDTIME}</span><br>
										<span id="cost"> 강습 비용 : ${cla.CLASS_COST}</span><br>
										<span id="lecturer">강사 이름 : ${cla.member.MEMBER_NAME}</span>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="paging">
						<c:if test="${page <= 1}">
							<a class="prev">이전</a>
						</c:if>
						<c:if test="${page > 1}">
							<a class="prev" href="classPaging.net?page=${page-1}">이전</a>
						</c:if>
						<c:forEach var="a" begin="${startpage}" end="${endpage}">
							<c:if test="${a == page}">
								<a class="current">${a}</a>
							</c:if>
							<c:if test="${a != page}">
								<a class="current" href="classPaging.net?page=${a}">${a}</a>
							</c:if>
						</c:forEach>

						<c:if test="${page >= maxpage}">
							<a class="next">다음</a>
						</c:if>
						<c:if test="${page < maxpage}">
							<a class="next" href="classPaging.net?page=${page+1}">다음</a>
						</c:if>
					</div>
				</c:if>
				<c:if test="${listcount == 0}">
					<h1>검색 결과가 없습니다.</h1>
				</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>