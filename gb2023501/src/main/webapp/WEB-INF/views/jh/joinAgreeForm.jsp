<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
 $(document).ready(function() {
	    // 전체 동의 체크박스를 클릭할 때 발생하는 이벤트 핸들러
	    $('.chkbox_group').on('click', '#chkAll', function(event) {
	        console.log(event.target);

	    	//const : 상수, 재할당 불가 -> 코드 안정성 높임
	        const checked = $(event.target).is(':checked');

	        if (checked) {
	            $(event.target).parents('.chkbox_group').find('input').prop('checked', true);
	        } else {
	            $(event.target).parents('.chkbox_group').find('input').prop('checked', false);
	        }
	    });
/* 	    위와 같은 기능
 
 		$(document).ready(function() {
	        // 전체 동의 체크박스를 클릭할 때 발생하는 이벤트 핸들러
	        $('.chkbox_group').on('click', '#chkAll', function(event) {
	            const checked = $(event.target).is(':checked');
	            // 모든 체크박스 상태를 전체 동의 체크박스와 동기화
	            $('.chkbox_group .form-check-input').prop('checked', checked);
	        }); */

	    // 각각의 체크박스를 클릭할 때 발생하는 이벤트 핸들러
	    $('.chkbox_group').on('click', '.form-check-input', function() {
	        // 모든 체크박스가 선택되었을 때 전체 동의 체크박스를 체크
	        const is_all_checked = $('.chkbox_group .form-check-input:not(#chkAll)').length === $('.chkbox_group .form-check-input:checked:not(#chkAll)').length;
	        $('#chkAll').prop('checked', is_all_checked);
	    });
	       
        // 추가: 페이지 로딩 시 버튼 숨기기(원래  style="display: none;"로 안보였어야 하는데 보여서 추가함)    
        $("#varificationButton").hide();    
	        
        // 인증확인 버튼 클릭 시 회원가입 페이지로 이동
        //$("#joinFormButton").on("click", function() {
          //  window.location.href = "/info/joinForm";
        //});    


        //인증수단에 따른 유효성 검증 함수 실행
     	$('#varificationInput').on('input', function(){
			var varificationType = $('#varificationInput').prop('type'); 		
    		if(varificationType === 'tel'){
    			validatePhone();
    		} else{
    			validateEmail();
    		}
    	});
        
        
        //이름 유효성 검사 숫자 못들어가게
        $('#name').on('input', function(){
    		validateName();    	
		});
        
        
});

		//이름 유효성 검사 숫자 못들어가게
        function validateName(){
        	var name = $('#name').val();
        	var regex = /^[a-zA-Zㄱ-ㅎ가-힣]*$/; // 영문자 또는 한글만 허용하는 정규 표현식
        	
        	if(regex.test(name)){
				$('#nameValidationMessage').text('');
				
        	} else if(name == ''){
				$('#nameValidationMessage').text('');
				
        	} else {
				$('#nameValidationMessage').text('이름에 숫자나 특수문자를 포함할 수 없습니다!');
			}
        	
        }
		
		
//인증 수단 선택하기
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

	
		 //이메일 형식 유효성 검사
        function validateEmail() {
        	var varificationInput = document.getElementById("varificationInput");
        	var emailInput = $(varificationInput).val(); // jQuery를 사용하여 값을 가져옴
            var emailValidationMessage = $('#validationMessage');

            var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z]+\.[a-zA-Z]{2,6}$/;

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
/* //인증 수단 선택하기 (원본)
 function toggleFields() {
	    var phoneRadio = document.getElementById("phone");
	    var emailRadio = document.getElementById("email");
	    var varificationInput = document.getElementById("varificationInput");

	    if (phoneRadio.checked) {
	        varificationInput.type = "tel";
	        varificationInput.name = "phone";
	        varificationInput.placeholder = "(-)없이 휴대폰 번호를 입력하세요";

	        // 아래 검증 제대로 안됨 추후에 변경 예정 11자 이하일땐 버튼 비활성화했다가 11자 되면 활성화 되게 하기?
	        // 휴대폰 선택 시 최대 11자리 숫자만 입력 가능하도록 설정
	        varificationInput.setAttribute("maxlength", "11");
	        // 숫자 이외의 문자 입력 방지
	        varificationInput.addEventListener("input", function () {
	            this.value = this.value.replace(/[^0-9]/g, '');
	        });
	    } else if (emailRadio.checked) {
	        varificationInput.type = "email";
	        varificationInput.name = "email";
	        varificationInput.placeholder = "이메일을 입력하세요";

	        // 이메일 형식 검증
	        varificationInput.addEventListener("input", function () {
	            var isValidEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.value);

	            // 유효하지 않은 형식이면 값을 지움
	            if (!isValidEmail) {
	                this.value = this.value.replace(/[^@a-zA-Z0-9._-]/g, '');
	            }
	        });
	    }
	} */
