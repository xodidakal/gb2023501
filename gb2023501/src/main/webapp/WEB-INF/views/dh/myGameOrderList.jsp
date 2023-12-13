<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	function myGameOrderList() {
		var searchType = $('#searchType').val();
		var keyword = $('#keyword').val();
		
		alert("searchType keyword ->"+searchType+" "+keyword);
			
		location.href = "myGameOrderList?keyword="+keyword;
	}
	
</script>
<body>
<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">내 구독상품</h2>
	         <p style="margin-bottom: 35px;">총 <fmt:formatNumber value="${totalSearchGameOrder}" groupingUsed="true"/> 건</p>
	    </div>
	    <div class="input-group col-md-5 mb-3">
	    	<input class="form-control" type="date" id="startDate" name="startDate" required="required" style="width: 130px; margin: 10px;"><div class="mt-3">~</div>					
			<input class="form-control" type="date" id="endDate" name="endDate" required="required" style="width: 130px; margin: 10px;">
		</div>
		<div class="input-group col-md-5 mb-3">
			<!-- 카테고리 검색 -->
			<select id="searchType" name="searchType" class="w-17 rounded" style="border-color: #ced4da">
				<option value="title">게임명</option>
			</select>&nbsp;&nbsp;
            <input id="keyword" name="keyword" class="form-control rounded" placeholder="검색어를 입력하세요." type="text" style="width: 160px;">
          	<div style="margin-left: 10px; width: 65px; margin-top: 6px;">
         		<a onclick="myGameOrderList()"><i class="bi bi-search mt-2"></i></a>
          	
          	</div>	
	    </div>
	    <!-- 내 구독목록 -->
	        	<table class="listTable">
	        		<thead>
						<tr>
							<th>No.</th>
							<th>썸네일</th>
							<th width= "200">게임명</th>
							<th width= "250" >가격/구독기간</th>
							<th>구매일자</th>
							<th>학습그룹 등록내역</th>
							<th width="100px;"></th>				
						</tr>
					</thead>
					 <tbody>
					 <c:set var="num" value="${page.startRow}"/>
					 <c:forEach var="game" items="${listGameOrder}" varStatus="status">
					 	<input type="hidden" name="g_num" value="${game.g_num}" id="g_num${status.index}">
					 	<tr id="gameOrder${status.index}">
							<td valign="middle">${num}</td>
							<td style="width:100px;" height="80px;" valign="middle">
								<div align=center>
								<img src="${game.g_attach_name}"class="mb-3 img-fluid" style="width: 5rem; height: 80px;">
								</div>
							</td>
							<td valign="middle">${game.g_title}</td>
							<td valign="middle"><fmt:formatNumber value="${game.g_sell_price}" groupingUsed="true"/>원 / ${game.g_period}개월</td>
							<td valign="middle"><fmt:formatDate value="${game.go_order_date}" type="date" pattern="YY/MM/dd"/></td>
							<td valign="middle">${game.lg_title}</td>
						</tr>
						<c:set var="num" value="${num+1}"/>
 					 </c:forEach>
	                 </tbody>   
                </table>
                 <!-- 페이징 처리 -->
               <div class="row mt-8" style="width:100%;">
 					<div class="d-flex justify-content-center" style="margin-top:12px">
		                <nav aria-label="Page navigation example">
						  <ul class="pagination">
						  	<c:if test="${page.startPage > page.pageLimit}">
						  		<li class="page-item"><a class="page-link" href="myGameOrderList?currentPage=${page.startPage-page.pageLimit}">이전</a></li>
						  	</c:if>
						    <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
						    	<li class="page-item"><a class="page-link" href="myGameOrderList?currentPage=${i }">${i }</a></li>
						    </c:forEach>
						 	<c:if test="${page.endPage < page.totalPage}">
						 		<li class="page-item"><a class="page-link" href="myGameOrderList?currentPage=${page.startPage+page.pageLimit}">다음</a></li>
						 	</c:if>
						  </ul>
						</nav>
					</div>
				</div>
			</div>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>