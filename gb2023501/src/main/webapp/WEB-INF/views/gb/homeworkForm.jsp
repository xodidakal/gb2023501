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
	.page-item{
		margin: 0px 5px 0px 5px;
	}
	.page-link{
		color: black;
	}
	#hwForm {
		border: 1px solid black;
		margin-bottom : 15px;
		padding: 4% 16%;
	}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$('#1p').click(function() {
		
	})
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
	         <h2 style="margin-bottom: 15px;">숙제 생성</h2>
	         <div id="hwForm">
	         	<table>
	         		<tr style="margin-bottom:10px;">
	         			<td>숙제명</td>
	         			<td><input type="text" class="form-control" name="h_title" style="margin-left: 20px; width: 300%;"></td>
	         		</tr>
	         		<tr>
	         			<td>숙제 내용</td>
	         			<td><textarea type="text" class="form-control" cols="20" rows="5" name="h_content" style="margin-left: 20px; width: 300%;"></textarea></td>
	         		</tr>
	         		<tr>
	         			<td>숙제 진도</td>
	         			<td><input type="number" class="form-control" name="h_level" style="margin-left: 20px; width: 300%;"></td>
	         		</tr>
	         		<tr>
	         			<td>제출 기한</td>
	         			<td><input type="date" class="form-control" name="h_deadline" style="margin-left: 20px; width: 300%;"></td>
	         		</tr>
	         	</table>
	         </div>
	    </div>

		<div class="input-group col-md-5 mb-3"> 
			<div class="col">
				<div class="d-flex align-items-center justify-content-end" style="margin-right:37px;">
	          		<div style="width: 65px;">
		          		<a href="boardForm"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="숙제 저장"></a>
	            	</div>
	            </div>
	            <p style="margin-bottom:-5px;">총 <fmt:formatNumber value="${homeworkListCnt}" groupingUsed="true"/> 건</p>
			</div>
	    </div>
		<form action="#!">
	        <div class="table-responsive" style="text-align: center;">
	        	<table class="table">
	        		<thead class="table-light" style="text-align: center;">
						<tr>
							<th style="padding: 15px;">No.</th>
							<th style="padding: 15px; width: 25%;">숙제명</th>
							<th style="padding: 15px; width: 45%;">숙제내용</th>
							<th style="padding: 15px;">진도</th>
							<th style="padding: 15px;">제출기한</th>
							<th style="padding: 15px;">생성일자</th>		
						</tr>
					</thead>
					<tbody>
						<c:forEach var="homework" items="${homeworkList }">
						 	<tr id="homeworkList" onmouseover="this.style.background='#BFE4FF'; this.style.cursor='pointer'" 
						 						  onmouseout="this.style.backgroundColor=''">
						 		<td class="align-middle">${StartRow }</td>
								<td class="align-middle">${homework.h_title }</td>
								<td class="align-middle">${homework.h_content }</td>
								<td class="align-middle">${homework.h_level }</td>
								<td class="align-middle">${homework.h_deadline }</td>
								<td class="align-middle">${homework.h_regi_date }</td>
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
						  	<c:if test="${page.startPage > page.pageLimit}">
						  		<li class="page-item"><a class="page-link" href="#">이전</a></li>
						  	</c:if>
						    <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
						    	<li class="page-item"><a class="page-link" href="#">${i }</a></li>
						    </c:forEach>
						 	<c:if test="${page.endPage < page.totalPage}">
						 		<li class="page-item"><a class="page-link" href="#">다음</a></li>
						 	</c:if>
						  </ul>
						</nav>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
