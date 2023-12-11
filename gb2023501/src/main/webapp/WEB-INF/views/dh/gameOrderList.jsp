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
</head>
<body>
<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">게임콘텐츠 목록</h2>
	    </div>
		<div class="input-group col-md-5 mb-3"> 
			<!-- 카테고리 검색 -->
			<select id="search_type" class="w-17 rounded" style="border-color: #ced4da">
				<option value="title">게임명</option>
			</select>&nbsp;&nbsp;
            <input id = "search_keyword" class="form-control rounded" type="search" placeholder="검색" style="width: 160px;">
          	<div style="margin-left: 10px; width: 65px; margin-top: 6px;">
         		<a href="#!"><i class="bi bi-search mt-2"></i></a>
          	</div>	
			<div class="col">
			<div class="d-flex align-items-center justify-content-end">
          		<div style="width: 65px;">
	          		<a href="gameOrderForm"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="구독하기"></a>
            	</div>
            </div>
			</div>
	    </div>
	        <div class="table" style="text-align: center;">
	        	<table class="table">
	        		<thead class="table-light" style="text-align: center;">
						<tr>
							<th style="padding: 15px;">No.</th>
							<th style="padding: 15px;">콘텐츠이미지</th>
							<th style="padding: 15px;">게임명</th>
							<th style="padding: 15px;">가격/구독기간</th>
							<th style="padding: 15px;">상품소개</th>
							<th width="100px;"></th>				
						</tr>
					</thead>
					 <tbody>
					 <c:forEach var="subscribe/gameOrderList" items="${listGameOrder}">
					 	<tr id="subscribe/gameOrderList${st.index}">
					 	<td><input class="form-check-input" type="checkbox" name="em_type" id="flexRadioDefault1" ></td>
							<td><input type="hidden" value="${gameOrder.g_num}" id="id${st.index}">${num}</td>
							<td>${gameOrder.g_attach_name}</td>
							<td>${gameOrder.g_title}</td>
							<td>${gameOrder.g_sell_price}</td>
							<td>${gameOrder.g_content}</td>
						</tr>
 					 </c:forEach>
	                 </tbody>   
                </table>
			</div>
			<nav aria-label="Page navigation example ">
				<ul class="pagination">
					<c:if test="${page.startPage > page.pageLimit}">
						<li class="page-item">
							<a href="subscribe/gameOrderList?currentPage=${page.startPage-page.pageLimit}" class="pageblock page-link">이전</a>
						</li>
					</c:if>
					<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
						<li class="page-item">
							<a href="subscribe/gameOrderList?currentPage=${i}" class="pageblock page-link ${page.currentPage == i ? 'active':'' }">${i}</a>
						</li>
					</c:forEach>
					<c:if test="${page.endPage < page.totalPage}">
						<li class="page-item">
							<a href="subscribe/gameOrderList?currentPage=${page.startPage+page.pageLimit}" class="pageblock page-link">다음</a>
						</li>
					</c:if>
				</ul>
			</nav>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>