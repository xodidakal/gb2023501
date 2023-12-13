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
					<th>학습 가능 인원</th>
					<th>학습 확정 인원</th>	
				</tr>
			</thead>
			 <tbody>
			 	<tr>
					<td>게임콘텐츠명</td>
					<td>구독 기간</td>
					<td>학습 가능 인원</td>
					<td>학습 확정 인원</td>
				</tr>
                </tbody>   
              </table>
              
	         <h5 style="margin-top: 70px;">그룹 상세 정보 입력</h5>
                <table class="formTable">
					<tr>
						<th>교육자명</th>
						<td colspan="3">(교육자명)</td>
	                </tr>
					<tr>
						<th>그룹명</th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="" placeholder="">
		            	</td>
	                </tr>
					<tr>
						<th>수용 가능 인원</th>
						<td colspan="3">
		                    <input type="number" class="form-control" id="" placeholder="">
		            	</td>
	                </tr>
					<tr>
						<th>학습기간</th>
						<td>
		                    <input type="date" class="form-control" id="" placeholder="">
		                </td>
		                <td>
		                    ~
		                </td>
		                <td>
		                    <input type="date" class="form-control" id="" placeholder="">
		            	</td>
	                </tr>
					<tr>
						<th>추가항목1</th>
						<td colspan="3" style="height: 120px">
		                    <textarea class="form-control" placeholder="" id="message" style="height: 100px"></textarea>    
		            	</td>
	                </tr>
					<tr>
						<th>추가항목2</th>
						<td colspan="3" style="height: 120px">
		                    <textarea class="form-control" placeholder="" id="message" style="height: 100px"></textarea>    
		            	</td>
	                </tr>
                </table>
	         
              
              <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="등록">
              </div>
              
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
