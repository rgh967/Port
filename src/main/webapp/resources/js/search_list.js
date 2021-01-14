$(document).ready(function(){
	// 화면 초기화 준비
    var nowAddress = unescape(encodeURIComponent($(location).attr('search')));
    nowAddress = decodeURIComponent(nowAddress)
    var parameters = (nowAddress.slice(nowAddress.indexOf('?') + 1, nowAddress.length)).split('&');

	var srhType = parameters[0].split('=')[1];
	var srhText = parameters[1].split('=')[1];
	
	// 검색 종류 선택
	if(srhType == "none"){
		$('#srhType>option:eq(0)').prop('selected', true);
	}else if(srhType == "title"){
		$('#srhType>option:eq(1)').prop('selected', true);
	}else{
		$('#srhType>option:eq(2)').prop('selected', true);
	}
	// 검색 텍스트 검색 창에 출력
	var text = srhText.replace('+', ' ');
	$("#srhText").val(text);
	
});