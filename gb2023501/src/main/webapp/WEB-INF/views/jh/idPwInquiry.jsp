<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(document).ready(function() {
	
    // 추가: 페이지 로딩 시 버튼 숨기기(원래  style="display: none;"로 안보였어야 하는데 보여서 추가함)    
    $("#varificationButton").hide();  
    $("#pwVarificationButton").hide();  

	//인증수단에 따른 유효성 검증 함수 실행
 	$('#varificationInput').on('input', function(){
		var varificationType = $('#varificationInput').prop('type'); 		
		if(varificationType === 'tel'){
			validatePhone();
		} else{
			validateEmail();
		}
	});
 	$('#pwVarificationInput').on('input', function(){
		var pwVarificationType = $('#pwVarificationInput').prop('type'); 		
		if(pwVarificationType === 'tel'){
			pwValidatePhone();
		} else{
			pwValidateEmail();
		}
	});
});
 	//아이디 찾기 인증 수단 선택하기
 	 function toggleFields() {
 		    var phoneRadio = document.getElementById("phone");
 		    var emailRadio = document.getElementById("email");
 		    var varificationInput = document.getElementById("varificationInput");
 		    var validationMessage = $('#validationMessage');

 		    if (phoneRadio.checked) {
 		    	varificationInput.value = "";
 		    	validationMessage.text('');
 		        varificationInput.type = "tel";
 		        varificationInput.name = "phone";
 		        varificationInput.placeholder = "(-)없이 휴대폰 번호를 입력하세요";
 		     	// 휴대폰 선택 시 최대 11자리 숫자만 입력 가능하도록 설정
 		        varificationInput.setAttribute("maxlength", "11");

 		        
 		        
 		    } else if (emailRadio.checked) {
 		    	varificationInput.value = "";
 		    	validationMessage.text('');
 		        varificationInput.type = "email";
 		        varificationInput.name = "email";
 		        varificationInput.placeholder = "이메일을 입력하세요";
 		        varificationInput.removeAttribute("maxlength");

 		       
 		    }
 		}
 	//비밀번호 찾기 인증 수단 선택하기
 	 function pwToggleFields() {
 		    var phoneRadio = document.getElementById("pwPhone");
 		    var emailRadio = document.getElementById("pwEmail");
 		    var varificationInput = document.getElementById("pwVarificationInput");
 		    var validationMessage = $('#pwValidationMessage');

 		    if (phoneRadio.checked) {
 		    	varificationInput.value = "";
 		    	validationMessage.text('');
 		        varificationInput.type = "tel";
 		        varificationInput.name = "phone";
 		        varificationInput.placeholder = "(-)없이 휴대폰 번호를 입력하세요";
 		     	// 휴대폰 선택 시 최대 11자리 숫자만 입력 가능하도록 설정
 		        varificationInput.setAttribute("maxlength", "11");

 		        
 		        
 		    } else if (emailRadio.checked) {
 		    	varificationInput.value = "";
 		    	validationMessage.text('');
 		        varificationInput.type = "email";
 		        varificationInput.name = "email";
 		        varificationInput.placeholder = "이메일을 입력하세요";
 		        varificationInput.removeAttribute("maxlength");

 		       
 		    }
 		}



//휴대폰 번호 유효성 검사
function validatePhone(){
	var varificationInput = document.getElementById("varificationInput");
   var phoneInput = $(varificationInput).val(); // jQuery를 사용하여 값을 가져옴
	var validatePhone = phoneInput.replace(/\D/g, ''); // 숫자 이외의 문자 모두 제거
	
   if (phoneInput.length === 0) {
       $('#validationMessage').text('');
       return;
   }
	if(phoneInput !== validatePhone){
		$('#validationMessage').text('숫자만 입력해주세요');
		//문자 입력한 건 다 지워지고 숫자만 남김
		$('#varificationInput').val(validatePhone);
	} else{
		if(phoneInput.length < 11){
		$('#validationMessage').text('휴대폰 번호 11자리를 입력해주세요');
			
		} else{
		$('#validationMessage').text('');
			
		}
	}
}  

//이메일들만 수정하면 됨

//이메일 형식 유효성 검사
function validateEmail() {
	var varificationInput = document.getElementById("varificationInput");
	var emailInput = $(varificationInput).val(); // jQuery를 사용하여 값을 가져옴
   var emailValidationMessage = $('#validationMessage');

   var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

   if (emailInput.length === 0) {
   	emailValidationMessage.text('');
      return ;
   }
   
   if (emailRegex.test(emailInput)) {
       // 올바른 이메일 형식
       emailValidationMessage.text('');
   } else {
       // 부적절한 이메일 형식
       emailValidationMessage.text('유효한 이메일 주소를 입력하세요.').css('color', 'red');
   }
}
//휴대폰 번호 유효성 검사
function pwValidatePhone(){
	var varificationInput = document.getElementById("pwVarificationInput");
   var phoneInput = $(varificationInput).val(); // jQuery를 사용하여 값을 가져옴
	var validatePhone = phoneInput.replace(/\D/g, ''); // 숫자 이외의 문자 모두 제거
	
   if (phoneInput.length === 0) {
       $('#pwValidationMessage').text('');
       return;
   }
	if(phoneInput !== validatePhone){
		$('#pwValidationMessage').text('숫자만 입력해주세요');
		//문자 입력한 건 다 지워지고 숫자만 남김
		$('#pwVarificationInput').val(validatePhone);
	} else{
		if(phoneInput.length < 11){
		$('#pwValidationMessage').text('휴대폰 번호 11자리를 입력해주세요');
			
		} else{
		$('#pwValidationMessage').text('');
			
		}
	}
}  


