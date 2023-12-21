<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	// 잔여 기간 및 인원 소진 건 disable
	$(function(){
		$('input[name="g_num"]').each(function(){			
			// index 정의
			var index = $(this).attr('id');
			
			if($('#remainingPeriod'+index).val() <= 0 || $('#remainingTo'+index).val() <= 0){
				$(this).prop('disabled', true);
			}
		})
	})
	
	// 정렬기준 변경
	function changeSort() {
		var sort = $('#sort').val();
		var type;
		var keyword;
		
		// keyword를 포함하는 화면이면 (= 검색을 통해 이동한 화면이면)
		// 해당 keyword와 type을 계속 갖고 이동하라
		if('${keyword}' != null){
			var keyword = '${keyword}';
			var type    = '${type}';
			
			location.href = "/educator/learnGroupForm1?sort="+sort+"&type="+type+"&keyword="+keyword;
		
		// keyword를 미포함하는 화면이면 (= 최초 진입 화면이면)
		// keyword와 type없이 이동하라
		} else {
			location.href = "/educator/learnGroupForm1?sort="+sort;
		}
	}
</script>
</head>
<body>
<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">학습 그룹 등록</h2>
	         <h5 style="margin-top: 35px;margin-bottom: 35px;">콘텐츠 선택</h5>
	         <c:if test="${keyword ne null and !empty keyword}">
		         <h6 style="margin-top: 35px;margin-bottom: 35px;">
		         	'
		         	<c:choose>
		         		<c:when test="${type eq 'typeGgTitle'}">게임콘텐츠명</c:when>
		         	</c:choose>
		         	 : ${keyword }' 검색 결과
		         </h6>
	         </c:if>
	    </div>

		<form action="/educator/learnGroupForm1">
			<div class="input-group col-md-5 mb-3"> 
				<!-- 정렬 -->
				<select id="sort" name="sort" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da"
						onchange="changeSort()">
					<option value="sortGgTitle" <c:if test="${sort eq 'sortGgTitle'}"> selected </c:if>>
						게임콘텐츠명순
					</option>
					<option value="remainingPeriod" <c:if test="${sort eq 'remainingPeriod'}"> selected </c:if>>
						잔여기간순
					</option>
					<option value="remainingTo" <c:if test="${sort eq 'remainingTo'}"> selected </c:if>>
						잔여인원순
					</option>
				</select>
				<!-- 검색 -->
				<select id="type" name="type" class="w-17 rounded" style="border-color: #ced4da">
					<option value="typeGgTitle">게임콘텐츠명</option>
				</select>&nbsp;&nbsp;
	            <input id="keyword" name="keyword" class="form-control rounded" type="search" placeholder="내용을 입력해주세요." style="width: 160px;">
	          	<div style="margin-left: 10px; width: 65px; margin-top: 6px;">
	         		<button class="btn bi bi-search rounded"></button>
	          	</div>
		    </div>
	    </form>
	    
	    <form action="/educator/learnGroupForm2">
	       	<table class="listTable">
	       		<thead>
					<tr>
						<th>선택</th>
						<th>게임콘텐츠명</th>
						<th>구독 기간</th>
						<th>구독 잔여 기간</th>
						<th>학습 가능 인원</th>
						<th>학습 잔여 인원</th>	
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="">
							<tr>
								<td colspan="6">게임콘텐츠가 없습니다.</td>
							</tr>
							<tr>
								<td colspan="6">구독 및 결제를 먼저 진행해주세요.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="gameList" items="${gameList }"  varStatus="status">
							 	<tr>
							 		<td>
							 			<input class="form-check-input" type="radio" name="g_num" id="${status.index }" value="${gameList.g_num }" required>
							 			<input type="hidden" id="remainingPeriod${status.index }" value="${gameList.remainingPeriod}" >
							 			<input type="hidden" id="remainingTo${status.index }" value="${gameList.remainingTo}" >
							 		</td>
									<td>${gameList.g_title}</td>
									<td>${gameList.g_period}개월</td>
									<td>${gameList.remainingPeriod}개월</td>
									<td>${gameList.g_to}명</td>
									<td>${gameList.remainingTo}명</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>   
	       	</table>
	       	
	       	<div class="d-grid gap-2 d-md-flex justify-content-center" >
	       		<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="컨텐츠 선택">
	       	</div>
       	</form>
              
              
              
              <!-- 페이지네이션 -->
              <!-- <div class="row mt-8" style="width:100%;">
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
              </div> -->
              
             
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
