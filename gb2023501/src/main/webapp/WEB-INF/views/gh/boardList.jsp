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
	#table {
		font-size: 18px;
	}
	th {
		text-align: center;
		padding: 15px;
	}
</style>
<!--CSS END -->

<!-- JS START -->
<script type="text/javascript">
	function chooseCount() {
		alert("선택");
		var selectedValue = document.getElementById("count_type").value;
		
		switch (selectedValue) {
			case "count_10":
				alert("10");
				break;
			case "count_20":
				alert("20");
				break;
			case "count_30":
				alert("30");
				break;
			default:
				break;
		}
	}
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
		<div class="mb-9">
	    	<!-- heading -->
	        <c:choose>
			 	<c:when test="${BoardCategory == 1}"><h2 style="margin-bottom: 15px;">공지사항</h2></c:when>
			 	<c:when test="${BoardCategory == 2}"><h2 style="margin-bottom: 15px;">Q&A</h2></c:when>
	 			<c:otherwise><h2 style="margin-bottom: 15px;">FAQ</h2></c:otherwise>
			</c:choose>
		 		<p style="margin-bottom: 35px;">총 ${BoardCount}</p>	         
	    </div>

		<div class="input-group col-md-5 mb-3"> 
			<!-- 카테고리 분류 -->
			<select id="count_type" class="w-17 rounded" onchange="chooseCount()" style="margin-right: 110px; border-color: #ced4da">
				<option value="count_10">10</option>
				<option value="count_20">20</option>
				<option value="count_30">30</option>
			</select>
			<!-- 카테고리 검색 -->
			<select id="search_type" class="w-17 rounded" style="border-color: #ced4da">
				<option value="title">제목 + 내용</option>
				<option value="writer">작성자</option>
			</select>&nbsp;&nbsp;
            <input id = "search_keyword" class="form-control rounded" type="search" placeholder="검색해라" style="width: 160px;">
          	<div style="margin-left: 10px; width: 65px; margin-top: 6px;">
         		<a href="#!"><i class="bi bi-search mt-2"></i></a>
          	</div>
          	
			<div class="col">
			<div class="d-flex align-items-center justify-content-end">
          		<div style="width: 65px;">
	          		<a href="boardForm"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="작성"></a>
            	</div>
            </div>
			</div>
	    </div>
		<form action="#!">
	        <div class="table-responsive" style="text-align: center;">
	        	<table class="table">
	        		<thead class="table-light" style="text-align: center;">
						<tr>
							<th style="padding: 15px;">No.</th>
							<th style="padding: 15px;">게시 분류</th>
							<th style="padding: 15px;">제목</th>
							<th style="padding: 15px;">작성자</th>
							<th style="padding: 15px;">등록일자</th>
							<th style="padding: 15px;">조회수</th>	
							<th width="100px;"></th>				
						</tr>
					</thead>
					<tbody>
 					<c:forEach var="Blist" items="${Boardlist}">
					 	<tr>
					 		<td>${Blist.b_num}</td>
					 		<td>
						 		<c:choose>
						 			<c:when test="${Blist.b_notie_type == 1}">공통</c:when>
						 			<c:when test="${Blist.b_notie_type == 2}">이벤트</c:when>
						 			<c:when test="${Blist.b_notie_type == 3}">업데이트</c:when>
						 			<c:otherwise>규정 및 정책</c:otherwise>
						 		</c:choose>
					 		</td>
							<td>${Blist.b_title} [${comtCount}]</td>
							<td>${Blist.m_name}</td>
							<td><fmt:formatDate value="${Blist.b_regi_date}" type="date" pattern="yyyy-MM-dd"/></td>
							<td>${Blist.b_count}</td>
							<td width="100px;"><a href="boardDetail?b_num=${Blist.b_num}"><button type="button" class="btn btn-light rounded py-2 px-3" style="background: #263d94; color: white;">상세</button></a></td>
						</tr>
 					</c:forEach>
						
	                </tbody>   
                </table>
                
                <c:if test="${page.startPage > page.pageBlock }">
					<a href="boardList?currentPage=${page.startPage-page.pageBlock}&b_category=${BoardCategory}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
					<a href="boardList?currentPage=${i}&b_category=${BoardCategory}">[${i}]</a>
				</c:forEach>
				<c:if test="${page.endPage > page.totalPage }">
					<a href="boardList?currentPage=${page.startPage+page.pageBlock}&b_category=${BoardCategory}">[다음]</a>
				</c:if>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
