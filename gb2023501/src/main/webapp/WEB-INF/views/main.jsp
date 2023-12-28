<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/headerFo.jsp" %>

    <!-- Carousel Start -->
    <div class="container-fluid p-0 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div id="header-carousel" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <c:forEach var="item" items="${selectGameList}" varStatus="status">
                    <button type="button" data-bs-target="#header-carousel" data-bs-slide-to="${status.index}" class="${status.index == 0 ? 'active' : ''}" aria-current="${status.index == 0}" aria-label="Slide ${status.index + 1}">
                        <img class="img-fluid" src="${item.g_attach_name}" alt="Image" >
                    </button>
                </c:forEach>
            </div>
            <div class="carousel-inner">
                <c:forEach var="item" items="${selectGameList}" varStatus="status">
                    <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                      <a href="/subscribe/gameOrderList">
                        <img class="w-100" src="${item.g_attach_name}" alt="Image" style="max-width: 600px; width: 100%; height: auto;">
                        <div class="carousel-caption">
                            <div class="p-3" style="max-width: 900px;">
                                <h4 class="text-white text-uppercase mb-4 animated zoomIn">${item.g_title}</h4>
                                <%-- <h1 class="display-1 text-white mb-0 animated zoomIn">${item.caption2}</h1> --%>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#header-carousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#header-carousel" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
    <!-- Carousel End -->


    <!-- Facts Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="row g-4">
                <div class="col-lg-6 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="fact-item bg-light rounded text-center h-100 p-5">
                        <i class="fa fa-certificate fa-4x text-primary mb-4"></i>
                        <h5 class="mb-3">공지사항</h5>
                        <h1 class="display-5 mb-0" data-toggle="counter-up">1234</h1>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                    <div class="fact-item bg-light rounded text-center h-100 p-5">
                        <i class="fa fa-users-cog fa-4x text-primary mb-4"></i>
                        <h5 class="mb-3">FAQ</h5>
                        <h1 class="display-5 mb-0" data-toggle="counter-up">1234</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Facts End -->


   


<%@ include file="common/footerFo.jsp"%>