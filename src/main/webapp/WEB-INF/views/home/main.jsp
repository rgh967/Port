<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<title>요리조리</title>
	<jsp:include page = "header.jsp" />
	<link rel="stylesheet" href="resources/css/main.css" type="text/css">
	<script src="resources/js/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="resources/js/main.js"></script>
</head>
<body>
<div class="container">

<div id="content" class="main">
  <div class="slide_main">
    <div class="btn_area"></div>
    <ul class="banner-slider" style="top:110px;">
          <li class="mainli">
              <a href="#">
              <img src="resources/image/test.jpg" class="mainimg"></a>
          </li>
    </ul>
  </div>
  <section class="sec_intro">
    <div class="inner">
      <input id="recipeCount" type="hidden"value="${recipeCount}">
      <dl class="count_area">
        <dt><span>요리조리!누적 레시피</span></dt>
        <dd>
			<span id="count1"><strong>0</strong></span>
			<span id="count2"><strong>0</strong></span>
			<span id="count3"><strong>0</strong></span>
			<span id="count4"><strong>0</strong></span>
			<span id="count5"><strong>0</strong></span>
        </dd>
      </dl>
      </div>
	</section>	
  </div>
  
  


</div>
</body>
</html>