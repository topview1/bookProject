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
	body{
		background-color:#E6E6E6;
	}
	ul{
		margin-top:30px;
	}
	li{
	list-style: none;
	}
	a{
	    text-decoration: none;
	    display: block;
	    height: 40px;
	    line-height: 40px;
	    text-align: center;
	    color: #234a7b;
	}
	
	a:hover{
		color:white;
		background-color: #007cc2;
	}
</style>
</head>
<body>
	<ul>
		<li><a href="${pageContext.request.contextPath}/ListProductServlet" target="mainFrame">商品管理</a><li>
		<li><a href="${pageContext.request.contextPath}/FineOrdersServlet" target="mainFrame">订单管理</a><li>
		<li><a href="${pageContext.request.contextPath}/admin/products/download.jsp" target="mainFrame">销售榜单</a><li>
	</ul>
</body>
</html>