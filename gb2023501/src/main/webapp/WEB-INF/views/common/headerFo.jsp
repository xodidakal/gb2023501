<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>한국기원</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="../assets/img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="../assets/lib/animate/animate.min.css" rel="stylesheet">
    <link href="../assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="../assets/lib/lightbox/css/lightbox.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="../assets/css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Spinner Start -->
    <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border position-relative text-primary" style="width: 6rem; height: 6rem;" role="status"></div>
        <i class="fa fa-laptop-code fa-2x text-primary position-absolute top-50 start-50 translate-middle"></i>
    </div>
    <!-- Spinner End -->


    <!-- Topbar Start -->
    <div class="container-fluid bg-light px-0 wow fadeIn" data-wow-delay="0.1s">
        <div class="row gx-0 align-items-center d-none d-lg-flex">
            <div class="col-lg-6 px-5 text-start">
                <ol class="breadcrumb mb-0">
                    <li class="breadcrumb-item"><a class="small text-secondary" href="/">Home</a></li>
                    <li class="breadcrumb-item"><a class="small text-secondary" href="#">Career</a></li>
                    <li class="breadcrumb-item"><a class="small text-secondary" href="#">Terms</a></li>
                    <li class="breadcrumb-item"><a class="small text-secondary" href="#">Privacy</a></li>
                </ol>
            </div>
            <div class="col-lg-6 px-5 text-end">
                <small>Follow us:</small>
                <div class="h-100 d-inline-flex align-items-center">
                    <a class="btn-square  border-end rounded-0" href=""><i class="fab fa-facebook-f"></i></a>
                    <a class="btn-square  border-end rounded-0" href=""><i class="fab fa-twitter"></i></a>
                    <a class="btn-square  border-end rounded-0" href=""><i class="fab fa-linkedin-in"></i></a>
                    <a class="btn-square  pe-0" href=""><i class="fab fa-instagram"></i></a>
                </div>
            </div>
        </div>
    </div>
    <!-- Topbar End -->


    <!-- Brand & Contact Start -->
    <div class="container-fluid py-4 px-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="row align-items-center top-bar">
            <div class="col-lg-3 col-md-12 text-center text-lg-start">
                <a href="" class="navbar-brand m-0 p-0">
<!--                     <h1 class="fw-bold text-primary m-0"><i class="fa fa-laptop-code me-3"></i></h1> -->
                    <img src="../assets/img/한국기원.png" alt="Logo">
                </a>
                
            </div>
            <div class="col-lg-6 col-md-12 text-center mt-3">
            	<h2>게임으로 배우는 바둑 교실</h2>
            </div>
              <div class="col-lg-3 col-md-12 text-center text-lg-start">
          	   <div class="row">	
           	 	<div class="col-11">
           	 	  <div class="d-flex align-items-center justify-content-end">
           	 		<div class="ps-3">            	 			
                       <h6 class="mb-0"><a href="/info/joinAgreeForm">회원가입</a></h6>
                    </div>
                    <div class="ps-3"> 
                       <h6 class="mb-0"><a href="/info/loginForm">로그인</a></h6>
                    </div>
                  </div>
<!--                   <div class="d-flex align-items-center justify-content-end"> -->
<!--                     <div class="ps-3">  -->
<!--                        <h6 class="mb-0 mt-2" style="width: 127px;"><a href="#!">ID/PW 찾기</a></h6> -->
<!--                     </div> -->
<!--            	 	  </div> -->
           	 	</div>     
           	   </div>       	 
            </div>
