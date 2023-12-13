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
<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">학습 그룹 등록</h2>
	         <h5 style="margin-top: 35px;margin-bottom: 35px;">콘텐츠 선택</h5>
	    </div>

		<div class="input-group col-md-5 mb-3"> 
			<!-- 카테고리 분류 -->
			<select id="search_type" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
				<option value="title">게임콘텐츠명순</option>
				<option value="writer">참여인원순</option>
				<option value="writer">구독만료임박순</option>
			</select>
			<!-- 카테고리 검색 -->
			<select id="search_type" class="w-17 rounded" style="border-color: #ced4da">
				<option value="gameTitle">게임콘텐츠명</option>
				<option value="add1">패키지 내용</option>
			</select>&nbsp;&nbsp;
            <input id = "search_keyword" class="form-control rounded" type="search" placeholder="내용을 입력해주세요." style="width: 160px;">
          	<div style="margin-left: 10px; width: 65px; margin-top: 6px;">
         		<a href="#!"><i class="bi bi-search mt-2"></i></a>
          	</div>
	    </div>
       	<table class="listTable">
       		<thead>
				<tr>
					<th>선택</th>
					<th>No.</th>
					<th>게임콘텐츠명</th>
					<th>구독 기간</th>
					<th>학습 가능 인원</th>
					<th>학습 확정 인원</th>	
				</tr>
			</thead>
			 <tbody>
			 	<%-- <c:forEach var="lg" items="${learnGrps }"> --%>
				 	<tr>
				 		<td><input class="form-check-input" type="radio" name="em_type" id="flexRadioDefault1" ></td>
						<td>No.</td>
						<td>게임콘텐츠명</td>
						<td>구독 기간</td>
						<td>학습 가능 인원</td>
						<td>학습 확정 인원</td>
					</tr>
				 	<tr>
				 		<td><input class="form-check-input" type="radio" name="em_type" id="flexRadioDefault1" ></td>
						<td>No.</td>
						<td>게임콘텐츠명</td>
						<td>구독 기간</td>
						<td>학습 가능 인원</td>
						<td>학습 확정 인원</td>
					</tr>
				<%-- </c:forEach> --%>
                </tbody>   
              </table>
              
              
              
              <!-- 페이지네이션 -->
              <!-- <div class="row mt-8" style="width:100%;">
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
              </div> -->
              
              <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="컨텐츠 선택">
              </div>
              
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
