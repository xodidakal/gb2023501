<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <div class="row g-0 justify-content-center">
        <div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
            <form>
                <div class="row justify-content-center">
                    <div class="row g-3 col-sm-6">
                        <h2 class="display-7 mb-4 text-center">로그인</h2>
                        <hr class="my-3">
                        <input type="text" class="form-control" id="id" name="m_id" placeholder="id">
                        <input type="password" class="form-control" id="pswd" name="m_pswd" placeholder="password">
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">아이디 기억하기</label>
                        </div>
                        <div class="d-grid gap-2 d-md-flex justify-content-center">
                            <input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="한국기원 ID 로그인 하기">
                        </div>
                            <div class="form-group text-center">
                                <a class="fs-sm text-reset" href="/signUp">회원가입 </a>
                                <small class="fs-sm text-reset"> | </small>
                                <a class="fs-sm text-reset" data-bs-toggle="modal" href="#modalFindId">아이디 찾기 </a>
                                <small class="fs-sm text-reset"> | </small>
                                <a class="fs-sm text-reset" data-bs-toggle="modal" href="#modalPasswordReset">비밀번호 찾기 </a>
                            </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <%@ include file="../common/footerFo.jsp" %>
</body>
</html>
