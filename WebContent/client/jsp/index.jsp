<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/client/css/autoPlay.css">
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
 <!-- 图书商场首页轮播图  start -->
    <div class="box_autoplay">
        <div class="box_img">
            <img src="${pageContext.request.contextPath }/client/source_pic/auto_1.jpg" width="990" height="360" />
        </div>
        <div class="box_img">
            <img src="${pageContext.request.contextPath }/client/source_pic/auto_2.jpg" width="990" height="360" />
        </div>
        <div class="box_img">
            <img src="${pageContext.request.contextPath }/client/source_pic/auto_3.jpg" width="990" height="360" />
        </div>
        <div class="box_img">
            <img src="${pageContext.request.contextPath }/client/source_pic/auto_4.jpg" width="990" height="360" />
        </div>

        <div class="box_left" id="box_left">&lt;</div>
        <div class="box_right" id="box_right">&gt;</div>

        <div class="box_circle">
            <ul>
                <li class="button" style="background-color: #fff;"></li>
                <li class="button"></li>
                <li class="button"></li>
                <li class="button"></li>
            </ul>
        </div>
    </div>
    <!-- 图书商场首页轮播图  end -->
<%@ include file="foot.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath }/client/js/autoPlay.js"></script>
</body>
</html>