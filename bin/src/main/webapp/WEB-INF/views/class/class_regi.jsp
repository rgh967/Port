<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>CLASS REGISTRATION</title>
	<link rel="stylesheet" href="resources/css/class_regi.css" type="text/css">
	<script src="resources/js/jquery-3.5.1.js"></script>
	<script src="resources/js/recipe_regi.js"></script>
</head>

<body>
<form name="class_regi"  action="classProcess.net" method="post" enctype="multipart/form-data">
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
			<!-- 클래스 메뉴 -->
			<!-- --------------------------------------------------------  -->
			<div class="cont_line">
				<p class="cont_tit4">메뉴</p>
				<input type="text" name="CLASS_MENU" id="regi_menu" class="space_set iuput_text_600" 
					placeholder="예) 소고기 미역국 끓이기" required>
			</div>
			
			
			<!-- --------------------------------------------------------  -->
			<!-- 요리정보  -->
			<!-- --------------------------------------------------------  -->
			<div class="cont_line">
				<p class="cont_tit4">수강 기간</p>
				
				<select name="RECIPE_PEOPLE" id="regi_people"  text="수강 시작일" class="space_set" required>
					<option value="" >수강 시작일</option>
					<option value="1">1인분</option>
					<option value="2">2인분</option>
					<option value="3">3인분</option>
					<option value="4">4인분</option>
					<option value="5">5인분</option>
					<option value="6">6인분이상</option>
				</select>
				
				<span class="pad_l_30 font_20"> ~ </span>
				<select name="RECIPE_TIME" id="regi_time"  text="수강 종료일 " class="space_set" required>
					<option value="" >수강 종료일</option>
					<option value="5">5분이내</option>
					<option value="10">10분이내</option>
					<option value="15">15분이내</option>
					<option value="20">20분이내</option>
					<option value="30">30분이내</option>
					<option value="60">60분이내</option>
					<option value="90">90분이내</option>
					<option value="120">2시간이내</option>
					<option value="999">2시간이상</option>
				</select>
			</div>
		</div><!-- end; cont_box pad_l_60 -->
			
			
			
			<!-- --------------------------------------------------------  -->
			<!-- 요리소개 -->
			<!-- --------------------------------------------------------  -->
			<div class="cont_line">
				<p class="cont_tit4">요리소개</p>
				<textarea name="RECIPE_INTRO" id="regi_intro" class="iuput_textArea_600 space_set" required 
					placeholder="이 레시피의 탄생배경을 적어주세요. 예) 남편의 생일을 맞아 소고기 미역국을 끓여봤어요. 어머니로부터 배운 미역국 레시피를 남편의 입맛에 맞게 고안했습니다."></textarea>
			</div>
      		


		
		<!-- --------------------------------------------------------  -->
		<!-- 재료추가  -->
		<!-- --------------------------------------------------------  -->
		<div class="cont_box pad_l_60">
			<p class="cont_tit4">재료</p>
			<!-- 재료추가 기능 ; script로 처리  -->	
			<div id="divIngredientArea" class="finder">
				
				<div id="divIngredientContents"></div>
				
				<!-- ingredient template -->
				<div id="divIngredientTemplate" style="display:none">
				
					<div id="divIngredient_IDX" class="pad_b_25">
						<input type="text" name="ING_NAME" id="ing_name_IDX"
							class="iuput_text_300 ingredient_add space_set" placeholder="재료" >
							
						<input type="text" name="ING_AMOUNT" id="ing_amount_IDX"
							class="iuput_text_300 ingredient_add space_set" placeholder="정량" >
							
						<a id="btnIngredientDel_IDX" href="javascript:delIngredient(ing_idx_del)" class="btn_del" style="display:inline-block">
							<img src="resources/image/btn-del.png" class="btn_img"></a>
					</div> 
				</div> <!-- /ingredient template -->
				
				<div class="div_btn_add">
					<a id="btnIngredientAdd" href="javascript:addIngredient()" class="a_btn_add" style="display:inline-block">
						<img src="resources/image/btn-add.png" class="btn_img"> 추가 </a>
				</div>
				
			</div>
		</div><!--end; cont_box pad_l_60-->
		
		
		<!-- --------------------------------------------------------  -->
		<!-- 요리 순서  -->
		<!-- --------------------------------------------------------  -->
		<div class="cont_box pad_l_60">
			<p class="cont_tit4">요리순서</p>
				
			<div id="divStepArea" class="finder"></div>

			<!-- step template -->
			<div id="divStepTemplate" style="display:none">
				<div id="divStepItem_IDX" class="div_step">
					<input type="hidden" name="STEP_NUM" value="seq_step_num">
					<p id="divStepNum_IDX" class="cont_tit1 color_green" style="cursor:pointer">seq_step_title</p>
					
					
					<div id="divStepText_IDX" style="display:inline-block">
						<textarea name="STEP_CONTENT" id="step_text_IDX" class="iuput_textArea_600 space_set"
							placeholder="" style="height:160px;"></textarea>
					</div>
				
					<!--  사진 추가  -->
					<div id="divStepUpload_IDX" style="display:inline-block">
						<!-- 이미지 파일 처리  -->
						<div style="position:absolute; left:-3000px">
							<input type="file" name="STEP_IMAGE" id="regi_step_file_IDX" file_gubun="step" accept="jpeg,png,gif" 
									style="display:;width:0px;height:0px;font-size:0px;" text="">
						</div>
						
						
						<div id="divStepPhotoBox_IDX" is_over="0">
							<img id="stepPhotoHolder_IDX" onclick="browseStepFile(__IDX)" src="resources/image/regi_food.png"
								class=""
							 width="160" height="160" style="cursor:pointer">
						</div>
					</div>
					
					<a id="btnStepDel" href="javascript:delStep(seq_step_del)" class="btn-del" style="display:inline-block">
						<img src="resources/image/btn-del.png" style="width:25px; height:25px;"></a>
				</div>
			</div><!--/step template-->
			
			<div class="div_btn_add">
				<a id="btnStepAdd" href="javascript:addStep()" class="a_btn_add" style="display:inline-block">
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