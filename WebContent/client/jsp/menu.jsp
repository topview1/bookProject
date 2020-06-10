<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/style.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="menu">
        <ul>
            <li><a href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=文学">文学</a></li>
            <li><a href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=计算机">计算机</a></li>
            <li><a href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=外语">外语</a></li>
            <li><a href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=经管">经管</a></li>
            <li><a href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=科技">科技</a></li>
            <li><a href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=社科">社科</a></li>
            <li><a href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=学术">学术</a></li>
            <li><a href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=少儿">少儿</a></li>
            <li><a href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=考试">考试</a></li>
            <li><a href="${pageContext.request.contextPath}/ShowProductByPageServlet?category=生活百科">生活百科</a></li>
            <li><a href="${pageContext.request.contextPath}/ShowProductByPageServlet">全部商品目录</a></li>
        </ul>

        <div class="option">
            <ul>
                 <li><a href="${pageContext.request.contextPath}/client/jsp/cart.jsp">购物车</a></li>
                 <li><a href="#">帮助中心</a></li>
               <c:if test="${empty sessionScope.user}">
                 <li><a href="${pageContext.request.contextPath}/client/jsp/login.jsp">用户登录</a></li>
                </c:if>
                
                 <c:if test="${!empty sessionScope.user}">
                 <li><a href="${pageContext.request.contextPath}/client/jsp/myInfo.jsp">我的账户</a></li>
                </c:if>
                
               <c:if test="${empty sessionScope.user}">
                  <li><a href="${pageContext.request.contextPath}/client/jsp/register.jsp">新用户注册</a></li>
               </c:if>
               <c:if test="${!empty sessionScope.user}">
                  <li><a href="${pageContext.request.contextPath}/LogOutServlet"> ${sessionScope.user.username}用户退出</a></li>
               </c:if>
               
            </ul>
        </div>
    </div>
    
</body>
</html>