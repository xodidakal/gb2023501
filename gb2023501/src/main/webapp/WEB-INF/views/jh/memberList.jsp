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
	         <h2 style="margin-bottom: 15px;">회원목록</h2>
	         <p style="margin-bottom: 35px;">총  건</p>
	    </div>


<div class="row justify-content-center">
	<div class="col-md-9">
		<table class="formTable">
			<tr>
				<th>기간</th>
				<td style="width: 150px;">
					<input class="form-control" type="date" id="startDate" name="startDate" >				
				</td>
				<td style="width: 150px;">
					<div class="text-center">~</div>
				</td>
				<td style="width: 150px;">
					<input class="form-control" type="date" id="endDate" name="endDate" >
				</td>
			</tr>
			<tr>
				<th>조건 검색</th>
				<td>
					<select id="search_type" class="form-select" style="border-color: #ced4da">
						<option value="title">아이디</option>
						<option value="writer">이름</option>
						<option value="writer">휴대폰</option>
					</select>
				</td>
				<td></td>
				<td>
					<input type="email" class="form-control" id="SearchCriteria" name="SearchCriteria">
				</td>
			</tr>
			<tr>
				<th>회원구분</th>
				<td>
					<select id="categorySelect" class="form-select" name="m_category">
							    <option value="1">교육자</option>
							    <option value="2">학습자</option>
							    <option value="3">일반인</option>
							    <option value="4">운영자</option>
					</select>
				</td>
				<th class="text-center">자격</th>
				<td>
					<select id="search_type" class="form-select" name="m_mship_type">
						<option value="null"></option>
						<option value="1">무료</option>
						<option value="2">유료</option>
					</select>
				</td>
			</tr>	
		</table>
</div>
    <div class="col-md-1 mt-auto mb-4">
                <div  class="col-md-2" style="margin-left: 10px; width: 65px; margin-top: 6px;">
                    <a href="#!"><i class="bi bi-search mt-2"></i></a>
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
</body>
<%@ include file="../common/footerFo.jsp" %>
</html>