<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css" type="text/css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/product_info.css" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="head.jsp" %>
<%@ include file="menu.jsp" %>
<%@ include file="search.jsp" %>
<div class="bookinfo">
	<h1>书本信息</h1>
	<div class="book">
			<img src="${pageContext.request.contextPath}${p.imgurl}" />
			<a class="button" href="${pageContext.request.contextPath}/AddCartServlet?id=${p.id}">加入购物车</a>
	</div>
	<div class="info">
		<font style="font-size:18px">&nbsp;${p.name}</font>
		<hr />售价：<font color="#FF0000">￥${p.price}</font>
		<hr /> 类别：${p.category }
		<hr />
		<p>
			<b>内容简介：</b>
		</p> ${p.description}
	</div>
</div>

<%@ include file="foot.jsp" %>
</body>
</html>