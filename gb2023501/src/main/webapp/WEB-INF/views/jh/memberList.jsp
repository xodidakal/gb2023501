<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#searchForm tr {
    height: 50px; /* 높이 설정 */
}
#searchForm {
        margin-bottom: 30px;
    }
#searchBtn {
        margin-bottom: 43px;
}


</style>
<script type="text/javascript">
	function pageMove(pPageNum) {
		//검색 조건 선택 x하면 null이 아니라 빈문자열''이 됨
	    startDate = $('#startDate').val() ;
	    endDate = $('#endDate').val() ;
	    searchType = $('#searchType').val() ;
	    searchValue = $('#searchValue').val() ;
	    category = $('#categorySelect').val() ;
		//alert("category " +category);
	    mshipType = $('#mshipType').val() ;
		//alert("mshipType " +mshipType);
	
	    if (
	        startDate === '' &&
	        endDate === '' &&
	        searchType === 'null' &&
	        searchValue === '' &&
	        category === '0' &&
	        mshipType === '0'
	    ) {
	        location.href = "/operate/memberList?page=" + pPageNum;
	    } else {
	    	location.href = "/operate/SearchMemberList?startDate="+startDate+"&endDate="+endDate+"&searchType="+searchType+"&searchValue="+searchValue+"&category="+category+"&mshipType="+mshipType+"&page="+pPageNum;
	    }
	    // 이하 코드는 그대로 유지
	}
	
	function memberDetail(pMmNum){
		startDate = $('#startDate').val() ;
	    endDate = $('#endDate').val() ;
	    searchType = $('#searchType').val() ;
	    searchValue = $('#searchValue').val() ;
	    category = $('#categorySelect').val() ;
		//alert("category " +category);
	    mshipType = $('#mshipType').val() ;
	    page = ${page};
		alert("page " +page);
		//alert("pMmNum " +pMmNum);
	    
	    
	    if (
	    		startDate === '' &&
		        endDate === '' &&
		        searchType === 'null' &&
		        searchValue === '' &&
		        category === '0' &&
		        mshipType === '0'
		    ) {
	    	alert("pMmNum" +pMmNum);
	    	location.href = "/operate/memberDetail?mmNum=" + pMmNum+"&page="+page;
	    } else  {
	    	alert("검색 유");
	    	location.href = "/operate/memberDetail?startDate="+startDate+"&endDate="+endDate+"&searchType="+searchType+"&searchValue="+searchValue+"&category="+category+"&mshipType="+mshipType+"&mmNum=" + pMmNum+"&page="+page;
	    	
	    } 
	}
</script> 
</head>
<body>
<div class="row g-0 justify-content-center">
    <div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
        <div class="mb-9">
            <!-- heading -->
            <h2 style="margin-bottom: 15px;">회원목록</h2>
            <p style="margin-bottom: 35px;">총 ${totalMembers}건</p>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-9 ">
            <form action="/operate/SearchMemberList" method="GET" >
                <table class="formTable" id="searchForm">
                    <tr>
                        <th>기간</th>
                        <td style="width: 150px;">
                        	<c:choose>
	                        	<c:when test="${searchCriteria.startDate == null || empty searchCriteria.startDate}">
		                            <input class="form-control" type="date" id="startDate" name="startDate">
	                        	</c:when>
	                        	<c:otherwise>
		                            <input class="form-control" type="date" id="startDate" name="startDate" value="${searchCriteria.startDate }">
	                        	</c:otherwise>
                        	</c:choose>
                        </td>
                        <td style="width: 150px;">
                            <div class="text-center">~</div>
                        </td>
                        <td style="width: 150px;">
                        	<c:choose>
	                        	<c:when test="${searchCriteria.endDate == null || empty  searchCriteria.endDate}">
		                            <input class="form-control" type="date" id="endDate" name="endDate">
	                        	</c:when>
	                        	<c:otherwise>
		                            <input class="form-control" type="date" id="endDate" name="endDate" value="${searchCriteria.endDate }">
	                        	</c:otherwise>
                        	</c:choose>
                        </td>
                    </tr> 
                    <tr>
                        <th>조건 검색</th>
                        <td>
                            <select id="searchType" name="searchType" class="form-select" style="border-color: #ced4da">
                                <option value="null" <c:if test="${searchCriteria.searchType eq 'null' }">	selected="selected"</c:if>>검색조건</option>
                                <option value="mmId" <c:if test="${searchCriteria.searchType eq 'mmId' }">	selected="selected"</c:if>>아이디</option>
                                <option value="mmName" <c:if test="${searchCriteria.searchType eq 'mmName' }">	selected="selected"</c:if>>이름</option>
                                <option value="phone" <c:if test="${searchCriteria.searchType eq 'phone' }">	selected="selected"</c:if>>휴대폰</option>
                            </select>
                        </td>
                        <td></td>
                        <td>
                        	<c:choose>
                        		<c:when test="${searchCriteria.searchValue != null}">
		                            <input type="text" class="form-control" id="searchValue" name="searchValue" value="${searchCriteria.searchValue }">
                        		</c:when>
                        		<c:otherwise>
		                            <input type="text" class="form-control" id="searchValue" name="searchValue">
                        		</c:otherwise>
                        	</c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th>회원구분</th>
                        <td>
