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
<script type="text/javascript">
	//combo box 변경
	function changeLg() {
		var lg_num = $('select[name="lgTitle"]').val();
		location.href = "/educator/learnGroupDetail?lg_num="+lg_num;
	}
</script>
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
	         <h2 style="margin-bottom: 15px;">학습 그룹 상세</h2>
	    </div>
	    
	    <table class="subTable">
			<tr>
				<th>학습그룹명</th>
				<td>
					<select id="lgTitle" name="lgTitle" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da; margin:0 auto;"
							onchange="changeLg()">
						<c:forEach var="learnGrpsAll" items="${learnGrpsAll }">
							<option value="${learnGrpsAll.learnGrp.lgNum }" <c:if test="${learnGrpsAll.learnGrp.lgNum eq learnGrpDTO.learnGrp.lgNum }"> selected </c:if>>
								${learnGrpsAll.learnGrp.lgTitle }
							</option>
						</c:forEach>
					</select>
				</td>
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
	    
		<div style="">
			<h5 style="float: left;">학습자 명단</h5>
			<div style="margin-bottom: 15px; float: right;">
<!-- 			<div class="d-flex align-items-center justify-content-end"> -->
          		<div>
	          		<a href="/educator/learnGroupJoinList?lg_num=${lg_num }"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="가입 신청 확인"></a>
            	</div>
            </div>
		</div>
       	<table class="listTable">
       		<thead>
				<tr>
					<th>No.</th>
					<th>이름</th>
					<th>휴대전화</th>
					<th>이메일</th>
					<th>주소</th>
					<th>가입승인일자</th>	
				</tr>
			</thead>
			 <tbody>
			 	<c:choose>
			 		<c:when test="${empty members}">
			 			<tr>
			 				<td colspan="6">아직 학습자가 없습니다.</td>
			 			</tr>
			 		</c:when>
			 		
			 		<c:otherwise>
						<c:forEach var="members" items="${members }">
							<c:set var="i" value="${i+1 }"></c:set>
						 	<tr>
						 		<td>${i }</td>
								<td>${members.member.mmName }</td>
								<td>${members.member.phone }</td>
								<td>${members.member.email }</td>
								<td>${members.member.address }</td>
								<td><fmt:formatDate value="${members.lgjAppdate }" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
			 		</c:otherwise>
			 	</c:choose>
			 </tbody>   
              </table>
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
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
