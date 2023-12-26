<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(document).ready(function () {

    // 페이지 로딩 시 버튼 숨기기(원래  style="display: none;"로 안보였어야 하는데 보여서 추가함)
    $("#varificationButton, #pwVarificationButton").hide();
   // $("#pwVarificationNumInput, #varificationNumInput").hide();

    // 인증수단에 따른 유효성 검증 함수 실행
    $('#varificationInput').on('input', function () {
        var varificationType = $('#varificationInput').prop('type');
        if (varificationType === 'tel') {
            validatePhone();
        } else {
            validateEmail();
        }
    });

    $('#pwVarificationInput').on('input', function () {
        var pwVarificationType = $('#pwVarificationInput').prop('type');
        if (pwVarificationType === 'tel') {
            pwValidatePhone();
        } else {
            pwValidateEmail();
        }
    });

});

// 아이디 찾기 인증 수단 선택하기
function toggleFields() {
    var phoneRadio = $("#phone");
    var emailRadio = $("#email");
    var varificationInput = $("#varificationInput");
    var validationMessage = $('#validationMessage');

    if (phoneRadio.prop('checked')) {
        varificationInput.val("");
        validationMessage.text('');
        varificationInput.prop('type', 'tel');
        varificationInput.prop('name', 'phone');
        varificationInput.prop('placeholder', '(-)없이 휴대폰 번호를 입력하세요');
        varificationInput.prop('maxlength', '11');
    } else if (emailRadio.prop('checked')) {
        varificationInput.val("");
        validationMessage.text('');
        varificationInput.prop('type', 'email');
        varificationInput.prop('name', 'email');
        varificationInput.prop('placeholder', '이메일을 입력하세요');
        varificationInput.removeAttr('maxlength');
    }
}

// 비밀번호 찾기 인증 수단 선택하기
function pwToggleFields() {
    var phoneRadio = $("#pwPhone");
    var emailRadio = $("#pwEmail");
    var varificationInput = $("#pwVarificationInput");
    var validationMessage = $('#pwValidationMessage');

    if (phoneRadio.prop('checked')) {
        varificationInput.val("");
        validationMessage.text('');
        varificationInput.prop('type', 'tel');
        varificationInput.prop('name', 'phone');
        varificationInput.prop('placeholder', '(-)없이 휴대폰 번호를 입력하세요');
        varificationInput.prop('maxlength', '11');
    } else if (emailRadio.prop('checked')) {
        varificationInput.val("");
        validationMessage.text('');
        varificationInput.prop('type', 'email');
        varificationInput.prop('name', 'email');
        varificationInput.prop('placeholder', '이메일을 입력하세요');
        varificationInput.removeAttr('maxlength');
    }
}

// 휴대폰 번호 유효성 검사
function validatePhone() {
    var varificationInput = $("#varificationInput");
    var phoneInput = $(varificationInput).val();
    var validatePhone  = phoneInput.replace(/\D/g, '');// 숫자 이외의 문자 모두 제거

    if (phoneInput.length === 0) {
        $('#validationMessage').text('');
        return;
    }

    if (phoneInput !== validatePhone ) {
        $('#validationMessage').text('숫자만 입력해주세요');
        varificationInput.val(validatePhone);
    } else {
        if (phoneInput.length < 11) {
            $('#validationMessage').text('휴대폰 번호 11자리를 입력해주세요');
        } else {
            $('#validationMessage').text('');
        }
    }
}

// 이메일 형식 유효성 검사
function validateEmail() {
    var varificationInput = $("#varificationInput");
    var emailInput = varificationInput.val();
    var emailValidationMessage = $('#validationMessage');
    var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z]+\.[a-zA-Z]{2,6}$/;

    if (emailInput.length === 0) {
        emailValidationMessage.text('');
        return;
    }

    if (emailRegex.test(emailInput)) {
        emailValidationMessage.text('');
    } else {
        emailValidationMessage.text('유효한 이메일 주소를 입력하세요.').css('color', 'red');
    }
}

