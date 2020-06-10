<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/style.css"> 
<!DOCTYPE html>
<html>
 <script type="text/javascript">
	function change_Image(){
		var varImage = document.getElementById("verCodeImg");
		//如果不加a参数，服务器会认为相同的地址，就把之前缓存的图片输出
		varImage.src = "${pageContext.request.contextPath}/verCodeServlet?a="+new Date().getTime(); 
	}
</script>
 
 
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <img style="margin: 10px auto;display: block;" src="${pageContext.request.contextPath}/client/images/logo.png">
    <div class="main">
        <form class="containers" action="${pageContext.request.contextPath}/RegisterServlet" onsubmit="return validateForm()" method="post">
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
                重复密码：<input class="textLine" type="password" name="checkedPassword" id="checkedPassword">
                <span  id="tip5" style="color:red;display:none">!上下密码不一致</span>
            </div>

            <div class="container">
                性别：<input type="radio" id="gender " name="gender" checked="checked" value="男"> 男
                <input type="radio" id="gender" name="gender" value="女"> 女
            </div>

            <div class="container">
                联系电话：<input class="textLine" type="text" name="telephone" id="telephone">
                <span  id="tip6" style="color:red;display:none">!电话格式错误</span>
                <span  id="tip7" style="color:red;display:none">!电话不能为空</span>
            </div>

            <div class="container">
                邮箱：<input class="textLine" type="email" name="email" id="email">
                <span  id="tip8" style="color:red;display:none">!邮箱格式错误</span>
                <span  id="tip9" style="color:red;display:none">!邮箱不能为空</span>
            </div>
            <div class="container">
                个人介绍：<textarea name="introduce" cols="20" rows="3" id="introduce">
                </textarea>
            </div>

            <div class="container">
                验证码：<input class="textLine" type="text" name="verCode" id="verCode">
                <span  id="tip10" style="color:red;display:none">!验证为空</span>
                <span  style="color:red;">${codeError} </span>
            </div>


 	<div class="container">
              <img src="${pageContext.request.contextPath}/verCodeServlet" id="verCodeImg" style="width:180px;height:50px">
                &nbsp;<a href="javascript:void(0);" onclick="change_Image()">看不清换一张</a>
            </div>
            <div class="container">
                <input class="textLine" style="background-color:limegreen;" type="submit" value="提交并登陆">
            </div>
        </form>
    </div>
    
   
   <script type="text/javascript" src="${pageContext.request.contextPath}/client/js/validform.js"></script>
</body>
</html>