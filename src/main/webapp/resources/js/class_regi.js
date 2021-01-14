
$(document).ready(function() {
	
	var memberId = $("#MEMBER_ID").val();
	$("#member_id").attr('value', memberId);
	
	// 요리 대표 사진 파일 처리 
	bindEvent(document.getElementById("regi_main_file"), 'change', handlePhotoFiles);
	
	// 디폴트 시 커리큘럼 추가 창 1개 
	addCur();
	
	// 디폴트 시 커리큘럼 추가 창 1개 
	addDetail();
	
	//-----------------------------------------------------------------------
	// 신청 기간 유효성 검사 
	//-----------------------------------------------------------------------
	$(document).on('focusout', 'input[name=CLASS_APP_STARTDATE]', function(){
		var app_start_date = $(this).val();
		var app_end_date = $('input[name = CLASS_APP_ENDDATE]').val();
		
		if(app_start_date > app_end_date && app_end_date != "") {
			alert("신청 시작일이 신청 마감일보다 빨라야 합니다.");
			$(this).val("");
		}
	});
	
	$(document).on('focusout', 'input[name=CLASS_APP_ENDDATE]', function(){
		var app_start_date = $('input[name = CLASS_APP_STARTDATE]').val();
		var app_end_date = $(this).val();
		
		if(app_start_date > app_end_date) {
			alert("신청 마감일이 신청 시작일보다 늦어야합니다.");
			$(this).val("");
		}
	});
	
	//-----------------------------------------------------------------------
	// 수강 기간 유효성 검사 
	//-----------------------------------------------------------------------
	$(document).on('focusout', 'input[name=CLASS_STARTDATE]', function(){
		var start_date = $(this).val();
		var end_date = $('input[name = CLASS_ENDDATE]').val();
		
		if(start_date > end_date && end_date != "") {
			alert("수강 시작일이 수강 마감일보다 빨라야 합니다.");
			$(this).val("");
		}
	});
	
	$(document).on('focusout', 'input[name=CLASS_ENDDATE]', function(){
		var start_date = $('input[name = CLASS_STARTDATE]').val();
		var end_date = $(this).val();
		
		if(start_date > end_date) {
			alert("수강 마감일이 수강 시작일보다 늦어야합니다.");
			$(this).val("");
		}
	});
	
	//-----------------------------------------------------------------------
	// 수강 시간 유효성 검사 
	//-----------------------------------------------------------------------
	$(document).on('focusout', 'input[name=CLASS_STARTTIME]', function(){
		var start_time = $(this).val();
		var end_time = $('input[name = CLASS_ENDTIME]').val();
		
		if(start_time > end_time && end_time != "") {
			alert("수강 시작 시간이 수강 마감 시간보다 빨라야 합니다.");
			$(this).val("");
		}
	});
	
	$(document).on('focusout', 'input[name=CLASS_ENDTIME]', function(){
		var start_time = $('input[name = CLASS_STARTTIME]').val();
		var end_time = $(this).val();
		
		if(start_time > end_time) {
			alert("수강 마감 시간이 수강 시작 시간보다 늦어야합니다.");
			$(this).val("");
		} 
	});
	
	
});

//===================================================================================
// 주소 검색 처리  
//===================================================================================

function openDaumZipAddress() {
	new daum.Postcode({
		oncomplete:function(data) {
			jQuery("#zonecode").val(data.zonecode);
			jQuery("#address").val(data.address);
			jQuery("#address_etc").focus();
			console.log(data);
		}
	}).open();

}

//===================================================================================
// 사진 파일 처리 
//===================================================================================

// 클래스 대표 이미지 파일을 클릭한 경우 
function browseMainFile(){
	$("#regi_main_file").click();
}

// 상세내용 순서 이미지 파일을 클릭한 경우 
function browseDetailFile(idx){
	 $("#divDetailArea [id=regi_detail_file_" + idx + "]").click();
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
		var idx = element_id.substring(17);
		if (e.target.id == "regi_detail_file_" + idx)
			chg_img_id ="#detailPhotoHolder_" + idx;	
	}
	 
	 // 읽기에 성공했을 떄 실행되는 이벤트 핸들러
	 reader.onload = function(e){
	    // result : 읽기 결과가 저장됨
	    // reader.result 또는 e.target.result
		 $(chg_img_id).attr('src', e.target.result);
	 }
	 
}

