<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/cart.css" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>详细订单页面</title>
</head>
<body>
<%@ include file="head.jsp" %>
<%@ include file="menu.jsp" %>
<%@ include file="search.jsp" %>
<div class="infocontent">
	<h1>详细订单页面</h1>
 	<table id="customers">
		<tr>
			<th width="15%">序号</th>
			<th width="30%">商品名称</th>
			<th width="15%">价格</th>
			<th width="15%">购买数量</th>
			<th width="15%">小计</th>
			
		</tr>
		<c:set var="total" value="0" /> 
		<c:forEach items="${items}" var="item" varStatus="vs">
			<table id="customers" style="margin-top: 0">
			<tr>
				<th width="15%">${vs.count} </th>
				<th width="30%">${item.key.name}</th>
				<th width="15%">${item.key.price}</th>
				<th width="15%">${item.value}</th>
				<th width="15%">${item.key.price*item.value}</th>
			</tr>
			</table>
			<c:set value="${total+item.key.price*item.value}" var="total" />
		</c:forEach>
	</table>
	<div style="text-align:right; padding: 20px 53px 10px 0;">
			<font style="color:#FF6600; font-weight:bold">合计：&nbsp;&nbsp;${total}元</font>
	</div>
	
	<div class="option">
		
		<a class="button" style="float: left" href="${pageContext.request.contextPath}/FindOrderByUserServlet">返回订单页</a>
		<a class="button" style="float: right" href="${pageContext.request.contextPath}/PayServelt">支付</a>
	</div>
</div>
<%@ include file="foot.jsp" %>

</body>
</html>