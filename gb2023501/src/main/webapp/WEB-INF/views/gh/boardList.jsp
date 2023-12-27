<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--CSS START -->
<!--CSS END -->

<!-- JS START -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	var BoardCategory = ${BoardCategory};
	
    $(function() {
    	var count_type = ${rowPage}

    	
    	// 게시물 개수
    	$("#count_type").on('change', function(){
    		var count_type = document.getElementById("count_type").value;
    		var search_keyword = $('#search_keyword').val();
    		var search_type = $('#search_type').val();
    		
    		alert("count_type->"+count_type);
//   		window.location.href="/customer/boardList?b_category=" + BoardCategory + "&rowPage=" + count_type;	
    		window.location.href="/customer/boardList?search_type=" + search_type + "&search_keyword=" + search_keyword + "&b_category=" + BoardCategory + "&rowPage=" + count_type;

    	});
    	
    	// 검색
    	$("#search_start").on('click', function(){
    		var search_keyword = document.getElementById("search_keyword").value;
    		var search_type = document.getElementById("search_type").value;
    		
    		alert("검색시작");
    		window.location.href="/customer/boardList?search_type=" + search_type + "&search_keyword=" + search_keyword + "&b_category=" + BoardCategory + "&rowPage=" + count_type;
    		
    	});
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
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	    	<!-- heading -->
	        <c:choose>
			 	<c:when test="${BoardCategory == 1}"><h2 style="margin-bottom: 15px;">공지사항</h2></c:when>
			 	<c:when test="${BoardCategory == 2}"><h2 style="margin-bottom: 15px;">Q&A</h2></c:when>
	 			<c:otherwise><h2 style="margin-bottom: 15px;">FAQ</h2></c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${not empty search_keyword}"><p style="margin-bottom: 35px;">총 ${BoardCount}건</p></c:when>
				<c:when test="${search_keyword eq null or search_keyword eq ''}"><p style="margin-bottom: 35px;">총 ${BoardCount}건</p></c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
	    </div>
	    
		<div class="input-group col-md-5 mb-3"> 
			<!-- 카테고리 분류 -->
			<select id="count_type" name="count_type" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
				<c:if test="${not empty rowPage}">
					<option hidden="" value="${rowPage}">${rowPage} 개씩</option>
				</c:if>
				<option value="10">10 개씩</option>
				<option value="20">20 개씩</option>
				<option value="30">30 개씩</option>
			</select>
			
				
			<!-- 카테고리 검색 -->
			<select name="search_type" id="search_type" style="border-color: #ced4da">
				<option value="s_title_content" <c:if test="${search_type eq 's_title_content'}">selected</c:if> >제목 + 내용</option>
				<option value="s_writer" <c:if test="${search_type eq 's_writer'}">selected</c:if> >작성자</option>
			</select>&nbsp;&nbsp;
            <input id = "search_keyword" name="search_keyword" class="form-control rounded" 
            	   type="search" value="<c:if test="${not empty search_keyword}">${search_keyword}</c:if>" placeholder="search" style="width: 160px;">
          	<div style="margin-left: 10px; width: 65px; margin-top: 6px;">
         		<i class="bi bi-search mt-2" id="search_start"></i>
          	</div>
          	
			<div class="col">
			<div class="d-flex align-items-center justify-content-end">
          		<div style="width: 65px;">
          			<!-- Q&A이면서 회원이면 글 작성 -->
          			<c:if test="${(BoardCategory eq '2') and not empty member.category}">
          				<a href="/customer/boardForm?b_category=${BoardCategory}"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="작성"></a>
          			</c:if>
          			<!-- 공지사항, FAQ이면서 운영자면 글 작성 -->
          			<c:if test="${(BoardCategory eq '1' or BoardCategory eq '3') and member.category eq '4'}">
          				<a href="/customer/boardForm?b_category=${BoardCategory}"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="작성"></a>
          			</c:if>
            	</div>
            </div>
			</div>
	    </div>

       	<table class="listTable">
	       		<thead>
					<tr>
						<th>No.</th>
						<th>게시 분류</th>
						<th>제목</th>
						<th>작성자</th>
						<th>등록일자</th>
						<th>조회수</th>	
						<th width="100px;"></th>				
					</tr>
				</thead>
				<tbody>
					<c:forEach var="Blist" items="${Boardlist}">
						<tr>
					 		<%-- <td>${Blist.b_num}</td> --%>
					 		<td>${StartRow}</td>
					 		<td>
						 		<c:choose>
						 			<c:when test="${Blist.b_notie_type == 1}">공통</c:when>
						 			<c:when test="${Blist.b_notie_type == 2}">이벤트</c:when>
						 			<c:when test="${Blist.b_notie_type == 3}">업데이트</c:when>
						 			<c:otherwise>규정 및 정책</c:otherwise>
						 		</c:choose>
					 		</td>
							<td style="text-align: left;">
								<!-- 상단 표시 -->
								<div>
								<c:if test="${Blist.b_flag eq '1'}"><img src="/assets/img/notice_icon.png" style="margin-bottom:4px"></c:if>
								<!-- 댓글 표시 -->
								<c:if test="${Blist.b_title.contains('[답변]')}"><img src="/assets/img/reply.png" style="margin-bottom:4px"></c:if>
								${Blist.b_title} &nbsp;
								<!-- 댓글 표시 -->
								<c:if test="${Blist.b_category eq '1' and Blist.comment_count > 0}"><label style="color: orange;">[${Blist.comment_count}]</label></c:if>
								</div>
							</td>
							<td>${Blist.m_name}</td>
							<td><fmt:formatDate value="${Blist.b_regi_date}" type="date" pattern="yyyy-MM-dd"/></td>
							<td>${Blist.b_count}</td>
							<td width="100px;"><a href="/customer/boardDetail?b_num=${Blist.b_num}"><button type="button" class="btn btn-light rounded py-2 px-3" style="background: #263d94; color: white;">상세</button></a></td>
						</tr>
						<c:set var="StartRow" value="${StartRow +1}"/>
					</c:forEach>
	            </tbody>
              		
       	</table>
              
              <!-- 페이징 처리 -->
              <div class="row mt-8" style="width:100%;">
					<div class="d-flex justify-content-center" style="margin-top:12px">
	                <nav aria-label="Page navigation example">
					  <ul class="pagination">
					  	<c:if test="${page.startPage > page.pageBlock}">
					  		<li class="page-item"><a class="page-link" href="/customer/boardList?currentPage=${page.startPage-page.pageBlock}&b_category=${BoardCategory}&rowPage=${rowPage}">이전</a></li>
					  	</c:if>
					    <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
					    	<c:choose>
								<c:when test="${page.currentPage==i}"><li class="page-item active"></c:when>
								<c:otherwise><li class="page-item"></c:otherwise>
							</c:choose>
					    	<li class="page-item"><a class="page-link" href="/customer/boardList?currentPage=${i}&b_category=${BoardCategory}&rowPage=${rowPage}&search_type=${search_type}&search_keyword=${search_keyword}">${i}</a></li>
					    </c:forEach>
					 	<c:if test="${page.endPage < page.totalPage}">
					 		<li class="page-item"><a class="page-link" href="/customer/boardList?currentPage=${page.startPage+page.pageBlock}&b_category=${BoardCategory}&rowPage=${rowPage}">다음</a></li>
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
