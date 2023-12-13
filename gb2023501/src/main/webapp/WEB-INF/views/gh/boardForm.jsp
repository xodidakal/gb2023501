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

	// 게시일자 Radio버튼
	document.addEventListener("DOMContentLoaded", function () {
	    var nowTimeRadio = document.getElementById("nowTimeRadio");
	    var notNowTimeRadio = document.getElementById("notNowTimeRadio");
	    var selectedDateInput = document.getElementById("selectedDate");
	
	    // 등록 즉시 게시
	    nowTimeRadio.addEventListener("change", function () {
	        selectedDateInput.style.display = "none";
	        selectedDateInput.value = ""; // 선택된 날짜 초기화
	    });
	
	    // 게시 일자 선택
	    notNowTimeRadio.addEventListener("change", function () {
	        selectedDateInput.style.display = "inline";
	    });
	    
	});
	 
	// 공지 등록 여부
	$(function() {
		$('#check_b_flag').click(function() {
			var checkboxValue = document.getElementById('check_b_flag').value;
			var checked = $(this).is(':checked');
			if(checked) {
				$(this).val("0");
				$('input[name=b_flag]').val("0");
				alert('Checkbox Value: ' + checkboxValue);
			}
			else {
				$(this).val("1");
				$('input[name=B_flag]').val("1");
				alert('Checkbox Value: ' + checkboxValue);
			}
		});
	});
	
	/* document.addEventListener("DOMContentLoaded", function() {
		var nowTimeRadio = getElementById("nowTimeRadio");
		var notNotTimeRadio = getElementById("notNowTimeRadio");
		var selectedDateInput = getElementById("selectedDate");
		
		nowTimeRadio.addEventListner("change", function() {
			selectedDateInput.value = "sysdate";
			selectedDateInput.style.display = "none";
			selectedDateInput.value = "";
		});
		
		notNotTimeRadio.addEventListner("change", function() {
			selectedDateInput.style.display = "inline";
		});
		
	});
	 */
	 
	 
	/* $(function() {
		$('#check_Bflag').change(function() {
			var checked = $(this).is(':checked');
			if (checked) {
                $(this).val("0");
                $('input[name=b_flag]').val("0");
            } else {
                $(this).val("1");
                $('input[name=b_flag]').val("1");
            }
		});
	}); */
	
	/* $(function() {
        var checkboxValue = document.getElementById('check_Bflag').value;
        var checked = $(this).is(':checked');
        
		$('#check_Bflag').click(function () {
			alert("클릭");
            // 체크된 경우
            if (checked) {
                // b_flag에 0을 주기
                $(this).val('0');
                $('input[name=b_flag]').val("0");
                alert('Checkbox Value: ' + checkboxValue);
                
            } else {
                // 체크가 해제된 경우 b_flag에 1을 주기
                $(this).val('1');
                $('input[name=b_flag]').val("1");
                alert('Checkbox Value: ' + checkboxValue);
            }
		});
	}); */
	
	/* $(function() {
		
	    $('#check_Bflag').click(function() {
	    	var checkboxValue = document.getElementById('check_Bflag').value;
	        var checked = $(this).is(':checked');
	        $('input[name=b_flag]').val(checked ? "1" : "0");
	        alert('Checkbox Value: ' + checkboxValue);
	    });
	}); */
	
	


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
		<form action="/customer/insertBoard" method="post" enctype="multipart/form-data">
	        <div class="row g-3">
	        <h2 class="display-7 mb-4">게시물 등록</h2>
	        <hr class="my-3">
	        	<table class="formTable">
					<tr>
						<th>게시 구분</th>
							<td width="150px;">
			                    <select id="b_category" name="b_category" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
			                    <c:if test=""></c:if>
									<option value="1">공지사항</option>
									<option value="2">Q&A</option>
									<option value="3">FAQ</option>
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
					<tr>
						<th>상단글로 노출</th>
						<td width="150px;">
							<input type="hidden" name="b_flag" value="1">
		                    <input class="form-check-input" type="checkbox" name="check_b_flag" id="check_b_flag" value="1">
		                </td>
					</tr>
		            <tr>
						<th>제목</th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="b_title" name="b_title" placeholder="Subject">
<!-- 		                    <label for="subject">자료명</label> -->
		            	</td>
					</tr>
					<tr>
						<th>게시 일자</th>
						<td width="150px;">
							<input type="radio" name="b_regi_date" value="sysdate" id="nowTimeRadio">
							<label class="form-check-label">등록 즉시 게시</label>
						</td>
						<td width="150px;">
							<input type="radio" name="b_regi_date" value="notNowTime" id="notNowTimeRadio">
							<label class="form-check-label">게시 일자 선택</label>
						</td>
						<td width="150px;">
							<input type="date" id="selectedDate" name="selectedDate" style="display: none;">
							<input type="date" name="b_regi_date">
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
					<a href="boardList"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="등록">
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
