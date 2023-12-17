<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<div class="row g-0 mb-5  justify-content-center">
       <div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
           <div class="row g-3">
               <h2 class="display-7 mb-4">회원가입</h2>

               <hr class="my-3">
	        	<table class="formTable">
		            <tr>
						<th>이름</th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="b_title" name="b_title" value="${name }" readonly="readonly">
		            	</td>
					</tr>
		            <tr>
						<th>핸드폰 번호</th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="b_title" name="b_title" value="${phone }" readonly="readonly">
		            	</td>
					</tr>
		            <tr>
						<th>이메일 주소</th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="b_title" name="b_title" value="${email }" readonly="readonly">
		            	</td>
					</tr>
		            <tr>
						<th>아이디</th>
						<td colspan="2">
		                    <input type="text" class="form-control" id="b_title" name="b_title" >
		            	</td>
		            	<td>
		            	<input class="btn rounded py-2 px-3 mx-4" type="button" style="background: #263d94; color: white;" value="중복확인" onclick="duplicateChk()">
		            	</td>
					</tr>
		            <tr>
						<th>비밀번호</th>
						<td>
		                    <input type="text" class="form-control" id="b_title" name="b_title" >
		            	</td>
						<th style="padding-left: 30px;" >비밀번호 확인</th>
						<td>
		                    <input type="text" class="form-control" id="b_title" name="b_title" >
		            	</td>
		            </tr>
		            <tr>
						<th>생년월일</th>
						<td>
<!-- 						<td colspan="3"> -->
		                    <input type="date" class="form-control" id="b_title" name="b_title">
		            	</td>
		            	<th style="padding-left: 30px;">성별</th>
                        <td width="150px">
					        <div class="form-check form-check-inline">
					            <input class="form-check-input" type="radio" name="verification" id="phone" checked onclick="updateVerificationInput()">
					            <label class="form-check-label">남자</label>
					        </div>
					        <div class="form-check form-check-inline mx-4">
					            <input class="form-check-input" type="radio" name="verification" id="email" onclick="updateVerificationInput()">
					            <label class="form-check-label">여자</label>
					        </div>
					    </td>
					</tr>
		            <tr>
						<th>이메일 주소</th>
						<td colspan="3">
		                    <input type="email" class="form-control" id="b_title" name="b_title">
		            	</td>
					</tr>
		            <tr>
						<th>핸드폰 번호</th>
						<td colspan="3">
		                    <input type="email" class="form-control" id="b_title" name="b_title">
		            	</td>
					</tr>
		            <tr>
						<th>전화 번호</th>
						<td colspan="3">
		                    <input type="email" class="form-control" id="b_title" name="b_title">
		            	</td>
					</tr>
		            <tr>
						<th>회원구분</th>
						<td colspan="3">
							<select id="fruitSelect" class="form-select" name="category">
							    <option value="1">교육자</option>
							    <option value="2">학습자</option>
							    <option value="3">일반인</option>
							    <option value="4">운영자</option>
							</select>
		            	</td>
					</tr>
					<tr>
						<th>수신 동의</th>
						<td colspan="3">
						이벤트, 커리큘럼, 신규 콘텐츠 등 광고 메시지 수집 동의
		                <input class="form-check-input" type="checkbox" name="check_b_flag" id="check_b_flag" value="1">
						<label>이메일 </label>
		                <input class="form-check-input" type="checkbox" name="check_b_flag" id="check_b_flag" value="1">
						<label>SMS</label>
		                </td>
					</tr>
					 <!-- <tr>
                        <th>성별</th>
                        <td width="150px;">
                            <input class="form-check-input" type="radio" name="verification" id="phone" checked onclick="updateVerificationInput()">
                            <label>여자</label>
                        </td>
                        <td width="150px">   
                            <input class="form-check-input" type="radio" name="verification" id="email" onclick="updateVerificationInput()">
                            <label>남자</label>
                        </td>
                    </tr> -->
					<tr>
						<th>게시 구분</th>
							<td width="150px;">
			                    <select id="b_category" name="b_category" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
			                    	<option value="1">공지사항</option>
									<option value="2">Q&A</option>
									<option value="3">FAQ</option>
								</select>
			                </td>
						<th>게시 분류</th>
							<td width="150px;">
								<select id="b_notie_type" name="b_notie_type" class="w-17 rounded" style="margin-right: 110px; border-color: #ced4da">
									<option value="1">공통</option>
									<option value="2">이벤트</option>
									<option value="3">업데이트</option>
									<option value="4">규정 및 정책</option>
								</select>							
							</td>
					</tr>

                </table>
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<a href="/customer/boardList?b_category=${BoardCategory}"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="등록">
				</div>
                
			</div>
		</form>
	</div>
</div>
               
               
            </div>
        </div>
</div>

<%@ include file="../common/footerFo.jsp" %>
</body>
</html>