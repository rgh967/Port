function sendQuery() {
	var $frm = $(".result-filter");
	var variables = $(".result-filter").serialize();

	$.ajax({
		url: $frm.attr("action"),
		type: $frm.attr("method"),
		data: variables,
		success: function(data) {
			$(".recipes .inner_result .lst_recipe").empty();
			$(".tit_area").find("strong").text(data.listcount);
			console.log(data)
			if(data.listcount > 0) {
				$(data.classlist).each(function(index, item) {
					output = '<li>';
					output += '<a href="classDetailProcess.net?CLASS_ID=' + item.class_ID + '"><img id="picture" src=/cook/resources/upload/class/' + item.class_MAIN_IMG + '/></a>';
					output += '<span class="people">' + item.class_REG_PEOPLE + '/' + item.class_PEOPLE + '</span>';
					output += '<div class="recipe_content"><span id="title"> ' + item.class_TITLE + '</span><br>';
					output += '<span id="app"> 신청 기간 : ' + item.class_APP_STARTDATE + ' ~ ' + item.class_APP_ENDDATE + '</span><br>';
					output += '<span id="date"> 강습 기간 : ' + item.class_STARTDATE + ' ~ ' + item.class_ENDDATE + '</span><br>';
					output += '<span id="time"> 강습 시간 : ' + item.class_STARTTIME + ' ~ ' + item.class_ENDTIME + '</span><br>';
					output += '<span id="cost"> 강습 비용 : ' + item.class_COST + '</span><br>';
					output += '<span id="lecturer"> 강사 이름 : ' + item.member.member_NAME + '</span>';
					output += '</li>';
					$('.inner_result .lst_recipe').append(output);
				});

				$('.paging').empty();
			
				if(data.page <= 1) {
					output2 = '<a class="prev">이전</a>';
				}else {
					output2 = '<a class="prev" href="classPaging.net?page=' + (data.page - 1) + '">이전</a>'
				}
				for(var i = data.startpage; i <= data.endpage; i++) {
					if(i == data.page) {
						output2 += '<a class="current">' + i + '</a>';
					}else {
						output2 += '<a class="current" href="classPaging.net?page=' + i + '">' + i + '</a>';
					}
				}
				if(data.page >= data.maxpage) {
					output2 += '<a class="next">다음</a>';
				}else {
					output2 += '<a class="next" href="classPaging.net?page=' + (data.page + 1) + '">다음</a>';
				}
			
				$('.paging').append(output2);
			}else {
				output = '<h1>검색결과가 없습니다.</h1>'
				$('.inner_result .lst_recipe').append(output);
				$('.paging').empty();
			}
			
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