<%--                         	<c:choose>
                        		<c:when test="${searchCriteria. }">
                        		</c:when>
                        		<c:otherwise>
                        		</c:otherwise>
                        	</c:choose> --%>
                            <select id="categorySelect" class="form-select" name="category">
                                <option value="0" <c:if test="${searchCriteria.category == 0 }">	selected="selected"</c:if>>회원구분</option>
                                <option value="1" <c:if test="${searchCriteria.category == 1 }">	selected="selected"</c:if>>교육자</option>
                                <option value="2" <c:if test="${searchCriteria.category == 2 }">	selected="selected"</c:if>>학습자</option>
                                <option value="3" <c:if test="${searchCriteria.category == 3 }">	selected="selected"</c:if>>일반인</option>
                                <option value="4" <c:if test="${searchCriteria.category == 4 }">	selected="selected"</c:if>>운영자</option>
                            </select>
                        </td>
                        <th class="text-center">자격</th>
                        <td>
                            <select id="mshipType" class="form-select" name="mshipType">
                                <option value="0" <c:if test="${searchCriteria.mshipType == 0 }">	selected="selected"</c:if>>회원 자격</option>
                                <option value="1" <c:if test="${searchCriteria.mshipType == 1 }">	selected="selected"</c:if>>무료</option>
                                <option value="2" <c:if test="${searchCriteria.mshipType == 2 }">	selected="selected"</c:if>>유료</option>
                            </select>
                        </td>
                    </tr> 
                </table>
            </div>
            
            <div class="col-md-1 mt-auto" id="searchBtn">
                <div class="col-md-2" style="margin-left: 10px; width: 65px; margin-top: 6px;">
                    <button type="submit"><i class="bi bi-search mt-2"></i></a>
                </div>
            </div>
            </form>
        </div>

       	<table class="listTable">
       		<thead>
				<tr>
					<th>No.</th>
					<th>구분</th>
					<th>이름</th>
					<th>아이디</th>
					<th>연락처</th>
					<th>자격</th>	
					<th>가입일자</th>	
					<th width="100px;"></th>				
				</tr>
			</thead>
			 <tbody>
			 <c:set var="num" value="${totalMembers - startNumber}"></c:set>
<%-- 			 <c:set var="num" value="${totalMembers - startNumber +1}"></c:set> --%>
			 <c:forEach var="member" items="${memberList.content}" varStatus="iterStat">
			 	<tr>
			 		<td>${num}</td>
			 		<td>
			 			<c:if test="${member.category == 1 }">교육자</c:if> 
			 			<c:if test="${member.category == 2 }">학습자</c:if> 
			 			<c:if test="${member.category == 3 }">일반인</c:if> 
			 			<c:if test="${member.category == 4 }">운영자</c:if> 
			 		</td>
			 		<td>${member.mmName }</td>
			 		<td>${member.mmId }</td>
			 		<td>${member.phone }</td>
			 		<td>
			 			<c:if test="${member.mshipType == 1 }">무료</c:if>
			 			<c:if test="${member.mshipType == 2 }">유료</c:if>
			 		</td>
			 		<td>
				 		<fmt:formatDate value="${member.regiDate }" pattern="yyyy-MM-dd"></fmt:formatDate>
			 		</td>
					<td width="100px;"><button type="button" class="btn btn-light rounded py-2 px-3" style="background: #263d94; color: white;" onclick="memberDetail(${member.mmNum})">상세</button></a></td>
				</tr>
				<c:set var="num" value="${num - 1 }"></c:set>
				
			</c:forEach>				
                </tbody>   
              </table>
              <div class="row mt-8" style="width:100%;">
    <div class="d-flex justify-content-center" style="margin-top: 12px">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
	        <c:if test="${startPage > pageBlock}">
	            <li class="page-item">
	                <a class="page-link" onclick="pageMove(${startPage - pageBlock})" href="?page=${startPage - pageBlock}" aria-label="Previous">
	                    <span aria-hidden="true">&laquo;</span>
	                </a>
	            </li>
	        </c:if>
            <c:forEach begin="${startPage}" end="${endPage}" var="pageIndex">
            	<c:if test="${page == pageIndex}">
	                <li class="page-item active">
	                    <a class="page-link"  onclick="pageMove(${pageIndex})">${pageIndex}</a>
	                </li>
            	</c:if>
            	
            	<c:if test="${page != pageIndex}">
	                <li class="page-item">
	                    <a class="page-link" onclick="pageMove(${pageIndex})">${pageIndex}</a>
	                </li>
            	</c:if>
            </c:forEach>
            <c:if test="${endPage < totalPage}">
	            <li class="page-item ">
	                <a class="page-link" onclick="pageMove(${endPage + 1})" aria-label="Next">
	                    <span aria-hidden="true">&raquo;</span>
	                </a>
	            </li>
	        </c:if>
        </ul>
    </nav>
</div>

</div>
		</div>
	</div>
</div>
</body>
<%@ include file="../common/footerFo.jsp" %>
</html>