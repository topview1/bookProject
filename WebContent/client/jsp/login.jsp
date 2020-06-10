<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/style.css">   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<img style="margin: 10px auto;display: block;" src="${pageContext.request.contextPath}/client/images/logo.png">
    <div class="main">
        <form class="containers" action="${pageContext.request.contextPath}/LoginServlet" onsubmit="return valiLoginForm()" method="post">
            <h3>新用户注册</h3>
           
               <div class="container">
                用户名：<input class="textLine" type="text" name="username" id="username">
                <span id="tip1" style="color:red;display:none">!用户名不能为空</span>
                <span id="tip2" style="color:red;display:none">!用户名至少是三位</span>
            	</div>

            <div class="container">
                密码：<input class="textLine" type="password" name="password" id="password">
                <span  id="tip3" style="color:red;display:none">!密码不能为空</span>
                <span  id="tip4" style="color:red;display:none">!密码为4到8位</span>

            </div>
	
	  <div class="container">
                <span style="color:red">${loginEorror_message}</span>
            </div>
        
            <div class="container">
                <input class="textLine" style="background-color:limegreen;" type="submit" value="登录">
            </div>
        </form>
    </div>
    
   
   <script type="text/javascript" src="${pageContext.request.contextPath}/client/js/validform.js"></script>
</body>
</html>