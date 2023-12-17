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
		            <tr>
						<th>게임명</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name=",g_Title" id="g_Title">
		            	</td>
					</tr>
					 <tr>
						<th>학습난이도</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="emTitle" id="emTitle" >
		            	</td>
					</tr>
					 <tr>
						<th>구독기간</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="g-period" id="g-period" >개월
		            	</td>
					</tr>
					 <tr>
						<th>구독가능인원</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="g_to" id="g_to" >
		            	</td>
					</tr>
					 <tr>
						<th>정가</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="g_price" id="g_price" >
		            	</td>
					</tr>
					 <tr>
						<th>할인율</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="discount" id="discount" >
		            	</td>
					</tr>
					 <tr>
						<th>판매가</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="g_sell_price" id="g_sell_price">
		            	</td>
					</tr>
					<tr>
						<th>상품소개</th>
						<td colspan="3">
		                   	<textarea class="form-control" placeholder="Leave a message here" name="g_content" id="g_content" style="height: 200px"></textarea>
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
		                    <input class="form-check-input" type="radio" name="g_dele_status" id="g_dele_status" value="0">
		                    <label>전시</label>
		                </td>
		                <td width="150px;">
		                    <input class="form-check-input" type="radio" name="g_dele_status" id="g_dele_status" value="1">
		                    <label>미전시</label>
						</td>
						<td></td>
					</tr>    
                </table>
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="등록하기">
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
