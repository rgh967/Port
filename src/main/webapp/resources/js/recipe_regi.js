
$(document).ready(function() {
	
	var memberId = $("#MEMBER_ID").val();
	$("#member_id").attr('value', memberId);
	
	// 요리 대표 사진 파일 처리 
	bindEvent(document.getElementById("regi_main_file"), 'change', handlePhotoFiles);
	
	// 디폴트 시 재료 추가 창 1개 
	addIngredient();

	// 디폴트 시 요리순서 추가 창 1개 
	addStep();
	

	var dict = {};
	$(document).on('focusout', 'input[name=ING_NAME]', function(){
		var name = $(this).val()
		var id = $(this).attr('id')
		
		// 입력했던 값 수정 시 
		for(var key in dict){
			if(id == key)
				dict[key] == name
		}
		
		if(name != ""){
			for(var key in dict){
				if (dict[key] == name){
					alert("재료명이 같습니다.")
					$(this).val("")
					$(this).focus()
					return 
				}
			}
			dict[id] = name
		}else{
			dict[id] = name
		}

		/*
		for(var key in dict){
			console.log(key + ":" + dict[key])
		}
		*/

	});
	

	// ul의 클래스 명 lst_check 내 checkbox 클릭 이벤트
	$(".lst_check").on("click", "input:checkbox", function() {
		var self = $(this);
		var li = $(this).parent("label").parent("li"); 
		if($(this).hasClass("depth_one")) { 
			if(li.hasClass("checked")) {	
				li.each(function() {
					$img = $(this).find("img");
					$checkbox = $(this).find("input:checkbox");
					toggleInput($checkbox, false);
					toggleTag($checkbox, false);
					toggleCheckbox($checkbox, false);
					toggleImg($img, false);
				});
			}else {
				li.each(function() {
					$img = $(this).find("img");
					$checkbox = $(this).find("input:checkbox");
					toggleInput($checkbox, true);
					toggleTag($checkbox, true);
					toggleCheckbox($checkbox, true);
					toggleImg($img, true);
				});
			}
		}
	});
	
	//===================================================================================
	
	// 처음 보여줄 페이지
	var page = 1;
	// 한 페이지에 보여줄 제한 갯수
	var limit = 12;
	// category_chef 갯수
	var chef_id = 4;
	// category_nation 갯수
	var nation_id = 4;
	// category_part 갯수
	var part_id = 13;
	// ajax에 넘겨줄 값
	var sdata = "limit" + limit + "&state=ajax&page=" + page + "&chef_id=" + chef_id + "&nation_id=" + nation_id + "&part_id=" + part_id;
	console.log(sdata);
	//체크박스 부분
	$.ajax({
		type : "POST",
		url : "recipeInitList.net",
		data : sdata,
		dataType : "json",
		cache : false,
		success : function(data) {
			$(".tit_area").find("strong").text(data.listcount);
			console.log(data.listcount);
			// recipe 테이블 총 갯수 : listcount
			
			// category_chef
			$(data.cheflist).each(function(index, item) {
				output1 = '<li><label><img src="resources/image/checkbox_uncheck.png" width="25px">';
				output1 += item.chef_NAME;
				output1 += '<input type="checkbox" name="CHEF_ID" class="depth_one" sub-name="' + item.chef_NAME + '"value="' + item.chef_ID + '">';
				output1 += '</label></li>'
				$('.dl_first .lst_check').append(output1);
			});
			
			// category_nation
			$(data.nationlist).each(function(index, item) {
				output2 = '<li><label><img src="resources/image/checkbox_uncheck.png" width="25px">';
				output2 += item.nation_NAME;
				output2 += '<input type="checkbox" name="NATION_ID" class="depth_one" sub-name="' + item.nation_NAME + '"value="' + item.nation_ID + 1 + '">';
				output2 += '</label></li>';
				$('.dl_second .lst_check').append(output2);
			});
			
			// category_part
			$(data.partlist).each(function(index, item) {
				output3 = '<li><label><img src="resources/image/checkbox_uncheck.png" width="25px">';
				output3 += item.part_NAME;
				output3 += '<input type="checkbox" name="PART_ID" class="depth_one" sub-name="' + item.part_NAME + '"value="' + item.part_ID + 2 + '">';
				output3 += '</label></li>';
				$('.dl_third .lst_check').append(output3);
			});
		}
	});
	//===================================================================================
	
});



