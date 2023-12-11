<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	tr {
		height: 40px;
	}
	#table {
		font-size: 18px;
	}
	#boardSubject {
		font-size: 22px;
	}
	th {
		text-align: left;
		width: 40px;
	}
</style>

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
	        <h2 class="display-7 mb-4">공지사항</h2>
	        <hr class="my-3">
	        	<table id="boardSubject">
	        		<tr>
						<th>제목</th>
						<td width="150px;" style="font-weight: bold;, font-size: xx-large;">
							규정 및 정책은 이렇습니다.
		            	</td>
					</tr>
	        	</table>
	        	<table id="table">
					<tr>
						<th>작성자</th>
						<td width="150px;">
							문경훈
						</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td width="150px;">
							2023-12-07
						</td>
					</tr>
					<tr>
						<th>게시 분류</th>
						<td width="150px;">
							규정 및 정책
						</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td width="150px;">
							100
						</td>
					</tr>
					<tr></tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							내용은 이렇습니다.
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
                <!-- 댓글 등록 -->
                <hr>
                <table>
                	<tr>
						<th>댓글</th>
						<td width="150px;">
		                   	<textarea class="form-control" placeholder="Leave a message here" id="message" style="height: 100px"></textarea>    
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div class="d-grid gap-2 d-md-flex justify-content-right" >
								<a href="boardList"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">댓글 등록</button></a>
							</div>
						</td>
					</tr>
                </table>
                <hr>
                <!-- 댓글 화면 -->
                <table>
                	<tr>
                		<th></th>
	                	<td width="150px;">
	                		<label style="font-size: small;">작성자 : 문경훈   2023 - 12 - 06  11:36</label><p>
			                <label style="font-size: medium;">댓글내용입니다. <button type="button" style="background: white; border: none; color: orange;">x</button> </label>
			                
			            </td>
                	</tr>
                	<tr>
                		<th></th>
	                	<td width="150px;">
	                		<label style="font-size: small;">작성자 : 문경훈   2023 - 12 - 06  11:36</label><p>
			                <label style="font-size: medium;">댓글내용2 입니다. <button type="button" style="background: white; border: none; color: orange;">x</button></label>
			            </td>
                	</tr>
                </table>
                
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<a href="boardList"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록보기</button></a>
				</div>
                
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
