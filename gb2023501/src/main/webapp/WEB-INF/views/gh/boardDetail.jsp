<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--CSS START -->
<style type="text/css">
	tr {
		height: 70px;
	}
	th {
		text-align: left;
		width: 130px;
	}
</style>
<!--CSS END -->

<!-- JS START -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

// 	댓글 등록
	/* function insertComment() {
		var params = {};
		params.b_num = "${BdDetail.b_num}";
		params.m_num = 1;
	//	params.m_num = "${member.m_num}";
		params.bc_content = $('textarea[name=bc_content]').val();
		
		$.ajax({
			url			: "insertBdComment",
			type		: 'POST',
			contentType : 'application/json; charset:utf-8',
			data		: JSON.stringify(params),
			dataType	: 'text',
			success		: function(data) {
				if(data == "success") {
					showCommentList();
				} else {
					alert("댓글 등록 실패");
				}
			},
			error : function(XHR, textStatus, errorThrown) {
				// http 오류 번호를 반환하며 케이스별 오류 메시지 판정에 사용하면 유용
				console.log( XHR.status );
				// url의 full response를 반환하기 때문에 ajax 오류 디버깅 시에 상당한 도움
				alert( jqXHR.responseText );
			}
		});
	} */

// 댓글 목록 조회
	/* function showCommentList() {
		var params = {};
		params.b_num = "${BdDetail.b_num}";
		
		$.ajax({
			url			: 'selectBdCommentList',
			type		: 'POST',
			contentType : 'application/json; charset:utf-8',
			data		: JSON.stringify(params),
			dataType	: 'json',
			success		: function(data) {
				drawCommentList(data);
			},
			error : function(XHR, textStatus, errorThrown) {
				// http 오류 번호를 반환하며 케이스별 오류 메시지 판정에 사용하면 유용
				console.log( XHR.status );
				// url의 full response를 반환하기 때문에 ajax 오류 디버깅 시에 상당한 도움
				alert( jqXHR.responseText );
			}
		});
	} */
</script>

<!-- JS END -->

