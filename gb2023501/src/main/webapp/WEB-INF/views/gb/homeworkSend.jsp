<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(function(){
		// 숙제 전송 여부 확인
		var count = '${count}';
		if(count > 0){
			alert("숙제를 정상적으로 전송하였습니다.");
		}else if (count == 0){
			alert("숙제는 이미 학습자에게 발송되었습니다.");
		}
		
		// 숙제명 검색 셀렉트 박스 변경 시 검색
		$("#searchHtitle").change(function(){
		    // Value값 가져오기
		    var h_title = $("#searchHtitle :selected").val();
		    var lg_num = ${hwsend.lg_num};
			
		    location.href = "homeworkSend?lg_num="+lg_num+"&h_title="+h_title;
		  });
		  
		// 학습그룹명 검색 셀렉트 박스 변경 시 검색
		$("#searchLgtitle").change(function(){
			// Value값 가져오기
			var lg_num = $("#searchLgtitle :selected").val();
			var h_title = $("#searchHtitle :selected").val();
			
			location.href = "homeworkSend?lg_num="+lg_num+"&h_title="+h_title;
		});
		  
		// 전체 선택 클릭 시 발생 이벤트
		$("#checkAll").click(function() {
			var checkAll = $('#checkAll').val();
			
			if(checkAll == '전체선택'){
				$("input[name=m_num]").prop("checked", true);
				$('#checkAll').val('전체해제');
			}else{
				$("input[name=m_num]").prop("checked", false);
				$('#checkAll').val('전체선택');
			}
		});
		
		// 학습자 체크할 때마다 체크
		$("input[name=m_num]").click(function() {
			var totalM_num = $("input[name=m_num]").length;
			var totalChecked = $("input[name=m_num]:checked").length;
			  
			if(totalM_num != totalChecked){
				$('#checkAll').val('전체선택');
			}else {
				$('#checkAll').val('전체해제');
			}
		});
		
		// 숙제번호 체크할 때마다 체크
		$("input[name=h_num]").click(function() {
			var pH_num = $("input[name=h_num]:checked").val();
			var pLg_num = $("#searchLgtitle").val();

			$.ajax({
				url : "/educator/homeworkSendExist",
				data : {h_num : pH_num, lg_num : pLg_num},
				dataType : 'json',
				success : function(data){	// data -> homeworkSendExist 결과값
					var hwSendMemberList = JSON.stringify(data);
					// alert("hwSendMemberList -> "+hwSendMemberList);
					
					if(hwSendMemberList == "[]"){
						$('#memberTable').empty();
						$('#memberTable').append("<tr><td>가입한 학습자가 없습니다.</td></tr>");
					}else {
						$('#memberTable').empty();
						var html = "";
						$(data).each(function() {
							html += "<tr>";
							if(this.existence == 1){
								html += "<td><span style='color:red;'>전송완료</span></td>";
							}else{
								html += "<td><input class='form-check-input' type='checkbox' name='m_num' value="+this.m_num;
								html += " id='flexRadioDefault1'></td>";
							}
							html += "<td>"+this.m_name+"</td>";
							html += "<td>"+this.m_phone+"</td>";
							html += "<td>"+this.hr_level+"</td></tr>";
						});
						$('#memberTable').append(html);
					}
				}
			});
		});
		  
	});
	
	function totalCheck() {
		// m_num 체크박스에서 체크된 개수
		var totalM_numChecked = $("input[name=m_num]:checked").length;
		// h_num 체크박스에서 체크된 개수
		var totalH_numChecked = $("input[name=h_num]:checked").length;
		
		// h_num과 m_num이 0보다 커야 true
		if(totalM_numChecked > 0 && totalH_numChecked > 0){
			return true;
		}else if(totalH_numChecked < 1){
			alert("숙제를 선택해주세요.");
			return false;
		}else if(totalM_numChecked < 1){
			alert("학습자를 선택해주세요.");
			return false;
		}
	}	
