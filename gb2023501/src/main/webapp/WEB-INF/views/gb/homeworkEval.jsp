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

	function homeworkClick(pIndex){
		var hhNum = $('#hhNum'+pIndex).val();
		alert("내가 선택한 숙제의 번호는 -> "+hhNum);
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
	         <h2 style="margin-bottom: 15px;">숙제 평가</h2>
	    </div>
	    
		<form action="#" name="frm" method="post">		
			<!-- 교육자 숙제 목록 -->
			<div class="input-group col-md-5 mb-3"> 
				<!-- 숙제명 검색 셀렉트 박스 -->
				<span style="margin: 10px 15px 10px 0px;">숙제명</span>&nbsp;&nbsp;
				<select id="searchHtitle" class="w-17 rounded" style="margin-right: 20%; border-color: #ced4da">
					<option id="h_title" value="">전체</option>
				</select>      	
		    </div>
		
	        <div>
	        	<!-- 교육자가 생성한 숙제 목록 -->
	        	<table class="listTable">
	        		<thead>
						<tr>
							<th>No.</th>
							<th style="width: 20%;">숙제명</th>
							<th style="width: 40%;">숙제내용</th>
							<th>진도</th>
							<th>제출기한</th>			
						</tr>
					</thead>
					 <tbody>
					 <c:set var="i" value="1"/>
					 <c:forEach var="homework" items="${homeworkList }" varStatus="status">
					 	<tr onmouseover="this.style.background='#BFE4FF'; this.style.cursor='pointer'" 
					 		onmouseout="this.style.backgroundColor=''"
					 		onclick="homeworkClick(${status.index})">
							<td>
								<input type="hidden" id="hhNum${status.index }" value="${homework.hhNum }">
								${i }
							</td>
							<td>${homework.hhTitle }</td>
							<td>${homework.hhContent }</td>
							<td>${homework.hhLevel }</td>
							<td>${homework.hhDeadline }</td>
						</tr>
						<c:set var="i" value="${i+1 }"/>	
					 </c:forEach>
					 				
	                 </tbody>   
                </table>
                <!-- 숙제목록 페이징 -->
                <%-- <div class="row mt-8" style="width:100%;">
 					<div class="d-flex justify-content-center" style="margin-top:12px">
 						<c:choose>
 							<c:when test="${hwsend.lg_num > 0 }">
				                <nav aria-label="Page navigation example">
								  <ul class="pagination">
								  	<c:if test="${page.startPage > page.pageLimit}">
								  		<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${page.startPage-page.pageLimit}&h_title=${homework1.h_title}&lg_num=${hwsend.lg_num}">이전</a></li>
								  	</c:if>
								    <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
								    	<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${i }&h_title=${homework1.h_title}&lg_num=${hwsend.lg_num}">${i }</a></li>
								    </c:forEach>
								 	<c:if test="${page.endPage < page.totalPage}">
								 		<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${page.startPage+page.pageLimit}&h_title=${homework1.h_title}&lg_num=${hwsend.lg_num}">다음</a></li>
								 	</c:if>
								  </ul>
								</nav>
							</c:when>
							<c:otherwise>
								<nav aria-label="Page navigation example">
								  <ul class="pagination">
								  	<c:if test="${page.startPage > page.pageLimit}">
								  		<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${page.startPage-page.pageLimit}&h_title=${homework1.h_title}">이전</a></li>
								  	</c:if>
								    <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
								    	<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${i }&h_title=${homework1.h_title}">${i }</a></li>
								    </c:forEach>
								 	<c:if test="${page.endPage < page.totalPage}">
								 		<li class="page-item"><a class="page-link" href="homeworkSend?currentPage=${page.startPage+page.pageLimit}&h_title=${homework1.h_title}">다음</a></li>
								 	</c:if>
								  </ul>
								</nav>
							</c:otherwise>
						</c:choose>
					</div>
				</div> --%>
			</div>

			<!-- 학습자가 제출한 숙제 제출 목록  -->
			<div class="input-group col-md-5 mb-3 mt-5"> 
				<!-- 평가 저장하기 버튼 -->
				<div class="col">
					<div class="d-flex align-items-center justify-content-end">
		          		<div>
			          		<button class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;">평가저장</button>
		            	</div>
		            </div>
				</div>
		    </div>
			<div>
				<table class="listTable">
	        		<thead>
						<tr>
							<th>No.</th>
							<th>학습자명</th>
							<th>숙제제출일자</th>
							<th style="width:30%;">학습제출내용</th>
							<th>학습진도</th>
							<th style="width:30%;">추가질의내용</th>
							<th>평가</th>	
						</tr>
					</thead>
					 <tbody id="memberTable">
					 	<tr>
					 		<td>1</td>
							<td>홍길동</td>
							<td>2023-12-20</td>
							<td>바둑숙제제출합니다. 이거 버튼으로 구현하는게 더 좋을듯</td>
							<td>3</td>
							<td>추가질의도 버튼으로 구현하는게 좋을듯</td>
							<td>
								<select id="Eval" class="w-17 rounded" style="margin-right: 20%; border-color: #ced4da">
									<option id="h_title" value="1">우수</option>
									<option id="h_title" value="2">보통</option>
									<option id="h_title" value="3">미흡</option>
								</select>
							</td>
						</tr>								
	                 </tbody>   
                </table>
            </div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
