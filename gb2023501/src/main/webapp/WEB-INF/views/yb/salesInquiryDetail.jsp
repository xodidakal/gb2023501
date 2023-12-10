<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp" %>
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
	         <h2 style="margin-bottom: 15px;">매출 조회</h2>
	         <p style="margin-bottom: 35px;"><span>총 10 건</span><span>총 매출액 : </span></p>
	    </div>
	    <div class="mb-1">
         	<div style="display: flex; ">
         		<div style="margin-right: 20px;"><input type="radio" name="selectCondition" id="date">일 단위</div> 
         		<div style="margin-right: 55px;"><input type="radio" name="selectCondition" id="month">월 단위</div>

				<input class="form-control" type="date" name="q_sdate" required="required" style="width: 130px;"><div class="mt-2">~</div>					
				<input class="form-control" type="date" name="q_edate" required="required" style="width: 130px;">
				
				<a href="#!"><button type="button" class="btn btn-light rounded py-2 px-2">그래프 보기</button></a>
			</div>
	    </div>
		<form action="#!">
	        <div class="table-responsive" style="text-align: center;">
	        	<table class="table">
	        		<thead class="table-light" style="text-align: center;">
						<tr>
							<th style="padding: 15px;">No.</th>
							<th style="padding: 15px;">일 / 월</th>
							<th style="padding: 15px;">건수</th>
							<th style="padding: 15px;">매출금액</th>	
							<th width="100px;"></th>				
						</tr>
					</thead>
					 <tbody>
<%-- 					 <c:forEach var="" items=""> --%>
					 	<tr>
					 		<td>1.</td>
							<td></td>
							<td></td>
							<td></td>
							<td width="100px;"><a href="#!"><button type="button" class="btn btn-light rounded py-2 px-3">상세</button></a></td>
						</tr>
<%-- 					 </c:forEach> --%>
						
	                 </tbody>   
                </table>
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