//이메일 형식 유효성 검사
function validateEmail() {
	var varificationInput = document.getElementById("pwVarificationInput");
	var emailInput = $(varificationInput).val(); // jQuery를 사용하여 값을 가져옴
   var emailValidationMessage = $('#pwValidationMessage');

   var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

   if (emailInput.length === 0) {
   	emailValidationMessage.text('');
      return ;
   }
   
   if (emailRegex.test(emailInput)) {
       // 올바른 이메일 형식
       emailValidationMessage.text('');
   } else {
       // 부적절한 이메일 형식
       emailValidationMessage.text('유효한 이메일 주소를 입력하세요.').css('color', 'red');
   }
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div class="row g-0 mb-5  justify-content-center">
        <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.5s">
            <div class="row g-3">
                        <h2 class="display-7 mb-4 ">아이디 찾기</h2>
                        <hr class="my-3">
                                                       <div class="row">
                    <div class="mb-4">
                         <form action="joinForm" method="post" id="varificationForm">
                                <table id="table" class="mx-auto formTable">
                                    <tr>
                                        <th>이름</th>
                                        <td colspan="2">
                                            <input type="text" class="form-control" id="name" name="name" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>인증수단</th>
                                        <td width="150px;">
                                            <input class="form-check-input" type="radio" name="varification" id="phone" checked onclick="toggleFields()">
                                            <label for="phone">휴대폰</label>
                                        </td>
                                        <td width="150px">   
                                            <input class="form-check-input" type="radio" name="varification" id="email" onclick="toggleFields()">
                                            <label for="email">이메일</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <td colspan="2">
                                            <input type="tel" class="form-control" id="varificationInput" name="phone" placeholder="(-)없이 휴대폰 번호를 입력하세요" required maxlength="11">
                                             <!-- 수정: 인증 번호 입력 필드 추가, 초기에는 숨김 상태로 설정 -->
    										<input type="text" class="form-control mt-2" id="varificationNumInput" name="varificationNum" placeholder="인증 번호를 입력하세요" style="display: none;">
                                        </td>
                                    </tr>
                                    <tr style="height: 5px;" id="validationMessageTr">
										<th>
										</th>
										<td colspan="3">
						                    <div id="validationMessage" style="color: red;"></div>
						                </td>
									</tr>
                                </table>
                            </form>
                       <div class="d-grid gap-2 d-md-flex justify-content-center">
                    	<input id="varificationNumButton" class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="인증번호전송" onclick="submitVarificationForm()">
                    	<input id="varificationButton" class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="인증확인" onclick="submitVarificationNum()">
                	  </div>
                        
                     </div>
				</div>                        
     		
     		<div class="row g-3">
                        <h2 class="display-7 mb-4 ">비밀번호 찾기</h2>
                        <hr class="my-3">
                                                       <div class="row">
                    <div class="mb-4">
                         <form action="joinForm" method="post" id="varificationForm">
                                <table id="table" class="mx-auto formTable">
                                    <tr>
                                        <th>이름</th>
                                        <td colspan="2">
                                            <input type="text" class="form-control" id="name" name="name" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>아이디</th>
                                        <td colspan="2">
                                            <input type="text" class="form-control" id="id" name="id" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>인증수단</th>
                                        <td width="150px;">
                                            <input class="form-check-input" type="radio" name="varification" id="pwPhone" checked onclick="pwToggleFields()">
                                            <label for="phone">휴대폰</label>
                                        </td>
                                        <td width="150px">   
                                            <input class="form-check-input" type="radio" name="varification" id="pwEmail" onclick="pwToggleFields()">
                                            <label for="email">이메일</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <td colspan="2">
                                            <input type="tel" class="form-control" id="pwVarificationInput" name="phone" placeholder="(-)없이 휴대폰 번호를 입력하세요" required maxlength="11">
    										<input type="text" class="form-control mt-2" id=pwVarificationNumInput" name="varificationNum" placeholder="인증 번호를 입력하세요" style="display: none;">
                                        </td>
                                    </tr>
                                    <tr style="height: 5px;" id="pwValidationMessageTr">
										<th>
										</th>
										<td colspan="3">
						                    <div id="pwValidationMessage" style="color: red;"></div>
						                </td>
									</tr>
                                </table>
                            </form>
                        <div class="d-grid gap-2 d-md-flex justify-content-center">
                    <input id="pwVarificationNumButton" class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="인증번호전송" onclick="pwSubmitVarificationForm()">
                    <input id="pwVarificationButton" class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="인증확인" onclick="pwSubmitVarificationNum()">
                </div>
                        
                     </div>

               
               
</div>
     		          
</div>
               
               
               
               
               
               
               
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>