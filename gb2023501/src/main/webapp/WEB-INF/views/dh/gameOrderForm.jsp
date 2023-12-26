<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	
	function mname(){    
		if($('input:radio[id=go_order_type1]').is(':checked')){ 
      		$('#go_order_type_view').show();    
		}else if($('input:radio[id=go_order_type2]').is(':checked')){
			$('#go_order_type_view').show();    
		}else{
			$('#go_order_type_view').hide();
		}
	}
</script>
</head>
<body>

<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<form action="gameOrderInsert" method="post">
	        <div class="row g-3">
	        <h2 class="display-7 mb-4">게임 콘텐츠 구독신청</h2>
	        <hr class="my-3">
	        	<table class="formTable">
		            <tr bgcolor="#EAEAEA">
						<th>주문하실 상품</th>
						<td colspan="3"></td>
					</tr>
					<c:set var="i" value="0"/>
					<c:forEach var="game" items="${gamelist}" varStatus="status">
						<input type="hidden" name="g_num" id="g_num" value="${game.g_num}">
						<input type="hidden" name="g_sell_price" value="${game.g_sell_price}">
						<tr>	
							<c:if test="${i eq 0}"><th rowspan="${gamelist.size() }">구매 상품명</th></c:if>
							<td colspan="3">
			                    ${game.g_title}
			            	</td>
						</tr>
						<c:set var="i" value="1"/>
					</c:forEach>   
					<tr bgcolor="#EAEAEA">
						<th>구매자 정보</th>
						<td colspan="3"></td>
					</tr>
					 <tr>
						<th>구매자명</th>
						<td colspan="3">
		                	${member.mmName}
		            	</td>
					</tr>
					<tr>
						<th id="phone">연락처</th>
						<td colspan="3">
							${phone}
		            	</td>
					</tr>
					<tr bgcolor="#EAEAEA">
						<th>주문합계</th>
						<td colspan="3">
							
							<fmt:formatNumber value="${gamesum}" groupingUsed="true"/>원
						</td>
					</tr>
					<tr>
						<th></th>
						<td colspan="3"></td>
					</tr>
					<tr bgcolor="#EAEAEA">
						<th>결제방법 선택</th>
						<td colspan="3"></td>
					</tr>
	                <tr>
						<td width="150px;">
		                    <input class="form-check-input" type="radio" name="go_order_type" id="go_order_type1" value="1" onchange="mname()">
		                    <label>무통장입금</label>
		                </td>
		                <td width="150px;">
		                    <input class="form-check-input" type="radio" name="go_order_type" id="go_order_type2" value="2" onchange="mname()">
		                    <label>계좌이체</label>
						</td>
						<td width="150px;">
		                    <input class="form-check-input" type="radio" name="go_order_type" id="go_order_type3" value="3" onchange="mname()">
		                    <label>카카오페이</label>
						</td>
					</tr>
					<tr id="go_order_type_view" style="display:none;">
						<th>입금자명</th>
						<td colspan="3">
		                    <input type="text" class="form-control" name="go_depositor" id="go_order_type_view" placeholder="입금자명을 입력해주세요.">
		            	</td>
					</tr>
                </table>
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="결제하기">
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
