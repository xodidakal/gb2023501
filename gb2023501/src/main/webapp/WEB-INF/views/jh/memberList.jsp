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
                    <!-- <tr>
                        <th>기간</th>
                        <td style="width: 150px;">
                            <input class="form-control" type="date" id="startDate" name="startDate">
                        </td>
                        <td style="width: 150px;">
                            <div class="text-center">~</div>
                        </td>
                        <td style="width: 150px;">
                            <input class="form-control" type="date" id="endDate" name="endDate">
                        </td>
                    </tr> -->
                    <tr>
                        <th>조건 검색</th>
                        <td>
                            <select id="searchType" name="searchType" class="form-select" style="border-color: #ced4da">
                                <option value="null">검색조건</option>
                                <option value="mmId">아이디</option>
                                <option value="mmName">이름</option>
                                <option value="phone">휴대폰</option>
                            </select>
                        </td>
                        <td></td>
                        <td>
                            <input type="text" class="form-control" id="SearchCriteria" name="SearchCriteria">
                        </td>
                    </tr>
                    <tr>
                        <th>회원구분</th>
                        <td>
                            <select id="categorySelect" class="form-select" name="category">
                                <option value="0">회원구분</option>
                                <option value="1">교육자</option>
                                <option value="2">학습자</option>
                                <option value="3">일반인</option>
                                <option value="4">운영자</option>
                            </select>
                        </td>
                        <th class="text-center">자격</th>
                        <td>
                            <select id="search_type" class="form-select" name="mshipType">
                                <option value="0">회원 자격</option>
                                <option value="1">무료</option>
                                <option value="2">유료</option>
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
			 <c:forEach var="member" items="${memberList.content}" varStatus="iterStat">
			 	<tr>
			 		<td>${startNumber - iterStat.count + 1}</td>
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
			 		<td>${member.regiDate }</td>
					<td width="100px;"><a href="#"><button type="button" class="btn btn-light rounded py-2 px-3" style="background: #263d94; color: white;">상세</button></a></td>
				</tr>
				
				
			</c:forEach>				
                </tbody>   
              </table>
              <div class="row mt-8" style="width:100%;">
    <div class="d-flex justify-content-center" style="margin-top: 12px">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="?page=${page - 1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach begin="0" end="${memberList.totalPages - 1}" var="pageIndex">
                <li class="page-item" id="${pageIndex + 1}">
                    <a class="page-link" href="?page=${pageIndex + 1}">${pageIndex + 1}</a>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link" href="?page=${page + 1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
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