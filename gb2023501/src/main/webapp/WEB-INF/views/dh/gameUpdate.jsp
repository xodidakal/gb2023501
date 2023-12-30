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
		<form action="gameUpdateResult" method="post" enctype="multipart/form-data">
	        <div class="row g-3">
	        <h2 class="display-7 mb-4">게임 콘텐츠 상세</h2>
	        <hr class="my-3">
	        		<input type="hidden" name="g_num" id="g_num" value="${game.g_num}">
					<input type="hidden" name="m_num" id="m_num" value="${game.m_num}">
	        	<table class="formTable">
		            <tr>
						<th>게임명</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="g_title" value="${game.g_title}" required>
		            	</td>
					</tr>
					 <tr>
						<th>학습난이도</th>
						<td colspan="3">
							<select id="g_step" name="g_step" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da; width: 100px">
								<option value="1" <c:if test="${game.g_step == 1}">selected</c:if> >초급</option>
								<option value="2" <c:if test="${game.g_step == 2}">selected</c:if> >중급</option>
								<option value="3" <c:if test="${game.g_step == 3}">selected</c:if> >고급</option>
							</select>
		            	</td>
					</tr>
					 <tr>
						<th>구독기간</th>
						<td>
		                    <input type="number" class="form-control" name="g_period" id="g_period"style="width: 100px" value="${game.g_period}" required >
		            	</td>
		            	<td width="10px;">개월</td>
					</tr>
					 <tr>
						<th>구독가능인원</th>
						<td colspan="3">
		                    <input type="number" class="form-control" name="g_to" value="${game.g_to}" required >
		            	</td>
					</tr>
					 <tr>
						<th>정가</th>
						<td colspan="3">
		                    <input type="number" class="form-control" name="g_price" value="${game.g_price}" required>
		            	</td>
					</tr>
					 <tr>
						<th>할인율</th>
						<td colspan="3">
		                    <input type="number" class="form-control" name="discount" value="${game.discount}" required>
		            	</td>
					</tr>
					 <tr>
						<th>판매가</th>
						<td colspan="3">
		                    <input type="number" class="form-control" name="g_sell_price" value="${game.g_sell_price}" required>
		            	</td>
					</tr>
					<tr>
						<th>상품소개</th>
						<td colspan="3">
		                   	<textarea class="form-control" name="g_content" style="height: 200px" required>${game.g_content}</textarea>
						</td>
					</tr>
	               <tr>
	                	<th>썸네일</th>
						<td colspan="3">
		                    <input type="file" class="form-control" name="file">
		                	<p>기존&nbsp;파일&nbsp;:&nbsp;<span>${game.g_attach_name}</span></p>
		                </td>
	                </tr>
	                <tr>
						<th>전시여부</th>
						<td width="150px;">
		                    <input class="form-check-input" type="radio" name="g_dele_status"  value="0" <c:if test="${game.g_dele_status == 0}">checked</c:if>>
		                    <label>전시</label>
		                </td>
		                <td width="150px;">
		                    <input class="form-check-input" type="radio" name="g_dele_status"  value="1" <c:if test="${game.g_dele_status == 1}">checked</c:if>>
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