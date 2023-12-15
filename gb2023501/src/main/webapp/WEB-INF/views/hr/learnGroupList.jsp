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
	         <h2 style="margin-bottom: 15px;">내 학습 그룹</h2>
	         <p style="margin-bottom: 35px;">총 N건</p>
	    </div>

		<form action="/educator/learnGroupList">
			<div class="input-group col-md-5 mb-3"> 
				<!-- 카테고리 분류 -->
				<select id="sort_type" name="sort" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
					<option value="sortLgTitle">학습그룹명순</option>
					<option value="sortGgTitle">게임콘텐츠명순</option>
				</select>
				<!-- 카테고리 검색 -->
				
				<select id="search_type" name="type" class="w-17 rounded" style="border-color: #ced4da">
					<option value="typeLgTitle">학습그룹명</option>
					<option value="typeGgTitle">게임콘텐츠명</option>
				</select>&nbsp;&nbsp;
	            <input id = "search_keyword" name="keyword" class="form-control rounded" type="search" placeholder="내용을 입력해주세요." style="width: 160px;">
	          	<div style="margin-left: 10px; width: 65px; margin-top: 6px;">
	         		<button class="btn bi bi-search rounded"></button>
	          	</div>

				<div class="col">
				<div class="d-flex align-items-center justify-content-end">
	          		<div style="width: 65px;">
		          		<a href="boardForm"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="삭제"></a>
	            	</div>
	            </div>
				</div>
		    </div>
	    </form>
       	<table class="listTable">
       		<thead>
				<tr>
					<th>선택</th>
					<th>No.</th>
					<th>학습그룹명</th>
					<th>게임콘텐츠명</th>
					<th>학습 기간</th>
					<th>수용 가능 인원</th>
					<th>가입 승인 인원</th>	
					<th width="100px;"></th>
				</tr>
			</thead>
			 <tbody>
			 	<c:forEach var="lgDto" items="${learnGrps }">
				 	<tr>
				 		<td><input class="form-check-input" type="checkbox" name="em_type" id="flexRadioDefault1" ></td>
						<td>No.</td>
						<td>${lgDto.learnGrp.lgTitle}</td>
						<td>${lgDto.learnGrp.game.ggTitle}</td>
						<td>${lgDto.learnGrp.lgSdate } ~ ${lgDto.learnGrp.lgEdate }</td>
						<td>${lgDto.learnGrp.lgTo }명</td>
						<td>${lgDto.mmCnt }명</td>
						<td width="100px;"><a href="/educator/learnGroupDetail?lg_num=${lgDto.learnGrp.lgNum }"><button type="button" class="btn btn-light rounded py-2 px-3" style="background: #263d94; color: white;">상세</button></a></td>
					</tr>
				</c:forEach>
                </tbody>   
              </table>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