//위에 검증 제대로 되는지 확인 
//아작스로 폼 제출하고 인풋태그 생성해서 인증번호 입력하게 하기
        
                
       //인증번호 전송 버튼 클릭 시          
		function submitVarificationForm(){
			var	requiredCheckboxes 	= document.querySelectorAll('.chkbox_group .form-check-input[required]');
		    var nameInput 			= document.getElementById("name");
		    var varificationInput 	= document.getElementById("varificationInput");
		   // var varificationForm 	= document.getElementById("varificationForm");
		        	
		    // 약관 동의 체크 여부 확인
		    var isAllAgreed = Array.from(requiredCheckboxes).every(function(checkbox) {
		        return checkbox.checked;
		    });
		    
		    if (!isAllAgreed) {
		        alert("필수 약관에 동의해야 합니다.");
		        return; // 폼 제출 막음
		    }
		    
		    if (nameInput.value.trim() === "") {
		     	alert("이름을 입력해 주세요.");
		      	nameInput.focus();
		    } else if (varificationInput.value.trim() === "") {
		      	alert("인증수단을 선택하고 값을 입력해 주세요.");
		      	varificationInput.focus();
		    } else if($('#validationMessage').text() !== '' ||
		    		  $('#nameValidationMessage').text() !== ''){
		    	alert('입력 정보를 확인해 주세요!')
		    	return;
		    }
		    
		    else{
            	// FormData 객체를 사용하여 폼 데이터를 가져옴
		    	//var formData = new FormData(varificationForm);
		        var varificationType  = varificationInput.name;
		        var varificationValue = varificationInput.value;            
		        var nameInputValue = document.getElementById("name").value;
		    	
		        //radio 버튼과 varificationInput을 disabled 상태로 변경
                $("input[name='varification']").prop("disabled", true);
                $("#varificationInput").prop("disabled", true);
            	
            	//alert("varificationType -> " + varificationType);
            	//alert("varificationValue -> " + varificationValue);
            	//alert("nameInput -> " + nameInputValue);
            	
            	var data = {};
       						data["name"] = nameInputValue;
        					data[varificationType] = varificationValue;
            	
            	//alert("data -> " + JSON.stringify(data));
            	
             	$.ajax({
            		url	: "/info/joinAgree",
            		type: "POST",
            		data: JSON.stringify(data),
            		contentType: "application/json",
            		dataType:'text',
            		success: function (response) {
                         // 서버로부터의 응답 처리
                         //alert("서버 응답: " + response);
                         // 필요한 추가 작업 수행
                         
                         if(response === "1"){
                        	 alert("이미 가입된 사용자 입니다.");
                        	 location.href='/info/loginForm';
                         } else if(response === "2"){
                        	 location.href="/info/joinForm";
                         } else if(response === "3"){
                        	 alert("인증번호가 전송되었습니다.");
                        	// 추가: 버튼 숨기기
                             $("#varificationNumButton").hide();
                             // 추가: 인증 번호 입력 필드 보이기
                             $("#varificationNumInput").show();
                             $("#varificationButton").show();
                             
                            
                         } else{
                        	 alert("메일전송에 실패했습니다.");
             		        //radio 버튼과 varificationInput을 원상태로 변경
                             $("input[name='varification']").prop("disabled", false);
                             $("#varificationInput").prop("disabled", false);
                         }
                     },
                     error: function (error) {
                         console.error("에러 발생:", error);
                     }
            	}); 
		    	
		    } 
        }    
            
	function submitVarificationNum(){
		var pVarificationNum = parseInt(document.getElementById("varificationNumInput").value);
       // alert("pVarificationNum : " + pVarificationNum);
        
        
        $.ajax({
        	url 	 : "/info/varification",
        	type 	 : "POST",
        	data 	 : {varificationNum : pVarificationNum},
        	dataType : "text",
        	success  : function(data){
			        		//alert("result -> " + data);
			        		if(data === "1"){
			        			 location.href="/info/joinForm";
			        		} else {
			        			alert("인증번호가 맞지 않습니다. 다시 입력해 주세요!");
			        		}
        	}
        });
		
	}

    </script>

    <meta charset="UTF-8">
    <title>회원 가입 동의</title>
