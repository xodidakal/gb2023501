<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	td {
		border : 1px solid black;
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
	        <h2 class="display-7 mb-4">숙제 제출</h2>
	        <hr class="my-3">
	        	<table class="formTable" style="border:1px;solid;black;">
					<thead>
						<tr>
							<th style="width:5%"><input class="form-check-input" type="checkbox" name="em_type" id="flexRadioDefault1" ></th>
							<th>숙제 내용</th>	
							<th>제출 내용</th>		
						</tr>
					</thead>
					<c:choose>
						<c:when test="${myHomeworkDetailList.size() > 0 }">
							<c:forEach var="myHomeworkDetail" items="${myHomeworkDetailList }">
								<tbody>
									<tr>
										<td rowspan="5">
						                    <input class="form-check-input" type="checkbox" name="em_type" id="flexRadioDefault1" >
						                </td>
						                <td>숙제명 : ${myHomeworkDetail.homework.hhTitle }</td>
						                <td>내 진도 : <input type="text" name="hrLevel" value="${myHomeworkDetail.hrLevel}"></td>
									</tr>
						            <tr>
										<td>교육자 : ${myHomeworkDetail.homework.member.mmName }</td>
										<td rowspan="2"> 
											금번학습내용 :<br>
											<textarea rows="3" cols="10">${myHomeworkDetail.hrContent }</textarea> 
						            	</td>
									</tr>
									<tr>
										<td rowspan="1">숙제내용 : <br>${myHomeworkDetail.homework.hhContent} </td>
									</tr>
									<tr>
										<td>숙제 진도 : ${myHomeworkDetail.homework.hhLevel}</td>
										<td rowspan="2">
											추가질의내용 : <br><textarea rows="3" cols="10">${myHomeworkDetail.hrQuestion }</textarea>
										</td>
									</tr>
									<tr>
										<td>제출기한 : ${myHomeworkDetail.homework.hhDeadline}</td>
									</tr>
								
							</c:forEach>
								<tr>
										<td rowspan="5">
						                    <input class="form-check-input" type="checkbox" name="em_type" id="flexRadioDefault1" >
						                </td>
						                <td>숙제명 : ${myHomeworkDetail.homework.hhTitle }</td>
						                <td>내 진도 : <input type="text" name="hrLevel"></td>
									</tr>
						            <tr>
										<td>교육자 : ${myHomeworkDetail.homework.member.mmName }</td>
										<td rowspan="2"> 
											금번학습내용 :<br>
											<textarea rows="3" cols="10"></textarea> 
						            	</td>
									</tr>
									<tr>
										<td rowspan="1">숙제내용 : <br>${myHomeworkDetail.homework.hhContent} </td>
									</tr>
									<tr>
										<td>숙제 진도 : ${myHomeworkDetail.homework.hhLevel}</td>
										<td rowspan="2">
											추가질의내용 : <br><textarea rows="3" cols="10"></textarea>
										</td>
									</tr>
									<tr>
										<td>제출기한 : ${myHomeworkDetail.homework.hhDeadline}</td>
									</tr>
								</tbody>
						</c:when>
						<c:otherwise>
							<tbody>
								<tr>
									<td rowspan="5">
					                    <input class="form-check-input" type="checkbox" name="em_type" id="flexRadioDefault1" >
					                </td>
					                <td>숙제명 : ${myHomeworkDetail.homework.hhTitle }</td>
					                <td>내 진도 : <input type="text" name="hrLevel"></td>
								</tr>
					            <tr>
									<td>교육자 : ${myHomeworkDetail.homework.member.mmName }</td>
									<td rowspan="2"> 
										금번학습내용 :<br>
										<textarea rows="3" cols="10"></textarea> 
					            	</td>
								</tr>
								<tr>
									<td rowspan="1">숙제내용 : <br>${myHomeworkDetail.homework.hhContent} </td>
								</tr>
								<tr>
									<td>숙제 진도 : ${myHomeworkDetail.homework.hhLevel}</td>
									<td rowspan="2">
										추가질의내용 : <br><textarea rows="3" cols="10"></textarea>
									</td>
								</tr>
								<tr>
									<td>제출기한 : ${myHomeworkDetail.homework.hhDeadline}</td>
								</tr>
							</tbody>
						</c:otherwise>
					</c:choose>
					
                </table>
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<a href="myhomeworkList?m_num=${mmNum }"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="숙제 제출 ">
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
