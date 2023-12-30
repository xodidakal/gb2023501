<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/none.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.center-text {
	  	text-align: center; /* 텍스트 가운데 정렬 */
	  	position: absolute;
	  	top: 30%;
	  	left: 50%;
	  	font-weight: bold;
	  	color:black;
	  	transform: translate(-50%, -50%); /* 가운데 정렬을 위한 변환 */
	}
	
.top {
         position: relative;
         display: flex; 
         justify-content: space-between;
         padding: 0.5rem 1.4rem;
         background-color: #263d94;
         vertical-align: middle;
     }
             
 h1.infoTit {
             margin-top : 10px;
                font-size: 20px; 
                color:#ffffff;
            }

</style>
</head>
<body>
<main class="textBox">
   <div class="mb-6">
      <header class="top">
         <h1 class="infoTit">
	         <c:if test="${stringDate == null}">
	           	매출 상세 <fmt:formatDate value="${date }" pattern="yyyy-MM-dd"/>
	         </c:if>
	         <c:if test="${stringDate != null}">
	           	매출 상세 ${stringDate }
	         </c:if>
         </h1>
      </header>
   </div>
   
   <div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mt-9 mb-9">
	         <!-- heading -->
	         <p style="margin-bottom: 15px; margin-top: 20px;">총 ${selectSaleList.size() } 건</p>
	    </div>

       	<table class="listTable">
       		<thead>
				<tr>
					<th>No.</th>
					<th>구매자</th>
					<th>구매상품</th>
					<th>구매금액</th>
				</tr>
			</thead>
			 <tbody>
				<c:forEach var="selectSaleList" items="${selectSaleList }">
				 	<tr>
				 		<td>${StartRow + 1}</td>						
						<td>${selectSaleList.member.mmName }</td>
						<td>${selectSaleList.game.ggTitle }</td>
						<td>
							<fmt:formatNumber value="${selectSaleList.goPayment }" pattern="#,###" /> 원
						</td>
					</tr>
					<c:set var="StartRow" value="${StartRow +1}"/>
				</c:forEach>
				
                </tbody>   
              </table>
<!--               <div class="row mt-8" style="width:100%;"> -->
<!-- 					<div class="d-flex justify-content-center" style="margin-top:12px"> -->
<!--                 <nav aria-label="Page navigation example"> -->
<!-- 				  <ul class="pagination"> -->
<!-- 				    <li class="page-item"><a class="page-link" href="#">이전</a></li> -->
<!-- 				    <li class="page-item" id="1p"><a class="page-link" href="#">1</a></li> -->
<!-- 				    <li class="page-item"><a class="page-link" href="#">2</a></li> -->
<!-- 				    <li class="page-item"><a class="page-link" href="#">3</a></li> -->
<!-- 				    <li class="page-item"><a class="page-link" href="#">4</a></li> -->
<!-- 				    <li class="page-item"><a class="page-link" href="#">5</a></li> -->
<!-- 				    <li class="page-item"><a class="page-link" href="#">6</a></li> -->
<!-- 				    <li class="page-item"><a class="page-link" href="#">7</a></li> -->
<!-- 				    <li class="page-item"><a class="page-link" href="#">8</a></li> -->
<!-- 				    <li class="page-item"><a class="page-link" href="#">9</a></li> -->
<!-- 				    <li class="page-item"><a class="page-link" href="#">10</a></li> -->
<!-- 				    <li class="page-item"><a class="page-link" href="#">다음</a></li> -->
<!-- 				  </ul> -->
<!-- 				</nav> -->
<!-- 			</div> -->
<!-- 		</div> -->
	</div>
</div>
</main>
</body>
</html>