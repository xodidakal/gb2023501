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
	
	// 업로드 파일 삭제
	function deleteFile(b_num) {
		
		if (!confirm("정말 삭제 하시겠습니까?")) {
		//	alert("아니오 클릭");
        } else {
		//  alert("네 클릭");
        	var params = {};
    		params.b_num = document.getElementById('b_num').value;
    		
    		$('#idAttachFile').hide();
    		$('#idAttachInput').show();
    		
    		$.ajax({
    			url			: 'deleteFile',
    			type		: 'POST',
    			contentType : 'application/json; charset:utf-8',
    			data		: JSON.stringify(params),
    			dataType	: 'text',
    			success		: function(data) {
    				if(data = "success") {
    					alert("업로드 파일 삭제");
    				} else {
    					alert("삭제 실패");
    				}
    			},
    			error : function(XHR, textStatus, errorThrown) {
    				// http 오류 번호를 반환하며 케이스별 오류 메시지 판정에 사용하면 유용
    				console.log( XHR.status );
    				// url의 full response를 반환하기 때문에 ajax 오류 디버깅 시에 상당한 도움
    				alert( jqXHR.responseText );
    			}
    		});
        }
		
		
	}
	
	/* function deleteFlagAttach() {
		$('#idAttachDeleteFlag').val("D");
		$('#idAttachFile').hide();
		$('#idAttachInput').show();
	} */
	

	// 게시일자 Radio버튼
	document.addEventListener("DOMContentLoaded", function () {
	    var nowTimeRadio = document.getElementById("nowTimeRadio");
	    var notNowTimeRadio = document.getElementById("notNowTimeRadio");
	    var selectedDateInput = document.getElementById("selectedDate");
	
	    // 등록 즉시 게시
	    nowTimeRadio.addEventListener("change", function () {
	        selectedDateInput.style.display = "none";
//	        selectedDateInput.value = ""; // 선택된 날짜 초기화
	        $('input[name=b_regi_date]').val(getTodayDate);
	    });
	
	    // 게시 일자 선택
	    notNowTimeRadio.addEventListener("change", function () {
	        selectedDateInput.style.display = "inline";
	    });
	    
	 	// 날짜 선택 처리
	    selectedDateInput.addEventListener("change", function () {
	        // 선택한 날짜로 값을 설정
	        $('input[name=b_regi_date]').val(selectedDateInput.value);
	    });
	    
	 	// 날짜가 있으면 게시 일자 선택 버튼 선택해서 달력에 날짜 표시 
	    if(selectedDateInput.value !== "") {
	    	notNowTimeRadio.checked = true;
	    	selectedDateInput.style.display = "inline";
	    	$('input[name=b_regi_date]').val(selectedDateInput.value);
	    }
	 	
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
		<form action="/customer/updateBoard" method="post" enctype="multipart/form-data" id="boardForm">
	        <div class="row g-3">
	        <input type="hidden" id="b_num" name="b_num" value="${BdDetail.b_num}">
	        <c:choose>
			 	<c:when test="${BdDetail.b_category == 1}"><h2 class="display-7 mb-4">공지사항</h2></c:when>
			 	<c:when test="${BdDetail.b_category == 2}"><h2 class="display-7 mb-4">Q&A</h2></c:when>
	 			<c:otherwise><h2 class="display-7 mb-4">FAQ</h2></c:otherwise>
			</c:choose>
	        
	        <hr class="my-3">
	        	
	        	<table class="formTable">
					<tr>
						<th>게시 구분</th>
							<td width="150px;">
			                    <select id="b_category" name="b_category" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
			                    	<c:choose>
			                    		<c:when test="${member.category eq '4'}">
			                    			<option value="1" <c:if test="${BdDetail.b_category == 1}">selected</c:if>>공지사항</option>
											<option value="2" <c:if test="${BdDetail.b_category == 2}">selected</c:if>>Q&A</option>
											<option value="3" <c:if test="${BdDetail.b_category == 3}">selected</c:if>>FAQ</option>
			                    		</c:when>
			                    		<c:otherwise>
			                    			<option value="2">Q&A</option>
			                    		</c:otherwise>
			                    	</c:choose>
								</select>
							</td>
							
						<th>게시 분류</th>
							<td width="150px;">
						 		<select id="b_notie_type" name="b_notie_type" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
								 	<option value="1" <c:if test="${BdDetail.b_notie_type == 1}">selected</c:if>>공통</option>
								 	<option value="2" <c:if test="${BdDetail.b_notie_type == 2}">selected</c:if>>이벤트</option>
								 	<option value="3" <c:if test="${BdDetail.b_notie_type == 3}">selected</c:if>>업데이트</option>
								 	<option value="4" <c:if test="${BdDetail.b_notie_type == 4}">selected</c:if>>규정 및 정책</option>
								</select>
							</td>
					</tr>
					<tr id="check_bflag">
		                <th>상단글로 노출</th>
						<td width="150px;">
    						<input class="form-check-input" type="checkbox" id="b_flag" name="b_flag" value="0" onchange="updateValue()" <c:if test="${BdDetail.b_flag == 1}">checked</c:if>>
		                </td>
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3">
							<input type="text" class="form-control" name="b_title" placeholder="Subject" value="${BdDetail.b_title}" required="required">
						</td>
					</tr>
					<tr id="radio_date">
						<th>게시 일자</th>
						<td width="150px;">
							<input type="radio" name="b_regi_date" id="nowTimeRadio" required="required">
							<label class="form-check-label">등록 즉시 게시</label>
						</td>
						<td width="150px;">
							<input type="radio" name="b_regi_date" value="notNowTime" id="notNowTimeRadio" required="required">
							<label class="form-check-label">게시 일자 선택</label>
						</td>
						<td width="150px;">
							<input type="date" id="selectedDate" name="b_regi_date" style="display: none;" required="required" 
								   value="<fmt:formatDate value='${BdDetail.b_regi_date}' pattern='yyyy-MM-dd'/>">
						</td>
					</tr>
					<tr></tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
		                   	<textarea class="form-control" placeholder="Leave a message here" name="b_content" id="message" style="height: 200px">${BdDetail.b_content}</textarea>    
						</td>
						
					</tr>
					<tr></tr>
	                <tr>
	                	<th>첨부파일</th>
		                <%-- <c:if test="${BdDetail.b_attach_path ne null}">
		                	<td colspan="3">
		                		<label><a href="javascript:popup('/upload/${BdDetail.b_attach_path}',800,600)">${BdDetail.b_attach_name}</a></label>
			                </td>
						</c:if> --%>
						<td colspan="3">
		                	<input type="hidden" name="b_attach_name" value="${BdDetail.b_attach_name}">
							<input type="hidden" name="b_attach_path" value="${BdDetail.b_attach_path}">
							
	                		<div id="idAttachFile">
								<c:if test="${BdDetail.b_attach_path ne null}">
	                				<label><a href="/upload/gh/${BdDetail.b_attach_name}" download="test">${BdDetail.b_attach_name}</a></label>
									&nbsp;&nbsp;<input type="button" onclick="deleteFile()" style="border-color:white; border:none; color: orange;" value="x">
								</c:if>	
							</div>												
																												
							<div id="idAttachInput" <c:if test="${BdDetail.b_attach_path ne null}">style="display:none;"</c:if> >
								<input type="file" class="form-control form-control-sm" name="file1">
							</div>
		                
		                </td>
	                </tr>
                </table>
                
                <!-- 수정, 삭제, 목록 버튼 -->
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<a href="boardList?b_category=${BdDetail.b_category}"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
					<c:if test="${BdDetail.m_category eq '4'}"></c:if>
						<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="수정">
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
