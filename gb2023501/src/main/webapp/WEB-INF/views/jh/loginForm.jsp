<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
<script type="text/javascript">
	function loginFormSubmit(){
		var idInput	 	= document.getElementById("id");
		var pswdInput 	= document.getElementById("pswd");
		var loginForm	= document.getElementById("loginForm");
		
		if(idInput.value.trim() === ""){
			alert("아이디를 입력해 주세요");
			idInput.focus();
			
		} else if(pswdInput.value.trim() === ""){
			alert("비밀번호를 입력해 주세요");
			pswdInput.focus();
		} else{
			loginForm.submit();
		}
		
	}
	
	 function showError() {
         var errorParam = new URLSearchParams(window.location.search).get('error');
         if (errorParam) {
             alert("로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
/*  실행 안됨            // 쿼리 파라미터 'error'를 제거하고 주소를 업데이트
             urlSearchParams.delete('error');
             var newUrl = window.location.pathname +  window.location.search.replace(/&?error=true/, '');
             window.history.replaceState({}, document.title, newUrl); */
         }
     }
	 
	 
	 $(function () {
		    showError();
		});
	 
</script>
</head>
<body>
<!-- <body onload="showError()"> -->
    <div class="row g-0 justify-content-center">
        <div class="col-lg-8 wow fadeInUp mb-4" data-wow-delay="0.5s">
        
            <form action="/login" method="post" id="loginForm">
            
                <div class="row justify-content-center mb-5">
                    <div class="row g-3 col-sm-6">
                        <h2 class="display-7 mb-4 ">로그인</h2>
                        <hr class="my-3">
                         <input type="text" class="form-control" id="id" name="mmId" placeholder="id">
                        <input type="password" class="form-control" id="pswd" name="mmPswd" placeholder="password">
<!--                          <input type="text" class="form-control" id="id" name="username" placeholder="id">
                        <input type="password" class="form-control" id="pswd" name="password" placeholder="password">  
 -->                        <!-- <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">아이디 기억하기</label>
                        </div> -->
                        <div class="d-grid gap-2 d-md-flex justify-content-center">
                            <input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="한국기원 ID 로그인 하기" onclick="loginFormSubmit()">
                        </div>
                            <div class="form-group text-center">
                                <a class="fs-sm text-reset" href="/info/joinAgreeForm">회원가입 </a>
                                <small class="fs-sm text-reset"> | </small>
                                <a class="fs-sm text-reset" href="/info/idPwInquiry">아이디 찾기 | 비밀번호 찾기 </a>
<!--                                 <small class="fs-sm text-reset"> | </small>
                                <a class="fs-sm text-reset" data-bs-toggle="modal" href="#modalPasswordReset">비밀번호 찾기 </a> -->
                            </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <%@ include file="../common/footerFo.jsp" %>
</body>
</html>
