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
<!-- 	<div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;"> -->
<!--         <h6 class="section-title bg-white text-center text-primary px-3">Services</h6> -->
<!--         <h2 class="display-6 mb-4">교육 자료 등록</h2> -->
<!--     </div> -->
<div class="row g-0 justify-content-center">
	<div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
		<form action="insertEduMaterials" method="post" enctype="multipart/form-data">
	        <div class="row g-3">
	        <h2 class="display-7 mb-4">학습 자료 등록</h2>
	        <hr class="my-3">
	        	<table class="formTable">
	        		<tr>
	        			<th>게임 컨텐츠</th>
	        			<td>
	        			<select id="ggNum" name="ggNum" class="w-17 rounded" style="border-color: #ced4da; height: 30px;">
	        				<option value="0">미선택</option>
							<c:forEach items="${selectGameList }" var="selectGameList">
								<option value="${selectGameList.g_num }">${selectGameList.g_title }</option>
							</c:forEach>	
						</select>
	        			</td>
	        			<td></td>
	        		</tr>
					<tr>
						
						<th>자료구분</th>
						<td width="150px;">
		                    <input class="form-check-input" type="radio" name="emCategory" id="em_typeTutorial" value="1" required>
		                    <label>튜토리얼</label>
		                </td>
		                <td width="150px;">   
		                    <input class="form-check-input" type="radio" name="emCategory" id="em_typeVideo" value="2">
		                    <label>교육영상</label>
						</td>
						<td></td>
					</tr>
					
					<tr>
						<th>자료유형</th>
						<td width="150px;">
		                    <input class="form-check-input" type="radio" name="emType" id="emType1" value="1" required>
		                    <label>동영상</label>
		                </td>
		                <td width="150px;">
		                    <input class="form-check-input" type="radio" name="emType" id="emType2" value="2">
		                    <label>교재</label>
		                </td>
		                <td>  
		                    <input class="form-check-input" type="radio" name="emType" id="emType3" value="3">
		                    <label>웹사이트</label>
						</td>
					</tr>
					<tr>
						<th>자료구분</th>
						<td width="150px;">
		                    <input class="form-check-input" type="radio" name="emPayment" id="emPayment1" value="1" required>
		                    <label>무료</label>
		                </td>
		                <td width="150px;">
		                    <input class="form-check-input" type="radio" name="emPayment" id="emPayment2" value="2">
		                    <label>유료</label>
						</td>
						<td></td>
					</tr>
		        
<!-- 		            <div class="col-md-6"> -->
<!-- 		                <div class="form-floating"> -->
<!-- 		                    <input type="text" class="form-control" id="name" placeholder="Your Name" > -->
<!-- 		                    <label for="name">Your Name</label> -->
<!-- 		                </div> -->
<!-- 		            </div> -->
<!-- 		            <div class="col-md-6"> -->
<!-- 		                <div class="form-floating"> -->
<!-- 		                    <input type="email" class="form-control" id="email" placeholder="Your Email"> -->
<!-- 		                    <label for="email">Your Email</label> -->
<!-- 		                </div> -->
<!-- 		            </div> -->
		            <tr>
						<th>자료명</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="emTitle" id="emTitle" required>
<!-- 		                    <label for="subject">자료명</label> -->
		            	</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
		                   	<textarea class="form-control" name="emContent" id="emContent" style="height: 200px" required></textarea>
<!-- 		                       <label for="message">내용</label> -->
						</td>
					</tr>
		            <tr>
						<th>자료주소</th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="emDataAddr" name="emDataAddr" required>
		                </td>
					</tr>
	                <tr>
	                	<th>썸네일</th>
						<td colspan="3">
		                    <input type="file" class="form-control" name="file1" id="file1" required>
		                </td>
	                </tr>    
                </table>
                <div class="d-grid gap-2 d-flex justify-content-center" >
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="등록">
					<a href="/operate/eduMaterialsList"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
