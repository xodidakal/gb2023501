<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/none.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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
      <canvas id="line_chart" width="1100" height="400"></canvas>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script type="text/javascript">
	document.addEventListener('DOMContentLoaded', function () {
	var selectSaleList = ${selectSaleListJson};
	var selectDateList = ${selectDateList};
	var dateList = [];
	var salesList = [];
	
	for(let i=selectSaleList.length -1; i>=0; i--){
		salesList.push(selectSaleList[i].salesSum);		
	}
	for(let i=selectDateList.length -1; i>=0; i--){
		dateList.push(selectDateList[i])
	}
	new Chart(document.getElementById("line_chart"), {
        type: 'line',
        data: {
            labels: dateList,
            datasets: [{
                data: salesList,
                label: "매출(원)",
                borderColor: "#263d94"
            }]
        },
        options: {
            title: {
                display: true,
                text: '검색 기간 매출'
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false,
                        callback: function (value, index, values) {
                            return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                        }
                    },
                    scaleLabel: {
                        display: true,
                        labelString: '매출 단위(원)'
                    }
                }]
            }
        }
    });
});
</script>
   </div>
</main>
</body>
</html>