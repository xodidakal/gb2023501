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
	 var lgTitle = document.getElementById("lgTitle").value;
	 
	 if(lgTitle == "") {
		 location.href="/learning/learnGrpJoinForm";
	 }
}

function signUp() {
	var lg_num = $('input[name=lg_num]:checked').val();
	alert(lg_num)
	location.href="/learning/learnGrpJoinDo?lg_num="+lg_num;
}


</script>
<body>
<!-- 	<div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;"> -->
<!--         <h6 class="section-title bg-white text-center text-primary px-3">Services</h6> -->
<!--         <h2 class="display-6 mb-4">교육 자료 등록</h2> -->
<!--     </div> -->
<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">학습그룹 가입신청</h2>
	         <p style="margin-bottom: 35px;">총 ${selectLgpListByTitleCnt } 건</p>
	    </div>
		
			<div class="input-group col-md-5 mb-3"> 
				<!-- 카테고리 분류 -->
				<form action="/learning/searchGrpList" method="post">
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
				<select id="mmName" class="w-17 rounded" style="border-color: #ced4da; display: none;" >
				<c:forEach var="selectLGpList" items="${selectLGpList }">
					<option value="title">${selectLGpList.member.mmName }</option>
				</c:forEach>

				</select>&nbsp;&nbsp;
	            <input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="조회" onsubmit="chk reload()">
	               </form>
				<div class="col">
				
					<div class="d-flex align-items-center justify-content-end">
		          		<div>
			          		<button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" onclick="signUp()">등록</button>
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
			 <tbody>
				<c:forEach var="lgpList" items="${selectLgpListByTitle }">
				 	<tr>
				 		<td>
				 			<input type="radio" value="${lgpList.lg_num }" name="lg_num" id="lg_num">

				 		</td>
				 			
						<td><img alt="" src="${lgpList.g_attach_name }" style="width: 100px; height: 100px;"></td>
						<td>${lgpList.lg_title } / ${lgpList.m_name }</td>
						<td>${lgpList.lg_sdate } ~ ${lgpList.lg_edate }</td>
						<td>${lgpList.remainningTo } / ${lgpList.lg_to }</td>
					</tr>
				</c:forEach>
				
                </tbody>   
              </table>
              <div class="row mt-8" style="width:100%;">
					<div class="d-flex justify-content-center" style="margin-top:12px">
                <nav aria-label="Page navigation example">
				  <ul class="pagination">
				    <li class="page-item"><a class="page-link" href="#">이전</a></li>
				    <li class="page-item" id="1p"><a class="page-link" href="#">1</a></li>
				    <li class="page-item"><a class="page-link" href="#">2</a></li>
				    <li class="page-item"><a class="page-link" href="#">3</a></li>
				    <li class="page-item"><a class="page-link" href="#">4</a></li>
				    <li class="page-item"><a class="page-link" href="#">5</a></li>
				    <li class="page-item"><a class="page-link" href="#">6</a></li>
				    <li class="page-item"><a class="page-link" href="#">7</a></li>
				    <li class="page-item"><a class="page-link" href="#">8</a></li>
				    <li class="page-item"><a class="page-link" href="#">9</a></li>
				    <li class="page-item"><a class="page-link" href="#">10</a></li>
				    <li class="page-item"><a class="page-link" href="#">다음</a></li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
<script type="text/javascript">

</script>
</body>
</html>