</head>
<body>
    <div class="row g-0 mb-5  justify-content-center">
        <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.5s">
            <div class="row g-3">
                <h2 class="display-7 mb-4">회원가입</h2>

                <hr class="my-3">

				
				<div class="row">
                <div class="mt-5 mb-4">
                    <h5 class="mb-4">약관동의</h5>

                    <div class="mb-5">
                    <div class="chkbox_group">
	                        <div class="form-check">
<!-- 	                            <input class="form-check-input" type="checkbox" value="selectAll" id="chkAll" > -->
 	                            <input class="form-check-input" type="checkbox" value="selectAll" id="chkAll">
	                            <label class="form-check-label" for="chkAll">
	                                전체 동의
	                            </label>
	                        </div>
	                        <div class="form-check">
	                            <input class="form-check-input" type="checkbox" value="terms1" id="chk1" required>
	                            <label class="form-check-label" for="chk1">
	                                이용약관 (필수)
	                            </label>
	                        </div>
	                        <div class="form-check">
	                            <input class="form-check-input" type="checkbox" value="terms2" id="chk2" required>
	                            <label class="form-check-label" for="chk2">
	                                개인정보 필수항목에 대한 처리 및 이용 (필수)
	                            </label>
	                        </div>
	                        <div class="form-check">
	                            <input class="form-check-input" type="checkbox" value="terms3" id="chk3" required>
	                            <label class="form-check-label" for="chk3">
	                                개인정보 필수항목에 대한 처리 및 이용 (필수)
	                            </label>
	                        </div>
	                        <div class="form-check">
	                            <input class="form-check-input" type="checkbox" value="terms4" id="chk4" >
	                            <label class="form-check-label" for="chk4">
	                                개인정보 선택항목에 대한 처리 및 이용 (선택)
	                            </label>
	                        </div>
	                        <div class="form-check">
	                            <input class="form-check-input" type="checkbox" value="terms5" id="chk5" >
	                            <label class="form-check-label" for="chk5">
	                                개인정보 마케팅 및 광고 활용 (선택)
	                            </label>
	                        </div>
	                        <div class="form-check">
	                            <input class="form-check-input" type="checkbox" value="terms6" id="chk6" required>
	                            <label class="form-check-label" for="chk6">
	                                개인정보의 위탁 (필수)
	                            </label>
	                        </div>
                        </div>
                    </div>
                </div>
				</div>

                <div class="row">
                    <div class="mb-4">
                        <h5 class="mb-4">본인 인증</h5>
                        <div>
                            <form action="joinForm" method="post" id="varificationForm">
                                <table id="table" class="mx-auto formTable">
                                    <tr>
                                        <th>이름  <span style="color: red;">*</span></th>
                                        <td colspan="2">
                                            <input type="text" class="form-control" id="name" name="name" required>
                                        </td>
                                    </tr>
                                    <tr style="height: 5px;" id="nameValidationTr">
										<th>
										</th>
										<td colspan="3">
						                    <div id="nameValidationMessage" style="color: red;"></div>
						                </td>
									</tr>
                                    <tr>
                                        <th>인증수단  <span style="color: red;">*</span></th>
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
                        </div>
                    </div>
                </div>

                <div class="d-grid gap-2 d-md-flex justify-content-center">
                    <input id="varificationNumButton" class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="인증번호전송" onclick="submitVarificationForm()">
                    <input id="varificationButton" class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="인증확인" onclick="submitVarificationNum()">
                </div>
            </div>
        </div>
    </div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
