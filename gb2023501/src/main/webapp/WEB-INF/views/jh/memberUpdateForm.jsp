<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<script type="text/javascript">
	$(document).ready(function(){
		
        
     // 비밀번호 입력 시 유효성 검사 이벤트 핸들러
        $('#mmPswd').on('input', function () {
            validatePassword();
        });

        // 비밀번호 확인 입력 시 유효성 검사 이벤트 핸들러
        $('#mmPswdConfirm').on('input', function () {
            validatePassword();
        });


		//비밀번호 유효성 검증
        function validatePassword() {
            var mmPswd = $('#mmPswd').val();
            var mmPswdConfirm = $('#mmPswdConfirm').val();

            // 비밀번호가 비어 있을 때는 검증을 하지 않음
            if (mmPswd.length === 0) {
                $('#pswdValidationMessage').text('');
                return;
            }

            // 비밀번호 유효성 검사 정규식
            var regex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,10}$/;

            if (regex.test(mmPswd)) {
                // 비밀번호가 조건에 부합하는 경우
                $('#pswdValidationMessage').text('');

                // 사용자가 비밀번호 확인을 입력하기 시작했을 때만 비밀번호 일치 여부 체크
                // 중간에 비밀번호 바꿔도 비밀번호 확인과 불일치하면 경고 메세지 뜸
                if (mmPswdConfirm !== "") {
                    if (mmPswd === mmPswdConfirm) {
                        $('#pswdValidationMessage').text('');
                    } else {
                        $('#pswdValidationMessage').text('비밀번호가 일치하지 않습니다.');
                    }
                }
            } else {
                // 비밀번호가 조건에 부합하지 않는 경우
                $('#pswdValidationMessage').text('비밀번호는 8~10자의 영문 대/소문자, 숫자, 특수문자를 포함해야 합니다.');
            }
        }

        //이메일 형식 검사 이벤트 핸들러
            $('#emailInput').on('input', function () {
                validateEmail();
            });

        //이메일 형식 유효성 검사
        function validateEmail() {
            var emailInput = $('#emailInput');
            var emailValidationMessage = $('#emailValidationMessage');

            var email = emailInput.val();
            var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z]+\.[a-zA-Z]{2,3}$/;

            if (email.length === 0) {
            	emailValidationMessage.text('');
               return ;
            }
            
            if (emailRegex.test(email)) {
                // 올바른 이메일 형식
                emailValidationMessage.text('');
            } else {
                // 부적절한 이메일 형식
                emailValidationMessage.text('유효한 이메일 주소를 입력하세요.').css('color', 'red');
            }
        }


     // 휴대폰번호에 숫자만 입력되게 하는 이벤트 핸들러
        $('#phoneInput').on('input', function () {
            validatePhone('#phoneInput'); // ID를 직접 사용하여 호출
        });

        // 휴대폰 번호 유효성 검사
        function validatePhone(phoneInputId) {
            var phoneInput = $(phoneInputId).val(); // ID로 요소를 선택
            var validatePhone = phoneInput.replace(/\D/g, ''); // 숫자 이외의 문자 모두 제거

            if (phoneInput.length === 0) {
                $('#phoneValidationMessage').text('');
                return;
            }

            if (phoneInput !== validatePhone) {
                $('#phoneValidationMessage').text('숫자만 입력해주세요');
                // 문자 입력한 건 다 지워지고 숫자만 남김
                $(phoneInputId).val(validatePhone);
            } else {
                if (phoneInput.length < 11) {
                    $('#phoneValidationMessage').text('휴대폰 번호 11자리를 입력해주세요');
                } else {
                    $('#phoneValidationMessage').text('');
                }
            }
        }

        
        //전화번호에도 적용하기
        $('#telInput').on('input', function(){
        	validateTel('#telInput');
        	
        });
        
        //전화번호 유효성 검증
        function validateTel(pTelInput){
        	
        	var telInput = $(pTelInput).val();
        	var validateTel = telInput.replace(/\D/g, ''); // 숫자 이외의 문자 모두 제거
        	
        	if(telInput !== validateTel){
        		$('#telValidationMessage').text('숫자만 입력해주세요');
        		//문자 입력한 건 다 지워지고 숫자만 남김
        		$(pTelInput).val(validateTel);
        	} else{
        		$('#telValidationMessage').text('');
        	}
        }
        
	});	
	
	
	
	function isFormValid() {
	    var isValid = true;

	    // 각 필수 필드 확인
	    $("#memberUpdateForm [required]").each(function () {
	        if (!$(this).val()) {
	            isValid = false;
	            // 검증 오류 처리 (예: 메시지 표시)
	            alert("모든 필수 항목을 작성하세요.");
	            return false; // 루프를 일찍 종료
	        }
	    });

	    return isValid;
	}

	function myUpdate() {
		//alert("마이 정보 수정");
		// 폼 제출 전에 유효성 검사 수행
		if (!isFormValid()) {
		//alert("필수항목 누락 -> ");
        	return; // 필수 항목이 비어있으면 폼 제출 중단
		}
		 
        // 모든 검사가 통과되었는지 확인
 		if ($('#pswdValidationMessage').text() !== '' ||
 	        $('#emailValidationMessage').text() !== '' ||
 	        $('#phoneValidationMessage').text() !== '' ||
 	        $('#telValidationMessage').text() !== '' 
 	    ) {
 			alert('입력 정보를 확인해 주세요!')
 			return;
 		  }
		//alert("수정시작 -> ");

	    // 폼을 선택하고 action, method 설정 후 submit 메서드 호출
	    var form = document.getElementById("memberUpdateForm");
	    form.action = "/info/myUpdate"; // 수정된 부분
	    form.method = "post"; // 수정된 부분
	    form.submit();
		
	}
	function memberUpdate(){
	    var startDate = "${criteria.startDate == null ? '' : criteria.startDate}";
	    var endDate = "${criteria.endDate == null ? '' : criteria.endDate}";
	    var searchType = "${criteria.searchType}";
	    var searchValue = "${criteria.searchValue}";
	    var category = "${criteria.searchCategory}";
	    var mshipType = "${criteria.searchMshipType}";
	    var page = "${page}";
	    var mmNum = ${member.mmNum};
	    
		//alert("formData -> ");

		// 폼 제출 전에 유효성 검사 수행
		if (!isFormValid()) {
		//alert("필수항목 누락 -> ");
        	return; // 필수 항목이 비어있으면 폼 제출 중단
		}
		 
        // 모든 검사가 통과되었는지 확인
 		if ($('#pswdValidationMessage').text() !== '' ||
 	        $('#emailValidationMessage').text() !== '' ||
 	        $('#phoneValidationMessage').text() !== '' ||
 	        $('#telValidationMessage').text() !== '' 
 	    ) {
 			alert('입력 정보를 확인해 주세요!')
 			return;
 		  }
		//alert("수정시작 -> ");
		
		var form = document.getElementById("memberUpdateForm");
	    form.action = "/operate/memberUpdate"; // 수정된 부분
	    form.method = "post"; // 수정된 부분
	    form.submit();
	}

	
	function memberList() {
		//startDate 등 검색 x하고 상세페이지 올 경우 criteria.startDate가 null인경우 ''로 치환하기 위해 전부 ""로 감쌈
	    var startDate = "${criteria.startDate == null ? '' : criteria.startDate}";
	    var endDate = "${criteria.endDate == null ? '' : criteria.endDate}";
	    var searchType = "${criteria.searchType}";
	    var searchValue = "${criteria.searchValue}";
	    var category = "${criteria.searchCategory}";
	    var mshipType = "${criteria.searchMshipType}";
	    var page = "${page}";
	    //criteriaList = "${criteriaList}";
	
	   /*  alert("startDate " + startDate);
	    alert("endDate " + endDate);
	    alert("searchType " + searchType);
	    alert("searchValue " + searchValue);
	    alert("category " + category);
	    alert("mshipType " + mshipType);
	    alert("page " + page); */
	
	    if (
	        startDate === '' &&
	        endDate === '' &&
	        searchType === '' &&
	        searchValue === '' &&
	        category === '' &&
	        mshipType === ''
	    ) {
	       // alert("검색 무");
	        location.href = "/operate/memberList?page=" + page;
	    } else {
	       // alert("검색 유");
	        location.href = "/operate/SearchMemberList?startDate=" + startDate + "&endDate=" + endDate + "&searchType=" + searchType + "&searchValue=" + searchValue + "&searchCategory=" + category + "&searchMshipType=" + mshipType + "&page=" + page;
	    }
	}
	