// 비밀번호 찾기 휴대폰 번호 유효성 검사
function pwValidatePhone() {
    var varificationInput = $("#pwVarificationInput");
    var phoneInput = $(varificationInput).val();
    var validatePhone  = phoneInput.replace(/\D/g, '');// 숫자 이외의 문자 모두 제거


    if (phoneInput.length === 0) {
        $('#pwValidationMessage').text('');
        return;
    }

    if (phoneInput !== validatePhone) {
        $('#pwValidationMessage').text('숫자만 입력해주세요');
        varificationInput.val(phoneInput);
    } else {
        if (phoneInput.length < 11) {
            $('#pwValidationMessage').text('휴대폰 번호 11자리를 입력해주세요');
        } else {
            $('#pwValidationMessage').text('');
        }
    }
}

// 비밀번호 찾기 이메일 형식 유효성 검사
function pwValidateEmail() {
    var varificationInput = $("#pwVarificationInput");
    var emailInput = varificationInput.val();
    var emailValidationMessage = $('#pwValidationMessage');
    var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z]+\.[a-zA-Z]{2,6}$/;

    if (emailInput.length === 0) {
        emailValidationMessage.text('');
        return;
    }

    if (emailRegex.test(emailInput)) {
        emailValidationMessage.text('');
    } else {
        emailValidationMessage.text('유효한 이메일 주소를 입력하세요.').css('color', 'red');
    }
}

// 아이디 찾기
function submitVarificationForm() {
    var name = $("#name");
    var varification = $('#varificationInput');

    if (name.val().trim() === '') {
        alert("이름을 입력해 주세요.");
        name.focus();
    } else if (varification.val().trim() === "") {
      	alert("인증수단을 선택하고 값을 입력해 주세요.");
      	varification.focus();
    } else if($('#validationMessage').text() !== ''){
    	alert('입력 정보를 확인해 주세요!')
    } else{
    	
        $("input[name='varification']").prop("disabled", true);
        varification.prop("disabled", true);
        
        var varificationType = varification.attr('name');
        var varificationValue = varification.val();
        var data = {};
        		 data["name"] = name.val();
        		 data[varificationType] = varificationValue;
        		 
        		 alert("data -> " + JSON.stringify(data)); // 객체를 문자열로 변환해서 출력
       			$.ajax({
       				url : "/info/idInquiry",
       				type: "POST",
       	    		data: JSON.stringify(data),
       	    		contentType: "application/json",
       	    		dataType:'text',
       	    		success: function (response) {
       	    			//alert("서버 응답: " + response);
       	    			if(response === "0"){
       	    				alert("가입되지 않은 회원입니다. 회원가입을 먼저 해주세요!")
       	    				location.href="/info/joinAgreeForm";
       	    			}else if(response ===""){
       	    				alert("메일 전송에 실패했습니다.")
       	    			}else if(response ==="1"){
       	    			 	alert("인증번호가 전송되었습니다.");
	       	    			 $("#varificationNumButton").hide();
	                         // 추가: 인증 번호 입력 필드 보이기
	                         $("#varificationNumInput").show();
	                         $("#varificationButton").show();
       	    			} else{
       	    				 alert("아이디는 "+response+ " 입니다!")
       	    			}
       	    			
       	    		},
       	    		error: function (error) {
     	    				 alert("에러 발생!")
                    }
       			});
        		 
    }
    
    
}

function submitVarificationNum(){
	var pVarificationNum = parseInt(document.getElementById("varificationNumInput").value);
    //alert("pVarificationNum : " + pVarificationNum);
    
    
    $.ajax({
    	url 	 : "/info/idInquiryByEmail",
    	type 	 : "POST",
    	data 	 : {varificationNum : pVarificationNum},
    	dataType : "text",
    	success  : function(data){
		        		//alert("result -> " + data);
		        		if(data === "0"){
		        			alert("인증번호가 맞지 않습니다. 다시 입력해 주세요!");
		        		} else {
       	    				 alert("아이디는 "+data+ " 입니다!")
		        		}
    	}
    });
	
}

