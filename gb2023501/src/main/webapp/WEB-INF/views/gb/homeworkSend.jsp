<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#table {
		font-size: 18px;
	}
	th {
		text-align: center;
		padding: 15px;
	}
	.page-item{
		margin: 0px 5px 0px 5px;
	}
	.page-link{
		color: black;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(function(){
		  $("#searchHtitle").change(function(){
		    // Value값 가져오기
		    var val = $("#searchHtitle :selected").val();

			alert("val -> "+val);
		  });
	});
	
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
		<form action="www.daum.net" name="frm">
		
			<!-- 교육자 숙제 목록 -->
			<div class="input-group col-md-5 mb-3"> 
				<!-- 숙제명 검색 셀렉트 박스 -->
				<span style="margin: 10px 15px 10px 0px;">숙제명</span>&nbsp;&nbsp;
				<select id="searchHtitle" class="w-17 rounded" style="margin-right: 20%; border-color: #ced4da">
					<option id="h_title" value="전체">전체</option>
					<c:forEach var="homework" items="${homeworkList }" varStatus="status">
						<option id="h_title" value="${homework.h_title }">${homework.h_title }</option>
					</c:forEach>
				</select>      	
		    </div>
		
	        <div class="table-responsive" style="text-align: center;">
	        	<table class="table">
	        		<thead class="table-light" style="text-align: center;">
						<tr>
							<th style="padding: 15px;">선택</th>
							<th style="padding: 15px;">No.</th>
							<th style="padding: 15px; width: 25%;">숙제명</th>
							<th style="padding: 15px; width: 45%;">숙제내용</th>
							<th style="padding: 15px;">진도</th>
							<th style="padding: 15px;">제출기한</th>			
						</tr>
					</thead>
					 <tbody>
 					 <c:forEach var="homework" items="${homeworkList }">
					 	<tr>
					 		<td class="align-middle">
					 			<input class="form-check-input" type="checkbox" name="h_num" value="${homework.h_num }" id="flexRadioDefault1" >
					 		</td>
							<td class="align-middle">${StartRow }</td>
							<td class="align-middle">${homework.h_title }</td>
							<td class="align-middle">${homework.h_content }</td>
							<td class="align-middle">${homework.h_level }</td>
							<td class="align-middle">${homework.h_deadline }</td>
						</tr>
						<c:set var="StartRow" value="${StartRow +1}"/>					
 					 </c:forEach>
						
	                 </tbody>   
                </table>
                <div class="row mt-8" style="width:100%;">
 					<div class="d-flex justify-content-center" style="margin-top:12px">
		                <nav aria-label="Page navigation example">
						  <ul class="pagination">
						  	<c:if test="${page.startPage > page.pageLimit}">
						  		<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${page.startPage-page.pageLimit}">이전</a></li>
						  	</c:if>
						    <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
						    	<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${i }">${i }</a></li>
						    </c:forEach>
						 	<c:if test="${page.endPage < page.totalPage}">
						 		<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${page.startPage+page.pageLimit}">다음</a></li>
						 	</c:if>
						  </ul>
						</nav>
					</div>
				</div>
			</div>
			<hr>
			<!-- 교육자 학습그룹 학습자 목록 -->
			<div class="input-group col-md-5 mb-3 mt-5"> 
				<!-- 카테고리 분류 -->
				<span style="margin: 10px 15px 10px 0px;">학습그룹명</span>&nbsp;&nbsp;
				<select id="searchHtitle" class="w-17 rounded" style="margin-right: 20%; border-color: #ced4da">
					<option id="h_title" value="전체">전체</option>
					<c:forEach var="homework" items="${homeworkList }" varStatus="status">
						<option id="h_title" value="${homework.h_title }">${homework.h_title }</option>
					</c:forEach>
				</select>      	
	          	
				<div class="col">
				<div class="d-flex align-items-center justify-content-end">
	          		<div>
		          		<a onclick="allcheck()"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="전체선택"></a>
		          		<button class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;">숙제전송</button>
	            	</div>
	            </div>
				</div>
		    </div>
			<div class="table-responsive" style="text-align: center;">
	        	<table class="table">
	        		<thead class="table-light" style="text-align: center;">
						<tr>
							<th style="padding: 15px;">선택</th>
							<th style="padding: 15px;">학습자명</th>
							<th style="padding: 15px;">전화번호</th>
							<th style="padding: 15px;">현재레벨</th>	
						</tr>
					</thead>
					 <tbody>
 					 <c:forEach var="homework" items="${homeworkList }">
					 	<tr>
					 		<td class="align-middle"><input class="form-check-input" type="checkbox" name="em_type" id="flexRadioDefault1" ></td>
							<td class="align-middle">${homework.h_title }</td>
							<td class="align-middle">${homework.h_content }</td>
							<td class="align-middle">${homework.h_level }</td>
						</tr>
						<c:set var="StartRow" value="${StartRow +1}"/>					
 					 </c:forEach>
						
	                 </tbody>   
                </table>
            </div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
