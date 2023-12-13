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
			 	<c:forEach var="gameList" items="${gameList }">
				 	<tr>
						<td>${gameList.g_title}</td>
						<td>${gameList.g_period}개월</td>
						<td>${gameList.remainingPeriod}일</td>
						<td>${gameList.g_to}명</td>
						<td>${gameList.remainingTo}명</td>
					</tr>
				</c:forEach>
			</tbody>   
              </table>
              
	         <h5 style="margin-top: 70px;">그룹 상세 정보 입력</h5>
	         <form action="learnGroupFormInsert" method="post">
                <table class="formTable">
					<tr>
						<th>교육자명</th>
						<td colspan="3">(교육자명)</td>
	                </tr>
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
						<td>
		                    <input type="month" class="form-control" name="lgSdate" required>
		                </td>
		                <td>
		                    ~
		                </td>
		                <td>
		                    <input type="month" class="form-control" name="lgEdate" required>
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
	         </form>
              
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
