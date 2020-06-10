<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/myInfo.css">
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>我的账户</title>
</head>
<body>
<%@ include file="head.jsp" %>
<%@ include file="menu.jsp" %>
<%@ include file="search.jsp" %>
	<div class="infocontent">
		<div class="client_menu">
			<ul>
				<li>我的帐户</li>
				<li><a href="${pageContext.request.contextPath }/client/jsp/myInfo.jsp">用户信息修改</a></li>
				<li><a href="${pageContext.request.contextPath}/FindOrderByUserServlet">订单查询</a></li>
				<li><a href="${pageContext.request.contextPath}/LogOutServlet">用戶退出</a></li>	
			</ul>
		</div>
		<div class="right" style="width:300px; margin-left:300px">
			<form action="${pageContext.request.contextPath}/ModifyUserServlet" method="post" onsubmit="return valiUserInfoForm()">
					<div class="Info_box">会员邮箱：${user.email }</div>
					<div class="Info_box">会员名：${user.username }</div>
					<div class="Info_box">修改密码：<input type="password" class="textLine" name="password" id="password"/>
						<span  id="tip3" style="color:red;display:none">!密码不能为空</span>
               			 <span  id="tip4" style="color:red;display:none">!密码为4到8位</span>
					</div>					
					<div class="Info_box">重复密码：<input type="password" class="textLine" name="checkedPassword" id="checkedPassword" />
						 <span  id="tip5" style="color:red;display:none">!上下密码不一致</span>
					</div>					
					<div class="Info_box">性别：<input type="radio" name="gender" value="男" ${user.gender=='男'?'checked':'' }/>男
											<input type="radio" name="gender" value="女" ${user.gender=='女'?'checked':'' }/>女				
					</div>				
					<div class="Info_box">
						联系方式：<input name="telephone" id = "telephone" type="text" value="${user.telephone}" class="textLine" />
						<span  id="tip6" style="color:red;display:none">!电话格式错误</span>
                		<span  id="tip7" style="color:red;display:none">!电话不能为空</span>
					</div>	
					<div class="Info_box">	
                		<input class="textLine" style="background-color:limegreen;" type="submit" value="确认">				
					</div>																			
		 	</form>
		 </div>
		 <div style="clear:both"></div>
	</div>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/client/js/validform.js"></script>
</body>
</html>