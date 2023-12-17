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
		<form action="gameUpdateResult" method="post">
	        <div class="row g-3">
	        <h2 class="display-7 mb-4">게임 콘텐츠 상세</h2>
	        <hr class="my-3">
	        	<table class="formTable">
		            <tr>
						<th>게임명</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name=",g_Title" value="${game.g_title}">
		            	</td>
					</tr>
					 <tr>
						<th>학습난이도</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="g_step" value="${game.g_step}" >
		            	</td>
					</tr>
					 <tr>
						<th>구독기간</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="g_period" value="${game.g_period}" >개월
		            	</td>
					</tr>
					 <tr>
						<th>구독가능인원</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="g_to" value="${game.g_to}" >
		            	</td>
					</tr>
					 <tr>
						<th>정가</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="g_price" value="${game.g_price}" >
		            	</td>
					</tr>
					 <tr>
						<th>할인율</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="discount" value="${game.discount}" >
		            	</td>
					</tr>
					 <tr>
						<th>판매가</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="g_sell_price" value="${game.g_sell_price}">
		            	</td>
					</tr>
					<tr>
						<th>상품소개</th>
						<td colspan="3">
		                   	<textarea class="form-control" name="g_content" style="height: 200px">${game.g_content}</textarea>
						</td>
					</tr>
	                <tr>
	                	<th>썸네일</th>
						<td colspan="3">
		                    <input type="file" class="form-control" name="g_attach_name" id="g_attach_name" placeholder="Subject">
		                </td>
	                </tr>
	                <tr>
						<th>전시여부</th>
						<td width="150px;">
		                    <input class="form-check-input" type="radio" name="g_dele_status"  value="${game.g_dele_status}">
		                    <label>전시</label>
		                </td>
		                <td width="150px;">
		                    <input class="form-check-input" type="radio" name="g_dele_status"  value="${game.g_dele_status}">
		                    <label>미전시</label>
						</td>
						<td></td>
					</tr>    
                </table>
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="수정하기">
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>