<!--            <div class="col-lg-8 col-md-7 d-none d-lg-block"> -->
<!--                <div class="row"> -->
<!--                     <div class="col-4"> -->
<!--                         <div class="d-flex align-items-center justify-content-end"> -->
<!--                             <div class="flex-shrink-0 btn-lg-square border rounded-circle"> -->
<!--                                 <i class="far fa-clock text-primary"></i> -->
<!--                             </div> -->
<!--                             <div class="ps-3"> -->
<!--                                 <p class="mb-2">Opening Hour</p> -->
<!--                                 <h6 class="mb-0">Mon - Fri, 8:00 - 9:00</h6> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                    <div class="col-4"> -->
<!--                        <div class="d-flex align-items-center justify-content-end"> -->
<!--                            <div class="flex-shrink-0 btn-lg-square border rounded-circle"> -->
<!--                                <i class="fa fa-phone text-primary"></i> -->
<!--                            </div> -->
<!--                            <div class="ps-3"> -->
<!--                                <p class="mb-2">Call Us</p> -->
<!--                                <h6 class="mb-0">+012 345 6789</h6> -->
<!--                            </div> -->
<!--                        </div> -->
<!--                    </div> -->
<!--                    <div class="col-4"> -->
<!--                        <div class="d-flex align-items-center justify-content-end"> -->
<!--                            <div class="flex-shrink-0 btn-lg-square border rounded-circle"> -->
<!--                                <i class="far fa-envelope text-primary"></i> -->
<!--                            </div> -->
<!--                            <div class="ps-3"> -->
<!--                                <p class="mb-2">Email Us</p> -->
<!--                                <h6 class="mb-0">info@example.com</h6> -->
<!--                            </div> -->
<!--                        </div> -->
<!--                    </div> -->
<!--                </div> -->
<!--            </div> -->
        </div>
    </div>
    <!-- Brand & Contact End -->


    <!-- Navbar Start -->
    
    <nav class="navbar navbar-expand-lg navbar-dark sticky-top py-lg-0 px-lg-5 wow fadeIn" data-wow-delay="0.1s" style="background: #263d94;" >
   
        <a href="#" class="navbar-brand ms-3 d-lg-none">MENU</a>
        <button type="button" class="navbar-toggler me-3" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav mx-auto me-auto p-3 p-lg-0 text-center">
                <a href="/" class="nav-item nav-link active" style="color: white;">Home</a>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">이용안내</a>
                    <div class="dropdown-menu border-1 rounded-0 rounded-bottom m-0">
                        <a href="#!" class="dropdown-item">사이트 소개</a>
                        <a href="#!" class="dropdown-item">이용 가이드</a>
                        <a href="#!" class="dropdown-item">상품 소개</a>
                    </div>
                </div>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">구독서비스</a>
                    <div class="dropdown-menu border-1 rounded-0 rounded-bottom m-0">
                        <a href="/subscribe/gameOrderList" class="dropdown-item">게임콘텐츠 구독신청</a>
                        <a href="/subscribe/myGameOrderList" class="dropdown-item">내 구독콘텐츠</a>
                    </div>
                </div>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">학습서비스</a>
                    <div class="dropdown-menu border-1 rounded-0 rounded-bottom m-0">
                        <a href="/learning/homeworkList" class="dropdown-item">내 숙제</a>
                        <a href="/learning/learnGrpJoinForm" class="dropdown-item">학습그룹 가입신청</a>
                    </div>
                </div>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">교육자마당</a>
                    <div class="dropdown-menu border-1 rounded-0 rounded-bottom m-0">
                        <a href="/educator/learnGroupForm" class="dropdown-item">학습그룹 등록</a>
                        <a href="/educator/learnGroupList" class="dropdown-item">내 학습그룹</a>
                        <a href="/educator/learnGroupDetail" class="dropdown-item">학습그룹 상세</a>
                        <a href="/educator/learnGroupJoinList" class="dropdown-item">학습그룹 가입승인</a>
                        <a href="/educator/homeworkForm" class="dropdown-item">숙제 생성</a>
                        <a href="/educator/homeworkSend" class="dropdown-item">숙제 전송</a>
                        <a href="/educator/homeworkEval" class="dropdown-item">숙제 평가</a>
                    </div>
                </div>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">운영마당</a>
                    <div class="dropdown-menu border-1 rounded-0 rounded-bottom m-0">
                        <a href="/operate/gameList" class="dropdown-item">게임콘텐츠 관리</a>
                        <a href="/operate/eduMaterialsList" class="dropdown-item">학습자료 관리</a>
                        <a href="/operate/memberList" class="dropdown-item">회원 관리</a>
                        <a href="/operate/salesInquiryDetail" class="dropdown-item">매출 관리</a>
                    </div>
                </div>
                 <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">고객센터</a>
                    <div class="dropdown-menu border-1 rounded-0 rounded-bottom m-0">
                        <a href="/customer/boardList?b_category=1" class="dropdown-item">공지사항</a>
                        <a href="/customer/boardList?b_category=2" class="dropdown-item">Q&A</a>
                        <a href="/customer/boardList?b_category=3" class="dropdown-item">FAQ</a>
                    </div>
                </div>
            </div>
<!--             <a href="#" class="btn btn-sm btn-light rounded-pill py-2 px-4 d-none d-lg-block">Get Started</a> -->
        </div>

    </nav>
<!-- Navbar End -->
<main>
    <div class="container-xxl">
    	<div class="container mt-5">
    	
    
    
    
    