//비번 찾기 -> 컨트롤러에서 수정 필요 사용자 찾는 조건에 아이디 추가하고 이메일 전송 성공하면 인증번호를 디비에 임시비밀번호 발급으로 바꾸기-> 뷰에서 인증번호 입력란 지우기
function pwSubmitVarificationForm() {
    var name = $("#pwName");
    var id = $("#pwId");
    var varification = $('#pwVarificationInput');

    if (name.val().trim() === '') {
        alert("이름을 입력해 주세요.");
        name.focus();
    } else if (id.val().trim() === '') {
        alert("아이디를 입력해 주세요");
        id.focus();
    } else if (varification.val().trim() === "") {
        alert("인증수단을 선택하고 값을 입력해 주세요.");
        varification.focus();
    } else if ($('#pwValidationMessage').text() !== '') {
        alert('입력 정보를 확인해 주세요!');
    } else {
        $("input[name='pwVarification']").prop("disabled", true);
        varification.prop("disabled", true);

        var varificationType = varification.attr('name');
        var varificationValue = varification.val();
        var data = {};
        data["name"] = name.val();
        data["id"] = id.val();
        data[varificationType] = varificationValue;

        alert("data -> " + JSON.stringify(data)); // 객체를 문자열로 변환해서 출력

        $.ajax({
            url: "/info/pswdInquiry",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: 'text',
            success: function (response) {
                alert("response" + response);
                if (response === "0") {
                	alert("가입되지 않은 회원입니다. 회원가입을 먼저 해주세요!");
                    location.href = "/info/joinAgreeForm";
                } else if (response === "") {
                    alert("메일 전송에 실패했습니다.");
                } else if (response === "1") {
                    alert("임시비밀번호가 전송되었습니다.");
                    $("#pwVarificationNumButton").hide();
                    // 추가: 인증 번호 입력 필드 보이기
                    $("#pwVarificationNumInput").show();
                    $("#pwVarificationButton").show();
                } 
                /* else {
                    alert("비밀번호는 " + response + " 입니다!");
                } */
            },
            error: function (error) {
                alert("에러 발생!");
            }
        });
    }
}

function pwSubmitVarificationNum(){
	var pVarificationNum = parseInt(document.getElementById("pwVarificationNumInput").value);
    alert("pVarificationNum : " + pVarificationNum);
    
    
    $.ajax({
    	url 	 : "/info/pswdInquiryByEmail",
    	type 	 : "POST",
    	data 	 : {varificationNum : pVarificationNum},
    	dataType : "text",
    	success  : function(data){
		        		//alert("result -> " + data);
		        		if(data === "0"){
		        			alert("인증번호가 맞지 않습니다. 다시 입력해 주세요!");
		        		} else {
       	    				 alert("임시비밀번호는 "+data+ " 입니다!")
		        		}
    	}
    });
	
}
	
	//var id	 = $('#id').val;
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
                                        <th>이름  <span style="color: red;">*</span></th>
                                        <td colspan="2">
                                            <input type="text" class="form-control" id="name" name="name" required>
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
                                        <th>이름 <span style="color: red;">*</span></th>
                                        <td colspan="2">
                                            <input type="text" class="form-control" id="pwName" name="name" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>아이디  <span style="color: red;">*</span></th>
                                        <td colspan="2">
                                            <input type="text" class="form-control" id="pwId" name="id" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>인증수단  <span style="color: red;">*</span></th>
                                        <td width="150px;">
                                            <input class="form-check-input" type="radio" name="pwVarification" id="pwPhone" checked onclick="pwToggleFields()">
                                            <label for="pwPhone">휴대폰</label>
                                        </td>
                                        <td width="150px">   
                                            <input class="form-check-input" type="radio" name="pwVarification" id="pwEmail" onclick="pwToggleFields()">
                                            <label for="pwEmail">이메일</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <td colspan="2">
                                            <input type="tel" class="form-control" id="pwVarificationInput" name="phone" placeholder="(-)없이 휴대폰 번호를 입력하세요" required maxlength="11">
    										<input type="text" class="form-control mt-2" id="pwVarificationNumInput" name="varificationNum" placeholder="인증 번호를 입력하세요" style="display: none;">
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
                    <input id="pwVarificationButton" class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;" value="인증확인2" onclick="pwSubmitVarificationNum()">
                </div>
                        
                     </div>

               
               
</div>
     		          
</div>
               
               
               
               
               
               
               
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>