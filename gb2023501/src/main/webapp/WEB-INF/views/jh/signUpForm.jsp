<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	tr {
		height: 70px;
	}
	#table {
		font-size: 18px;
	}
	th {
		text-align: left;
		width: 150px;
	}
</style>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <div class="row g-0 justify-content-center">
        <div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
           <div class="row g-3">
               <h2 class="display-7 mb-4">회원가입</h2>
               <hr class="my-3">
               <h5>약관동의</h5>
               <div class="mb-5">
                   <div class="form-check">
                       <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                       <label class="form-check-label" for="flexCheckDefault">
                           이용약관 (필수)
                       </label>
                   </div>
                   <div class="form-check">
                       <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                       <label class="form-check-label" for="flexCheckChecked">
                           개인정보 필수항목에 대한 처리 및 이용 (필수)
                       </label>
                   </div>
                   <div class="form-check">
                       <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                       <label class="form-check-label" for="flexCheckChecked">
                           개인정보 필수항목에 대한 처리 및 이용 (필수)
                       </label>
                   </div>
                   <div class="form-check">
                       <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                       <label class="form-check-label" for="flexCheckChecked">
                           개인정보 선택항목에 대한 처리 및 이용 (선택)
                       </label>
                   </div>
                   <div class="form-check">
                       <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                       <label class="form-check-label" for="flexCheckChecked">
                           개인정보 마케팅 킻 광고 활용 (선택)
                       </label>
                   </div>
                   <div class="form-check">
                       <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                       <label class="form-check-label" for="flexCheckChecked">
                           개인정보의 위탁 
                       </label>
                   </div>
               </div>
               
               
               
                 <h5>본인 인증</h5>
               <div>
                 <form action="">
                 <table id="table">
                 <tr>
						<th>이름</th>
						<td colspan="4">
                    <input type="text" class="form-control" id="name" name="m_name" >
                    </td>
					</tr>
					<tr>
						<th>인증수단</th>
						<td width="150px;">
		                    <input class="form-check-input" type="radio" name="em_type" id="em_typeTutorial">
		                    <label>휴대폰</label>
		                </td>
		                <td width="150px;">   
		                    <input class="form-check-input" type="radio" name="em_type" id="em_typeVideo" >
		                    <label>이메일</label>
						</td>
					</tr>
                 <tr>
						<th></th>
						<td colspan="4">
                    <input type="text" class="form-control" id="name" name="m_name" >
                    </td>
					</tr>
                 	 </table>
                 </form>
               
               </div>
               
               
               <div class="d-grid gap-2 d-md-flex justify-content-center">
                   <input class="btn rounded py-2 px-3" type="submit" style="background: #263d94; color: white;" value="회원가입">
               </div>
           </div>
        </div>
    </div>
    <%@ include file="../common/footerFo.jsp" %>
</body>
</html>