</script>
</head>
<body>
<div class="row g-0 mb-5  justify-content-center">
       <div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
           <div class="row g-3">
               <h2 class="display-7 mb-4">회원정보 수정</h2>

               <hr class="my-3">
	               <form id="memberUpdateForm"  >
               <input type="hidden" id="mmNum" name="mmNum" value="${member.mmNum }">
               <input type="hidden" id="regiDate" name=regiDate value="${member.regiDate }">
               <input type="hidden" id="searchCriteria" name=searchCriteria value="${criteria}">
                <input type="hidden" id="page" name="page" value="${page }">
               <input type="hidden" id="startDate" name="startDate" value="${criteria.startDate }">
               <input type="hidden" id="endDate" name="endDate" value="${criteria.endDate }">
               <input type="hidden" id="searchType" name="searchType" value="${criteria.searchType }">
               <input type="hidden" id="searchValue" name="searchValue" value="${criteria.searchValue }"> 
               <input type="hidden" id="category" name="searchCategory" value="${criteria.searchCategory }">
               <input type="hidden" id="mshipType" name="searchMshipType" value="${criteria.searchMshipType }">
	        	<table class="formTable">
		            <tr>
						<th>이름</th>
						<td colspan="3">
						<input type="hidden" v class="form-control" id="mmName" name="mmName" value="${member.mmName }" required>
						
		                    ${member.mmName }
		            	</td>
					</tr>
		            <tr>
						<th>아이디</th>
						<td colspan="3">
						<input type="hidden" v class="form-control" id="mmId" name="mmId" value="${member.mmId }" required>
							${member.mmId }
						</td>
					</tr>
		            <tr id="phoneInputTr">
						<th>휴대폰 번호 <span style="color: red;">*</span></th>
						<td colspan="3">
		                    <input value="${member.phone}" type="tel" class="form-control" id="phoneInput" name="phone" placeholder="(-)없이 입력하세요"  maxlength="11" required>
		            	</td>
		            </tr>
		            <tr style="height: 5px;" id="phoneValidationMessageTr">
						<th>
						</th>
						<td colspan="3">
		                    <div id="phoneValidationMessage" style="color: red;"></div>
		                </td>
					</tr>
					<tr>
						<th>전화 번호</th>
						<td colspan="3">
		                    <input type="tel"  value="${member.tel}" class="form-control" id="telInput" name="tel" placeholder="(-)없이 입력하세요" maxlength="11">
		            	</td>
		            </tr>
					<tr>
						<c:choose>
							<c:when test="${my == 1 }">
								<th>회원구분</th>	
							</c:when>
							<c:otherwise>
								<th>회원구분 <span style="color: red;">*</span></th>
							</c:otherwise>
						</c:choose>
						<td   width="150px;">
							<c:choose>
								<c:when test="${my == 1 }">
									<c:if test="${member.category == 1 }">교육자<input type="hidden" value="${member.category}" id="category" class="form-control" name="category" ></c:if>
									<c:if test="${member.category == 2 }">학습자<input type="hidden" value="${member.category}" id="category" class="form-control" name="category" ></c:if>
									<c:if test="${member.category == 3 }">일반인<input type="hidden" value="${member.category}" id="category" class="form-control" name="category" ></c:if>
									<c:if test="${member.category == 4 }">운영자<input type="hidden" value="${member.category}" id="category" class="form-control" name="category" ></c:if>
								</c:when>
								<c:otherwise>
									<select id="categorySelect" class="form-select" name="category"> 
		                                <option value="1" <c:if test="${member.category == 1 }">	selected="selected"</c:if>>교육자</option> 
		                                <option value="2" <c:if test="${member.category == 2 }">	selected="selected"</c:if>>학습자</option> 
		                                <option value="3" <c:if test="${member.category == 3 }">	selected="selected"</c:if>>일반인</option> 
		                                <option value="4" <c:if test="${member.category == 4 }">	selected="selected"</c:if>>운영자</option> 
									</select> 
								</c:otherwise>
							</c:choose>
							
		            	</td>
						<th  style="padding-left: 40px;">회원자격 <span style="color: red;">*</span></th>
						<td  width="150px;">
		                    <select id="mshipType" class="form-select" name="mshipType">
                                <option value="1" <c:if test="${member.mshipType == 1 }">	selected="selected"</c:if>>무료회원</option>
                                <option value="2" <c:if test="${member.mshipType == 2 }">	selected="selected"</c:if>>유료회원</option>
							</select>
		            	</td>
					</tr>
		            <tr style="height: 5px;" id="telValidationMessageTr">
						<th>
						</th>
						<td colspan="3">
		                    <div id="telValidationMessage" style="color: red;"></div>
		                </td>
					</tr>
					<tr>
						<th>생년월일 <span style="color: red;">*</span></th>
						<td>