//===================================================================================
// 커리큘럼 부분
//===================================================================================

// 커리큘럼 추가 
function addCur(){
    
	// 순서 가져오기 
	var idx = 0;
    $("#divCurArea [id^=divCurDay_]").each(function(){ 
        var tmp = $(this).prop('id').replace('divCurDay_', '');
        var tmp_idx = parseInt(tmp);
        idx = Math.max(idx, tmp_idx);
    });   
    idx++;
    
    // _IDX으로 끝나는 id 값에 해당 인덱스 부여 
    var str = $("#divCurTemplate").html().replace(/__IDX/g, idx);
    var str = str.replace(/seq_cur_day/, idx);
    var str = str.replace(/seq_cur_day_title/, 'DAY ' + idx);
    var str = str.replace(/cur_idx_del/, + idx);			// 해당 항목 삭제 기능 
    var str = str.replace(/_IDX/g, '_' + idx);
    
    // 유효성 검사 
    if (typeof prev_idx == 'undefined' || prev_idx === null || prev_idx == 0) {
        $(str).appendTo('#divCurArea');
    }
    else {
        $(str).insertAfter("#divCurDay_" + prev_idx);
    }
    remakeCurNumber();
}

// 커리큘럼  삭제 
function delCur(idx) {
    $("#divCurArea [id=divCurDay_"+idx+"]").fadeOut(200,function() {
    	$("#divCurArea [id=divCurDay_" + idx + "]").remove();
        remakeCurNumber();
    });
}

function remakeCurNumber(){
    $("#divCurArea [id^=divCurDay_]").each(function(idx, obj){
        var cur_step = $(this).prop('id').replace('divCurDay_', '');
        $("#divCurArea [id=divCurNum_" + cur_step + "]").html('DAY ' + (idx + 1));
    });
}

//===================================================================================
//상세 내용 부분
//===================================================================================

//상세 내용 추가 
function addDetail(prev_step, init_json){
    
    // 순서 인덱스 가져오기 
	var idx = 0;
    $("#divDetailArea [id^=divDetailItem_]").each(function(){
        var tmp = $(this).prop('id').replace('divDetailItem_', '');
        var tmp_idx = parseInt(tmp, 10);
        idx = Math.max(idx, tmp_idx);
    });
    idx++;

    // _IDX으로 끝나는 id 값에 해당 인덱스(idx) 부여 
    var str = $("#divDetailTemplate").html().replace(/__IDX/g, idx);
    var str = str.replace(/seq_detail_num/, idx);
    var str = str.replace(/seq_detail_title/, 'DETAIL ' + idx);
    var str = str.replace(/seq_detail_del/, idx);
    var str = str.replace(/_IDX/g, '_' + idx);
    
    // 유효성 검사 
    if (typeof prev_idx == 'undefined' || prev_idx === null || prev_idx == 0) {
        $(str).appendTo('#divDetailArea');
    }
    else {
        $(str).insertAfter("#divDetailItem_" + prev_idx);
    }
       
    // 상세 내용 이미지 파일 처리 
    console.log("regi_detail_file_" + idx);
    bindEvent(document.getElementById("regi_detail_file_" + idx), 'change', handlePhotoFiles);
    
    // 화면에 보여지는 상세 내용 순서 
    remakeDetailNumber();
}

// 상세 내용 제거 
function delDetail(idx){
	$("#divDetailArea [id=divDetailItem_" + idx + "]").fadeOut(200,function() {
		$("#divDetailArea [id=divDetailItem_" + idx + "]").remove();
		remakeDetailNumber();			// 화면에 보여지는 스텝 순서 
	});
}

function remakeDetailNumber(){
    $("#divDetailArea [id^=divDetailItem_]").each(function(idx, obj){
        var detail_step = $(this).prop('id').replace('divDetailItem_', '');
        $("#divDetailArea [id=divDetailNum_" + detail_step + "]").html('DETAIL ' + (idx + 1));
    });
}

