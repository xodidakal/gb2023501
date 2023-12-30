<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--CSS START -->
<style type="text/css">
	tr {
		height: 70px;
	}
	th {
		text-align: left;
		width: 150px;
	}
</style>
<!--CSS END -->

<!-- JS START -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	
	
	$(function() {
		// 게시구분이 Q&A이면 상단글, 게시일자 필드 사라짐
		if ($("#b_category").val() == 2 || $("#b_category").val() == 3) {
	        // 숨길 요소들 숨기기
	        $("#check_bflag, #radio_date").hide();
	    }

	    // b_category의 값이 변경될 때의 이벤트 처리
	    $("#b_category").change(function() {
	        // b_category의 값이 2인 경우
	        if ($(this).val() == 2 || $("#b_category").val() == 3) {
	            // 숨길 요소들 숨기기
	            $("#check_bflag, #radio_date").hide();
	        } else {
	            // b_category의 값이 2가 아닌 경우
	            // 숨겼던 요소들 다시 보이기
	            $("#check_bflag, #radio_date").show();
	        }
	    });
	});
	
	// 공지 등록 여부
	function updateValue() {
        // 체크박스 요소 가져오기
        var checkbox = document.getElementById("b_flag");

        // b_flag의 값 업데이트
        if (checkbox.checked) {
            // 체크되었을 때
            checkbox.value = "1";
            alert("checkbox.value->"+checkbox.value);
        } else {
            // 체크되지 않았을 때
            checkbox.value = "0";
            alert("checkbox.value->"+checkbox.value);
        }
    }
	
	// 게시일자 Radio버튼
	document.addEventListener("DOMContentLoaded", function () {
	    var nowTimeRadio = document.getElementById("nowTimeRadio");
	    var notNowTimeRadio = document.getElementById("notNowTimeRadio");
	    var selectedDateInput = document.getElementById("selectedDate");

	    // 등록 즉시 게시
	    nowTimeRadio.addEventListener("click", function () {
	        selectedDateInput.style.display = "none";
//	        selectedDateInput.value = ""; // 선택된 날짜 초기화
	        $('input[name=b_regi_date]').val(getTodayDate);
	    });
	
	    // 게시 일자 선택
	    notNowTimeRadio.addEventListener("click", function () {
	        selectedDateInput.style.display = "inline";
	    });
	    
	 	// 날짜 선택 처리
	    selectedDateInput.addEventListener("change", function () {
	        // 선택한 날짜로 값을 설정
	        $('input[name=b_regi_date]').val(selectedDateInput.value);
	    });
	 	
	 	// 등록 즉시 게시에 오늘날짜 입력
	    function getTodayDate() {
	        var today = new Date();
	        var year = today.getFullYear();
	        var month = today.getMonth() + 1; // 월은 0부터 시작하므로 +1
	        var day = today.getDate();

	        // 날짜를 'YYYY-MM-DD'
	        return year + '-' + (month < 10 ? '0' : '') + month + '-' + (day < 10 ? '0' : '') + day;
	    }
	});
	
</script>
<!-- JS END -->


</head>
<body>
<!-- 	<div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;"> -->
<!--         <h6 class="section-title bg-white text-center text-primary px-3">Services</h6> -->
<!--         <h2 class="display-6 mb-4">교육 자료 등록</h2> -->
<!--     </div> -->
<div class="row g-0 justify-content-center">
	<div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
		<form action="/customer/insertBoard" method="post" enctype="multipart/form-data" id="boardForm">
	        <div class="row g-3">
	        <h2 class="display-7 mb-4">게시물 등록</h2>
	        <hr class="my-3">
	        	<table class="formTable">
					<tr>
						<th>게시 구분</th>
							<td width="150px;">
								<input type="hidden" name="m_num" 		value="${member.mmNum}">		                			
		                		<input type="hidden" name="b_ref_num" 	value="1">
		                		<input type="hidden" name="b_step" 		value="1">
		                		
			                    <select id="b_category" name="b_category" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
			                    	<c:choose>
			                    		<c:when test="${member.category eq 4}">
			                    			<option value="1" <c:if test="${BoardCategory == 1}">selected</c:if>>공지사항</option>
											<option value="2" <c:if test="${BoardCategory == 2}">selected</c:if>>Q&A</option>
											<option value="3" <c:if test="${BoardCategory == 3}">selected</c:if>>FAQ</option>
			                    		</c:when>
			                    		<c:otherwise>
			                    			<option value="2" <c:if test="${BoardCategory == 2}">selected</c:if>>Q&A</option>
			                    		</c:otherwise>
			                    	</c:choose>
								</select>
			                </td>
						<th>게시 분류</th>
							<td width="150px;">
								<select id="b_notie_type" name="b_notie_type" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
									<option value="1">공통</option>
									<option value="2">이벤트</option>
									<option value="3">업데이트</option>
									<option value="4">규정 및 정책</option>
								</select>							
							</td>
					</tr>
					<tr id="check_bflag">
		                <th>상단글로 노출</th>
						<td width="150px;">
    						<input class="form-check-input" type="checkbox" id="b_flag" name="b_flag" value="0" onchange="updateValue()">
		                </td>
					</tr>
		            <tr>
						<th>제목</th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="b_title" name="b_title" placeholder="Subject" required="required">
<!-- 		                    <label for="subject">자료명</label> -->
		            	</td>
					</tr>
					<tr id="radio_date">
						<th>게시 일자</th>
						<td width="150px;">
							<input type="radio" name="b_regi_date" id="nowTimeRadio">
							<label class="form-check-label">등록 즉시 게시</label>
						</td>
						<td width="150px;">
							<input type="radio" name="b_regi_date" value="notNowTime" id="notNowTimeRadio">
							<label class="form-check-label">게시 일자 선택</label>
						</td>
						<td width="150px;">
							<input type="date" id="selectedDate" name="b_regi_date" style="display: none;">
							<!-- <input type="date" name="b_regi_date"> -->
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
		                   	<textarea class="form-control" placeholder="Leave a message here" name="b_content" id="message" style="height: 200px"></textarea>    
						</td>
					</tr>
					<tr></tr>
	                <tr>
	                	<th>첨부파일</th>
	                	<td colspan="3">
	                		<label style="font-size: medium;">파일 1개당 최대 첨부 용량 30MB</label>
		                    <input type="file" class="form-control" name="file1" id="subject" placeholder="Subject">
		                </td>
	                
	                </tr>
                </table>
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<a href="/customer/boardList?b_category=${BoardCategory}"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="등록">
					<!-- <input type="button" onclick="button_test()" value="테스트"> -->
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