<!-- 						<td colspan="3"> -->
		                    <input value="${member.birth}" type="date" class="form-control" id="birth" name="birth" required>
		            	</td>
		            	<th style="padding-left: 40px;">성별 <span style="color: red;">*</span></th>
                        <td width="180px">
					        <c:choose>
					        	<c:when test="${member.gender == 1}">
							        <div class="form-check form-check-inline">
							            <input class="form-check-input" type="radio" name="gender" id="genderMan" value="1" checked>
							            <label class="form-check-label" for="genderWoman">남자</label>
							        </div>
							        <div class="form-check form-check-inline mx-4">
							            <input class="form-check-input" type="radio" name="gender" value="2" id="">
							            <label class="form-check-label" for="genderMan">여자</label>
							        </div>
					        	</c:when>
					        	<c:otherwise>
							        <div class="form-check form-check-inline">
							            <input class="form-check-input" type="radio" name="gender" id="genderMan" value="1" >
							            <label class="form-check-label" for="genderWoman">남자</label>
							        </div>
							        <div class="form-check form-check-inline mx-4">
							            <input class="form-check-input" type="radio" name="gender" value="2" id="genderWoman" checked>
							            <label class="form-check-label" for="genderMan">여자</label>
							        </div>
					        	
					        	</c:otherwise>
					        </c:choose>
					    </td>
					</tr>
		            
		            <tr id="emailInputTr">
						<th>이메일 <span style="color: red;">*</span></th>
						<td colspan="3">
		                    <input type="email" value="${member.email}" class="form-control" id="emailInput" name="email" required>
		            	</td>
					</tr>
					<tr style="height: 5px;" id="emailValidationMessageTr">
					    <th></th>
					    <td colspan="3">
					        <div id="emailValidationMessage" style="color: red;"></div>
					    </td>
					</tr>
					<c:choose>
						<c:when test="${member.mmId == pageContext.request.userPrincipal.name}">
				            <tr>
								<th>비밀번호 <span style="color: red;">*</span></th>
								<td  width="150px;">
				                    <input type="password" class="form-control" id="mmPswd" name="mmPswd" minlength="8" maxlength="10" required>
				            	</td>
								<th style="padding-left: 40px;" >비밀번호 확인 <span style="color: red;">*</span></th>
								<td  width="150px;">
				                    <input type="password" class="form-control" id="mmPswdConfirm" minlength="8" maxlength="10" required>
				            	</td>
				            </tr>
				            <tr style="height: 5px;" id="pswdValidationMessageTr">
								<th>
								</th>
								<td colspan="3">
				                    <div id="pswdValidationMessage" style="color: red;"></div>
				                </td>
							</tr>
						</c:when>
						<c:otherwise>
			               <input type="hidden" id="mmPswd" name="mmPswd" value="${member.mmPswd }">
						</c:otherwise>
					</c:choose>
		            
		            <tr>
						<th>주소</th>
						<td colspan="3">
		                    <input value="${member.address}" type="text" class="form-control" id="address" name="address">
		            	</td>
					</tr>

	
					<tr>
						<th>수신 동의</th>
						<td colspan="3">
						이벤트, 커리큘럼, 신규 콘텐츠 등 광고 메시지 수집 동의 <br>
			                <c:choose>
								<c:when test="${member.econsent == 1 }">
					                <input class="form-check-input" style="margin-left: 20px;" type="checkbox" name="econsent" id="econsent" value="1" checked >
									<label>이메일 </label>
								</c:when>
								<c:otherwise>
				                	<input class="form-check-input" style="margin-left: 20px;" type="checkbox" name="econsent" id="econsent" value="1" >
									<label>이메일 </label>
								</c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${member.sconsent ==  1 }">
					                <input class="form-check-input"  style="margin-left: 20px;" type="checkbox" name="sconsent" id="sconsent" value="1" checked >
									<label>SMS</label>
								</c:when>
								<c:otherwise>
					                <input class="form-check-input"  style="margin-left: 20px;" type="checkbox" name="sconsent" id="sconsent" value="1" >
									<label>SMS</label>
								</c:otherwise>
							</c:choose>
		                </td>
					</tr>


                </table>
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
                <c:choose>
                	<c:when test="${my == 1 }">
						<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="수정하기" onclick="myUpdate()">
                	</c:when>
                	<c:otherwise>
						<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="수정하기" onclick="memberUpdate()" >
						<input class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="목록" onclick="memberList()">
                	</c:otherwise>
                </c:choose>
				</div>
			</form>
                
			</div>
	</div>
</div>
               
               
            </div>
        </div>
</div>

<%@ include file="../common/footerFo.jsp" %>
</body>
</html>