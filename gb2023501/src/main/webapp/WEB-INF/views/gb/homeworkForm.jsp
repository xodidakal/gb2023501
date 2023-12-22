<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#hwForm {
		border: 1px solid black;
		margin-bottom : 15px;
		padding: 4% 16%;
	}
	#hwFormtd{
		color: black;
		font-weight : bold;
	}
	.form-control {
		margin-left: 20px; 
		width: 300%;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">

	$(function(){
		var result = '${result}';
		
		if(result != ""){
			alert("정상적으로 처리되었습니다.");
		}
		
		// 제출기한은 오늘날짜 이전은 선택되지 않도록 오늘날짜를 불러온다.
		
		let today = new Date();						// Mon Dec 22 2023 11:03:22 GMT+0900 (한국 표준시)

		console.log(today.toISOString()); 			// 2023-08-07T02:08:44.315Z

		today = today.toISOString().substring(0,10)	// 2023-08-07

		$('#h_deadline').attr("min", today);
	});

	// 숙제 목록에서 행 클릭 시 입력 태그로 값 가져가기
	function homeworkUpdate(pIndex){
		$("#h_title").focus();
		
		var h_num = $('#h_num'+pIndex).val();				// 숙제번호
		var h_title = $('#h_title'+pIndex).val();			// 숙제명
		var h_content = $('#h_content'+pIndex).val();		// 숙제내용
		var h_level = $('#h_level'+pIndex).val();			// 숙제진도
		var h_deadline = $('#h_deadline'+pIndex).val();		// 제출기한
		
		$('#h_num').val(h_num);
		$('#h_title').val(h_title);
		$('#h_content').val(h_content);
		$('#h_level').val(h_level);
		$('#h_deadline').val(h_deadline);
	}
	
	
	
</script>
</head>
<body>
<!-- 	<div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;"> -->
<!--         <h6 class="section-title bg-white text-center text-primary px-3">Services</h6> -->
<!--         <h2 class="display-6 mb-4">교육 자료 등록</h2> -->
<!--     </div> -->
<div class="row g-0 justify-content-center">
	<div class="col-lg-11 wow fadeInUp" data-wow-delay="0.5s">
		<!-- 숙제 입력 폼 -->
		<form action="homeworkInsertUdpate" method="post">
			<div class="mb-9">
		         <!-- heading -->
		         <h2 style="margin-bottom: 15px;">숙제 생성</h2>
		         <div id="hwForm">
		         	<input type="hidden" readonly="readonly" class="form-control" id="h_num" name="h_num" value=0>
		         	<table>
		         		<tr>
		         			<td id="hwFormtd">숙제명</td>
		         			<td><input type="text" class="form-control" id="h_title" name="h_title" required="required"></td>
		         		</tr>
		         		<tr>
		         			<td id="hwFormtd">숙제 내용</td>
		         			<td><textarea type="text" class="form-control" cols="20" rows="5" id="h_content" name="h_content" required="required"></textarea></td>
		         		</tr>
		         		<tr>
		         			<td id="hwFormtd">숙제 진도</td>
		         			<td><input type="number" class="form-control" id="h_level" name="h_level" required="required"></td>
		         		</tr>
		         		<tr>
		         			<td id="hwFormtd">제출 기한</td>
		         			<td><input type="date" class="form-control" id="h_deadline" name="h_deadline" required="required"></td>
		         		</tr>
		         	</table>
		         </div>
		    </div>
	
			<div class="input-group col-md-5 mb-3"> 
				<div class="col">
					<div class="d-flex align-items-center justify-content-end">
		          		<div>
			          		<button class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;">숙제 저장</button>
		            	</div>
		            	<div>
			          		<button class="btn rounded py-2 px-3" type="reset" style="background: #808080; color: white; margin-left:20px;">초기화</button>
		            	</div>
		            </div>
		            <p style="margin-bottom:-5px;">총 <fmt:formatNumber value="${homeworkListCnt}" groupingUsed="true"/> 건</p>
				</div>
		    </div>
	    </form>
	    
	    <!-- 생성한 숙제 목록 -->
        	<table class="listTable">
        		<thead>
					<tr>
						<th>No.</th>
						<th style="width: 25%;">숙제명</th>
						<th style="width: 45%;">숙제내용</th>
						<th>진도</th>
						<th>제출기한</th>
						<th>생성일자</th>		
					</tr>
				</thead>
				<tbody>
					<c:forEach var="homework" items="${homeworkList }" varStatus="status">
					 	<tr onmouseover="this.style.background='#BFE4FF'; this.style.cursor='pointer'" 
					 		onmouseout="this.style.backgroundColor=''"
					 		onclick="homeworkUpdate(${status.index })">
					 		
					 		<td>
					 			<input type="hidden" id="h_num${status.index }" value="${homework.h_num }">${StartRow }
					 		</td>
							<td>
								<input type="hidden" id="h_title${status.index }" value="${homework.h_title }"> ${homework.h_title }
							</td>
							<td>
								<input type="hidden" id="h_content${status.index }" value="${homework.h_content }">${homework.h_content }
							</td>
							<td>
								<input type="hidden" id="h_level${status.index }" value="${homework.h_level }">${homework.h_level }
							</td>
							<td>
								<input type="hidden" id="h_deadline${status.index }" value="${homework.h_deadline }">${homework.h_deadline }
							</td>
							<td>
								<input type="hidden" id="h_regi_date${status.index }" value="${homework.h_regi_date }">${homework.h_regi_date }
							</td>
						</tr>
						<c:set var="StartRow" value="${StartRow +1}"/>
					</c:forEach>
                </tbody>   
               </table>
               
               <!-- 페이징 처리 -->
               <div class="row mt-8" style="width:100%;">
 					<div class="d-flex justify-content-center" style="margin-top:12px">
		                <nav aria-label="Page navigation example">
						  <ul class="pagination">
						  	<c:if test="${page.startPage > page.pageLimit}">
						  		<li class="page-item"><a class="page-link" href="homeworkForm?currentPage=${page.startPage-page.pageLimit}">이전</a></li>
						  	</c:if>
						    <c:forEach var="i" begin="${page.startPage }" end="${page.endPage }">
						    	<li class="page-item"><a class="page-link" href="homeworkForm?currentPage=${i }">${i }</a></li>
						    </c:forEach>
						 	<c:if test="${page.endPage < page.totalPage}">
						 		<li class="page-item"><a class="page-link" href="homeworkForm?currentPage=${page.startPage+page.pageLimit}">다음</a></li>
						 	</c:if>
						  </ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
