<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/style.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.success{
		height: 300px;
        padding-left: 50px;
        
        margin-top:50px;
	}
</style>
</head>
<body>
<%@ include file="head.jsp" %>
<%@ include file="menu.jsp" %>
	<div class="success">
	<h2 id="text" style="font-weight:500"></h2>
	</div>
	
<%@ include file="foot.jsp" %>


<script> 
    var t = 5;//设定跳转的时间 
    setInterval("refer()", 1000); //启动1秒定时 
    function refer() {
        if (t == 0){
            location = "${pageContext.request.contextPath}/client/jsp/login.jsp"; //跳转的地址
        }
        document.getElementById('text').innerHTML = "用户信息修改成功! " + t + "秒后跳转转到登录页面"; // 显示倒计时 
        t--; // 计数器递减 
    } 
</script>
</body>
</html>