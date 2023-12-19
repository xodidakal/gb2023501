<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.subTable {
	width: 100%;
	text-align: center;
	margin-bottom: 50px;
}
.subTable th {
	background-color: #EEEEEE;
	padding: 10px;
	color: black;
	width: 25%;
}
.subTable td {
	width: 25%;
}
.subTable tr {
	border-bottom: 1px solid #dfdfdf;
	border-top: 1px solid #dfdfdf;
}
</style>
</head>
<body>
<!-- 	<div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;"> -->
<!--         <h6 class="section-title bg-white text-center text-primary px-3">Services</h6> -->
<!--         <h2 class="display-6 mb-4">교육 자료 등록</h2> -->
<!--     </div> -->
<div class="row g-0 justify-content-center">
	<div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">학습 그룹 가입 승인</h2>
	    </div>
	    
	    <table class="subTable">
			<tr>
				<th>학습그룹명</th>
				<td>${learnGrpDTO.learnGrp.lgTitle}</td>
				<th>게임콘텐츠명</th>
				<td>${learnGrpDTO.learnGrp.game.ggTitle}</td>
			</tr>
			<tr>
				<th>수용 가능 인원</th>
				<td>${learnGrpDTO.learnGrp.lgTo }명</td>
				<th>가입 승인 인원</th>
				<td>${learnGrpDTO.mmCnt }명</td>
			</tr>
	    </table>
	    
		<h5 style="margin-top: 35px;">가입 신청 명단</h5>
       	<table class="listTable">
       		<thead>
				<tr>
					<th>No.</th>
					<th>이름</th>
					<th>휴대전화</th>
					<th>가입신청일자</th>
					<th>선택</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
			 		<c:when test="${empty members}">
			 			<tr>
			 				<td colspan="5">가입 신청자가 없습니다.</td>
			 			</tr>
			 		</c:when>
			 		
			 		<c:otherwise>
			 			<c:forEach var="members" items="${members }">
						 	<tr>
						 		<td>No.</td>
								<td>${members.member.mmName }</td>
								<td>${members.member.phone }</td>
								<td><fmt:formatDate value="${members.lgjJoindate }" pattern="yyyy-MM-dd"/></td>
								<td><input class="form-check-input" type="checkbox" name="em_type" id="flexRadioDefault1" ></td>
							</tr>
			 			</c:forEach>
			 		</c:otherwise>
			 	</c:choose>
			</tbody>   
       	</table>
       	
		<div class="d-grid gap-2 d-md-flex justify-content-center" >
			<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="가입 승인">
		</div>
		
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
