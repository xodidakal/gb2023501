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
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
	function deleteEdu(emNum) {
		 if (confirm("삭제하시겠습니까?") == true){  
			 	location.href ="/operate/deleteEduMaterials?emNum="+emNum;
	    	 } else {
	    		    	return false;
	    	 }
		   	 return false;
	}
	
	 // input file에 change 이벤트 부여
    const file1 = document.getElementById("file1")
    file1.addEventListener("change", e => {
    	readImage(e.target)
    })
	
	function readImage(input){
		if(input.files && input.files[0]){
			//FileReader 인스턴스 생성
			const reader = new FileReader()	

			//이미지파일만 넣을 수 있도록하기

			//이미지가 로드 된 경우
			reader.onload = e => {
				const img = document.getElementById("img")
				img.src = e.target.result
			}
			// reader가 이미지 읽도록 하기
			reader.readAsDataURL(input.files[0])
		}
	}
   
</script>
</head>
<body>
<!-- 	<div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;"> -->
<!--         <h6 class="section-title bg-white text-center text-primary px-3">Services</h6> -->
<!--         <h2 class="display-6 mb-4">교육 자료 등록</h2> -->
<!--     </div> -->
<div class="row g-0 justify-content-center">
	<div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
		<form action="/operate/updateEduMaterials" method="post" enctype="multipart/form-data">
			<input type="hidden" value="${eduMaterials.emNum }" name="em_num" id="em_num">

	        <div class="row g-3">
		        <h2 class="display-7 mb-4">학습 자료 상세</h2>
		        <hr class="my-3">
	        	<small>등록일 : <fmt:formatDate value="${eduMaterials.emRegiDate }" pattern="yyyy-MM-dd"/></small>			                  
	        	<table id="table" style="margin-top: 0px;">
					<tr>
						<th>게임 콘텐츠</th>
						<td><input type="hidden" value="${eduMaterials.ggNum }" id="ggNum" name="ggNum">
							<select id="gNum" name="gNum" class="w-17 rounded" style="border-color: #ced4da; height: 30px;">
								<option value="0">미선택</option>
								<c:forEach items="${selectGameList }" var="selectGameList">
									<option value="${selectGameList.g_num }" 
										<c:if test="${selectGameList.g_num eq eduMaterials.ggNum }"> selected="selected" </c:if>>
											${selectGameList.g_title }
									</option>
								</c:forEach>	
							</select>
						 </td>
					</tr>
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
				                    <input class="form-check-input" type="radio" name="emType" value="2" id="em_typeVideo" checked="checked">
				                    <label>교재</label>
								</td>
								<td width="150px;">   
				                    <input class="form-check-input" type="radio" name="emType" value="3" id="em_typeSite" >
				                    <label>웹사이트</label>
								</td>
							</c:when>
							<c:otherwise>
								<td width="150px;">
				                    <input class="form-check-input" type="radio" name="emType" value="1" id="em_typeTutorial">
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
						<td colspan="2">
							<div class="d-grid gap-2 d-flex justify-content-center">
								<input type="hidden" value="${eduMaterials.emAttachName}" name="beforeName"> 
								<label for="file1">
									<c:choose>
			                            <c:when test="${fn:contains(eduMaterials.emAttachName, 'http')}">
			                            	<img src="${eduMaterials.emAttachName}" alt="Ecommerce"  width="75px" height="90px" id="img">
		                           		</c:when>
		                            	<c:otherwise>
		                              		<img src="${pageContext.request.contextPath}/upload/yb/${eduMaterials.emAttachName}" alt="Ecommerce"  width="75px" height="90px" id="img">
		                            	</c:otherwise>
	                        	    </c:choose>
	                        	</label>
								<input type="file" class="form-control" id="file1" name="file1" width="100px;" style="height: 40px;" value="${eduMaterials.emAttachName }" >
			                </div>
		                </td>
	                </tr>   
                </table>
                <div class="d-grid gap-2 d-flex justify-content-center" >
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="수정">
					<button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" onclick="deleteEdu(${eduMaterials.emNum })">삭제</button>
					<a href="/operate/eduMaterialsList"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
				</div>
				

               
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
