<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/headerFo.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	tbody {
		border : 1px solid #959595;
	}
	th{
		border : 1px solid #959595;
		background-color: #EEEEEE;
		text-align: center;
	}
	td{
		padding-left: 13px;
	}
	#borderRight{
		border-right: 1px solid #959595;
	}
	#borderLeft{
		border-left: 1px solid #959595;
	}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(function(){
		
		// 전체 선택 클릭 시 발생 이벤트
		$("#checkAll").click(function() {
			if($("#checkAll").is(":checked")){
				$("input[name=h_num]").prop("checked", true);
			}else{
				$("input[name=h_num]").prop("checked", false);
			}
		});
		
		// 전체 체크에서 하나라도 체크를 해제하거나 전체가 다 체크되었을 때
		$("input[name=h_num]").click(function(){
		    var totalHrLevel = $("input[name=h_num]").length;		// 전체 체크박스 개수
		    var checked1 = $("input[name=h_num]:checked").length;	// 클릭된 체크박스 개수
		    
		    if(totalHrLevel != checked1){	// 전체 체크박스 개수 != 클릭된 체크박스 개수 (전체가 체크안되어있을 경우)
		      $("#checkAll").prop("checked", false);
		    }else{
		      $("#checkAll").prop("checked", true);
		    }
		  });
		
		var result = '${result}';
		if(result > 0){
			alert("숙제가 정상적으로 제출되었습니다.");
		}
		
		// 만약 제출기한이 오늘날짜 기준으로 지난날일 떄는 수정 및 제출 버튼이 안보이도록 설정
		let today = new Date();							// Mon Dec 22 2023 11:03:22 GMT+0900 (한국 표준시)
		today = today.toISOString().substring(0,10);	// 2023-08-07
		let hhDeadline = new Date('${myHomework.hhDeadline}'); // 제출기한을 날짜형식으로 변경
		hhDeadline = hhDeadline.toISOString().substring(0,10); // 2023-08-07 형식으로 포맷
		
		// today = today.toISOString().substring(0,10)	// 2023-08-07
		// var hhDeadline = ${myHomework.hhDeadline};
		
		
		if(hhDeadline < today){	// 오늘날짜와 비교하여 제출기한이 지났다면 실행
			// 여러개를 한번에 변경하고 싶다면 class 나 name으로 불러와야 함.
			$('.submitBtn').attr('style', "display:none;");
		}
			
		
	});
	
	function myHomeworkSubmit(pIndex){
		var h_num = $('#h_num'+pIndex).val();
		var hr_level = $('#hr_level'+pIndex).val();
		var hr_content = $('#hr_content'+pIndex).val();
		var hr_question = $('#hr_question'+pIndex).val();

		
		location.href = "/learning/myHomeworkSubmitAction?homework.hhNum="+h_num+"&hrLevel="+hr_level+"&hrContent="+hr_content+"&hrQuestion="+hr_question;
	}

