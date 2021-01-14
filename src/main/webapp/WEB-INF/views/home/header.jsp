<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="resources/css/header.css" />
<style>
button { cursor: pointer; }
</style>
<script src="resources/js/jquery-3.5.1.js"></script>
<script>
	$(function() {
		
		$(".alt").click(function() {
			var memberId=$("#MEMBER_ID").val();
			if(memberId == null || memberId == ""){
				alert("로그인 후 이용가능한 서비스 입니다.");
				location.href="login.net";
				return false;
			}
		});
		
		$("#srhType").change(function(){
			var srhType =  $(this).val();
			var palceholder = "";
			if(srhType == "title"){
				palceholder = '제목으로 검색합니다.';
			}else if(srhType == "name"){
				palceholder = '이름으로 검색합니다.';
			}
			$('#srhText').attr('placeholder', palceholder);
		})
		
		// 검색창에서 엔터 키 입력 시
		$("#srhText").keydown(function(key) {
			if (key.keyCode == 13) {
				$("#my-btn").trigger("click");
			}
		});

	});	
</script>

<div class="gnb">
	<div class="gnb_top_wrap">
		<div class="gnbtop">
		  
			<div class="gnblogo">
				<a href="main.net">
				<img src="resources/image/logo.png" alt="로고" class="logo"></a>
			</div>
       
       		<div class="gnbsearch">
       			<form id="TopSearch" method="get" action="search.net">
					<select id="srhType" name="srhType">
						<option value="none" selected>전체</option>
						<option value="title">제목</option>
						<option value="name">닉네임</option>
					</select>
	                <input id="srhText" name="srhText" type="text" style="ime-mode:active;">
	                <span class="searchbtnGroup">
	                	<button id="searchbtn" type="submit">
	                    	<img id="searchImg" alt="검색버튼" src="resources/image/search.png">
	                    </button>
	                </span>
	            </form>
       		</div>
             
			<div class="gnbsetting">
				<nav class="gnbright">
					<c:choose>
						<c:when test= '${empty sessionScope.id}'>
							<div class="dropdown">
								<button class="dropbtn"><img alt="회원버튼" src="resources/image/login_unlock.png" class="member"></button>
			  					<div class="dropdown-content">
			 						 <a href="login.net">로그인</a>
			  					</div>
							</div> 
						</c:when>
						
						<c:otherwise>
							<input id="MEMBER_ID" type="hidden" name="MEMBER_ID" value="${sessionScope.id}">
							<div class="dropdown">
								<button class="dropbtn">
									<img alt="회원버튼" src="resources/image/login_lock.png" class="member">
								</button>
								<div class="dropdown-content">
									<a href="#" >찜한 내역</a>
									<a href="#" >구매 내역</a>
									<a href="memberInfo.net">정보 수정</a>
									<a href="logout.net">로그 아웃</a>
								</div>
							</div>
						</c:otherwise>
					
					</c:choose>
             	
             	
					<div class="dropdown">
						<button class="dropbtn"><img alt="글작성버튼" src="resources/image/registration.png" class="insert"></button>
						<div class="dropdown-content">
							<a href="recipeRegi.net" class="alt">레시피 등록</a>
							<a href="classRegi.net" class="alt">클래스 등록</a>
							<a href="#" class="alt">재료손질 등록</a>
							<a href="#" class="alt">편의점 꿀조합 등록</a>
							<a href="#" class="alt">맛집 리뷰 등록</a>
						</div>
					</div>
				</nav>
			</div>
             
             
         </div>
     </div>

     <div class="gnbnav">
         <ul class="gnbnavea10">
             <li><a href="recipePaging.net" >레시피</a></li>
             <li><a href="classPaging.net">요리클래스</a></li>
             <li><a href="">요리정보</a></li>
             <li><a href="NoticeList.bo">고객지원</a></li>         
         </ul>
     </div>
 </div>
                    