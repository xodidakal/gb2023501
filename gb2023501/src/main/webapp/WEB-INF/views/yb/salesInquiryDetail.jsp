<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">매출 조회</h2>
	         <p style="margin-bottom: 35px;"><span>총 10 건</span><span>총 매출액 : </span></p>
	    </div>
	    <div class="mb-1">
	    <form action="/operate/searchSalesInquiry" method="GET" role="search"> 
         	<div style="display: flex; ">
	         	<select id="selectDate" name="selectDate" class="w-17 rounded" style="border-color: #ced4da">
					<option value="date">일단위</option>
					<option value="month">월단위</option>
				</select>&nbsp;&nbsp;
<!-- 				<div style="margin-right: 20px;"><input type="radio" name="selectCondition" id="date">일 단위</div>  -->
<!--          		<div style="margin-right: 55px;"><input type="radio" name="selectCondition" id="month">월 단위</div> -->
				<input class="form-control" type="date" id="startDate" name="startDate" required="required" style="width: 130px;"><div class="mt-2">~</div>					
				<input class="form-control" type="date" id="endDate" name="endDate" required="required" style="width: 130px;">
				
				<button type="submit" class="btn btn-light rounded py-2 px-2">검색</button>
			</div>
		</form>
		<a href="#!"><button type="button" class="btn btn-light rounded py-2 px-2">그래프 보기</button></a>
	    </div>
       	<table class="listTable" style="text-align: center;">
       		<thead>
				<tr>
					<th>No.</th>
					<th>일 / 월</th>
					<th>건수</th>
					<th>매출금액</th>	
					<th width="100px;"></th>				
				</tr>
			</thead>
			<tbody>
				 <c:forEach var="selectSaleList" items="${selectSaleList }">
				 	<tr>
				 		<td>1.</td>
						<td>
							<fmt:formatDate value="${selectSaleList.goOrderDate }" pattern="yyyy년MM월dd일"/>
						</td>
						<td>${selectSaleList.salesCnt } 개</td>
						<td>
							<fmt:formatNumber value="${selectSaleList.salesSum }" pattern="#,###" /> 원
							
						</td>
						<td width="100px;"><a href="#!"><button type="button" class="btn btn-light rounded py-2 px-3" type="button" style="background: #263d94; color: white;">상세</button></a></td>
					</tr>
				 </c:forEach>
				
             </tbody>   
		</table>
<!-- 		<div class="row mt-8" style="width:100%;"> -->
<!-- 			<div class="d-flex justify-content-center" style="margin-top:12px"> -->
<!-- 	               <nav aria-label="Page navigation example"> -->
<!-- 				  <ul class="pagination"> -->
<%-- 				  	<c:if test="${page.startPage > page.pageLimit}"> --%>
<%-- 				  		<li class="page-item"><a class="page-link" href="eduMaterialsList?currentPage=${page.startPage-page.pageLimit}">이전</a></li> --%>
<%-- 				  	</c:if> --%>
<%-- 				    <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }"> --%>
<%-- 				    	<li class="page-item"><a class="page-link" href="eduMaterialsList?currentPage=${i }">${i }</a></li> --%>
<%-- 				    </c:forEach> --%>
<%-- 				 	<c:if test="${page.endPage < page.totalPage}"> --%>
<%-- 				 		<li class="page-item"><a class="page-link" href="eduMaterialsList?currentPage=${page.startPage+page.pageLimit}">다음</a></li> --%>
<%-- 				 	</c:if> --%>
<!-- 				  </ul> -->
<!-- 				</nav> -->
<!-- 			</div> -->
<!-- 		</div> -->
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
