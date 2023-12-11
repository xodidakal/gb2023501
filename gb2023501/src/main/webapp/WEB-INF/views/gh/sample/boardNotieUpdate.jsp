<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	tr {
		height: 70px;
	}
	#table {
		font-size: 18px;
	}
	th {
		text-align: left;
		width: 150px;
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
		<form>
	        <div class="row g-3">
	        <h2 class="display-7 mb-4">게시물 수정</h2>
	        <hr class="my-3">
	        	<table id="table">
					<tr>
						<th>게시 구분</th>
							<td width="150px;">
			                    <select id="search_type" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
									<option value="title">공지사항</option>
									<option value="writer">Q&A</option>
									<option value="writer">FAQ</option>
								</select>
			                </td>
						<th>게시 분류</th>
							<td width="150px;">
								<select id="search_type" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
									<option value="title">공통</option>
									<option value="writer">이벤트</option>
									<option value="writer">업데이트</option>
									<option value="writer">규정 및 정책</option>
								</select>							
							</td>
					</tr>
					<tr>
						<th>상단글로 노출</th>
						<td width="150px;">
		                    <input class="form-check-input" type="checkbox" name="em_type" id="flexRadioDefault1" >
		                </td>
					</tr>
		            <tr>
						<th>제목</th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="subject" placeholder="Subject">
<!-- 		                    <label for="subject">자료명</label> -->
		            	</td>
					</tr>
					<tr>
						<th>게시 일자</th>
						<td width="150px;">
							<input class="form-check-input" type="radio" name="em_type" id="flexRadioDefault1" >
							<label for="subject">등록 즉시 게시</label>
						</td>
						<td width="150px;">
							<input class="form-check-input" type="radio" name="em_type" id="flexRadioDefault1" >
							<label for="subject">게시 일자 선택</label>					
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
		                   	<textarea class="form-control" placeholder="Leave a message here" id="message" style="height: 200px"></textarea>    
						</td>
					</tr>
					<tr></tr>
	                <tr>
	                	<th>첨부파일</th>
	                	<td colspan="3">
	                		<label style="font-size: medium;">파일 1개당 최대 첨부 용량 30MB</label>
		                    <input type="file" class="form-control" id="subject" placeholder="Subject">
		                </td>
	                
	                </tr>
                </table>
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<a href="boardList"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="등록">
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
