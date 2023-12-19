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
	
	// 댓글 날짜 표시 = 2023-11-09
	function formatDate(d) {
		if(d.indexOf('T') != -1) {
			d = d.substring(0, d.indexOf('T'));
		}
		return d;
	}
	
	//답변작성 작성일 오늘날짜 표시
	function getTodayDate() {
	    var today = new Date();
	    var year = today.getFullYear();
	    var month = today.getMonth() + 1; // 월은 0부터 시작하므로 +1
	    var day = today.getDate();
	
	    // 날짜를 'YYYY-MM-DD'
	    return year + '-' + (month < 10 ? '0' : '') + month + '-' + (day < 10 ? '0' : '') + day;
	    
	}
	
	/* function answerWrite() {
		alert("클릭");
		document.answerWriteForm.submit();
	} */
	
	// 답변작성 오늘날짜 표시
	$(function() {
		var b_regi_date = $('b_regi_date').val();
	    $('input[name=b_regi_date]').val(getTodayDate);
	    
	   /*  $("#answerWrite").on('click',function() {
			document.answerWriteForm.submit();
		}); */
	});
	
	// 댓글 등록
	function insertComment() {
		
		var params = {};
		params.b_num = "${BdDetail.b_num}";
		params.m_num = "${member.mmNum}";
		params.bc_content = $('textarea[name=bc_content]').val();

		alert("bc_content->"+params.bc_content);
		$.ajax({
			url			: "insertComment",
			type		: 'POST',
			contentType : 'application/json; charset:utf-8',
			data		: JSON.stringify(params),
			dataType	: 'text',
			success		: function(data) {
				if(data == "success") {
					showCommentList();
					$('textarea[name=bc_content]').val("");
				} else {
					alert("댓글 등록 실패");
				}
			},
			error : function(XHR, textStatus, errorThrown) {
				// http 오류 번호를 반환하며 케이스별 오류 메시지 판정에 사용하면 유용
				console.log(XHR.status );
				// url의 full response를 반환하기 때문에 ajax 오류 디버깅 시에 상당한 도움
				alert(XHR.responseText );
			}
		});
	}

	// 댓글 목록 조회
	function showCommentList() {
		var params = {};
		params.b_num = "${BdDetail.b_num}";
		
		$.ajax({
			url			: 'selectCommentList',
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
	}
	
	// 댓글 목록 그려주기
	function drawCommentList(comments) {
		$("#divCommentList").empty();
		if(comments.length == 0) {
			alert("댓글 없음");
			$("#divCommentList").html("");
			$("#divCommentList").hide();
		} else {
			$(comments).each(function(index, comment) {
				var list = '<table id=formTable>';
				list += '<tr>';
				list += '<th></th>';
				list += '<td width="410px;">';
				list += '<label style="font-size: small;">작성자 : '+ comment.m_name +'&nbsp;&nbsp;&nbsp; '+ formatDate(comment.bc_regi_date) +' </label><p>';
				list += '<label style="font-size: medium;">'+ comment.bc_content
				// 로그인 num과 일치하면 x버튼 생성
				if(comment.m_num == "${member.mmNum}") {
					list += '<button type="button" style="background: white; border: none; color: orange;" onclick="deleteComment(\''+comment.bc_num+'\')">x</button> </label>';
				}
				list += '</td>';
				list += '</tr>';
				list += '</table>';
				list += '</tr>';
				$("#divCommentList").append(list);
				$("#divCommentList").show();
			});
		}
	}
	
	// 댓글 삭제
	function deleteComment(bc_num) {
		var params = {};
		params.m_num = "${member.mmNum}";
		params.b_num = "${BdDetail.b_num}";
		params.bc_num = bc_num;
		
		$.ajax({
			url			: 'deleteComment',
			type		: 'POST',
			contentType : 'application/json; charset:utf-8',
			data		: JSON.stringify(params),
			dataType	: 'text',
			success		: function(data) {
				if(data == "success") {
					showCommentList();
				} else {
					alert("댓글 삭제 실패했습니다.");
				}
			},
			error : function(XHR, textStatus, errorThrown) {
				// http 오류 번호를 반환하며 케이스별 오류 메시지 판정에 사용하면 유용
				console.log( XHR.status );
				// url의 full response를 반환하기 때문에 ajax 오류 디버깅 시에 상당한 도움
				alert( jqXHR.responseText );
			}
		});
	}
	
	// 게시글 들어가면 댓글 목록 조회 실행
	$(document).ready(function() {		
		
		showCommentList();

	});
	
	// 게시글 삭제 질문
	function deleteQuestion() {
        if (!confirm("정말 삭제 하시겠습니까?")) {
        } else {
            window.location.href="/customer/boardDelete?b_num=${BdDetail.b_num}&b_category=${BdDetail.b_category}";
        }
    }
	
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
		<form action="/customer/insertBoard" method="post" enctype="multipart/form-data">
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
               	<c:if test="${BdDetail.b_category eq '1'}">
               		<!-- 댓글 등록 -->
	                <hr>
	                <table id=formTable>
	                	<tr>
							<th>댓글</th>
							<td width="410px;">
			                   	<textarea class="form-control" placeholder="Leave a message here" id="bc_content" name="bc_content" style="height: 100px"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div class="d-grid gap-2 d-md-flex justify-content-right" >
									<button class="btn rounded py-2 px-3" type="button" onclick="insertComment()" style="background: #263d94; color: white;">댓글 등록</button>
								</div>
							</td>
						</tr>
	                </table>
	                
	                <!-- 댓글 화면 -->
	                <hr>
	                <div id="divCommentList"></div>
	                
	                <!-- 댓글번호 -->
                	<c:forEach var="cList" items="${commentList}">
		                		<input type=hidden value="${cList.bc_num}">
                	</c:forEach>
               	</c:if>
                	
                	<%-- <c:when test="${BdDetail.b_ref_num eq BdDetail.b_num}"></c:when> --%>
                	
                	
                
               	<!-- Q&A와 FAQ이면서 운영자이고 [답변]이 포함 되어있지 않으면 답변 가능 -->
               	<c:if test="${(BdDetail.b_category eq '2' or BdDetail.b_category eq '3') and member.category eq 4 and not BdDetail.b_title.contains('[답변]')}">
               		<hr>
	                
	                <h4 class="display-7 mb-4">답변 작성</h4>
	                
	                <table id="formTable">
	                	<tr>
	                		<td>
	                			<input type="hidden" name="m_num" 			value="${member.mmNum}">
	                			<input type="hidden" name="b_category" 		value="${BdDetail.b_category}">
	                			<input type="hidden" name="b_notie_type" 	value="${BdDetail.b_notie_type}">
	                			<input type="hidden" name="b_flag" 			value="1">
	                			<input type="hidden" name="b_ref_num" 		value="${BdDetail.b_num}">
	                			
	                		</td>
	                	</tr>
						<tr>	
							<th>작성일</th>
							<td width="410px;">
								<input class="form-control" id="b_regi_date" name="b_regi_date" type="text" readonly="readonly">
							</td>
						</tr>
	                	<tr>
							<th>제목</th>
							<td colspan="3">
			                    <input type="text" class="form-control" id="subject" placeholder="Subject" value="[답변] " name="b_title">
			            	</td>
						</tr>
						<tr></tr>
						<tr>
							<th>내용</th>
							<td colspan="3">
			                   	<textarea class="form-control" placeholder="Leave a message here" id="message" name="b_content" style="height: 200px"></textarea>    
							</td>
						</tr>
						<tr></tr>
						<tr>
		                	<th>첨부파일</th>
		                	<td colspan="3">
		                		<label style="font-size: medium;">파일 1개당 최대 첨부 용량 30MB</label>
			                    <input type="file" name="file1" class="form-control" id="subject" placeholder="Subject">
			                </td>
		                </tr>
	                </table>
               	</c:if>
                	<!-- Q&A와 FAQ이면서 운영자이고 [답변]이 포함 되어있으면 답변 수정 가능 -->
               	<c:if test="${(BdDetail.b_category eq '2' or BdDetail.b_category eq '3') and member.category eq 4 and BdDetail.b_title.contains('[답변]')}">
               		<hr>
	                
	                <h4 class="display-7 mb-4">답변 수정</h4>
	                
	                <table id="formTable">
	                	<tr>
	                		<td>
	                			<input type="hidden" name="m_num" 			value="${member.mmNum}">
	                			<input type="hidden" name="b_category" 		value="${BdDetail.b_category}">
	                			<input type="hidden" name="b_notie_type" 	value="${BdDetail.b_notie_type}">
	                			<input type="hidden" name="b_flag" 			value="1">
	                			<input type="hidden" name="b_ref_num" 		value="${BdDetail.b_num}">
	                			
	                		</td>
	                	</tr>
						<tr>	
							<th>작성일</th>
							<td width="410px;">
								<input class="form-control" id="b_regi_date" name="b_regi_date" type="text" readonly="readonly">
							</td>
						</tr>
	                	<tr>
							<th>제목</th>
							<td colspan="3">
			                    <input type="text" class="form-control" id="subject" placeholder="Subject" value="[답변] " name="b_title">
			            	</td>
						</tr>
						<tr></tr>
						<tr>
							<th>내용</th>
							<td colspan="3">
			                   	<textarea class="form-control" placeholder="Leave a message here" id="message" name="b_content" style="height: 200px"></textarea>    
							</td>
						</tr>
						<tr></tr>
						<tr>
		                	<th>첨부파일</th>
		                	<td colspan="3">
		                		<label style="font-size: medium;">파일 1개당 최대 첨부 용량 30MB</label>
			                    <input type="file" name="file1" class="form-control" id="subject" placeholder="Subject">
			                </td>
		                </tr>
	                </table>
               	</c:if>
                
				<c:if test="${(BdDetail.b_category eq '2' or BdDetail.b_category eq '3') and member.category eq 4 and not BdDetail.b_title.contains('[답변]')}">
	                	
                </c:if>
                
                <!-- 수정, 삭제, 목록 버튼 -->
                <%-- <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<a href="boardList?b_category=${BdDetail.b_category}"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
					<!-- 운영자만 삭제, 수정버튼 o -->
					<c:if test="${BdDetail.m_category eq '4'}">
						<a href="boardList"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">삭제</button></a>
						<a href="/customer/boardUpdate?b_num=${BdDetail.b_num}"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">수정</button></a>					
					</c:if>
					<!-- Q&A면 모든회원 삭제, 수정버튼 o -->
					<c:choose>
						<c:when test="${BdDetail.b_category == 2}">
							<a href="/customer/boardDelete?b_num=${BdDetail.b_num}&b_category=${BdDetail.b_category}"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">삭제</button></a>
							<a href="/customer/boardUpdate?b_num=${BdDetail.b_num}"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">수정</button></a>
						</c:when>
					</c:choose>
                	<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="작성">
				</div> --%>
				
				<div class="d-grid gap-2 d-md-flex justify-content-center" >
					<a href="boardList?b_category=${BdDetail.b_category}"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
					<!-- 회원정보가 같거나 운영자면 버튼 노출 -->
					<c:if test="${member.mmNum == BdDetail.m_num or member.category == 4}">
						<a onclick="deleteQuestion()"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">삭제</button></a>
						<a href="/customer/boardUpdate?b_num=${BdDetail.b_num}"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">수정</button></a>					
                	</c:if>
                	<!-- Q&A or FAQ이면서 운영자면 작성 버튼 노출 -->
                	<c:if test="${(BdDetail.b_category eq '2' or BdDetail.b_category eq '3') and member.category eq 4}">
                		<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="작성">
                	</c:if>
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
