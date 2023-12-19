<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function ramainingChk() {
		//alert("lgForm.lgTo.value -> "+lgForm.lgTo.value);
		//alert("'${game.remainingTo}' -> "+'${game.remainingTo}');
		//alert("lgForm.lgPeriod.value -> "+lgForm.lgPeriod.value);
		//alert("'${game.remainingPeriod}' -> "+'${game.remainingPeriod}');
		
		if(Number(lgForm.lgTo.value) > Number('${game.remainingTo}')) {
			alert("수용 가능 인원은 학습 잔여 인원보다 클 수 없습니다.");
			lgForm.lgTo.focus();
			lgForm.lgTo.value = "";
			return false;
		}
		
		if(Number(lgForm.lgPeriod.value) > Number('${game.remainingPeriod}')) {
			alert("학습 기간 개월수는 구독 잔여 기간보다 클 수 없습니다.");
			lgForm.lgPeriod.focus();
			lgForm.lgPeriod.value = "";
			return false;
		}
		
		return true;
	}
</script>
</head>
<body>
<div class="row g-0 justify-content-center">
	<div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">학습 그룹 등록</h2>
	         <h5 style="margin-top: 35px;">선택 콘텐츠</h5>
	    </div>

       	<table class="listTable">
       		<thead>
				<tr>
					<th>게임콘텐츠명</th>
					<th>구독 기간</th>
					<th>구독 잔여 기간</th>
					<th>학습 가능 인원</th>
					<th>학습 잔여 인원</th>	
				</tr>
			</thead>
			<tbody>
			 	<tr>
					<td>${game.g_title}</td>
					<td>${game.g_period}개월</td>
					<td>${game.remainingPeriod}개월</td>
					<td>${game.g_to}명</td>
					<td>${game.remainingTo}명</td>
				</tr>
			</tbody>   
              </table>
              
	         <h5 style="margin-top: 70px;">그룹 상세 정보 입력</h5>
	         <form action="learnGroupFormInsert" method="post" name="lgForm" onsubmit="return ramainingChk()">
                <table class="formTable">
					<!-- <tr>
						<th>교육자명</th>
						<td colspan="3">(교육자명)</td>
	                </tr> -->
					<tr>
						<th>그룹명</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="lgTitle" required>
		            	</td>
	                </tr>
					<tr>
						<th>수용 가능 인원</th>
						<td colspan="3">
		                    <input type="number" class="form-control" name="lgTo" required>
		            	</td>
	                </tr>
					<tr>
						<th>학습기간</th>
						<td width="80px;">시작일자 : </td>
						<td>
		                    <input type="date" class="form-control" name="lgSdate" required style="width: 300px; display: inline-block;">
		                </td>
	                </tr>
					<tr>
						<td></td>
						<td width="80px;">개월수 : </td>
						<td>
							<select class="w-17 rounded" name="lgPeriod" required="required"
									style="border-color: #ced4da;
										   width: 300px;
										   display: inline-block;
										   padding: 0.375rem 0.75rem;
										   color: #777;">
								<option value="">선택</option>
								<option value="1">1개월</option>
								<option value="2">2개월</option>
								<option value="3">3개월</option>
								<option value="4">4개월</option>
								<option value="5">5개월</option>
								<option value="6">6개월</option>
								<option value="7">7개월</option>
								<option value="8">8개월</option>
								<option value="9">9개월</option>
								<option value="10">10개월</option>
								<option value="11">11개월</option>
								<option value="12">12개월</option>
							</select>
		                </td>
	                </tr>
					<tr>
						<th>추가항목1</th>
						<td colspan="3" style="height: 120px">
		                    <textarea class="form-control" placeholder="" name="lgAdd1" style="height: 100px"></textarea>    
		            	</td>
	                </tr>
					<tr>
						<th>추가항목2</th>
						<td colspan="3" style="height: 120px">
		                    <textarea class="form-control" placeholder="" name="lgAdd2" style="height: 100px"></textarea>    
		            	</td>
	                </tr>
                </table>
              
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="등록">
                </div>
                
                <input type="hidden" name="g_num" value="${g_num }">
                <input type="hidden" name="m_num" value="${m_num }">
	         </form>
              
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
