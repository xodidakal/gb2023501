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
	// 검색 함수
	function searchMyHw(){
		var search_type = $('#search_type').val();
		var search_keyword = $('#search_keyword').val();
		var searchSubmit = $('#search_submit').val();
		
		location.href="/learning/myhomeworkList?searchType="+search_type+"&searchKeyword="+search_keyword+"&searchSubmit="+searchSubmit;
		
	}

	function searchSubmit(){
		var search_type = $('#search_type').val();
		var search_keyword = $('#search_keyword').val();
		var searchSubmit = $('#search_submit').val();
		
		location.href="/learning/myhomeworkList?searchType="+search_type+"&searchKeyword="+search_keyword+"&searchSubmit="+searchSubmit;
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
	         <h2 style="margin-bottom: 15px;">내 숙제</h2>
	         <p style="margin-bottom: 35px;">총 <fmt:formatNumber value="${myHomeworkCnt}" groupingUsed="true"/> 건</p>
	    </div>

		<div class="input-group col-md-5 mb-3"> 
			<!-- 카테고리 분류 -->
			<select id="search_submit" class="w-17 rounded" style="margin-right: 15%; border-color: #ced4da" onchange="searchSubmit();">
				<option value="0">전체</option>
				<option value="1" <c:if test="${hwsend.searchSubmit == 1 }">selected</c:if>>제출완료</option>
				<option value="2" <c:if test="${hwsend.searchSubmit == 2 }">selected</c:if>>미제출</option>
			</select>
			<!-- 카테고리 검색 -->
			<select id="search_type" class="w-17 rounded" style="border-color: #ced4da">
				<option value="title" <c:if test="${hwsend.searchType eq 'title' }">selected</c:if>>숙제명</option>
				<option value="teacher" <c:if test="${hwsend.searchType eq 'teacher' }">selected</c:if>>교육자</option>
			</select>&nbsp;&nbsp;
            <input id = "search_keyword" class="form-control rounded" type="search" placeholder="내용을 입력해주세요" 
            	   style="width: 160px;" onkeypress="if( event.keyCode == 13 ){searchMyHw();}" value="${hwsend.searchKeyword }">
          	<div style="margin-left: 10px; width: 65px; margin-top: 6px;">
         		<a onclick="searchMyHw()"><i class="bi bi-search mt-2"></i></a>
          	</div>
	    </div>
	    
       	<table class="listTable">
       		<thead>
				<tr>
					<th>No.</th>
					<th style="width:40%">숙제명</th>
					<th>교육자</th>
					<th>진도</th>
					<th>제출기한</th>	
					<th width="100px;">상세</th>				
				</tr>
			</thead>
			<tbody>
				 <c:set var="startRow" value="${startRow }"></c:set>
				 <c:forEach var="myHomework" items="${myHomeworkList }">
				 	<tr>
				 		<td>${startRow}</td>
						<td>${myHomework.homework.hhTitle }</td>
						<td>${myHomework.homework.member.mmName }</td>
						<td>${myHomework.homework.hhLevel }</td>
						<td>${myHomework.homework.hhDeadline }</td>
						<td width="100px;"><a href="/learning/myHomeworkDetail?h_num=${myHomework.homework.hhNum }"><button type="button" class="btn btn-light rounded py-2 px-3" style="background: #263d94; color: white;">상세</button></a></td>
					</tr>
					<c:set var="startRow" value="${startRow + 1 }"></c:set>				
				 </c:forEach>
             </tbody>   
          </table>
              <div class="row mt-8" style="width:100%;">
					<div class="d-flex justify-content-center" style="margin-top:12px">
                <nav aria-label="Page navigation example">
				  <ul class="pagination">
				    <li class="page-item"><a class="page-link" href="#">이전</a></li>
				    <li class="page-item" id="1p"><a class="page-link" href="#">1</a></li>
				    <li class="page-item"><a class="page-link" href="#">2</a></li>
				    <li class="page-item"><a class="page-link" href="#">3</a></li>
				    <li class="page-item"><a class="page-link" href="#">4</a></li>
				    <li class="page-item"><a class="page-link" href="#">5</a></li>
				    <li class="page-item"><a class="page-link" href="#">6</a></li>
				    <li class="page-item"><a class="page-link" href="#">7</a></li>
				    <li class="page-item"><a class="page-link" href="#">8</a></li>
				    <li class="page-item"><a class="page-link" href="#">9</a></li>
				    <li class="page-item"><a class="page-link" href="#">10</a></li>
				    <li class="page-item"><a class="page-link" href="#">다음</a></li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
