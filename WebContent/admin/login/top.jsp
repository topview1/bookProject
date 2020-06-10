<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	*{
		padding:0;
		margin:0;
	}
	.height1{
	text-align: center;
    height: 74px;
    background-color: green;
    color: white;
    width: 100%;
    line-height: 74px;
    font-size: 26px;
    font-weight: 700;
	}
	.bottom{
		height:25px;
		 background-color: #E6E6E6;
		 margin:2px 0;
	}
	.bottom .right{
		float:right;
	}
	.bottom .right button{
	float: right;
    font-size: 12px;
    padding:0 30px;
    margin-right: 20px;
    height: 25px;
    line-height:25px;
	}
	.bottom .left{
		float:left;
		color:#000000;
		font-size:12px;
		padding-left:20px;
		 line-height:25px;
	}
	
</style>
<script type="text/javascript">
	function exitSys() {
		var flag = window.confirm("确认退出系统吗?");
		 console.log(flag);
		if (flag) {
			top.location.href="${pageContext.request.contextPath}/client/jsp/login.jsp";
		}
	}
</script>
</head>
<body>
<div class="height1">书伴后台管理系统</div>
<div class="bottom">
	<div class="left">
		<script language="JavaScript">								
			var tmpDate = new Date();
			var date = tmpDate.getDate();
			var month = tmpDate.getMonth() + 1;//月是从0月开始
			var year = tmpDate.getFullYear();
			var weekday = tmpDate.getDay();  //一周的第几天，星期日是第0天
			document.write(year);
			document.write("年");
			document.write(month);
			document.write("月");
			document.write(date);
			document.write("日 ");
	
			myArray = new Array(6);
			myArray[0] = "星期日"
			myArray[1] = "星期一"
			myArray[2] = "星期二"
			myArray[3] = "星期三"
			myArray[4] = "星期四"
			myArray[5] = "星期五"
			myArray[6] = "星期六"
			document.write(myArray[weekday])						
		</script> 									
	</div>
	<div class="right"><button onclick="exitSys()">退出系统</button></div>
</div>
</body>
</html>