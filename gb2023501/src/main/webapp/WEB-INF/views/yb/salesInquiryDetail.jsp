<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
    function toggleInput() {
        var selectValue = document.getElementById("selectDate").value;
        var dateInput = document.getElementById("startDate");
        var endDateInput = document.getElementById("endDate");
        var smonthInput = document.getElementById("sMonth");
        var emonthInput = document.getElementById("eMonth");

        if (selectValue === "date1") {
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
    
	function saleInquiryChart(sDate, eDate) {
		var date = document.getElementById('selectDate1').value
		alert(date);
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
	    	 	<span>기간 : <fmt:formatDate value="${s_date }" pattern="yyyy-MM-dd"/> ~ <fmt:formatDate value="${e_date }" pattern="yyyy-MM-dd"/></span></p>
	         </c:if>
	        <p style="margin-bottom: 35px;"><span style="margin-right: 20px;">총 ${selectListCnt } 건</span>   
	         <span>총 매출액 : <fmt:formatNumber value="${selectTotal }" pattern="#,###" />원</span></p>
	    </div>
	   
	    <div class="mb-1">
	    <form action="/operate/searchSalesInquiry" method="post" role="search" class="mb-3"> 
         	<div style="display: contents;">
         	<div style="display: inline-flex;">
	         	<select id="selectDate" name="selectDate" class="w-17 rounded" style="border-color: #ced4da" onchange="toggleInput()">
					<option value="date1">일단위</option>
					<option value="month">월단위</option>
				</select>&nbsp;&nbsp;
<!-- 				<div style="margin-right: 20px;"><input type="radio" name="selectCondition" id="date">일 단위</div>  -->
<!--          		<div style="margin-right: 55px;"><input type="radio" name="selectCondition" id="month">월 단위</div> -->
				<input class="form-control" type="month" id="sMonth" name="sMonth" style="width: 130px; display: none;">
				<input class="form-control" type="date" id="startDate" name="startDate" required="required" style="width: 130px;"><div class="mt-2">~</div>					
				<input class="form-control" type="date" id="endDate" name="endDate" required="required" style="width: 130px;">
				<input class="form-control" type="month" id="eMonth" name="eMonth" style="width: 130px; display: none;">
				&nbsp;&nbsp;
				<button type="submit" class="btn btn-light rounded py-2 px-3" style="background: #263d94; color: white">검색</button>
				</div>
				<button type="button" class="btn btn-light rounded py-2 px-2" style="background: #263d94; color: white; float: right;"
						onclick="saleInquiryChart(<fmt:formatDate value="${s_date }" pattern="yyyyMMdd"/>, <fmt:formatDate value="${e_date }" pattern="yyyyMMdd"/>)">그래프 보기</button>
		
			</div>
		</form>
		<input type="hidden" value="${selectDate1 }" name="selectDate1" id="selectDate1">
	    </div>
	   
		
 	
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
			<c:if test="${selectListCnt == 0 }">
			    <tr>			
							<td colspan="5">기간을 선택해주세요.</td>
				</tr>
			</c:if>
			<c:if test="${selectListCnt != 0 }">	
				<tbody>
					<c:if test="${selectSaleList.size() != 0  }">
					 <c:forEach var="selectSaleList" items="${selectSaleList }">
					 	<tr>
					 		<td>${StartRow +1}</td>
							<td>
								<fmt:formatDate value="${selectSaleList.goOrderDate }" pattern="yyyy-MM-dd"/>
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
								<fmt:formatDate value="${selectSaleList.goOrderDate }" pattern="yyyy-MM"/>
							
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
             </c:if>   
		</table>
	
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