</head>
<body>
<!-- 	<div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;"> -->
<!--         <h6 class="section-title bg-white text-center text-primary px-3">Services</h6> -->
<!--         <h2 class="display-6 mb-4">교육 자료 등록</h2> -->
<!--     </div> -->
<div class="row g-0 justify-content-center">
	<div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
		<form>
	        <div class="row g-3">
	        <c:choose>
			 	<c:when test="${BdDetail.b_category == 1}"><h2 class="display-7 mb-4">공지사항</h2></c:when>
			 	<c:when test="${BdDetail.b_category == 2}"><h2 class="display-7 mb-4">Q&A</h2></c:when>
	 			<c:otherwise><h2 class="display-7 mb-4">FAQ</h2></c:otherwise>
			</c:choose>
	        
	        <hr class="my-3">
	        	
	        	<table class="formTable">
					<tr>
						<th>게시 구분</th>
						<td width="150px;">
							<c:choose>
							 	<c:when test="${BdDetail.b_category == 1}"><label style="margin-right: 110px;">공지사항</label></c:when>
							 	<c:when test="${BdDetail.b_category == 2}"><label style="margin-right: 110px;">Q&A</label></c:when>
					 			<c:otherwise><label style="margin-right: 110px;">FAQ</label></c:otherwise>
							</c:choose>
						</td>
						
						<th>게시 분류</th>
						<td width="150px;">
							<c:choose>
					 			<c:when test="${BdDetail.b_notie_type == 1}"><label style="margin-right: 110px;">공통</label></c:when>
					 			<c:when test="${BdDetail.b_notie_type == 2}"><label style="margin-right: 110px;">이벤트</label></c:when>
					 			<c:when test="${BdDetail.b_notie_type == 3}"><label style="margin-right: 110px;">업데이트</label></c:when>
					 			<c:otherwise>규정 및 정책</c:otherwise>
					 		</c:choose>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3">
							<label>${BdDetail.b_title}</label>
						</td>
					</tr>
					<tr>
						<th>게시 일자</th>
						<td width="150px;">
							<label>
								<fmt:formatDate value="${BdDetail.b_regi_date}" type="date" pattern="yyyy-MM-dd"/>
							</label>
						</td>
					</tr>
					<tr></tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<label>${BdDetail.b_content}</label>
						</td>
					</tr>
					<tr></tr>
	                <tr>
	                	<th>첨부파일</th>
		                <%-- <c:if test="${BdDetail.b_attach_path ne null}">
		                	<td colspan="3">
		                		<label><a href="javascript:popup('/upload/${BdDetail.b_attach_path}',800,600)">${BdDetail.b_attach_name}</a></label>
			                </td>
						</c:if> --%>
						<td colspan="3">
	                		<label><a href="/upload/gh/${BdDetail.b_attach_name}" download="test">${BdDetail.b_attach_name}</a></label>
		                </td>
	                </tr>
                </table>
                <!-- 공지사항이면 댓글화면 -->
                <c:choose>
                	<c:when test="${BdDetail.b_category eq '1'}">
                		<!-- 댓글 등록 -->
		                <hr>
		                <table id=formTable>
		                	<tr>
								<th>댓글</th>
								<td width="410px;">
				                   	<textarea class="form-control" placeholder="Leave a message here" id="message" name="bc_content" style="height: 100px"></textarea>    
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<div class="d-grid gap-2 d-md-flex justify-content-right" >
										<a href="boardList"><button class="btn rounded py-2 px-3" type="button" onclick="insertComment()" style="background: #263d94; color: white;">댓글 등록</button></a>
									</div>
								</td>
							</tr>
		                </table>
		                
		                <!-- 댓글 화면 -->
		                <hr>
		                <table>
		                	<c:forEach var="cList" items="${commentList}">
			                	<tr>
			                	<th></th>
			                		<td width="410px;">
				                		<label style="font-size: small;">작성자 : ${cList.m_name}&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${cList.bc_regi_date}" type="date" pattern="yyyy-MM-dd"/></label><p>
						                <label style="font-size: medium;">${cList.bc_content} <button type="button" style="background: white; border: none; color: orange;">x</button> </label>
						            </td>
			                	</tr>
		                	</c:forEach>
		                </table>
                	</c:when>
                	
                	<c:otherwise>
                	<!-- 게시판이 나머지면 답변 등록 -->
	                	<hr>
		                
		                <h4 class="display-7 mb-4">답변 작성</h4>
		                
		                <table id="formTable">
							<tr>
								<th>작성일</th>
								<td width="410px;">
									2023-12-08
								</td>
							</tr>
		                	<tr>
								<th>제목</th>
								<td colspan="3">
				                    <input type="text" class="form-control" id="subject" placeholder="Subject" value="[답변] ">
				            	</td>
							</tr>
							<tr></tr>
							<tr>
								<th>내용</th>
								<td colspan="3">
				                   	<textarea class="form-control" placeholder="Leave a message here" id="message" style="height: 200px"></textarea>    
								</td>
							</tr>
							<tr></tr>
							<tr>
			                	<th>첨부파일</th>
			                	<td colspan="3">
			                		<label style="font-size: medium;">파일 1개당 최대 첨부 용량 30MB</label>
				                    <input type="file" class="form-control" id="subject" placeholder="Subject">
				                </td>
			                
			                </tr>
		                </table>
                	</c:otherwise>
                </c:choose>
                
                <!-- 수정, 삭제, 목록 버튼 -->
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<a href="boardList?b_category=${BdDetail.b_category}"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
					<c:if test="${BdDetail.m_category eq '4'}"></c:if>
						<a href="boardList"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">삭제</button></a>
						<a href="/customer/boardUpdate?b_num=${BdDetail.b_num}"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">수정</button></a>
					
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
