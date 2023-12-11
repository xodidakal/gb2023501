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
</style>
</head>
<body>
<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">내 학습 그룹</h2>
	         <p style="margin-bottom: 35px;">총 N건</p>
	    </div>

		<div class="input-group col-md-5 mb-3"> 
			<!-- 카테고리 분류 -->
			<select id="search_type" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
				<option value="title">학습그룹명순</option>
				<option value="writer">가입확정인원순</option>
				<option value="writer">학습만료임박순</option>
			</select>
			<!-- 카테고리 검색 -->
			<select id="search_type" class="w-17 rounded" style="border-color: #ced4da">
				<option value="learnGrpTitle">학습그룹명</option>
				<option value="gameTitle">게임콘텐츠명</option>
				<option value="add1">추가항목1</option>
				<option value="add2">추가항목2</option>
			</select>&nbsp;&nbsp;
            <input id = "search_keyword" class="form-control rounded" type="search" placeholder="내용을 입력해주세요." style="width: 160px;">
          	<div style="margin-left: 10px; width: 65px; margin-top: 6px;">
         		<a href="#!"><i class="bi bi-search mt-2"></i></a>
          	</div>
          	
          	
			<div class="col">
			<div class="d-flex align-items-center justify-content-end">
          		<div style="width: 65px;">
	          		<a href="boardForm"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="삭제"></a>
            	</div>
            </div>
			</div>
	    </div>
		<form action="#!">
	        <div class="table-responsive" style="text-align: center;">
	        	<table class="table">
	        		<thead class="table-light" style="text-align: center;">
						<tr>
							<th style="padding: 15px;">선택</th>
							<th style="padding: 15px;">No.</th>
							<th style="padding: 15px;">학습그룹명</th>
							<th style="padding: 15px;">게임콘텐츠명</th>
							<th style="padding: 15px;">학습 기간</th>
							<th style="padding: 15px;">수용 가능 인원</th>
							<th style="padding: 15px;">가입 승인 인원</th>	
							<th width="100px;"></th>
						</tr>
					</thead>
					 <tbody>
					 	<c:forEach var="lg" items="${learnGrps }">
						 	<tr>
						 		<td><input class="form-check-input" type="checkbox" name="em_type" id="flexRadioDefault1" ></td>
								<td>No.</td>
								<td>${lg.lgTitle}</td>
								<td>${lg.game.ggTitle}</td>
								<td>${lg.lgSdate } ~ ${lg.lgEdate }</td>
								<td>${lg.lgTo }</td>
								<td>${lg.lgTo }</td>
								<td width="100px;"><a href="#"><button type="button" class="btn btn-light rounded py-2 px-3" style="background: #263d94; color: white;">상세</button></a></td>
							</tr>
						</c:forEach>
	                 </tbody>   
                </table>
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
