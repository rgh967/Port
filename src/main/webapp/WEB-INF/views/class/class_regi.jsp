<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>CLASS REGISTRATION</title>
	<jsp:include page = "../home/header.jsp" />
	<link rel="stylesheet" href="resources/css/class_regi.css" type="text/css">
	<script src="resources/js/jquery-3.5.1.js"></script>
	<script type="text/JavaScript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script src="resources/js/class_regi.js"></script>
</head>

<body>

<form name="class_regi"  action="classRegiProcess.net" method="post" enctype="multipart/form-data">
<div class="container class_regi">
	<div class="regi_top">
		<div class="regi_title">클래스 등록</div>
	</div>
	<input type="hidden" name="MEMBER_ID" value="A1111"/>
	<div class="regi_center">
		<div class="cont_box pad_l_60">
		
			<!-- --------------------------------------------------------  -->
			<!-- 클래스 대표 사진 설정  -->
			<!-- --------------------------------------------------------  -->
			<div id="divMainPhotoUpload" class="cont_pic2">
				<div style="position:absolute; left:-3000px">
					<input type="file" name="uploadFile" id="regi_main_file" accept="jpeg,png,gif">
				</div>
				<div id="divMainPhotoBox" is_over="0">
					<img id="mainPhotoHolder" class="img_main" onclick="browseMainFile()" src="resources/image/regi_food.png" >
				</div>
			</div>   
			
			<!-- --------------------------------------------------------  -->
			<!-- 클래스 제목 -->
			<!-- --------------------------------------------------------  -->
			<div class="cont_line">
				<p class="cont_tit4">클래스 제목</p>
				<input type="text" name="CLASS_TITLE" id="regi_title" class="space_set iuput_text_600" 
					placeholder="예) 소고기 미역국 끓이기" required>
			</div>
			
			<!-- --------------------------------------------------------  -->
			<!-- 강사 소개  -->
			<!-- --------------------------------------------------------  -->
			<div class="cont_line pad_b_25">
				<p class="cont_tit4">강사소개</p>
				<textarea name="CLASS_LECTURER" id="class_lecturer" class="iuput_textArea_600 space_set" required 
					placeholder="강사 소개란입니다. 자신을 마음껏 뽐내주세요! 예) 안녕하세요! 저는 30년간 요리를 해온 땡땡이입니다. 힌식 부분에서는 누구보다 자신있습니다. "></textarea>
			</div>	
			
			<!-- --------------------------------------------------------  -->
			<!-- 수강 인원, 수강 비용, 수강 장소 -->
			<!-- --------------------------------------------------------  -->
			<div class="cont_line">
				<p class="cont_tit4" >수강 인원</p>
				<input type="text" name="CLASS_PEOPLE" id="regi_title" class="space_set iuput_text_200" 
					placeholder="26" required>
				<span class="font_12">명</span>
			</div>
			
			<div class="cont_line">
				<p class="cont_tit4">수강 비용</p>
				<input type="text" name="CLASS_COST" id="regi_title" class="space_set iuput_text_200" 
					placeholder="26000" required>
				<span class="font_12">원</span>
			</div>
			
			<div class="cont_line">
				<p class="cont_tit4">수강 장소</p>
				<div class="cont_line_650">
					<input name = "postcode" id="zonecode" type="text" placeholder="우편번호" class="space_set iuput_text_200 mar_b_10" readonly/> &nbsp;
					<input type="button" onClick="openDaumZipAddress();" value = "주소 찾기" class = "btn_address"/> <br/>
		
					<input name = "address" type="text" id="address" placeholder="기본 주소" class="space_set iuput_text_600 mar_b_10"  readonly/>
					<input name = "address_etc" type="text" id="address_etc" placeholder="상세 주소" class="space_set iuput_text_600 mar_b_10" />
				
				</div>
			</div>
		</div><!-- end; cont_box pad_l_60 -->
		
		
		<!-- --------------------------------------------------------  -->
		<!-- 신청 기간, 수강 기간, 수강 시간 -->
		<!-- --------------------------------------------------------  -->
		<div class="cont_box pad_l_60">
		
			<div class="cont_line">
				<p class="cont_tit4">신청 기간</p>
				<input type="date" name="CLASS_APP_STARTDATE" class="space_set iuput_text_250" required>
				<span class="pad_sides_20 font_20"> ~ </span>
				<input type="date" name="CLASS_APP_ENDDATE" class="space_set iuput_text_250" required>
			</div>
			
			<div class="cont_line">
				<p class="cont_tit4">수강 기간</p>
				<input type="date" name="CLASS_STARTDATE" class="space_set iuput_text_250" required>
				<span class="pad_sides_20 font_20"> ~ </span>
				<input type="date" name="CLASS_ENDDATE" class="space_set iuput_text_250" required>
			</div>
			
			<div class="cont_line">
				<p class="cont_tit4">수강 시간</p>
				<input type="time" name="CLASS_STARTTIME" class="space_set iuput_text_250" required>
				<span class="pad_sides_20 font_20"> ~ </span>
				<input type="time" name="CLASS_ENDTIME" class="space_set iuput_text_250" required>
			</div>
		
		</div>
			
		<!-- --------------------------------------------------------  -->
		<!-- 커리큘럼  -->
		<!-- --------------------------------------------------------  -->
		<div class="cont_box pad_l_60">
			<p class="cont_tit4">커리큘럼</p>
			<!-- 재료추가 기능 ; script로 처리  -->	
			<div id="divCurArea" class="finder"></div>
				
			<!-- ingredient template -->
			<div id="divCurTemplate" style="display:none">
				<div id="divCurDay_IDX" class="pad_b_10">
					<input type="hidden" name="ARR_CUR_DAY" value="seq_cur_day">
					<p id="divCurNum_IDX" class="cont_tit1 color_green" style="cursor:pointer">seq_cur_day_title</p>
												
					<input type="text" name="ARR_CUR_CONTENT" id="cur_content_IDX"
						class="iuput_text_300 ingredient_add space_set" placeholder="커리큘럼 내용  ex)김치찌개 " >
						
					<a id="btnCurDel_IDX" href="javascript:delCur(cur_idx_del)" class="btn_del" style="display:inline-block">
						<img src="resources/image/btn-del.png" class="btn_img"></a>
				</div> 
			</div> <!-- /ingredient template -->
			
			<div class="div_btn_add">
				<a id="btnCurAdd" href="javascript:addCur()" class="a_btn_add" style="display:inline-block">
					<img src="resources/image/btn-add.png" class="btn_img"> 추가 </a>
			</div>
			
			
		</div><!--end; cont_box pad_l_60-->
		
		
		<!-- --------------------------------------------------------  -->
		<!--  상세내용 -->
		<!-- --------------------------------------------------------  -->
		<div class="cont_box pad_l_60">
			<p class="cont_tit4">상세내용</p>
				
			<div id="divDetailArea" class="finder"></div>

			<!-- detail template -->
			<div id="divDetailTemplate" style="display:none">
				<div id="divDetailItem_IDX" class="div_detail">
				
					<input type="hidden" name="ARR_DETAIL_STEP" value="seq_detail_num">
					<p id="divDetailNum_IDX" class="cont_tit1 color_green" style="cursor:pointer">seq_detail_title</p>
					
					<div id="divDetailText_IDX" style="display:inline-block">
						<textarea name="ARR_DETAIL_CONTENT" id="detail_text_IDX" class="iuput_textArea_600 space_set"
							placeholder="" style="height:160px;"></textarea>
					</div>
				
					<!--  사진 추가  -->
					<div id="divDetailUpload_IDX" style="display:inline-block">
						<!-- 이미지 파일 처리  -->
						<div style="position:absolute; left:-3000px">
							<input type="file" name="ARR_DETAIL_UPLOADFILE" id="regi_detail_file_IDX" file_gubun="step" accept="jpeg,png,gif" 
									style="display:;width:0px;height:0px;font-size:0px;" text="">
						</div>
						
						
						<div id="divDetailPhotoBox_IDX" is_over="0">
							<img id="detailPhotoHolder_IDX" onclick="browseDetailFile(__IDX)" src="resources/image/regi_food.png"
								class=""
							 width="180" height="180" style="cursor:pointer">
						</div>
					</div>
					
					<a id="btnDetailDel" href="javascript:delDetail(seq_detail_del)" class="btn-del" style="display:inline-block">
						<img src="resources/image/btn-del.png" style="width:25px; height:25px;"></a>
				</div>
			</div><!--/step template-->
			
			<div class="div_btn_add">
				<a id="btnDetailAdd" href="javascript:addDetail()" class="a_btn_add" style="display:inline-block">
						<img src="resources/image/btn-add.png" class="btn_img"> 순서 추가 </a>
			</div>
			
			
		</div><!--/cont_box-->
	</div>	<!-- end; regi_center -->
    
	<div class="regi_btm">
		<button type="submit" class="btn_submit">저장</button>
		<button type="reset" onclick="history.back();" class="btn_reset">취소</button>
	</div>
</div><!-- /container --></form>
</body>
</html>