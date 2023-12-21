<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
</style>
<script type="text/javascript">
	function detailForm(em_num, ggNum) {
		if(ggNum == null) {
			ggNum = 0;
			alert(em_num);
			alert(ggNum);
			location.href = "/operate/eduMaterialsDetail?em_num="+em_num+"&ggNum="+ggNum;
		} else {
			alert(em_num);
			location.href = "/operate/eduMaterialsDetail?em_num="+em_num+"&ggNum="+ggNum;
		}
		
	}
	

	function selectType() {
		var typeSelect1 = document.getElementById("search_type1").value;
		var typeSelect2 = document.getElementById("search_type2").value;
		var typeSelect3 = document.getElementById("search_type3").value;
		
		location.href = "/operate/selectSearchType?typeSelect1="+typeSelect1+"&typeSelect2="+typeSelect2+"&typeSelect3="+typeSelect3;
	}
</script>
</head>
<style type="text/css">            
	.center-text {
	  text-align: center; /* 텍스트 가운데 정렬 */
	  position: absolute;
	  top: 55%;
	  left: 48%;
	  font-weight: bold;
	  color:black;
	  transform: translate(-50%, -50%); /* 가운데 정렬을 위한 변환 */
	}
</style>
<body>
<!-- 	<div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;"> -->
<!--         <h6 class="section-title bg-white text-center text-primary px-3">Services</h6> -->
<!--         <h2 class="display-6 mb-4">교육 자료 등록</h2> -->
<!--     </div> -->
<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">학습 자료 목록</h2>
	         <p style="margin-bottom: 35px;">총 ${selectEduMaterialsList.size() } 건</p>
	         <c:if test="${keyword ne null}">
		         <h6 style="margin-top: 35px;margin-bottom: 35px;">
		         	<c:choose>
		         		<c:when test="${type eq 'em_title'}">자료명</c:when>
		         	</c:choose>
		         	 : '${keyword }' 검색 결과
		         </h6>
	         </c:if>
	         
	    </div>
	    <form action="/operate/searchEduMaterials" method="GET"> 
		<div class="input-group col-md-5 mb-3"> 
			<!-- 카테고리 검색 -->
			<select id="type" name="type" class="w-17 rounded" style="border-color: #ced4da">
				<option value="em_title">자료명</option>
			</select>&nbsp;&nbsp;
	            <input id="search" name="keyword" class="form-control rounded" type="search" placeholder="내용을 입력해주세요" style="width: 160px;">
	         		<button class="btn bi bi-search rounded"></button>
			<div class="col">
			<div class="d-flex align-items-center justify-content-end">
          		<div style="width: 65px;">
	          		<a href="/operate/eduMaterialsForm"><input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="등록"></a>
            	</div>
            </div>
			</div>
	    </div>
	    </form>
	    <form action="" name="frm">
        	<table class="listTable" style="text-align: center;">
        		<thead>
        		
					<tr>
						<th>No.</th>
						<th>썸네일</th>
						<th>학습자료명</th>
						<th><select id="search_type1" name="typeSelect1" class="w-17 rounded" style="border-color: #eeeeee; 
									background: #eeeeee; text-align: center;" onchange="selectType()">
								<option  value="${eduMaterials.emCategory }" <c:if test="${typeSelect1 eq eduMaterials.emCategory}"> selected="selected" </c:if>>
									<c:choose>
										<c:when test="${eduMaterials.emCategory == 0}">자료구분</c:when>
										<c:when test="${eduMaterials.emCategory == 1}">튜토리얼</c:when> 
										<c:otherwise>교육영상</c:otherwise> 
									</c:choose>
								</option>
								<option value="0">전체</option>
								<option value="1">튜토리얼</option>
								<option value="2">교육영상</option>			
							</select></th>
						<th><select id="search_type2" class="w-17 rounded" name="typeSelect2" style="border-color: #eeeeee; background: #eeeeee; text-align: center;" onchange="selectType()">
								<option  value="${eduMaterials.emType }" <c:if test="${typeSelect2 eq eduMaterials.emType}"> selected="selected" </c:if>>
									<c:choose>
										<c:when test="${eduMaterials.emType == 0}">자료유형</c:when>
										<c:when test="${eduMaterials.emType == 1}">웹사이트</c:when>
										<c:when test="${eduMaterials.emType == 2}">교재</c:when>  
										<c:otherwise>동영상</c:otherwise> 
									</c:choose>
								</option>
								<option value="0">전체</option>
								<option value="1">웹사이트</option>
								<option value="2">교재</option>	
								<option value="3">동영상</option>		
							</select></th>
						<th><select id="search_type3" class="w-17 rounded" name="typeSelect3" style="border-color: #eeeeee; background: #eeeeee; text-align: center;" onchange="selectType()">
								<option  value="${eduMaterials.emPayment }" <c:if test="${typeSelect3 eq eduMaterials.emPayment}"> selected="selected" </c:if>>
									<c:choose>
										<c:when test="${eduMaterials.emPayment == 0}">서비스구분</c:when>
										<c:when test="${eduMaterials.emPayment == 1}">무료</c:when>
										<c:when test="${eduMaterials.emPayment == 2}">유료</c:when>  
										<c:otherwise>동영상</c:otherwise> 
									</c:choose>
								</option>
								<option value="0">전체</option>
								<option value="1">무료</option>
								<option value="2">유료</option>			
							</select></th>
						<th width="100px;"></th>				
					</tr>
				</thead>
			
				<tbody>
				 <c:forEach var="eduMaterialsList" items="${selectEduMaterialsList }">
				 	<tr>
				 		<td>${StartRow + 1}</td>
						<td style="width: 100px;" height="80px;">
						<div class="col-3 col-md-2">
		                	<c:choose>
	                           <c:when test="${fn:contains(eduMaterialsList.emAttachName, 'http')}">
	                              <img src="${eduMaterialsList.emAttachName}" alt="Ecommerce"  width="75px" height="90px">
	                           </c:when>
	                           <c:otherwise>
	                              <img src="${pageContext.request.contextPath}/upload/yb/${eduMaterialsList.emAttachName}" alt="Ecommerce"  width="75px" height="90px">
	                           </c:otherwise>
                        	</c:choose>
		                </div>
						</td>
						<td>
							<input type="hidden" value="${eduMaterialsList.emNum }" id="em_num" name="em_num">
							<input type="hidden" value="${eduMaterialsList.ggNum }" id="ggNum" name="ggNum">
							${eduMaterialsList.emTitle }</td>
						<td>
							<c:if test="${eduMaterialsList.emCategory  == 1}">튜토리얼</c:if>
							<c:if test="${eduMaterialsList.emCategory  == 2}">교육영상</c:if>							
						</td>
						<td>
							<c:if test="${eduMaterialsList.emType  == 1}">동영상</c:if>
							<c:if test="${eduMaterialsList.emType  == 2}">교재</c:if>		
							<c:if test="${eduMaterialsList.emType  == 3}">웹사이트</c:if>
						</td>

						<td>
							<c:if test="${eduMaterialsList.emPayment == 1}">무료</c:if>
							<c:if test="${eduMaterialsList.emPayment == 2}">유료</c:if>
							
						</td>
						<td width="100px;"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" onclick="detailForm(${eduMaterialsList.emNum }, ${eduMaterialsList.ggNum })">상세</button></td>
					</tr>
					<c:set var="StartRow" value="${StartRow +1}"/>
					 </c:forEach>
  				   </tbody>
  				 
               </table>
			</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
