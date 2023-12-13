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
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<form action="/operate/updateEduMaterials" method="post">
			<input type="hidden" value="${eduMaterials.emNum }" name="em_num" id="em_num">
<%-- 			<input type="text" value="${m_num }" name="mNum" id="mNum"> --%>
<%-- 			<input type="text" value="${eduMaterials.ggNum }" name="ggNum" id="ggNum"> --%>
<%-- 			<input type="text" value="${eduMaterials.emAttachPath }" name="emAttachPath" id="emAttachPath"> --%>

	        <div class="row g-3">
		        <h2 class="display-7 mb-4">학습 자료 상세</h2>
		        <hr class="my-3">
	        	<small>작성일 : <fmt:formatDate value="${eduMaterials.emRegiDate }" pattern="yyyy년MM월dd일"/></small>			                  
<%-- 	        	<input type="hidden" value="${eduMaterials.emRegiDate }" name="emRegiDate" id="emRegiDate"> --%>
	        	<table id="table" style="margin-top: 0px;">
					<tr>
						<th>자료구분</th>
						<c:choose>
							<c:when test="${eduMaterials.emCategory  == 1}">	
								<td width="150px;">
				                    <input class="form-check-input" type="radio" name="emCategory" value="1" id="em_categoryTutorial" checked="checked">
				                    <label>튜토리얼</label>
				                </td>
				                <td width="150px;">   
				                    <input class="form-check-input" type="radio" name="emCategory" value="2" id="em_categoryVideo" >
				                    <label>교육영상</label>
								</td>
								<td></td>
							</c:when>			
							<c:otherwise>
								<td width="150px;">
				                    <input class="form-check-input" type="radio" name="emCategory" value="1" id="em_categoryTutorial">
				                    <label>튜토리얼</label>
				                </td>
				                <td width="150px;">   
				                    <input class="form-check-input" type="radio" name="emCategory" value="2" id="em_categoryVideo" checked="checked">
				                    <label>교육영상</label>
								</td>
								<td></td>

							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
					<th>자료유형</th>
						<c:choose>
							<c:when test="${eduMaterials.emType  == 1}">	
								<td width="150px;">
				                    <input class="form-check-input" type="radio" name="emType" value="1" id="em_typevideo" checked="checked">
				                    <label>동영상</label>
				                </td>
				                <td width="150px;">   
				                    <input class="form-check-input" type="radio" name="emType" value="2" id="em_typeVideo" >
				                    <label>교재</label>
								</td>
								<td width="150px;">   
				                    <input class="form-check-input" type="radio" name="emType" value="3" id="em_typeSite" >
				                    <label>웹사이트</label>
								</td>
							</c:when>			
							<c:when test="${eduMaterials.emType  == 2}">
								<td width="150px;">
				                    <input class="form-check-input" type="radio" name="emType" value="1" id="em_typeTutorial">
				                    <label>동영상</label>
				                </td>
				                <td width="150px;">   
				                    <input class="form-check-input" type="radio" name="em_type" value="2" id="em_typeVideo" checked="checked">
				                    <label>교재</label>
								</td>
								<td width="150px;">   
				                    <input class="form-check-input" type="radio" name="em_type" value="3" id="em_typeSite" >
				                    <label>웹사이트</label>
								</td>
							</c:when>
							<c:otherwise>
								<td width="150px;">
				                    <input class="form-check-input" type="radio" name="em_type" value="1" id="em_typeTutorial">
				                    <label>동영상</label>
				                </td>
				                <td width="150px;">   
				                    <input class="form-check-input" type="radio" name="emType" value="2" id="em_typeVideo" >
				                    <label>교재</label>
								</td>
								<td width="150px;">   
				                    <input class="form-check-input" type="radio" name="emType" value="3" id="em_typeSite"  checked="checked">
				                    <label>웹사이트</label>
								</td>	
							</c:otherwise>
						</c:choose>					
					</tr>
					<tr>
						<th>자료구분</th>
						<c:choose>
							<c:when test="${eduMaterials.emPayment  == 1}">	
								<td>
				                    <input class="form-check-input" type="radio" name="emPayment" value="1" id="emPayment" checked="checked">
				                    <label>무료</label>
				                </td>
				                <td>   
				                    <input class="form-check-input" type="radio" name="emPayment" value="2" id="emPayment" >
				                    <label>유료</label>
								</td>
								<td></td>
							</c:when>			
							<c:otherwise>
								<td>
				                    <input class="form-check-input" type="radio" name="emPayment" value="1" id="emPayment">
				                    <label>무료</label>
				                </td>
				                <td>   
				                    <input class="form-check-input" type="radio" name="emPayment" value="2" id="emPayment" checked="checked">
				                    <label>유료</label>
								</td>
								<td></td>
							</c:otherwise>
						</c:choose>
					</tr>
		            <tr>
						<th>자료명</th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="emTitle" name="emTitle" value="${eduMaterials.emTitle }">
		            	</td>
					</tr>				
					<tr>
						<th>내용</th>
						<td colspan="3">
		                   	<textarea class="form-control" id="emContent" name="emContent" style="height: 200px">${eduMaterials.emContent }</textarea>
						</td>
					</tr>
		            <tr>
						<th>자료주소</th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="emDataAddr" name="emDataAddr" value="${eduMaterials.emDataAddr }">
		                </td>
					</tr>
	                <tr>
	                	<th>썸네일</th>
						<td colspan="3">
							<div class="d-grid gap-2 d-flex justify-content-center" >
								<label for="emAttachName"><img src="${eduMaterials.emAttachName }" alt="도서 썸네일" class="img-fluid" style="width: 5rem; height: 80px;"></label>
								<input type="file" class="form-control" id="emAttachName" name="emAttachName" value="" style="visibility: hidden;" >
			                </div>
		                </td>
	                </tr>   
                </table>
                <div class="d-grid gap-2 d-flex justify-content-center" >
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="수정">
					<a href="/operate/eduMaterialsList"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">삭제</button></a>
					<a href="/operate/eduMaterialsList"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
				</div>
				

               
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
