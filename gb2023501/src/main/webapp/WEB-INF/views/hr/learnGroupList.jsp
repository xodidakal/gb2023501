<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
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
			
			location.href = "/educator/learnGroupList?sort="+sort+"&type="+type+"&keyword="+keyword;
		
		// keyword를 미포함하는 화면이면 (= 최초 진입 화면이면)
		// keyword와 type없이 이동하라
		} else {
			location.href = "/educator/learnGroupList?sort="+sort;
		}
	}
	
	// 삭제
	function clickDelete() {
		// 체크 수 = 0일 때
		if($('input[name="checkbox"]:checked').length == 0){
			alert("삭제할 학습그룹을 선택해주세요.");
			
		// 체크 수 = 0 아닐 때만 동작
		} else {
			// 가입 승인 인원이 0이 아닌 그룹이 있는지
			var emptyChk;
			
			$('input[name="checkbox"]:checked').each(function(){
				// index 정의
				var index = $(this).val();
				
				//alert("$('#mmCnt'+index).val() -> "+$('#mmCnt'+index).val());
				
				// 모든 그룹을 순차 확인
				// 0이면 emptyChk = 1
				if($('#mmCnt'+index).val() == 0) {
					emptyChk = 1;
					//alert("가입 승인 인원 0 -> 삭제 가능");
					
				// 한 그룹이라도 0이 아니면 emptyChk = 0
				} else {
					emptyChk = 0;
					//alert("가입 승인 인원 0 아님 -> 삭제 불가능");
				}
			})
			
			//alert("최종 emptyChk -> "+emptyChk);
			
			// 가입 승인 인원이 0인 그룹이 있을 때 (삭제 불가)
			if(emptyChk == 0) {
				alert("가입 승인 인원이 0인 그룹만 삭제 가능합니다.");
				
			// 가입 승인 인원이 0인 그룹이 있을 때 (삭제 가능)
			} else {
				if(confirm("삭제하시겠습니까?")){
					$('input[name="checkbox"]:checked').each(function(){
						// index 정의
						var index = $(this).val();
						
						$.ajax(
								{
									type : "DELETE",
									url : "/educator/learnGroupListDelete",
									data : {lg_num : $('#lg_num'+index).val()},
									dataType : 'text',
									success : function(data){
										if(data == "1"){
											alert("삭제 완료되었습니다.");
											location.reload();
										} else {
											alert("삭제 실패하였습니다. 다시 시도해주세요.");
											location.reload();
										}
									}
								}
						)
					})
				}
			}
		}
	}
</script>
</head>
<body>
<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<div class="mb-9">
	         <!-- heading -->
	         <h2 style="margin-bottom: 15px;">내 학습 그룹</h2>
	         <p style="margin-bottom: 35px;">총 ${size }건</p>
	         <c:if test="${keyword ne null and !empty keyword}">
		         <h6 style="margin-top: 35px;margin-bottom: 35px;">
		         	'
		         	<c:choose>
		         		<c:when test="${type eq 'typeLgTitle'}">학습그룹명</c:when>
		         		<c:when test="${type eq 'typeGgTitle'}">게임콘텐츠명</c:when>
		         	</c:choose>
		         	 : ${keyword }' 검색 결과
		         </h6>
	         </c:if>
	         
	    </div>

		<form action="/educator/learnGroupList">
			<div class="input-group col-md-5 mb-3"> 
				<!-- 정렬 -->
				<select id="sort" name="sort" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da"
						onchange="changeSort()">
					<option value="sortLgTitle" <c:if test="${sort eq 'sortLgTitle'}"> selected </c:if>>
						학습그룹명순
					</option>
					<option value="sortGgTitle" <c:if test="${sort eq 'sortGgTitle'}"> selected </c:if>>
						게임콘텐츠명순
					</option>
				</select>
				
				<!-- 검색 -->
				<select id="type" name="type" class="w-17 rounded" style="border-color: #ced4da">
					<option value="typeLgTitle">학습그룹명</option>
					<option value="typeGgTitle">게임콘텐츠명</option>
				</select>&nbsp;&nbsp;
	            <input id="keyword" name="keyword" class="form-control rounded" type="search" placeholder="내용을 입력해주세요." style="width: 160px;">
	          	<div style="margin-left: 10px; width: 65px; margin-top: 6px;">
	         		<button class="btn bi bi-search rounded"></button>
	          	</div>

				<div class="col">
				<div class="d-flex align-items-center justify-content-end">
	          		<div style="width: 65px;">
		          		<%-- <a href="/educator/learnGroupListDelete?lg_num=${lgDto.learnGrp.lgNum }"> --%>
		          			<input type="button" class="btn rounded py-2 px-3" style="background: #263d94; color: white;"
		          				   value="삭제" onclick="clickDelete()" >
		          		<!-- </a> -->
	            	</div>
	            </div>
				</div>
		    </div>
	    </form>
       	<table class="listTable">
       		<thead>
				<tr>
					<th>선택</th>
					<th>No.</th>
					<th>학습그룹명</th>
					<th>게임콘텐츠명</th>
					<th>학습 기간</th>
					<th>수용 가능 인원</th>
					<th>가입 승인 인원</th>	
					<th width="100px;"></th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty learnGrps}">
						<tr>
							<td colspan="8">내 학습 그룹이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
					 	<c:forEach var="lgDto" items="${learnGrps }" varStatus="status">
					 		<c:set var="i" value="${i+1 }"></c:set>
						 	<tr>
						 		<td>
						 			<input class="form-check-input" type="checkbox" name="checkbox" id="checkbox${status.index }" value="${status.index }" >
						 			<input type="hidden" id="lg_num${status.index }" value="${lgDto.learnGrp.lgNum}" >
						 			<input type="hidden" id="mmCnt${status.index }" value="${lgDto.mmCnt }" >
						 		</td>
								<td>${i }</td>
								<td>${lgDto.learnGrp.lgTitle}</td>
								<td>${lgDto.learnGrp.game.ggTitle}</td>
								<td>${lgDto.learnGrp.lgSdate } ~ ${lgDto.learnGrp.lgEdate }</td>
								<td>${lgDto.learnGrp.lgTo }명</td>
								<td>${lgDto.mmCnt }명</td>
								<td width="100px;">
									<a href="/educator/learnGroupDetail?lg_num=${lgDto.learnGrp.lgNum }">
										<button type="button" class="btn btn-light rounded py-2 px-3" style="background: #263d94; color: white;">
											상세
										</button>
									</a>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>   
       	</table>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>