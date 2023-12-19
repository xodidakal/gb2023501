<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/none.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.center-text {
	  	text-align: center; /* 텍스트 가운데 정렬 */
	  	position: absolute;
	  	top: 30%;
	  	left: 50%;
	  	font-weight: bold;
	  	color:black;
	  	transform: translate(-50%, -50%); /* 가운데 정렬을 위한 변환 */
	}
	
.top {
         position: relative;
         display: flex; 
         justify-content: space-between;
         padding: 0.5rem 1.4rem;
         background-color: #263d94;
         vertical-align: middle;
     }
             
 h1.infoTit {
             margin-top : 10px;
                font-size: 20px; 
                color:#ffffff;
            }

</style>
</head>
<body>
<main class="textBox">
   <div class="mb-6">
      <header class="top">
         <h1 class="infoTit">
           	매출 그래프 ${s_date } ~ ${e_date }
         </h1>
      </header>
   </div>
</main>
</body>
</html>