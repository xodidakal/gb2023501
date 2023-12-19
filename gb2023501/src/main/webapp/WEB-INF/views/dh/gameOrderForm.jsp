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
		<form action="gameOrderInsertResult" method="post">
	        <div class="row g-3">
	        <h2 class="display-7 mb-4">게임 콘텐츠 구독신청</h2>
	        <hr class="my-3">
	        	<table class="formTable">
		            <tr bgcolor="#EAEAEA">
						<th>주문하실 상품</th>
						<td colspan="3"></td>
					</tr>
					<tr>	
						<th>구매 상품명</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name=",g_Title" id="g_Title">
		            	</td>
					</tr>
					<tr bgcolor="#EAEAEA">
						<th>구매자 정보</th>
						<td colspan="3"></td>
					</tr>
					 <tr>
						<th>구매자명</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="emTitle" id="emTitle" >
		            	</td>
					</tr>
					<tr>
						<th>연락처</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="emTitle" id="emTitle" >
		            	</td>
					</tr>
					<tr bgcolor="#EAEAEA">
						<th>주문합계</th>
						<td colspan="3"></td>
					</tr>
					<tr bgcolor="#EAEAEA">
						<th>결제방법 선택</th>
						<td colspan="3"></td>
					</tr>
	                <tr>
						<td width="150px;">
		                    <input class="form-check-input" type="radio" name="g_dele_status" id="g_dele_status" value="1">
		                    <label>무통장입금</label>
		                </td>
		                <td width="150px;">
		                    <input class="form-check-input" type="radio" name="g_dele_status" id="g_dele_status" value="2">
		                    <label>계좌이체</label>
						</td>
						<td width="150px;">
		                    <input class="form-check-input" type="radio" name="g_dele_status" id="g_dele_status" value="3">
		                    <label>카카오페이</label>
						</td>
					</tr>
					<tr>	
						<th>입금자명</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name=",g_Title" id="g_Title">
		            	</td>
					</tr>    
                </table>
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="결제하기">
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
