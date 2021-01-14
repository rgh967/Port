window.onload = function() {
	$('#aside').height($('#section').height());
}

$(function(){
	// 화면 초기화
	$(".review textarea").val("");  // 후기 입력창 내용 지우기
	$(".review table").hide();  // 후기 테이블 숨기기
	$("#moreview").hide();  // 더보기 버튼 숨기기
	$("#message").hide();  // 후기 없음 message 숨기기
	var page = 1;  // 더보기에서 보여줄 페이지를 기억할 변수
	// 후기 listcount가 0이 아니면(후기가 존재하면),
	count = parseInt($("#count").val());
	if(count != 0){
		getList(1);  // 후기 출력
	}else{  // 후기 listcount가 0이면(후기가 없으면),
		$("#message").show();  // 후기 없음 message 보이기
	}
	
	// 댓글 리스트 출력
	function getList(currentPage){
		$.ajax({
			type: "post",
			url: "classReviewProcess.net",
			data: {"CLASS_ID": $("#CLASS_ID").val(), 
				   "page": currentPage},
			dataType: "json",
			success: function(rdata){
				$("#count").text(rdata.listcount);
				if(rdata.listcount > 0){
					$("#message").hide();  // 후기 없음 message 숨기기
					$(".review table").show();  // 문서가 로딩될 떄 hide했던 것을 보이게 함
					$(".review tbody").empty();
					$(rdata.list).each(function(){
						output = "";
						img = "";
						var reviewDate = this.review_DATE.replace(" ", "<br>")
						if($("#MEMBER_ID").val() == this.member_ID){  // 로그인한 id와 작성자가 같은 경우
							img = "<img src='resources/image/pencil.png' width='15px' class='update'>"
								+ "<img src='resources/image/del_btn.png' width='15px' class='remove'>";
						}
						output += "<tr>"
								+ "	<td>"
								+ "		<input class='REVIEW_ID' type='hidden' value='" + this.review_ID + "'>"
								+ 		this.member.member_NAME 
								+ "</td>"
						 		+ "	<td></td>"  // content에 스크립트를 입력할 수 있어 처리하기위해 아무것도 넣지않음
						 		+ " <td>" + reviewDate + "</td>"
						 		+ " <td>" + img + "</td>"
						 		+ "</tr>";
						$(".review tbody").append(output);  // content를 제외한 나머지들을 추가
						$(".review tbody tr:last").find("td:nth-child(2)").text(this.review_CONTENT);
					})
					if(rdata.listcount > rdata.list.length){
						$("#moreview").show();
					}else{
						$("#moreview").hide();
					}
				}else{
					$("#message").show();
					$(".review table").hide();
				}
				$('#aside').height($('#section').height());
			}
		});
	}
	
	// 댓글 더보기 버튼 클릭
	$("#moreview").click(function(){
		getList(++page);
	})
	
	// 후기 수정 버튼 클릭 시, 값 초기화
	$(document).on('click', '.update', function(){
		// REVIEW_ID 설정
		$("#REVIEW_ID").val($(this).parent().prev().prev().prev().children(".review_table .REVIEW_ID").val());
		// 버튼 내용 변경
		$("#review_btn").text("수정");
		// content 출력
		$(".review textarea").val($(this).parent().prev().prev().text());
	});
	
	// 후기 삭제 버튼 클릭
	$(document).on('click', '.remove', function(){
		var result = confirm("정말 후기를 삭제하시겠습니까?");
		
		// 삭제
		if(result){
			var REVIEW_ID = $(this).parent().prev().prev().prev().children(".review_table .REVIEW_ID").val();
			$.ajax({
				type: "post",
				url: "classReviewDelProcess.net",
				data: {"REVIEW_ID": REVIEW_ID}, 
				dataType: "json",
				complete: function(rdata){
					getList(page)
				}
			});
		}
		// 삭제 취소됨
	});
	
	// 리뷰 등록/수정 버튼 클릭 시, 
	$("#review_btn").click(function(){
		// 로그인시에만 등록/수정 가능
		var memberId = $("#MEMBER_ID").val();
		if(memberId == null || memberId == ""){  // 비로그인 상태일 경우,
			alert("후기는 로그인 후 등록이 가능합니다.");
			location.href = "login.net";
			return false;
		}else{  // 로그인 상태일 경우,
			$(".review #ajaxForm").append("<input type='hidden' id='MEMBER_ID' name='MEMBER_ID' value='" + memberId + "'>")
		}
		
		// 후기를 작성하시 않았다면, 후기작성을 유도하고 등록되지 않음
		if($(".review textarea").val() == ""){
			alert("후기를 작성해주세요!");
			$(".review textarea").focus();
			return false;
		}
		
		// 수정 중일 경우
		if($("#review_btn").text() == "수정"){
			$("#review_btn").text("등록");
			$.ajax({
				type: "post",
				url: "classReviewModifyProcess.net",
				data: {"MEMBER_ID": $("#MEMBER_ID").val(),
					   "REVIEW_CONTENT": $(".review textarea").val(),
					   "REVIEW_ID": $("#REVIEW_ID").val()},
				dataType: "json",
				complete: function(rdata){
					$(".review textarea").val("");  // 후기 입력창 내용 지우기
					getList(page)
				}
			});
		}else{  // 등록일 경우
			$.ajax({
				type: "post",
				url: "classReviewRegiProcess.net",
				data: {"CLASS_ID": $("#CLASS_ID").val(), 
					   "MEMBER_ID": $("#MEMBER_ID").val(),
					   "REVIEW_CONTENT": $(".review textarea").val()},
				dataType: "json",
				success: function(rdata){
					// 등록 조건이 안될 경우
					if(rdata.result == "1"){
						alert("수강완료한 수강생만 작성 가능합니다.");
					}
				},
				complete: function(rdata){
					$(".review textarea").val("");  // 후기 입력창 내용 지우기
					
					getList(page)
				}
			});
		}
	});
	
	// 찜하기 버튼 클릭 시,
	$("#clove").click(function(){
		// 로그인시에만 스크랩 가능
		var memberId = $("#MEMBER_ID").val();
		if(memberId == null || memberId == ""){  // 비로그인 상태일 경우,
			alert("'클래스 찜하기'는 로그인 후 가능합니다.");
			location.href = "login.net";
			return false;
		}else{  // 로그인 상태일 경우,
			$("#cloveForm").append("<input type='hidden' name='MEMBER_ID' value='" + memberId + "'>");
		}
	});
	
	// 수강신청 버튼 클릭 시,
	$("#buy").click(function(){
		// 로그인시에만 스크랩 가능
		var memberId = $("#MEMBER_ID").val();
		if(memberId == null || memberId == ""){  // 비로그인 상태일 경우,
			alert("'수강 신청'은 로그인 후 가능합니다.");
			location.href = "login.net";
			return false;
		}else{  // 로그인 상태일 경우,
			$("#buyForm").append("<input type='hidden' name='MEMBER_ID' value='" + memberId + "'>")
		}
	});
});