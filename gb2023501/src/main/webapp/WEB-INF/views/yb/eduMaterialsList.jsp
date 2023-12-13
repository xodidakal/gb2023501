<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
</style>
<script type="text/javascript">
	function detailForm(em_num) {
		alert(em_num);
		location.href = "/operate/eduMaterialsDetail?em_num="+em_num;
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
	         <p style="margin-bottom: 35px;">총 ${selectEduMaterialsList.size() } 건</p>
	    </div>
	    <form action="/operate/searchEduMaterials" method="GET" role="search"> 
		<div class="input-group col-md-5 mb-3"> 
			<!-- 카테고리 분류 -->
<!-- 		<select id="search_type" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da"> -->
<!-- 			<option value="title">튜토리얼</option> -->
<!-- 			<option value="writer">교육</option> -->
<!-- 		</select>&nbsp;&nbsp; -->
			<!-- 카테고리 검색 -->
			<select id="type" name="type" class="w-17 rounded" style="border-color: #ced4da">
				<option value="em_title">자료명</option>
				<option value="writer">교육</option>
			</select>&nbsp;&nbsp;
	            <input id="search" name="keyword" class="form-control rounded" type="search" placeholder="검색해라" style="width: 160px;">
	         		<button class="btn bi bi-search rounded"></button>
			<div class="col">
			<div class="d-flex align-items-center justify-content-end">
          		<div style="width: 65px;">
	          		<a href="/operate/eduResourceForm"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="작성"></a>
            	</div>
            </div>
			</div>
	    </div>
	    </form>
        	<table class="listTable" style="text-align: center;">
        		<thead>
					<tr>
						<th>No.</th>
						<th>썸네일</th>
						<th>학습자료명</th>
						<th>자료구분</th>
						<th>자료유형</th>
<!-- 							<th style="padding: 15px; width: 250px;">자료주소</th> -->
						<th>서비스구분</th>		
						<th width="100px;"></th>				
					</tr>
				</thead>
				
				<tbody>
				 <c:forEach var="eduMaterialsList" items="${selectEduMaterialsList }">
				 	<tr>
				 		<td>${StartRow }</td>
						<td style="width: 100px;" height="80px;">
							<img src="${eduMaterialsList.emAttachName }" alt="도서 썸네일" class="img-fluid" style="width: 5rem; height: 80px;">
						</td>
						<td>
							<input type="hidden" value="${eduMaterialsList.emNum }" id="em_num" name="em_num">
							${eduMaterialsList.emTitle }</td>
						<td>
							<c:if test="${eduMaterialsList.emCategory  == 1}">튜토리얼</c:if>
							<c:if test="${eduMaterialsList.emCategory  == 2}">교육영상</c:if>							
						</td>
						<td>
							<c:if test="${eduMaterialsList.emType  == 1}">동영상</c:if>
							<c:if test="${eduMaterialsList.emType  == 2}">교재</c:if>		
							<c:if test="${eduMaterialsList.emType  == 3}">웹사이트</c:if>
						</td>

						<td>
							<c:if test="${eduMaterialsList.emPayment == 1}">무료</c:if>
							<c:if test="${eduMaterialsList.emPayment == 2}">유료</c:if>
							
						</td>
						<td width="100px;"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" onclick="detailForm(${eduMaterialsList.emNum })">상세</button></td>
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
						  		<li class="page-item"><a class="page-link" href="eduMaterialsList?currentPage=${page.startPage-page.pageLimit}">이전</a></li>
						  	</c:if>
						    <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
						    	<li class="page-item"><a class="page-link" href="eduMaterialsList?currentPage=${i }">${i }</a></li>
						    </c:forEach>
						 	<c:if test="${page.endPage < page.totalPage}">
						 		<li class="page-item"><a class="page-link" href="eduMaterialsList?currentPage=${page.startPage+page.pageLimit}">다음</a></li>
						 	</c:if>
						  </ul>
						</nav>
					</div>
				</div>

	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
