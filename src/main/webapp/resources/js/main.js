$(function(){
	// 화면 초기화
	var recipeCount = $("#recipeCount").val();
	var index = 0;
	for(var i=5-recipeCount.length+1; i<=5; i++){
		$("#count" + i + " strong").text(recipeCount.substr(index, index+1))
		index++;
	}
});