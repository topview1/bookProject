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
		
		 
		 <div class="right">
			<table id="customers">
				<tr>
					<th width="10%">序号</th>
					<th width="39%">订单id</th>
					<th width="10%">金额</th>
					<th width="24%">下单时间</th>
					<th width="10%">支付状态</th>
					<th width="17%">其他</th>
				</tr>
				<c:forEach items="${orders}" var="order" varStatus="vs">
					<table id="customers" style="margin-top: 0">
					<tr>
						<th width="10%">${vs.count} </th>
						<th width="39%">${order.id}</th>
						<th width="10%">${order.money}</th>
						<th width="24%">${order.ordertime}</th>
						<th width="10%">${order.paystate == '0'?"未支付":"已支付"}</th>
						<th width="17%"><a href="${pageContext.request.contextPath}/OrderInfoServlet?id=${order.id}">详情</a></th>
					</tr>
					</table>
				</c:forEach>
			</table>
		 	<div >
			</div>
		 </div>
		 <div style="clear:both"></div>
	</div>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/client/js/validform.js"></script>
</body>
</html>