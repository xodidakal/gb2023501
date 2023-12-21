<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function selectType() {
    var searchType = document.getElementById("searchType").value;
    var lgTitle = document.getElementById("lgTitle");
    var mmName = document.getElementById("mmName");


    if (searchType == "lgTitle") {
    	lgTitle.style.display = "block";
    	mmName.style.display = "none";
        
    	lgTitle.setAttribute("required", "required");
    	mmName.removeAttribute("required", "required");
        
    } else if (searchType == "mmName") {
    	lgTitle.style.display = "none";
    	mmName.style.display = "block";
        
    	lgTitle.removeAttribute("required", "required");
    	mmName.setAttribute("required", "required");
    }
}
function reload() {
	 var lgTitle = document.getElementById("searchType").value;
	 
	 if(lgTitle == "") {
		 location.href="/learning/learnGrpJoinForm";
	 }
}

function signUp() {
	 var lg_num = $('input[name=lg_num]:checked').val();
	 if(lg_num == null) {
		 alert("학습그룹을 선택해주세요.");
		 return false;
	 }
	 if (confirm("신청하시겠습니까?") == true){  
		 location.href="/learning/learnGrpJoinDo?lg_num="+lg_num;
   	 } else {
   		    	return false;
   	 }
	   	 return false;
	
}


</script>
<body>
<!-- 	<div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;"> -->
<!--         <h6 class="section-title bg-white text-center text-primary px-3">Services</h6> -->
<!--         <h2 class="display-6 mb-4">교육 자료 등록</h2> -->
<!--     </div> -->
<main>
<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">학습그룹 가입신청</h2>
	         <p style="margin-bottom: 35px;">총 ${selectLgpListByTitle.size() } 건</p>
	    </div>
			<div class="input-group col-md-5 mb-3"> 
				<!-- 카테고리 분류 -->
				<div class="col">
					<form action="/learning/searchGrpList" method="post" style="display: flex;">
						<select name="searchType" id="searchType" class="w-17 rounded" style="margin-right: 10px; border-color: #ced4da; height: 40px;" onchange="selectType()">
							<option value="lgTitle">그룹명</option>
							<option value="mmName">교육자명</option>
						</select>
						<select name="lgTitle" id="lgTitle" class="w-17 rounded" style="margin-right: 5px; border-color: #ced4da; height: 40px;" required>
							<option value="reload">전체보기</option>
							<c:forEach var="selectLGpList" items="${selectLGpList }">
								<option value="${selectLGpList.lgTitle }">${selectLGpList.lgTitle }</option>
							</c:forEach>
						</select>
						
						<!-- 카테고리 검색 -->
						<select id="mmName" name="mmName" class="w-17 rounded" style="margin-right: 5px; border-color: #ced4da; display: none; height: 40px;" >
							<option value="reload">전체보기</option>
							<c:forEach var="selectMNameList" items="${selectMNameList }">
			            		<option value="${selectMNameList.m_num }">${selectMNameList.m_name }</option>
			            	</c:forEach>
						</select>&nbsp;&nbsp;
		            	<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="조회" onsubmit="chk reload()">
	            </form>
	            </div>
				<div class="col">
			
					<div class="d-flex align-items-center justify-content-end">
		          		<div>
			          		<button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" onclick="signUp()">신청</button>
		            	</div>
		            </div>
				</div>
	    	</div>
	    	
	    	
       	<table class="listTable">
       		<thead>
				<tr>
					<th>선택</th>
					<th>콘텐츠 이미지</th>
					<th>그룹명 / 교육자명</th>
					<th>학습 기간</th>
					<th>잔여 인원</th>
				
				</tr>
			</thead>
			<c:if test="${selectLgpListByTitle.size() == 0 }">
				<tr>
					<td colspan="5">해당 학습그룹이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${selectLgpListByTitle.size() != 0 }">
			 <tbody>
				<c:forEach var="lgpList" items="${selectLgpListByTitle }">
				 	<tr>
				 		<td>
				 			<input type="radio" value="${lgpList.lg_num }" name="lg_num" id="lg_num" required="required">

				 		</td>
				 			
						<td><img alt="" src="${lgpList.g_attach_name }" style="width: 100px; height: 100px;"></td>
						<td><p>${lgpList.lg_title } / ${lgpList.m_name }</p>
							(${lgpList.g_title })
						</td>
						<td>${lgpList.lg_sdate } ~ ${lgpList.lg_edate }</td>
						<td>${lgpList.remainningTo } / ${lgpList.lg_to }</td>
					</tr>
				</c:forEach>
				
                </tbody> 
              </c:if>  
              </table>
  
	</div>
</div>
</main>
<%@ include file="../common/footerFo.jsp" %>
<script type="text/javascript">

</script>
</body>
</html>
