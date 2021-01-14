$(document).ready(function(){
	// ul의 클래스 명 lst_check 내 checkbox 클릭 이벤트
	$(".lst_check").on("click", "input:checkbox", function() {
		// li 찾기
		var li = $(this).parent("label").parent("li"); 
		// input class로 depth_one을 포함한다면
		if($(this).hasClass("depth_one")) {
			// li class로 checked를 포함한다면
			if(li.hasClass("checked")) {
				// li 배열
				li.each(function() {
					// li 내 img 찾기
					$img = $(this).find("img");
					// li 내 checkbox 찾기
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
		
		if(check() == false) {
			sendResult();
		}
		
		sendQuery();
		
	});
	
	// table #sub-result td에서 .remove 클래스를 가진 이미지 클릭 이벤트
	$("#sub-result").on("click", ".remove", function() {
		// table #sub-result에서 span
		var $wrap = $(this).parent();
		console.log($wrap);
		// span에서의 sub-id 속성
		var id = $wrap.attr("sub-id");
		// checkbox
		var $selectedInput = $(".finder").find("input:checkbox[value="+ id +"]");
		// checkbox image
		var $img = $selectedInput.parent().find("img");
		console.log($selectedInput);

		toggleInput($selectedInput, false);
		toggleCheckbox($selectedInput, false);
		toggleImg($img, false);
		
		$wrap.remove();
		
		if(check() == false) {
			sendResult();
		}
		
		sendQuery();
		
	});
	
	// 처음 보여줄 페이지
	var page = $('#page').val();
	// 한 페이지에 보여줄 제한 갯수
	var limit = $('#limit').val();
	// category_chef 갯수
	var chef_id = 4;
	// category_nation 갯수
	var nation_id = 4;
	// category_part 갯수
	var part_id = 13;
	var filter = $('#filter-sort').val();
	// ajax에 넘겨줄 값
	var sdata = "limit" + limit + "&state=ajax&page=" + page + "&chef_id=" + chef_id + "&nation_id=" + nation_id + "&part_id=" + part_id + "&filter=" + filter;
	console.log(sdata);
	
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
			if(data.listcount > 0) {
				$('.lst_recipe').empty();
				// controller에서 넘겨 준 recipelist
				$(data.recipelist).each(function(index, item) {
					output = '<li>';
					output += '<input type="hidden" id="RECIPE_ID_star" value=' + item.recipe_ID + '>'
					output += '<a href="recipeDetailProcess.net?RECIPE_ID=' + item.recipe_ID + '"><img id="picture" src=/cook/resources/upload/recipe/' + item.recipe_MAIN_IMG + '/></a>';
					output += '<span class="judge">요리조리등급<strong> ' + item.member.member_RANK + ' </strong></span>';
					output += '<span class="readcount">조회수: <strong> ' + item.recipe_READCOUNT + ' </strong></span>';
					output += '<div class="recipe_content"><span id="title"> ' + item.recipe_TITLE + '</span><br>';
					output += '<span id="intro"> ' + item.recipe_INTRO + '</span><br>';
					output += '<span id="name"> 작성자 : ' + item.member.member_NAME + '</span></div>';
					output += '</li>';
					$('.inner_result .lst_recipe').append(output);
				});
				$(data.reviewlist).each(function(index, item) {
					$('.lst_recipe > li').each(function() {
						var recipe_id_star = $(this).find('#RECIPE_ID_star').val();
						console.log(recipe_id_star)
						if(item.recipe_ID == recipe_id_star && item.review_STAR != 0) {
							console.log('실행')
							output = '<span class="star">';
							for(var i = 0; i < item.review_STAR; i++) {
								output += "<img class='stars' alt='별점' src='resources/image/filed_star.png'>";
							}
							output += '(' + item.review_STAR + ')' + '</span>';
							
							if($(this).find('#RECIPE_ID_star').val() == recipe_id_star) {
								$(this).find('.judge').after(output);
							}
						}
					});
				});
				
				console.log(data.page)
				
				$('.paging a').click(function() {
					if(data.page > 1) {
						history.go((data.page - 1));
					}
					for(var i = data.startpage; i <= data.endpage; i++) {
						if( i != data.page) {
							history.go(i);
						}
					}
					if(data.page < data.maxpage) {
						history.go((data.page + 1));
					}
				});
				page -= 8;
				limit -= 8;
			
			}
			// category_chef
			$(data.cheflist).each(function(index, item) {
				output1 = '<li><label><img src="resources/image/checkbox_uncheck.png" width="25px">';
				output1 += item.chef_NAME;
				output1 += '<input type="checkbox" name="CHEF" class="depth_one" sub-name="' + item.chef_NAME + '"value="' + item.chef_ID + '">';
				output1 += '</label></li>'
				$('.dl_first .lst_check').append(output1);
			});
			
			// category_nation
			$(data.nationlist).each(function(index, item) {
				output2 = '<li><label><img src="resources/image/checkbox_uncheck.png" width="25px">';
				output2 += item.nation_NAME;
				output2 += '<input type="checkbox" name="NATION" class="depth_one" sub-name="' + item.nation_NAME + '"value="' + item.nation_ID + 1 + '">';
				output2 += '</label></li>';
				$('.dl_second .lst_check').append(output2);
			});
			
			// category_part
			$(data.partlist).each(function(index, item) {
				output3 = '<li><label><img src="resources/image/checkbox_uncheck.png" width="25px">';
				output3 += item.part_NAME;
				output3 += '<input type="checkbox" name="PART" class="depth_one" sub-name="' + item.part_NAME + '"value="' + item.part_ID + 2 + '">';
				output3 += '</label></li>';
				$('.dl_third .lst_check').append(output3);
			});
		}
	});
	
	$(".sort_area").on("change", ".sort-selector", function() {
		var filter = $("#filter-sort");
		var option = $(this).val();

		$("#filter-sort").val(option);
		if(check() == false) {
			sendResult();
		}
		sendQuery();
	});
	
});

function sendResult() {
	// 처음 보여줄 페이지
	var page = $('#page').val();
	// 한 페이지에 보여줄 제한 갯수
	var limit = $('#limit').val();
	// category_chef 갯수
	var chef_id = 4;
	// category_nation 갯수
	var nation_id = 4;
	// category_part 갯수
	var part_id = 13;
	
	var filter = $('#filter-sort').val();
	// ajax에 넘겨줄 값
	var sdata = "limit" + limit + "&state=ajax&page=" + page + "&chef_id=" + chef_id + "&nation_id=" + nation_id + "&part_id=" + part_id + "&filter=" + filter;
	console.log(sdata);
	$.ajax({
		type : "POST",
		url : "recipeInitList.net",
		data : sdata,
		dataType : "json",
		cache : false,
		success : function(data) {
			$(".recipes .inner_result .lst_recipe").empty();
			$(".tit_area").find("strong").text(data.listcount);
			console.log(data.listcount);
			// recipe 테이블 총 갯수 : listcount
			if(data.listcount > 0) {
				// controller에서 넘겨 준 recipelist 
				$(data.recipelist).each(function(index, item) {
					output = '<li>';
					output += '<input type="hidden" id="RECIPE_ID_star" value=' + item.recipe_ID + '>'
					output += '<a href="recipeDetailProcess.net?RECIPE_ID=' + item.recipe_ID + '"><img id="picture" src=/cook/resources/upload/recipe/' + item.recipe_MAIN_IMG + '/></a>';
					output += '<span class="judge">요리조리등급<strong> ' + item.member.member_RANK + ' </strong></span>';
					output += '<span class="readcount">조회수:<strong> ' + item.recipe_READCOUNT + ' </strong></span>';
					output += '<div class="recipe_content"><span id="title"> ' + item.recipe_TITLE + '</span><br>';
					output += '<span id="intro"> ' + item.recipe_INTRO + '</span><br>';
					output += '<span id="name"> 작성자 : ' + item.member.member_NAME + '</span></div>';
					output += '</li>';
					$('.inner_result .lst_recipe').append(output);
				});
				
				$(data.reviewlist).each(function(index, item) {
					$('.lst_recipe > li').each(function() {
						var recipe_id_star = $(this).find('#RECIPE_ID_star').val();
						console.log(recipe_id_star)
						if(item.recipe_ID == recipe_id_star && item.review_STAR != 0) {
							console.log('실행')
							output = '<span class="star">';
							for(var i = 0; i < item.review_STAR; i++) {
								output += "<img class='stars' alt='별점' src='resources/image/filed_star.png'>";
							}
							output += '(' + item.review_STAR + ')' + '</span>';
							
							if($(this).find('#RECIPE_ID_star').val() == recipe_id_star) {
								$(this).find('.judge').after(output);
							}
						}
					});
				});
				
				$('.paging a').click(function() {
					if(data.page > 1) {
						history.go((data.page - 1));
					}
					for(var i = data.startpage; i <= data.endpage; i++) {
						if( i != data.page) {
							history.go(i);
						}
					}
					if(data.page < data.maxpage) {
						history.go((data.page + 1));
					}
				});
				page -= 8;
				limit -= 8;
			}
		}
	});
}

function check() {
	var checkbox = $('input:checkbox');
	for(var i = 0; i < checkbox.length; i++) {
		if(checkbox[i].checked) {
			return true;
		}
	}
	return false;
}

// checkbox checked, unchecked 시 이미지 처리 함수
function toggleImg($img, isTurnOn) {
	 if(!isTurnOn) {
		 $img.prop('src', 'resources/image/checkbox_uncheck.png');
	 }else {
		 $img.prop('src', 'resources/image/checkbox_check.png');
	 }
}

// 각 checkbox 부모 li에 checked class 추가 및 제거 하는 함수 - checked클래스가 있는 경우와 없는 경우 일 때 checkbox checkbox_empty, checkbox_checked 이미지
function toggleInput($input, isTurnOn) {
	var $li = $input.parent("label").parent("li");

	if ( !isTurnOn ) {
		$li.removeClass("checked");
	} else {
		$li.addClass("checked");
	}
}

// 각 checkbox checked속성 true/false 함수
function toggleCheckbox($input, isTurnOn) {
	var $li = $input.parent("label").parent("li");

	if (!isTurnOn) {
		$input.prop("checked", false);
	} else {
		$input.prop("checked", true);
	}
}

// 미완성 - .recipes에 내용 추가 
function sendQuery() {
	var $frm = $(".result-filter");
	
	// 처음 보여줄 페이지
	var page = $('#page').val();
	// 한 페이지에 보여줄 제한 갯수
	var limit = $('#limit').val();

	var filter = $('#filter-sort').val();
	var checkArr = [];
	
	$("input[class='depth_one']:checked").each(function() {
		checkArr.push($(this).val());
	})

	$.ajax({
		url: $frm.attr("action"),
		type: $frm.attr("method"),
		data: {
			chbox : checkArr,
			filter,
			page,
			limit
		},
		dataType: 'json',
		success: function(data) {
			$(".recipes .inner_result .lst_recipe").empty();
			$(".tit_area").find("strong").text(data.listcount);
			console.log(data)
			// controller에서 넘겨 준 recipelist 
			$(data.recipelist).each(function(index, item) {
				output = '<li>';
				output += '<input type="hidden" id="RECIPE_ID_star" value=' + item.recipe_ID + '>'
				output += '<a href="recipeDetailProcess.net?RECIPE_ID=' + item.recipe_ID + '"><img id="picture" src=/cook/resources/upload/recipe/' + item.recipe_MAIN_IMG + '/></a>';
				output += '<span class="judge">요리조리등급<strong> ' + item.member.member_RANK + ' </strong></span>';
				output += '<span class="readcount">조회수:<strong> ' + item.recipe_READCOUNT + ' </strong></span>';
				output += '<div class="recipe_content"><span id="title"> ' + item.recipe_TITLE + '</span><br>';
				output += '<span id="intro"> ' + item.recipe_INTRO + '</span><br>';
				output += '<span id="name"> 작성자 : ' + item.member.member_NAME + '</span></div>';
				output += '</li>';
				$('.inner_result .lst_recipe').append(output);
			});
			
			$(data.reviewlist).each(function(index, item) {
				$('.lst_recipe > li').each(function() {
					var recipe_id_star = $(this).find('#RECIPE_ID_star').val();
					console.log(recipe_id_star)
					if(item.recipe_ID == recipe_id_star && item.review_STAR != 0) {
						console.log('실행')
						output = '<span class="star">';
						for(var i = 0; i < item.review_STAR; i++) {
							output += "<img class='stars' alt='별점' src='resources/image/filed_star.png'>";
						}
						output += '(' + item.review_STAR + ')' + '</span>';
						
						if($(this).find('#RECIPE_ID_star').val() == recipe_id_star) {
							$(this).find('.judge').after(output);
						}
					}
				});
			});
			
			$('.paging a').click(function() {
				if(data.page > 1) {
					history.go((data.page - 1));
				}
				for(var i = data.startpage; i <= data.endpage; i++) {
					if( i != data.page) {
						history.go(i);
					}
				}
				if(data.page < data.maxpage) {
					history.go((data.page + 1));
				}
			});
			page -= 8;
			limit -= 8;
		}
	});
}

// table내 #sub-result id를 가진 td에 체크박스 체크시 체크한 내용의 값 출력 함수
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