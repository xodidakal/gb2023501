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
	// 옵션 선택에 따라 게시 숫자 변경
	/* function chooseCount() {
		var selectedValue = document.getElementById("count_type").value;
		
		switch (selectedValue) {
			case "10":
				alert("10");
				window.location.href="/customer/boardList?b_category=" + BoardCategory + "&rowPage=10";
				break;
			case "20":
				alert("20");
				window.location.href="/customer/boardList?b_category=" + BoardCategory + "&rowPage=20";
				break;
			case "30":
				alert("30");
				window.location.href="/customer/boardList?b_category=" + BoardCategory + "&rowPage=30";
				break;
			default:
				break;
		}
	} */
	/* $(function() {
		var count_type = document.getElementById("count_type").value;
		
		$("#count_type").on('change',function() {
			alert("숫자선택");
			alert("count_type->"+count_type);
			
			window.location.href="/customer/boardList?b_category=" + BoardCategory + "&rowPage=" + count_type;
			
		});
	
	}); */
	
	
	// 옵션 선택 후 페이지 이동하면 옵션값 선택유지
	/* window.onload = function () {
        var countType = '${rowPage}';
        document.getElementById("count_type").value = countType;
    } */
    
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
    
	/* function search_start() {
		var search_keyword = document.getElementById("search_keyword").value;
		var search_type = document.getElementById("search_type").value;
		
		alert("검색시작");
		window.location.href="/customer/boardList?search_type=" + search_type + "&search_keyword=" + search_keyword + "&b_category=" + BoardCategory;
		
	} */
	
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
		 		<p style="margin-bottom: 35px;">총 ${BoardCount}</p>	         
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
				<%-- <option value="s_title_content" <c:if test="${search_type eq s_title_content}">selected</c:if> >제목 + 내용</option>
				<option value="s_writer" <c:if test="${search_type eq s_writer}">selected</c:if> >작성자</option> --%>
				<option value="s_title_content">제목 + 내용</option>
				<option value="s_writer" >작성자</option>
			</select>&nbsp;&nbsp;
            <input id = "search_keyword" name="search_keyword" class="form-control rounded" 
            	   type="search" value="<c:if test="${not empty search_keyword}">${search_keyword}</c:if>" placeholder="search" style="width: 160px;">
          	<div style="margin-left: 10px; width: 65px; margin-top: 6px;">
         		<i class="bi bi-search mt-2" id="search_start"></i>
          	</div>
          	
			<div class="col">
			<div class="d-flex align-items-center justify-content-end">
          		<div style="width: 65px;">
	          		<a href="/customer/boardForm?b_category=${BoardCategory}"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="작성"></a>
            	</div>
            </div>
			</div>
	    </div>

       	<table class="listTable" style="text-align: center;">
       	<!-- style="text-align: center;"없으면 안맞음 -->
			<tr>
				<th>No.</th>
				<th>게시 분류</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일자</th>
				<th>조회수</th>	
				<th width="100px;"></th>				
			</tr>
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
							<td>
								<c:if test="${Blist.b_flag eq '0'}"><img src="/assets/img/notice_icon.png" style="margin-bottom:4px"></c:if>
								${Blist.b_title} &nbsp;
								<c:if test="${Blist.b_category eq '1'}"><label style="color: orange;">[${Blist.comment_count}]</label></c:if>
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
					    	<li class="page-item"><a class="page-link" href="/customer/boardList?currentPage=${i}&b_category=${BoardCategory}&rowPage=${rowPage}">${i}</a></li>
					    </c:forEach>
					 	<c:if test="${page.endPage < page.totalPage}">
					 		<li class="page-item"><a class="page-link" href="/customer/boardList?currentPage=${page.startPage+page.pageBlock}&b_category=${BoardCategory}&rowPage=${rowPage}">다음</a></li>
					 	</c:if>
					  </ul>
					</nav>
				</div>
			</div>
              
              	<%-- <c:if test="${page.startPage > page.pageBlock }">
					<a href="/customer/boardList?currentPage=${page.startPage-page.pageBlock}&b_category=${BoardCategory}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
					<a href="/customer/boardList?currentPage=${i}&b_category=${BoardCategory}">[${i}]</a>
				</c:forEach>
				<c:if test="${page.endPage > page.totalPage }">
					<a href="/customer/boardList?currentPage=${page.startPage+page.pageBlock}&b_category=${BoardCategory}">[다음]</a>
				</c:if> --%>
				
			</div>
	</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
