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
	tr{
		border-bottom: 1px solid #f8f8f8;
	}
</style>
<script type="text/javascript">
	function detailForm(em_num) {
		alert(em_num);
		location.href = "eduMaterialsDetail?em_num="+em_num;
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
	         <h2 style="margin-bottom: 15px;">학습 자료 목록</h2>
	         <p style="margin-bottom: 35px;">총 10 건</p>
	    </div>
		<a href="salesInquiryDetail">가즈아</a>
		<div class="input-group col-md-5 mb-3"> 
			<!-- 카테고리 분류 -->
			<select id="search_type" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
				<option value="title">튜토리얼</option>
				<option value="writer">교육</option>
			</select>&nbsp;&nbsp;
			<!-- 카테고리 검색 -->
			<select id="search_type" class="w-17 rounded" style="border-color: #ced4da">
				<option value="title">튜토리얼</option>
				<option value="writer">교육</option>
			</select>&nbsp;&nbsp;
            <input id = "search_keyword" class="form-control rounded" type="search" placeholder="검색해라" style="width: 160px;">
          	<div style="margin-left: 10px; width: 65px; margin-top: 6px;">
         		<a href="#!"><i class="bi bi-search mt-2"></i></a>
          	</div>	
			<div class="col">
			<div class="d-flex align-items-center justify-content-end">
          		<div style="width: 65px;">
	          		<a href="eduResourceForm"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="작성"></a>
            	</div>
            </div>
			</div>
	    </div>
		<form action="" method="get">
	        <div class="table" style="text-align: center;">
	        	<table class="table">
	        		<thead class="table-light" style="text-align: center;">
						<tr>
							<th style="padding: 15px;">No.</th>
							<th style="padding: 15px;">썸네일</th>
							<th style="padding: 15px;">학습자료명</th>
							<th style="padding: 15px;">자료구분</th>
							<th style="padding: 15px;">자료유형</th>
<!-- 							<th style="padding: 15px; width: 250px;">자료주소</th> -->
							<th style="padding: 15px;">서비스구분</th>		
							<th width="100px;"></th>				
						</tr>
					</thead>
					 <tbody>
					 
					 <c:forEach var="eduMaterialsList" items="${selectEduMaterialsList }" varStatus="status" begin="1">
					 	<tr>
					 		<td>${status.index }</td>
							<td style="width: 100px;" height="80px;">
								<img src="${eduMaterialsList.em_attach_name }" alt="도서 썸네일" class="mb-3 img-fluid" style="width: 5rem; height: 80px;">
							</td>
							<td>
								<input type="hidden" value="${eduMaterialsList.em_num }" id="em_num" name="em_num">
								${eduMaterialsList.em_title }</td>
							<td>
								<c:if test="${eduMaterialsList.em_category  == 1}">튜토리얼</c:if>
								<c:if test="${eduMaterialsList.em_category  == 2}">교육영상</c:if>							
							</td>
							<td>
								<c:if test="${eduMaterialsList.em_type  == 1}">동영상</c:if>
								<c:if test="${eduMaterialsList.em_type  == 2}">교재</c:if>		
								<c:if test="${eduMaterialsList.em_type  == 3}">웹사이트</c:if>
							</td>

							<td>
								<c:if test="${eduMaterialsList.em_payment == 1}">무료</c:if>
								<c:if test="${eduMaterialsList.em_payment == 2}">유료</c:if>
								
							</td>
							<td width="100px;"><button type="button" class="btn btn-light rounded py-2 px-3" onclick="detailForm(${eduMaterialsList.em_num })">상세</button></td>
						</tr>
 					 </c:forEach>
						
	                 </tbody>   
                </table>
              		
              		<nav class="owl-nav">

				  <ul class="pagination justify-content-center">
					 	<c:if test="${page.startPage > page.pageBlock }">
							 <li class="page-item justify-content-center">					
								<a class="owl-dot text-body" href="eduMaterialsList?currentPage=${page.startPage-page.pageBlock}">이전</a>
							</li>
						</c:if>
		 				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
							 <li class="page-item justify-content-center">
		 						<a class="owl-dot mx-1 text-body" href="eduMaterialsList?currentPage=${i}">${i}</a>
							</li>
						</c:forEach>
							
						<c:if test="${page.endPage < page.totalPage }">
							 <li class="page-item justify-content-center">		 
								<a class="owl-dot mx-1 text-body" href="eduMaterialsList?currentPage=${page.startPage+page.pageBlock}">다음</a>
							</li>
						</c:if>
				  	</ul>
				</nav>
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
