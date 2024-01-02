<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 상세</title>
</head>
<script type="text/javascript">
	function memberList() {
		//startDate 등 검색 x하고 상세페이지 올 경우 criteria.startDate가 null인경우 ''로 치환하기 위해 전부 ""로 감쌈
	    startDate = "${criteria.startDate == null ? '' : criteria.startDate}";
	    endDate = "${criteria.endDate == null ? '' : criteria.endDate}";
	    searchType = "${criteria.searchType}";
	    searchValue = "${criteria.searchValue}";
	    category = "${criteria.searchCategory}";
	    mshipType = "${criteria.searchMshipType}";
	    page = "${page}";
		criteriaList = "${criteriaList}";
	    
	    
	   // alert("startDate " + startDate);
//	    alert("endDate " + endDate);
	//    alert("searchType " + searchType);
	  //  alert("searchValue " + searchValue);
	   // alert("category " + category);
	   // alert("mshipType " + mshipType);
	    //alert("page " + page);
	
	    if (
	        startDate === '' &&
	        endDate === '' &&
	        searchType === '' &&
	        searchValue === '' &&
	        category === '' &&
	        mshipType === ''
	    ) {
	        //alert("검색 무");
	        location.href = "/operate/memberList?page=" + page;
	    } else {
	       // alert("검색 유");
	        location.href = "/operate/SearchMemberList" + criteriaList;
	        //location.href = "/operate/SearchMemberList?startDate=" + startDate + "&endDate=" + endDate + "&searchType=" + searchType + "&searchValue=" + searchValue + "&category=" + category + "&mshipType=" + mshipType + "&page=" + page;
	    }
	}
	
	function memberUpdateForm(pMmNum){
		startDate = "${criteria.startDate == null ? '' : criteria.startDate}";
	    endDate = "${criteria.endDate == null ? '' : criteria.endDate}";
	    searchType = "${criteria.searchType}";
	    searchValue = "${criteria.searchValue}";
	    category = "${criteria.searchCategory}";
	    mshipType = "${criteria.searchMshipType}";
	    page = "${page}";
	    criteriaList = "${criteriaList}";
	    
	    if (
		        startDate === '' &&
		        endDate === '' &&
		        searchType === '' &&
		        searchValue === '' &&
		        category === '' &&
		        mshipType === ''
		    ) {
		       // alert("검색 무");
		        location.href = "/operate/memberUpdateForm?mmNum="+pMmNum+"&page=" + page;
		    } else {
		       // alert("검색 유");
		        location.href = "/operate/memberUpdateForm" + criteriaList+"&mmNum="+pMmNum;
		    }
	}
	
	function myUpdateForm(pMmNum){
		location.href = "/info/myUpdateForm?mmNum="+pMmNum;
	}

</script>
<body>
<div class="row g-0 mb-5  justify-content-center">
       <div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
           <div class="row g-3">
               <h2 class="display-7 mb-4">회원정보 상세</h2>

               <hr class="my-3">
               <form id="joinForm">
	        	<table class="formTable">
		            <tr>
						<th>이름</th>
						<td colspan="3">
		                    ${member.mmName }
		            	</td>
					</tr>
		            <tr>
						<th>아이디</th>
						<td colspan="3">
							${member.mmId }
						</td>

					</tr>
		            <tr id="phoneTr">
						<th>휴대폰 번호</th>
						<td colspan="3">
							${member.phone }
		            	</td>
					</tr>
		            <tr>
						<th>전화 번호</th>
						<td colspan="3">
							${member.tel }
		            	</td>
					</tr>
					<tr>
						<th>회원구분</th>
						<td   width="150px;">
							<c:if test="${member.category == 1}">교육자</c:if>
							<c:if test="${member.category == 2}">학습자</c:if>
							<c:if test="${member.category == 3}">일반인</c:if>
							<c:if test="${member.category == 4}">운영자</c:if>
		            	</td>
		            	<th  style="padding-left: 40px;">회원자격</th>
						<td  width="150px;">
							<c:if test="${member.mshipType == 1}">무료회원</c:if>
							<c:if test="${member.mshipType == 2}">유료회원</c:if>
						
						</td>
					</tr>
		            <tr>
						<th>생년월일</th>
						<td>
							${member.birth }
		            	</td>
		            	<th style="padding-left: 40px;">성별</th>
                        <td width="180px">
                        	<c:if test="${member.gender == 1}">남자</c:if>
                        	<c:if test="${member.gender == 2}">여자</c:if>
					    </td>
					</tr>
		            <tr id="emailTr">
						<th>이메일 주소</th>
						<td colspan="3">
							${member.email }
		            	</td>
					</tr>
		            <tr>
						<th>주소</th>
						<td colspan="3">
							${member.address }
		            	</td>
					</tr>
					<tr>
						<th>수신 동의</th>
						<td colspan="3">
						이벤트, 커리큘럼, 신규 콘텐츠 등 광고 메시지 수집 동의 <br>
						<c:choose>
							<c:when test="${member.econsent == 1 }">
				                <input class="form-check-input" style="margin-left: 20px;" type="checkbox" name="econsent" id="econsent" value="1" checked disabled>
								<label>이메일 </label>
							</c:when>
							<c:otherwise>
			                	<input class="form-check-input" style="margin-left: 20px;" type="checkbox" name="econsent" id="econsent" value="1" disabled>
								<label>이메일 </label>
							</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${member.sconsent ==  1 }">
				                <input class="form-check-input"  style="margin-left: 20px;" type="checkbox" name="sconsent" id="sconsent" value="1" checked disabled>
								<label>SMS</label>
							</c:when>
							<c:otherwise>
				                <input class="form-check-input"  style="margin-left: 20px;" type="checkbox" name="sconsent" id="sconsent" value="1" disabled>
								<label>SMS</label>
							</c:otherwise>
						</c:choose>
		                </td>
					</tr>


                </table>
                </form>
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
                	<c:choose>
	                	<c:when test="${my == 1 }">
							<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="수정" onclick="myUpdateForm(${member.mmNum})">
	                	</c:when>
	                	<c:otherwise>
						<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="수정" onclick="memberUpdateForm(${mmNum})">
						<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="목록" onclick="memberList()">
	                	</c:otherwise>
                	</c:choose>
				</div>
                
			</div>
		</form>
	</div>
</div>
               
               
            </div>
        </div>
</div>

<%@ include file="../common/footerFo.jsp" %>
</body>
</html>