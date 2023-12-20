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
	// combo box 변경
	function changeLg() {
		var lg_num = $('select[name="lgTitle"]').val();
		location.href = "/educator/learnGroupJoinList?lg_num="+lg_num;
	}
	
	// 가입 승인
	function clickApproval() {
		// 체크 수 = 0일 때
		if($('input[name="checkbox"]:checked').length == 0){
			alert("학습자를 선택해주세요.");
			
		// 체크 수 = 0 아닐 때
		} else {
			
			var remainingTo = Number('${learnGrpDTO.learnGrp.lgTo }') - Number('${learnGrpDTO.mmCnt }');
			
			// 잔여 인원(수용 가능 인원 - 가입 승인 인원) = 0일 때
			if(remainingTo == 0) {
				alert("정원 초과로 더이상 승인 불가능합니다.");
			
			// 체크 수 > 잔여 인원일 때
			} else if($('input[name="checkbox"]:checked').length > remainingTo) {
				alert("최대 "+remainingTo+"명까지만 승인 가능합니다.");
			
			// 체크 수 < 잔여 인원일 때 -> 이때만 동작
			} else {
				if(confirm("승인하시겠습니까?")){
					$('input[name="checkbox"]:checked').each(function(){
						// index 정의
						var index = $(this).val();
						
						$.ajax(
								{
									url : "/educator/learnGroupJoinApproval",
									data : {lg_num : ${learnGrpDTO.learnGrp.lgNum },
											m_num : $('#mmNum'+index).val()},
									dataType : 'text',
									success : function(data){
										if(data == "1"){
											alert("승인 완료되었습니다.");
											location.reload();
										} else {
											alert("승인 실패하였습니다. 다시 시도해주세요.");
											location.reload();
										}
									}
								}
						)
					})
				}				
			}
		}
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
	         <h2 style="margin-bottom: 15px;">학습 그룹 가입 승인</h2>
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
			 			<c:forEach var="members" items="${members }" varStatus="status">
							<c:set var="i" value="${i+1 }"></c:set>
						 	<tr>
						 		<td>${i }</td>
								<td>${members.member.mmName }</td>
								<td>${members.member.phone }</td>
								<td><fmt:formatDate value="${members.lgjJoindate }" pattern="yyyy-MM-dd"/></td>
								<td>
									<input class="form-check-input" type="checkbox" name="checkbox" id="checkbox${status.index }" value="${status.index }" >
									<input type="hidden" id="mmNum${status.index }" value="${members.member.mmNum }" >
								</td>
							</tr>
			 			</c:forEach>
			 		</c:otherwise>
			 	</c:choose>
			</tbody>   
       	</table>
       	
		<div class="d-grid gap-2 d-md-flex justify-content-center" >
			<input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;"
				   value="가입 승인" onclick="clickApproval()">
		</div>
		
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