//===================================================================================
// 사진 파일 처리 
//===================================================================================

// 요리 대표 이미지 파일을 클릭한 경우 
function browseMainFile(){
	$("#regi_main_file").click();
}

// 요리 순서 이미지 파일을 클릭한 경우 
function browseStepFile(idx){
	 $("#divStepArea [id=regi_step_file_" + idx + "]").click();
}

// 해당 객체의 이벤트 연결 
function bindEvent(element, eventName, eventHandler){
	 if (element.addEventListener) {
		 element.addEventListener(eventName, eventHandler, false);
	 }
	 
	 else
	     if (element.attachEvent) {
	    	 element.attachEvent(eventName, eventHandler);
	     }
}

// 이미지 파일 핸들러 처리 
function handlePhotoFiles(e){

	 // 선택한 그림의 파일 객체 취득
	 var file = e.target.files[0];
	    
	 // file.type : 파일의 형식(MIME타입 = 예) text/html)
	 if(!file.type.match('image.*')){  // 파일 타입이 image인지 확인
	    alert("확장자는 이미지 확장자만 가능합니다.");
	    return;
	 }
	 // 파일을 읽기 위한 객체 생성
	 var reader = new FileReader();
	    
	 // DataURL 형식으로 파일을 읽어옴
	 // 읽어온 결과는 reader 객체의 result 속성에 저장됨
	 reader.readAsDataURL(file);
	 
	var element_id =  e.target.id;
	var chg_img_id;
	
	if(element_id == "regi_main_file"){
		chg_img_id = "#mainPhotoHolder";	
	}
	else {
		var idx = element_id.substring(15);
		if (e.target.id == "regi_step_file_" + idx)
			chg_img_id ="#stepPhotoHolder_" + idx;	
	}
	 
	 // 읽기에 성공했을 떄 실행되는 이벤트 핸들러
	 reader.onload = function(e){
	    // result : 읽기 결과가 저장됨
	    // reader.result 또는 e.target.result
		 $(chg_img_id).attr('src', e.target.result);
	 }
	 
}

//각 checkbox 부모 li에 checked class 추가 및 제거 하는 함수 
//- checked클래스가 있는 경우와 없는 경우 일 때 checkbox checkbox_empty, checkbox_checked 이미지
function toggleImg($img, isTurnOn) {
	 if(!isTurnOn) {
		 $img.prop('src', 'resources/image/checkbox_uncheck.png');
	 }else {
		 $img.prop('src', 'resources/image/checkbox_check.png');
	 }
}

//각 checkbox 부모 li에 checked class 추가 및 제거 하는 함수 - checked클래스가 있는 경우와 없는 경우 일 때 checkbox checkbox_empty, checkbox_checked 이미지
function toggleInput($input, isTurnOn) {
	var $li = $input.parent("label").parent("li");

	if ( !isTurnOn ) {
		$li.removeClass("checked");
	} else {
		$li.addClass("checked");
	}
}

//각 checkbox checked속성 true/false 함수
function toggleCheckbox($input, isTurnOn) {
	var $li = $input.parent("label").parent("li");

	if (!isTurnOn) {
		$input.prop("checked", false);
	} else {
		$input.prop("checked", true);
	}
}

//table내 #sub-result id를 가진 td에 체크박스 체크시 체크한 내용의 값 출력 함수
function toggleTag($input, isTurnOn) {
	console.log(isTurnOn);
	var isDuplicated = false;
	var name = $input.attr("sub-name");
	var id = $input.val();
	$("#sub-result span").each(function() {
		if ( $(this).attr("sub-id") == id ) {
			isDuplicated = true;
		}
	});

	if ( !isTurnOn ) {
		$("#sub-result").find("span[sub-id=" + id + "]").remove();
	} else {
		if (!isDuplicated) {
			var output = '<span class="added-sub" sub-id="' + id + '">' + name  + '<img src="resources/image/remove.png" width="15px" class="remove"></span>'
			$("#sub-result").append(output);
		}
	}
}

