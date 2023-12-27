<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript">
	$(document).ready(function(){
		
		
		//인증방식(이메일, 휴대폰) 따라 input태그 토글(두 가지 상태 간의 전환) 
		toggleFields();
		function toggleFields(){
			// 각 입력 필드에 대한 jQuery 객체 가져오기
			var email 		 = $('#email').val();
			var phone 		 = $('#phone').val();
			var emailTr 	 = $('#emailTr');
			var phoneTr 	 = $('#phoneTr');
			var emailInputTr = $('#emailInputTr');
            var phoneInputTr = $('#phoneInputTr');
            var emailInput	 = $('#emailInput');
            var phoneInput	 = $('#phoneInput');
            
			if(email){
				emailTr.show();
				phoneTr.hide();
				phoneInputTr.show();
				emailInputTr.hide();
				emailInput.prop('required', false);
				phoneInput.prop('required', true);
				emailInput.removeAttr('name');
				$('#phone').removeAttr('name');
			} else{
				phoneTr.show();
				emailTr.hide();
				emailInputTr.show();
				phoneInputTr.hide();
				emailInput.prop('required', true);
				phoneInput.prop('required', false);
				phoneInput.removeAttr('name');
				$('#email').removeAttr('name');
			}
		}
		
		
		// 아이디 유효성 검사 이벤트 핸들러
		//입력란에 무언가를 입력할 때 실행
        $('#mmId').on('input', function () {
            validateId();
        });
		//아이디 유효성 검사
        function validateId() {
            var id = $('#mmId').val();
            var regex = /^[a-zA-Z0-9]{1,6}$/;
			var duplicateChkBtn = $('#duplicateChkBtn');
            
			//아이디 비었을 땐 검증 안함
		    if (id.length === 0) {
		        $('#idValidationMessage').text('');
                duplicateChkBtn.prop('disabled', true);
                duplicateChkBtn.prop('disabled', true);
		        return;
		    }
            if (regex.test(id)) {
                // 유효한 아이디 형식
                $('#idValidationMessage').text('');
                duplicateChkBtn.prop('disabled', false);
            } else {
                // 유효하지 않은 아이디 형식
                $('#idValidationMessage').text('아이디는 영문/숫자 조합으로 6자 이하만 가능합니다.').css('color', 'red');
                duplicateChkBtn.prop('disabled', true);
            }
        }
        
        
        
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


        //휴대폰번호에 숫자만 입력되게 하는 이벤트 핸들러
        $('#phoneInput').on('input', function(){
        	validatePhone();
        });
        
        //휴대폰 번호 유효성 검사
        function validatePhone(){
        	
        	var phoneInput = $('#phoneInput').val();
        	var validatePhone = phoneInput.replace(/\D/g, ''); // 숫자 이외의 문자 모두 제거
        	
            if (phoneInput.length === 0) {
                $('#phoneValidationMessage').text('');
                return;
            }
        	if(phoneInput !== validatePhone){
        		$('#phoneValidationMessage').text('숫자만 입력해주세요');
        		//문자 입력한 건 다 지워지고 숫자만 남김
        		$('#phoneInput').val(validatePhone);
        	} else{
        		if(phoneInput.length < 11){
        		$('#phoneValidationMessage').text('휴대폰 번호 11자리를 입력해주세요');
        			
        		} else{
        		$('#phoneValidationMessage').text('');
        			
        		}
        	}
        }
        
        //전화번호에도 적용하기
        $('#telInput').on('input', function(){
        	validateTel();
        	
        });
        
        //전화번호 유효성 검증
        function validateTel(){
        	
        	var telInput = $('#telInput').val();
        	var validateTel = telInput.replace(/\D/g, ''); // 숫자 이외의 문자 모두 제거
        	
        	if(telInput !== validateTel){
        		$('#telValidationMessage').text('숫자만 입력해주세요');
        		//문자 입력한 건 다 지워지고 숫자만 남김
        		$('#telInput').val(validateTel);
        	} else{
        		$('#telValidationMessage').text('');
        	}
        }
        
	});	
	
	function duplicateChk(){
		var pId = $('#mmId').val();
		//alert("pId -> " + pId);
		
		$.ajax({
			url	 	 : "/info/idDuplicateCheck",
			type 	 : "POST",
			data 	 : {id : pId},
			dataType : "text",
			success	 : function(data){
							//alert("result -> " + data);
							if(data ==="1"){
								$('#idValidationMessage').text('사용불가능한 아이디입니다.').css('color', 'red');
							} else{
								$('#idValidationMessage').text('사용 가능한 아이디입니다.').css('color', 'blue');
							}
			}
			
		});
		
	}
	
	
	function isFormValid() {
	    var isValid = true;

	    // 각 필수 필드 확인
	    $("#joinForm [required]").each(function () {
	        if (!$(this).val()) {
	            isValid = false;
	            // 검증 오류 처리 (예: 메시지 표시)
	            alert("모든 필수 항목을 작성하세요.");
	            return false; // 루프를 일찍 종료
	        }
	    });

	    return isValid;
	}


	 
	function joinChk(){
		//alert("formData -> " + formData);
		
		 // 폼 제출 전에 유효성 검사 수행
            if (!isFormValid()) {
                return; // 필수 항목이 비어있으면 폼 제출 중단
            }
		
		// 아이디 중복 확인이 이루어졌는지 확인
	    var idValidationMessage = $('#idValidationMessage').text();
	    if (idValidationMessage === '사용불가능한 아이디입니다.') {
	    	alert('다른 아이디를 사용해 주세요!')
	        return;
	    } else if(idValidationMessage !== '사용 가능한 아이디입니다.'){
	        alert("아이디 중복확인을 먼저 진행해주세요.");
	        return;
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
	    
		var formData = $("#joinForm").serialize();
	    
		$.ajax({
			url		 : '/info/join',
			type	 : 'POST',
			data	 : formData,
			contentType: 'application/x-www-form-urlencoded', // 기본 폼 데이터 형식
			async: false, // 동기적으로 처리
			dataType : 'text',
			success	 : function(data){
						alert("result -> " + data);
						if(data === "1"){
							alert("회원가입이 완료되었습니다!");
							location.href="/";
						} else{
							alert("회원가입이 완료되지 않았습니다. 작성내용을 확인해 주세요!");
						}
			},
			error: function (xhr, status, error) {
		        alert("인증번호를 다시 받아 주세요!"); // 에러 메시지 표시
		    }
			
		});
		
	}

</script>
</head>
<body>
<div class="row g-0 mb-5  justify-content-center">
       <div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
           <div class="row g-3">
               <h2 class="display-7 mb-4">회원가입</h2>

               <hr class="my-3">
               <form id="joinForm">
	        	<table class="formTable">
		            <tr>
						<th>이름 <span style="color: red;">*</span></th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="mmName" name="mmName" value="${name }" readonly="readonly" required>
		            	</td>
					</tr>
		            <tr id="phoneTr">
						<th>휴대폰 번호 <span style="color: red;">*</span></th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="phone" name="phone" value="${phone }" readonly="readonly">
		            	</td>
					</tr>
		            <tr id="emailTr">
						<th>이메일 주소 <span style="color: red;">*</span></th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="email" name="email" value="${email }" readonly="readonly">
		            	</td>
					</tr>
		            <tr>
						<th>아이디 <span style="color: red;">*</span></th>
						<td>
		                    <input type="text" class="form-control" id="mmId" name="mmId" required>
						</td>
						<td>
		            		<input class="btn rounded py-2 px-3 mx-5" type="button" style="background: #263d94; color: white;" id="duplicateChkBtn" value="중복확인" onclick="duplicateChk()" disabled >
		            	</td>
					</tr>
					<tr style="height: 5px;" id="idValidationMessageTr">
						<th>
						</th>
						<td colspan="3">
		                    <div id="idValidationMessage" style="color: red;"></div>
		                </td>
					</tr>
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
		            <tr>
						<th>생년월일 <span style="color: red;">*</span></th>
						<td>
<!-- 						<td colspan="3"> -->
		                    <input type="date" class="form-control" id="birth" name="birth" required>
		            	</td>
		            	<th style="padding-left: 40px;">성별 <span style="color: red;">*</span></th>
                        <td width="180px">
					        <div class="form-check form-check-inline">
					            <input class="form-check-input" type="radio" name="gender" id="genderWoman" value="1" checked>
					            <label class="form-check-label" for="genderWoman">남자</label>
					        </div>
					        <div class="form-check form-check-inline mx-4">
					            <input class="form-check-input" type="radio" name="gender" value="2" id="genderMan">
					            <label class="form-check-label" for="genderMan">여자</label>
					        </div>
					    </td>
					</tr>
		            <tr id="emailInputTr">
						<th>이메일 <span style="color: red;">*</span></th>
						<td colspan="3">
		                    <input type="email" class="form-control" id="emailInput" name="email" required>
		            	</td>
					</tr>
					<tr style="height: 5px;" id="emailValidationMessageTr">
					    <th></th>
					    <td colspan="3">
					        <div id="emailValidationMessage" style="color: red;"></div>
					    </td>
					</tr>
		            <tr>
						<th>주소</th>
						<td colspan="3">
		                    <input type="text" class="form-control" id="address" name="address">
		            	</td>
					</tr>
		            <tr id="phoneInputTr">
						<th>휴대폰 번호</th>
						<td colspan="3">
		                    <input type="tel" class="form-control" id="phoneInput" name="phone" placeholder="(-)없이 입력하세요"  maxlength="11">
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
						<td  width="150px;">
		                    <input type="tel" class="form-control" id="telInput" name="tel" placeholder="(-)없이 입력하세요" maxlength="11">
		            	</td>
						<th style="padding-left: 40px;">회원구분 <span style="color: red;">*</span></th>
						<td   width="150px;">
							<select id="categorySelect" class="form-select" name="category">
							    <option value="1">교육자</option>
							    <option value="2">학습자</option>
							    <option value="3">일반인</option>
							    <option value="4">운영자</option>
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
						<th>수신 동의</th>
						<td colspan="3">
						이벤트, 커리큘럼, 신규 콘텐츠 등 광고 메시지 수집 동의 
		                <input class="form-check-input" style="margin-left: 20px;" type="checkbox" name="econsent" id="econsent" value="1">
						<label>이메일 </label>
		                <input class="form-check-input"  style="margin-left: 20px;" type="checkbox" name="sconsent" id="sconsent" value="1">
						<label>SMS</label>
		                </td>
					</tr>


                </table>
                </form>
                <div class="d-grid gap-2 d-md-flex justify-content-center" >
					<input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="회원가입하기" onclick="joinChk()">
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