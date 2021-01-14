<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>RECIPE REGISTRATION</title>
	<jsp:include page = "../home/header.jsp" />
	<link rel="stylesheet" href="resources/css/recipe_regi.css" type="text/css">
	<script src="resources/js/jquery-3.5.1.js"></script>
	<script src="resources/js/recipe_regi.js"></script>
</head>

<body>
<form name="recipe_regi" id="recipe_regi"  action="recipeRegiProcess.net" method="post" enctype="multipart/form-data">
<div class="container recipe_regi">
	<div class="regi_top">
		<div class="regi_title">레시피 등록</div>
	</div>
	<div class="regi_center">
		<div class="cont_box pad_l_60">
		
			<input type="hidden" name="MEMBER_ID" id="member_id"/>
			<!-- --------------------------------------------------------  -->
			<!-- 요리 대표 사진 설정  -->
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
			<!-- 레시피 제목 -->
			<!-- --------------------------------------------------------  -->
			<div class="cont_line">
				<p class="cont_tit4">레시피 제목</p>
				<input type="text" name="RECIPE_TITLE" id="regi_title" class="space_set iuput_text_600" 
					placeholder="예) 소고기 미역국 끓이기" required>
			</div>
			
			<!-- --------------------------------------------------------  -->
			<!-- 요리소개 -->
			<!-- --------------------------------------------------------  -->
			<div class="cont_line">
				<p class="cont_tit4">요리소개</p>
				<textarea name="RECIPE_INTRO" id="regi_intro" class="iuput_textArea_600 space_set" required 
					placeholder="이 레시피의 탄생배경을 적어주세요. 예) 남편의 생일을 맞아 소고기 미역국을 끓여봤어요. 어머니로부터 배운 미역국 레시피를 남편의 입맛에 맞게 고안했습니다."></textarea>
			</div>
      		
      		<!-- --------------------------------------------------------  -->
      		<!-- 동영상 -->
      		<!-- --------------------------------------------------------  -->
			<div class="cont_line pad_b_25"><p class="cont_tit4">동영상</p>				
				<textarea name="RECIPE_URL" id="regi_url" class="iuput_textArea_600 step_cont space_set" prev_url="" required 
					placeholder="동영상이 있으면 주소를 입력하세요.(Youtube,네이버tvcast,다음tvpot 만 가능) 예)http://youtu.be/lA0Bxo3IZmM"></textarea>
			</div>
			
			<!-- --------------------------------------------------------  -->
			<!-- 카테고리  -->
			<!-- --------------------------------------------------------  -->
			<div class="cont_line">
				<p class="cont_tit4">카테고리</p>
				<div class="finder">
					<dl class="dl_first">
						<dt><span>쉐프별</span></dt>
						<dd>
							<ul class="lst_check">
								<c:forEach var="chef" items="${cheflist}">
									<li><label><img src="resources/image/checkbox_uncheck.png" width="25px">${chef.CHEF_NAME}
									<input type="checkbox" name="CHEF_ID" 
											class="depth_one" sub-name="${chef.CHEF_NAME}" value="${chef.CHEF_ID}"></label></li>
								</c:forEach>											
							</ul>
						</dd>
					</dl>
					<dl class="dl_second">
						<dt><span>나라별</span></dt>
						<dd>
							<!-- value + 4 -->
							<ul class="lst_check">
								<c:forEach var="nation" items="${nationlist}">
									<li><label><img src="resources/image/checkbox_uncheck.png" width="25px">${nation.NATION_NAME}
									<input type="checkbox" name="NATION_ID"
											class="depth_one" sub-name="${nation.NATION_NAME}" value="${nation.NATION_ID}"></label></li>
								</c:forEach>
							</ul>
						</dd>
					</dl>
					<dl class="dl_third">
						<dt><span>재료별</span></dt>
						<dd>
							<!-- value + 8 -->
							<ul class="lst_check">
								<c:forEach var="part" items="${partlist}">
									<li><label><img src="resources/image/checkbox_uncheck.png" width="25px">${part.PART_NAME}
									<input type="checkbox" name="PART_ID"
										class="depth_one" sub-name="${part.PART_NAME}" value="${part.PART_ID}"></label></li>
								</c:forEach>
							</ul>
						</dd>
					</dl>
				</div>
			</div>

			<!-- --------------------------------------------------------  -->
			<!-- 요리정보  -->
			<!-- --------------------------------------------------------  -->
			<div class="cont_line">
				<p class="cont_tit4">요리정보</p>
      			<span class="font_20">인원 </span>
				<select name="RECIPE_PEOPLE" id="regi_people"  text="인원" class="space_set" required>
					<option value="" >인원</option>
					<option value="1">1인분</option>
					<option value="2">2인분</option>
					<option value="3">3인분</option>
					<option value="4">4인분</option>
					<option value="5">5인분</option>
					<option value="6">6인분이상</option>
				</select>
				
				<span class="pad_l_30 font_20">시간 </span>
				<select name="RECIPE_TIME" id="regi_time"  text="요리시간" class="space_set" required>
					<option value="" >시간</option>
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
				
				<span class="pad_l_30 font_20">난이도 </span>
				<select name="RECIPE_DEGREE" id="regi_degree"  text="난이도" class="space_set" required>
					<option value="" >난이도</option>
					<option value="상">상</option>
					<option value="중">중</option>
					<option value="하">하</option>
				</select>
			</div>
		</div><!-- end; cont_box pad_l_60 -->
		
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
		<button type="submit" id="submit" class="btn_submit">저장</button>
		<button type="reset" onclick="history.back();" class="btn_reset">취소</button>
	</div>
	
</div><!-- /container --></form>
</body>
</html>