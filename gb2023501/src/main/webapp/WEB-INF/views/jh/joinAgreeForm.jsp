<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
/*     $(document).ready(function() {*/
	//     $('.chkbox_group').on('click', '#chkAll', function() { 
	function chkBoxAllChecked(event){
	    	console.log(event.target);
	    	//const : 상수, 재할당 불가 -> 코드 안정성 높임
	    	const checked = $(event.target).is(':checked');
	
	    	if(checked){
	    		$(event.target).parents('.chkbox_group').find('input').prop('checked',true);
	    	} else {
	    		$(event.target).parents('.chkbox_group').find('input').prop('checked',false);
	    	}
	     }   	
/* 	    });

    }); */
        function updateVerificationInput() {
            var phoneRadio 		 	= document.getElementById("phone");
            var emailRadio 		  	= document.getElementById("email");
            var verificationInput 	= document.getElementById("verificationInput");

            if (phoneRadio.checked) {
            	
                verificationInput.type 			= "tel";
                verificationInput.name 			= "m_phone";
                verificationInput.placeholder 	= "(-)없이 휴대폰 번호를 입력하세요";
                
            } else if (emailRadio.checked) {
            	
                verificationInput.type 			= "text";
                verificationInput.name 			= "m_email";
                verificationInput.placeholder 	= "이메일을 입력하세요";
            }
        }
        
/*          function submitVerificationForm(){
       		var nameInput 			= document.getElementById("name");
       		var verificationInput 	= document.getElementById("verificationInput");
        	var verificationForm 	= document.getElementById("verificationForm");
        	
            if (nameInput.value.trim() === "") {
                alert("이름을 입력해 주세요.");
                nameInput.focus();
            } else if (verificationInput.value.trim() === "") {
                alert("인증수단을 선택하고 값을 입력해 주세요.");
                verificationInput.focus();
            } *//* else {
            	// FormData 객체를 사용하여 폼 데이터를 가져옴
                var formData = new FormData(verificationForm);
            	
            	//ajax를 이용해 회원 존재 유무 비교하고 있으면 이미 가입한 사용자다 아니면 joinform으로 이동(제이쿼리가 아닌 fech api이용 ) -> 시큐리티로 회원 유무 검증 할 수 있을 것 같아 보류
                fetch("/joinAgree", {
                    method: "POST",
                    body: formData
                })
                 .then(response => response.json())
        		 .then(data => {
        			// 서버로부터의 응답 처리
                     if (data.exists) {
                         alert("이미 가입한 사용자입니다.");
                     } else {
                         // 가입한 사용자가 아니라면 joinForm으로 이동
                         window.location.href = "joinForm";
                     }
                 })
        	        .catch(error => {
        	            console.error("에러 발생:", error);
        	        });
        		 }
            }
        } */
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
 	                            <input class="form-check-input" type="checkbox" value="selectAll" id="chkAll" onclick="chkBoxAllChecked(event)">
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
	                                개인정보 마케팅 킻 광고 활용 (선택)
	                            </label>
	                        </div>
	                        <div class="form-check">
	                            <input class="form-check-input" type="checkbox" value="terms6" id="chk6" required>
	                            <label class="form-check-label" for="chk6">
	                                개인정보의 위탁 
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
                            <form action="joinForm" method="post" id="verificationForm">
                                <table id="table" class="mx-auto formTable">
                                    <tr>
                                        <th>이름</th>
                                        <td colspan="2">
                                            <input type="text" class="form-control" id="name" name="m_name" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>인증수단</th>
                                        <td width="150px;">
                                            <input class="form-check-input" type="radio" name="verification" id="phone" checked onclick="updateVerificationInput()">
                                            <label>휴대폰</label>
                                        </td>
                                        <td width="150px">   
                                            <input class="form-check-input" type="radio" name="verification" id="email" onclick="updateVerificationInput()">
                                            <label>이메일</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <td colspan="2">
                                            <input type="tel" class="form-control" id="verificationInput" placeholder="(-)없이 휴대폰 번호를 입력하세요">
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="d-grid gap-2 d-md-flex justify-content-center">
                    <input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="본인인증" onclick="submitVerificationForm()">
                </div>
            </div>
        </div>
    </div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