//===================================================================================
//재료 부분
//===================================================================================

// 재료 추가 
function addIngredient(){
    
	// 순서 가져오기 
	var idx = 0;
    $("#divIngredientContents [id^=divIngredient_]").each(function(){ 
        var tmp = $(this).prop('id').replace('divIngredient_', '');
        var tmp_idx = parseInt(tmp);
        idx = Math.max(idx, tmp_idx);
    });   
    idx++;
    
    // _IDX으로 끝나는 id 값에 해당 인덱스 부여 
    var str = $("#divIngredientTemplate").html().replace(/__IDX/g, idx);
    var str = str.replace(/ing_idx_del/, + idx);			// 해당 항목 삭제 기능 
    var str = str.replace(/_IDX/g, '_' + idx);
    
    // 유효성 검사 
    if (typeof prev_idx == 'undefined' || prev_idx === null || prev_idx == 0) {
        $(str).appendTo('#divIngredientContents');
    }
    else {
        $(str).insertAfter("#divIngredient_" + prev_idx);
    }
}

// 재료 삭제 
function delIngredient(idx) {
    $("#divIngredientContents [id=divIngredient_"+idx+"]").fadeOut(200,function() {
        $(this).remove();
    });
}

//===================================================================================
//순서 부분
//===================================================================================

//순서 추가 
function addStep(prev_step, init_json){
    
    // 순서 인덱스 가져오기 
	var idx = 0;
    $("#divStepArea [id^=divStepItem_]").each(function(){
        var tmp = $(this).prop('id').replace('divStepItem_', '');
        var tmp_idx = parseInt(tmp, 10);
        idx = Math.max(idx, tmp_idx);
    });
    idx++;

    // _IDX으로 끝나는 id 값에 해당 인덱스(idx) 부여 
    var str = $("#divStepTemplate").html().replace(/__IDX/g, idx);
    var str = str.replace(/seq_step_num/, idx);
    var str = str.replace(/seq_step_title/, 'STEP ' + idx);
    var str = str.replace(/seq_step_del/, idx);
    var str = str.replace(/_IDX/g, '_' + idx);
    
    // 유효성 검사 
    if (typeof prev_idx == 'undefined' || prev_idx === null || prev_idx == 0) {
        $(str).appendTo('#divStepArea');
    }
    else {
        $(str).insertAfter("#divStepItem_" + prev_idx);
    }
    
    /*
	if ($("#cok_reg_type").val() == 'edit') {
		
		$("#divStepItem_"+step).droppable({
            accept: "#divLeftContent img, #divLeftContent span",
			drop: function( event, ui ) {
				//var src = ui.draggable.attr('src');
				var src = ($(ui.draggable).prop('tagName') == 'IMG') ? ui.draggable.attr('src') : ui.draggable.attr('img_src');
                var target_step = $(this).prop('id').replace('divStepItem_','');
				setStepPhoto('1',src,src,target_step);
            }
        });
	}
	*/
    
    // 요리순서 이미지 파일 처리 
    bindEvent(document.getElementById("regi_step_file_" + idx), 'change', handlePhotoFiles);
    
    // 화면에 보여지는 스텝 순서 
    remakeStepNumber();
}

// 순서 제거 
function delStep(idx){
	$("#divStepArea [id=divStepItem_" + idx + "]").fadeOut(200,function() {
		$("#divStepArea [id=divStepItem_" + idx + "]").remove();
		remakeStepNumber();			// 화면에 보여지는 스텝 순서 
	});
}

function remakeStepNumber(){
    $("#divStepArea [id^=divStepItem_]").each(function(idx, obj){
        var step = $(this).prop('id').replace('divStepItem_', '');
        $("#divStepArea [id=divStepNum_" + step + "]").html('STEP ' + (idx + 1));
    });
}

