<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	         <h2 style="margin-bottom: 15px;">타이틀</h2>
	         <p style="margin-bottom: 35px;">총 9,999 건</p>
	    </div>

		<div class="input-group col-md-5 mb-3"> 
			<!-- 카테고리 분류 -->
			<select id="search_type" class="w-17 rounded" style="margin-right: 20%; border-color: #ced4da">
				<option value="title">10</option>
				<option value="writer">15</option>
				<option value="writer">20</option>
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
          		<div>
	          		<a href="boardForm"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="등록"></a>
            	</div>
            </div>
			</div>
	    </div>
       	<table class="listTable">
       		<thead>
				<tr>
					<th>No.</th>
					<th>분류</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일자</th>
					<th>조회수</th>	
					<th width="100px;"></th>				
				</tr>
			</thead>
			 <tbody>
<%-- 					 <c:forEach var="" items=""> --%>
			 	<tr>
			 		<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td width="100px;"><a href="#"><button type="button" class="btn btn-light rounded py-2 px-3" style="background: #263d94; color: white;">상세</button></a></td>
				</tr>
				
				<tr>
			 		<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td width="100px;"><a href="#"><button type="button" class="btn btn-light rounded py-2 px-3" style="background: #263d94; color: white;">상세</button></a></td>
				</tr>
				
<%-- 					 </c:forEach> --%>
				
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
