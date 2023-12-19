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
	}
	td{
		padding-left: 13px;
	}
	#borderRight{
		border-right: 1px solid #959595;
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
	});
	
	function myHomeworkSubmit() {
		alert("myHomeworkSubmit Run...")
		let myHomeworkList = [];
	 //   const inputs = document.querySelectorAll('input[name="h_num1"], input[name="m_num"],input[name="hr_level"],textarea[name="hr_content"],textarea[name="hr_question"]' );
	    const inputs = document.querySelectorAll('input[name="h_num1"], input[name="m_num"],input[name="hr_level3"]');
	    
 	    alert("inputs.length -> "+inputs.length);
 	    for (let i = 0; i < inputs.length; i += 3) {
 		    const h_num = inputs[i+1].value;
 		    const m_num = inputs[i+2].value;
 		    const hr_level = inputs[i+3].value;
 		    alert("h_num ->"+h_num+"| hr_level ->"+hr_level);
 /* 		   	const hr_content = inputs[i+4].value;
 		    alert("m_num ->"+m_num+"| hr_content ->"+hr_content);
		  	const hr_question = inputs[i+5].value;
		    alert("hr_question ->"+hr_question);
 */
		  	// 불러온 값들을 JSON 객체 형태로 만든다.
 	/*     	const myHomeworkItem = { 
 		    		        "h_num":h_num
 		    		      , "m_num":m_num
 		    		      , "hr_level":hr_level 
 		    		      , "hr_content":hr_content 
 		    		      , "hr_question":hr_question 
 		    		    };
 */	 		// alert("h_num->"+h_num+"/"+i);
	 		// JSON 객체를 배열 안에 넣어둔다.
//	 		myHomeworkList.push(myHomeworkItem);
  		}
 	   // alert("h_num->"+h_num);
	
		//완성된 dataList를 확인한다.
		//console.log(empList);
		//dataList를 문자열로 바꾼 결과를 확인한다.
		/* console.log(JSON.stringify(empList));
		alert("JSON.stringify(empList)->"+JSON.stringify(empList));
	
		if (empList.length > 0) {
				   
			$.ajax({
		          url: 'empListUpdate'
		        , contentType: 'application/json'
		        , data: JSON.stringify(empList) //JSON 객체를 불러와서 stringify() 함수 안에 배열
		        , method: 'POST'
				, dataType:'text'
		        , success: function(result) {
		            console.log(result);
		        }
		    }); 
		   
		} */
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
						<th style="width:5%; text-align: center;">
							<input class="form-check-input" type="checkbox" name="em_type" id="checkAll">
						</th>
						<th style="width:45%; text-align: center;">숙제 내용</th>	
						<th style="text-align: center;">제출 내용</th>		
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
											<td id="borderRight" rowspan="5">
												<input type="hidden" name="m_num" value="${mmNum }">
												<input type="hidden" name="h_num1" value="${myHomeworkDetail.homework.hhNum }">
							                    <input type="hidden" name="hr_level3" value="${myHomeworkDetail.hrLevel }">
							                    <input class="form-check-input" type="checkbox" name="h_num" value="${myHomeworkDetail.homework.hhNum }" id="flexRadioDefault1" >
							                </td>
							                <td id="borderRight">숙제명 : ${myHomeworkDetail.homework.hhTitle }</td>
							                <td>
							                	내 진도 : 
							                	${myHomeworkDetail.hrLevel } 레벨
										</tr>
							            <tr>
											<td id="borderRight">교육자 : ${myHomeworkDetail.homework.member.mmName }</td>
											<td rowspan="2"> 
												금번학습내용 :<br>
												<textarea rows="5" cols="45" name="hr_content">${myHomeworkDetail.hrContent }</textarea> 
							            	</td>
										</tr>
										<tr>
											<td id="borderRight" rowspan="1">숙제내용 : <br>${myHomeworkDetail.homework.hhContent} </td>
										</tr>
										<tr>
											<td id="borderRight">숙제 진도 : ${myHomeworkDetail.homework.hhLevel}</td>
											<td rowspan="2">
												추가질의내용 : <br><textarea rows="3" cols="45" name="hr_question" >${myHomeworkDetail.hrQuestion }</textarea>
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
									<td id="borderRight" rowspan="5">
										<input type="hidden" name="m_num" value="${mmNum }">
										<input type="hidden" name="h_num1" value="${myHomework.hhNum }">
					                    <input class="form-check-input" type="checkbox" name="h_num" value="${myHomework.hhNum }" id="flexRadioDefault1" >
					                </td>
					                <td id="borderRight">숙제명 : ${myHomework.hhTitle }</td>
					                <td>
					                	내 진도 : 
					                	<input type="hidden" name="hr_level" value="${totalRow }">${totalRow } 레벨
					                </td>
								</tr>
					            <tr>
									<td id="borderRight">교육자 : ${myHomework.member.mmName }</td>
									<td rowspan="2"> 
										금번학습내용 :<br>
										<textarea rows="5" cols="45" name="hr_content"></textarea> 
					            	</td>
								</tr>
								<tr>
									<td id="borderRight" rowspan="1">숙제내용 : <br>${myHomework.hhContent} </td>
								</tr>
								<tr>
									<td id="borderRight">숙제 진도 : ${myHomework.hhLevel}</td>
									<td rowspan="2">
										추가질의내용 : <br><textarea rows="3" cols="45" name="hr_question"></textarea>
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
									<td id="borderRight" rowspan="5">
										<input type="hidden" name="m_num" value="${mmNum }">
										<input type="hidden" name="h_num1" value="${myHomework.hhNum }">
					                    <input class="form-check-input" type="checkbox" name="h_num" value=0 id="flexRadioDefault1" >
					                </td>
					                <td id="borderRight">숙제명 : ${myHomework.hhTitle }</td>
					                <td>
					                	내 진도 : 
					                	<input type="hidden" name="hr_level" value="${totalRow }">${totalRow } 레벨
					                </td>
								</tr>
					            <tr>
									<td id="borderRight">교육자 : ${myHomework.member.mmName }</td>
									<td rowspan="2"> 
										금번학습내용 :<br>
										<textarea rows="5" cols="45" name="hr_content"></textarea> 
					            	</td>
								</tr>
								<tr>
									<td id="borderRight" rowspan="1">숙제내용 : <br>${myHomework.hhContent} </td>
								</tr>
								<tr>
									<td id="borderRight">숙제 진도 : ${myHomework.hhLevel}</td>
									<td rowspan="2">
										추가질의내용 : <br><textarea rows="3" cols="45" name="hr_question"></textarea>
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
				<input class="btn rounded py-2 px-3" type="button" onclick="myHomeworkSubmit()" style="background: #263d94; color: white;" value="숙제 제출 ">
			</div>
               
		</div>
	</div>
</div>
<%@ include file="../common/footerFo.jsp" %>
</body>
</html>
