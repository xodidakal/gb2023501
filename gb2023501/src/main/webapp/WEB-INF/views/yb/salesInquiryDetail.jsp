<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">            
	.center-text {
	  text-align: center; /* 텍스트 가운데 정렬 */
	  position: absolute;
	  top: 55%;
	  left: 55%;
	  font-weight: bold;
	  color:black;
	  transform: translate(-50%, -50%); /* 가운데 정렬을 위한 변환 */
	}
</style>
</head>
<script>
    function toggleInput() {
        var selectValue = document.getElementById("selectDate").value;
        var dateInput = document.getElementById("startDate");
        var endDateInput = document.getElementById("endDate");
        var smonthInput = document.getElementById("sMonth");
        var emonthInput = document.getElementById("eMonth");

        if (selectValue === "date") {
            dateInput.style.display = "block";
            endDateInput.style.display = "block";
            smonthInput.style.display = "none";
            emonthInput.style.display = "none";
            
            dateInput.setAttribute("required", "required");
            endDateInput.setAttribute("required", "required");
            
        } else if (selectValue === "month") {
            dateInput.style.display = "none";
            endDateInput.style.display = "none";
            smonthInput.style.display = "block";
            emonthInput.style.display = "block";
            
            emonthInput.setAttribute("required", "required");
            smonthInput.setAttribute("required", "required");
            dateInput.removeAttribute("required", "required");
            endDateInput.removeAttribute("required", "required");
        
        }
    }
    function searchSalesInquiryDetail(go_order_date) {
		var popupW = 1000;
		var popupH = 500;
		var left = Math.ceil((window.screen.width - popupW)/2);
		var top = Math.ceil((window.screen.height - popupH)/2);
	
		var url = "/operate/searchSalesInquiryDetail?go_order_date="+go_order_date;
        var name = "searchSalesInquiryDetail";
        
        window.open(url, name, 'width='+popupW+',height='+popupH+',left='+left+',top='+top);
	}
	function saleInquiryChart(sDate, eDate, date) {
		alert(selectDate);
		var popupW = 1000;
		var popupH = 500;
		var left = Math.ceil((window.screen.width - popupW)/2);
		var top = Math.ceil((window.screen.height - popupH)/2);
	
		var url = "/operate/saleInquiryChart?sDate="+sDate +"&eDate="+eDate+"&date="+date;
        var name = "saleInquiryChart";
        
        window.open(url, name, 'width='+popupW+',height='+popupH+',left='+left+',top='+top);
	}
</script>
<body>
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
	
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">매출 조회</h2>
	         <c:if test="${not empty s_date }">
	    	 	<span>기간 : <fmt:formatDate value="${s_date }" pattern="yyyy년MM월dd일"/> ~ <fmt:formatDate value="${e_date }" pattern="yyyy년MM월dd일"/></span></p>
	         </c:if>
	         <p style="margin-bottom: 35px;"><span style="margin-right: 20px;">총 ${selectSaleList.size() } 건</span>
	         <span>총 매출액 : <fmt:formatNumber value="${selectTotal }" pattern="#,###" />원</span></p>
	    </div>
	   
	    <div class="mb-1">
	    <form action="/operate/searchSalesInquiry" method="post" role="search"> 
         	<div style="display: flex; ">
	         	<select id="selectDate" name="selectDate" class="w-17 rounded" style="border-color: #ced4da" onchange="toggleInput()">
					<option value="date">일단위</option>
					<option value="month">월단위</option>
				</select>&nbsp;&nbsp;
<!-- 				<div style="margin-right: 20px;"><input type="radio" name="selectCondition" id="date">일 단위</div>  -->
<!--          		<div style="margin-right: 55px;"><input type="radio" name="selectCondition" id="month">월 단위</div> -->
				<input class="form-control" type="month" id="sMonth" name="sMonth" style="width: 130px; display: none;">
				<input class="form-control" type="date" id="startDate" name="startDate" required="required" style="width: 130px;"><div class="mt-2">~</div>					
				<input class="form-control" type="date" id="endDate" name="endDate" required="required" style="width: 130px;">
				<input class="form-control" type="month" id="eMonth" name="eMonth" style="width: 130px; display: none;">
				
				<button type="submit" class="btn btn-light rounded py-2 px-2">검색</button>
				<button type="button" class="btn btn-light rounded py-2 px-2" style="margin-left: 660px;" 
						onclick="saleInquiryChart(<fmt:formatDate value="${s_date }" pattern="yyyyMMdd"/>, <fmt:formatDate value="${e_date }" pattern="yyyyMMdd"/>, ${selectDate })">그래프 보기</button>
						<input type="text" value="${selectDate }" name="selectDate" id="selectDate">
						
			</div>
		</form>
		
	    </div>
<%-- 	    <c:if test="${selectSaleList.size() == 0 && selectSaleList1.size() == 0 }">			 --%>
<!-- 	 		<div class="row" style="height: 100px"> -->
<!-- 				<div class="center-text mt-14 md-14"> -->
<!-- 					기간을 선택해주세요.<p><p> -->
<!-- 				</div> -->
<!-- 			</div>	 	 -->
<%-- 		</c:if> --%>
 	<c:if test="${selectSaleList.size() != 0 || selectSaleList1.size() != 0 }">	
       	<table class="listTable" style="text-align: center;">
       		<thead>
				<tr>
					<th>No.</th>
					<th>일 / 월</th>
					<th>건수</th>
					<th>매출금액</th>	
					<th width="100px;"></th>				
				</tr>
			</thead>
			<tbody>
				<c:if test="${selectSaleList.size() != 0  }">
				 <c:forEach var="selectSaleList" items="${selectSaleList }">
				 	<tr>
				 		<td>${StartRow +1}</td>
						<td>
							<fmt:formatDate value="${selectSaleList.goOrderDate }" pattern="yyyy년MM월dd일"/>
						</td>
						<td>${selectSaleList.salesCnt } 건</td>
						<td>
							<fmt:formatNumber value="${selectSaleList.salesSum }" pattern="#,###" /> 원
							
						</td>
						<td width="100px;"><button type="button" class="btn btn-light rounded py-2 px-3" type="button" style="background: #263d94; color: white;" 
												   onclick="searchSalesInquiryDetail(<fmt:formatDate value="${selectSaleList.goOrderDate }" pattern="yyyyMMdd"/>)">상세</button></td>
										
					</tr>
					<c:set var="StartRow" value="${StartRow +1}"/>
				 </c:forEach>				 
				</c:if>
				<c:if test="${selectSaleList1.size() != 0 }">
				 <c:forEach var="selectSaleList" items="${selectSaleList1 }">
				 	<tr>
				 		<td>${StartRow +1}</td>
						<td>
							<fmt:formatDate value="${selectSaleList.goOrderDate }" pattern="yyyy년MM월"/>
						
						</td>
						<td>${selectSaleList.salesCnt } 건</td>
						<td>
							<fmt:formatNumber value="${selectSaleList.salesSum }" pattern="#,###" /> 원
						</td>
						<td width="100px;"><button type="button" class="btn btn-light rounded py-2 px-3" type="button" style="background: #263d94; color: white;" 
												   onclick="searchSalesInquiryDetail(<fmt:formatDate value="${selectSaleList.goOrderDate }" pattern="yyyyMM"/>)">상세</button></td>
					</tr>
					<c:set var="StartRow" value="${StartRow +1}"/>
				 </c:forEach>
				</c:if>
             </tbody>   
		</table>
	</c:if>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