</script>
</head>
<body>
<!-- 	<div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;"> -->
<!--         <h6 class="section-title bg-white text-center text-primary px-3">Services</h6> -->
<!--         <h2 class="display-6 mb-4">교육 자료 등록</h2> -->
<!--     </div> -->
<div class="row g-0 justify-content-center">
	<div class="col-lg-8 wow fadeInUp" data-wow-delay="0.5s">
        <div class="row g-3">
        <h2 class="display-7 mb-4">숙제 제출</h2>
        <hr class="my-3">
        	<table class="formTable" style="border:1px;solid;#959595;">
				<thead>
					<tr>	
						<th style="width:45%; text-align: center;">숙제 내용</th>	
						<th style="text-align: center;">제출 내용</th>		
						<th class="submitBtn" style="width:10%; text-align: center;">제출/수정</th>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${myHomeworkDetailList.size() > 0 }">
						<c:forEach var="totalRow" begin= "1" end="${myHomework.hhLevel}" >
							<!-- 학습현황 테이블에 있는 진도인지 체크하기 위한 변수 설정 -->
							<c:set var="homeworkChK" value="0" />
							<!-- 학습현황에 해당되는 진도가 있을 때 학습현황에 등록된 값 출력 -->
							<c:forEach var="myHomeworkDetail" items="${myHomeworkDetailList }">
								<c:if test="${myHomeworkDetail.hrLevel eq totalRow }">
									<tbody>
										<tr>
							                <td id="borderRight">숙제명 : ${myHomeworkDetail.homework.hhTitle }</td>
							                <td>
							                	내 진도 : <input type="hidden" id="hr_level${totalRow}" name="hr_level" value="${myHomeworkDetail.hrLevel }">
							                	${myHomeworkDetail.hrLevel } 레벨
							                </td>
							                <td id="borderLeft" class="submitBtn" rowspan="5" style="padding-left: 0px; text-align:center;">
												<input type="hidden" id="h_num${totalRow}" name="h_num1" value="${myHomeworkDetail.homework.hhNum }">
							                    <input class="btn rounded py-2 px-3" type="button" onclick="myHomeworkSubmit(${totalRow})" style="background: #263d94; color: white;" value="수정 ">
							                </td>
										</tr>
							            <tr>
											<td id="borderRight">교육자 : ${myHomeworkDetail.homework.member.mmName }</td>
											<td rowspan="2"> 
												금번학습내용 :<br>
												<textarea rows="5" cols="40" id="hr_content${totalRow}" name="hr_content">${myHomeworkDetail.hrContent }</textarea> 
							            	</td>
										</tr>
										<tr>
											<td id="borderRight" rowspan="1">숙제내용 : <br>${myHomeworkDetail.homework.hhContent} </td>
										</tr>
										<tr>
											<td id="borderRight">숙제 진도 : ${myHomeworkDetail.homework.hhLevel}</td>
											<td rowspan="2">
												추가질의내용 : <br><textarea rows="3" cols="40" id="hr_question${totalRow}" name="hr_question" >${myHomeworkDetail.hrQuestion }</textarea>
											</td>
										</tr>
										<tr>
											<td id="borderRight">제출기한 : ${myHomeworkDetail.homework.hhDeadline}</td>
										</tr>
									</tbody>
									<!-- 학습현황 테이블에 진도가 있으면 1로 변경하기 -->
									<c:set var="homeworkChK" value="1" />
								</c:if>
							</c:forEach>
							<!-- 학습현황에서 해당되는 진도레벨이 없을 때 빈 값 출력 -->
							<c:if test="${homeworkChK eq '0' }">
								<tbody>
								<tr>
					                <td id="borderRight">숙제명 : ${myHomework.hhTitle }</td>
					                <td>
					                	내 진도 : 
					                	<input type="hidden" id="hr_level${totalRow}" name="hr_level" value="${totalRow }">${totalRow } 레벨
					                </td>
					                <td id="borderLeft" class="submitBtn" rowspan="5" style="padding-left: 0px; text-align:center;">
										<input type="hidden" id="h_num${totalRow}" name="h_num" value="${myHomework.hhNum }">
					                    <input class="btn rounded py-2 px-3" type="button" onclick="myHomeworkSubmit(${totalRow})" style="background: #263d94; color: white;" value="제출 ">
					                </td>
								</tr>
					            <tr>
									<td id="borderRight">교육자 : ${myHomework.member.mmName }</td>
									<td rowspan="2"> 
										금번학습내용 :<br>
										<textarea rows="5" id="hr_content${totalRow}" cols="40" name="hr_content"></textarea> 
					            	</td>
								</tr>
								<tr>
									<td id="borderRight" rowspan="1">숙제내용 : <br>${myHomework.hhContent} </td>
								</tr>
								<tr>
									<td id="borderRight">숙제 진도 : ${myHomework.hhLevel}</td>
									<td rowspan="2">
										추가질의내용 : <br><textarea rows="3" cols="40" id="hr_question${totalRow}" name="hr_question"></textarea>
									</td>
								</tr>
								<tr>
									<td id="borderRight">제출기한 : ${myHomework.hhDeadline}</td>
								</tr>
							 </tbody>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="totalRow" begin= "1" end="${myHomework.hhLevel}" >
							<tbody>
								<tr>
					                <td id="borderRight">숙제명 : ${myHomework.hhTitle }</td>
					                <td>
					                	내 진도 : 
					                	<input type="hidden" id="hr_level${totalRow}" name="hr_level" value="${totalRow }">${totalRow } 레벨
					                </td>
					                <td id="borderLeft" class="submitBtn" rowspan="5" style="padding-left: 0px; text-align:center;">
										<input type="hidden" id="h_num${totalRow}" name="h_num1" value="${myHomework.hhNum }">
					                    <input class="btn rounded py-2 px-3" type="button" onclick="myHomeworkSubmit(${totalRow})" style="background: #263d94; color: white;" value="제출 ">
					                </td>
								</tr>
					            <tr>
									<td id="borderRight">교육자 : ${myHomework.member.mmName }</td>
									<td rowspan="2"> 
										금번학습내용 :<br>
										<textarea rows="5" cols="40" id="hr_content${totalRow}" name="hr_content"></textarea> 
					            	</td>
								</tr>
								<tr>
									<td id="borderRight" rowspan="1">숙제내용 : <br>${myHomework.hhContent} </td>
								</tr>
								<tr>
									<td id="borderRight">숙제 진도 : ${myHomework.hhLevel}</td>
									<td rowspan="2">
										추가질의내용 : <br><textarea id="hr_question${totalRow}" rows="3" cols="40" name="hr_question"></textarea>
									</td>
								</tr>
								<tr>
									<td id="borderRight">제출기한 : ${myHomework.hhDeadline}</td>
								</tr>
							 </tbody>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
               </table>
               <div class="d-grid gap-2 d-md-flex justify-content-center" >
				<a href="myhomeworkList?m_num=${mmNum }"><button class="btn rounded py-2 px-3" type="button" style="background: #263d94; color: white;">목록</button></a>
			</div>
               
		</div>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