</script>
</head>
<body>
<!-- 	<div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;"> -->
<!--         <h6 class="section-title bg-white text-center text-primary px-3">Services</h6> -->
<!--         <h2 class="display-6 mb-4">교육 자료 등록</h2> -->
<!--     </div> -->
<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">숙제 전송</h2>
	    </div>
	    
		<form action="homeworkSendAction" name="frm" method="post" onsubmit="return totalCheck()">		
			<!-- 교육자 숙제 목록 -->
			<div class="input-group col-md-5 mb-3"> 
				<!-- 숙제명 검색 셀렉트 박스 -->
				<span style="margin: 10px 15px 10px 0px;">숙제명</span>&nbsp;&nbsp;
				<select id="searchHtitle" class="w-17 rounded" style="margin-right: 20%; border-color: #ced4da">
					<option id="h_title" value="">전체</option>
					<c:forEach var="allhomework" items="${allhomeworkList }" varStatus="status">
						<option id="h_title" value="${allhomework.h_title }" <c:if test ="${homework1.h_title eq allhomework.h_title}"> selected="selected"</c:if>>${allhomework.h_title }</option>
					</c:forEach>
				</select>      	
		    </div>
		
	        <div>
	        	<table class="listTable">
	        		<thead>
						<tr>
							<th>선택</th>
							<th>No.</th>
							<th style="width: 25%;">숙제명</th>
							<th style="width: 45%;">숙제내용</th>
							<th>진도</th>
							<th>제출기한</th>			
						</tr>
					</thead>
					 <tbody>
 					 <c:forEach var="homework" items="${homeworkList }">
					 	<tr>
					 		<td>
					 			<input class="form-check-input" type="radio" name="h_num" value="${homework.h_num }" id="flexRadioDefault1">
					 		</td>
							<td>${StartRow }</td>
							<td>${homework.h_title }</td>
							<td>${homework.h_content }</td>
							<td>${homework.h_level }</td>
							<td>${homework.h_deadline }</td>
						</tr>
						<c:set var="StartRow" value="${StartRow +1}"/>					
 					 </c:forEach>
						
	                 </tbody>   
                </table>
                <div class="row mt-8" style="width:100%;">
 					<div class="d-flex justify-content-center" style="margin-top:12px">
 						<c:choose>
 							<c:when test="${hwsend.lg_num > 0 }">
				                <nav aria-label="Page navigation example">
								  <ul class="pagination">
								  	<c:if test="${page.startPage > page.pageLimit}">
								  		<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${page.startPage-page.pageLimit}&h_title=${homework1.h_title}&lg_num=${hwsend.lg_num}">이전</a></li>
								  	</c:if>
								    <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
								    	<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${i }&h_title=${homework1.h_title}&lg_num=${hwsend.lg_num}">${i }</a></li>
								    </c:forEach>
								 	<c:if test="${page.endPage < page.totalPage}">
								 		<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${page.startPage+page.pageLimit}&h_title=${homework1.h_title}&lg_num=${hwsend.lg_num}">다음</a></li>
								 	</c:if>
								  </ul>
								</nav>
							</c:when>
							<c:otherwise>
								<nav aria-label="Page navigation example">
								  <ul class="pagination">
								  	<c:if test="${page.startPage > page.pageLimit}">
								  		<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${page.startPage-page.pageLimit}&h_title=${homework1.h_title}">이전</a></li>
								  	</c:if>
								    <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
								    	<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${i }&h_title=${homework1.h_title}">${i }</a></li>
								    </c:forEach>
								 	<c:if test="${page.endPage < page.totalPage}">
								 		<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${page.startPage+page.pageLimit}&h_title=${homework1.h_title}">다음</a></li>
								 	</c:if>
								  </ul>
								</nav>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>

			<!-- 교육자 학습그룹 학습자 목록 -->
			<div class="input-group col-md-5 mb-3 mt-5"> 
				<!-- 카테고리 분류 -->
				<span style="margin: 10px 15px 10px 0px;">학습그룹명</span>&nbsp;&nbsp;
				<select id="searchLgtitle" class="w-17 rounded" style="margin-right: 20%; border-color: #ced4da">
					<c:forEach var="learnGrp" items="${learnGrpList }" varStatus="status">
						<option id="lg_title" value="${learnGrp.lg_num }"<c:if test ="${hwsend.lg_num eq learnGrp.lg_num}"> selected="selected"</c:if>>${learnGrp.lg_title }</option>
					</c:forEach>
				</select>      	
	          	
				<div class="col">
				<div class="d-flex align-items-center justify-content-end">
	          		<div>
		          		<input id="checkAll" class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="전체선택">
		          		<button class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;">숙제전송</button>
	            	</div>
	            </div>
				</div>
		    </div>
			<div>
				<c:choose>
					<c:when test="${lgJoinMemberList.size() > 0 }">
						<table class="listTable">
			        		<thead>
								<tr>
									<th>선택</th>
									<th>학습자명</th>
									<th>전화번호</th>
									<th>현재레벨</th>	
								</tr>
							</thead>
							 <tbody id="memberTable">
		 					 <c:forEach var="lgJoinMember" items="${lgJoinMemberList }">
							 	<tr>
							 		<td><input class="form-check-input" type="checkbox" name="m_num" value="${lgJoinMember.m_num }" id="flexRadioDefault1" ></td>
									<td>${lgJoinMember.m_name }</td>
									<td>${lgJoinMember.m_phone }</td>
									<td>${lgJoinMember.hr_level }</td>
								</tr>
								<c:set var="StartRow" value="${StartRow +1}"/>					
		 					 </c:forEach>
								
			                 </tbody>   
		                </table>
					</c:when>
					<c:otherwise>
						등록된 학습 그룹이 없습니다.
					</c:otherwise>
				</c:choose>
            </div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
