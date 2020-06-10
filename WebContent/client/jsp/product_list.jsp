<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css" type="text/css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/product_list.css" type="text/css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	
</style>
</head>
<body>
<%@ include file="head.jsp" %>
<%@ include file="menu.jsp" %>
<%@ include file="search.jsp" %>
	<div class="bookList">
		<h1>商品目录</h1>
		<h1>${bean.category}&nbsp;&nbsp;&nbsp;&nbsp;共${bean.totalCount}种商品</h1>
		<div class="products">
			<c:forEach items="${bean.ps}" var="p" varStatus="vs">
				<div class="product">
					<div class="productImg">
						<a href="${pageContext.request.contextPath}/findProductByIdServlet?id=${p.id}">
							<img src="${pageContext.request.contextPath}${p.imgurl}" width="115" height="129" border="0" /> 
						</a>
					</div>	
					<div class="productTip">
						<a href="${pageContext.request.contextPath}/findProductByIdServlet?id=${p.id}">书名： ${p.name}<br />售价：￥${p.price} </a>	
					</div>	
				</div>						
			</c:forEach>
		</div>
		<div class="pagination">
		<ul>
			<c:if test="${bean.currentPage!=1}">
				<li class="disablepage_p">
					<a class="disablepage_a" href="${pageContext.request.contextPath}/ShowProductByPageServlet?currentPage=${bean.currentPage-1}&category=${bean.category}"></a>
				</li>
			</c:if>
			<c:if test="${bean.currentPage==1}">
				<li class="disablepage_p2"></li>
			</c:if>
			<c:forEach begin="1" end="${bean.totalPage}" var="pageNum">
				<c:if test="${pageNum==bean.currentPage}">
						<li class="currentpage">${pageNum }</li>
				</c:if>
				<c:if test="${pageNum!=bean.currentPage}">
						<li><a href="${pageContext.request.contextPath}/ShowProductByPageServlet?currentPage=${pageNum}&category=${bean.category}">${pageNum}</a>
						</li>
				</c:if>
			</c:forEach>

			<c:if test="${bean.currentPage==bean.totalPage||bean.totalPage==0}">
					<li class="disablepage_n2"></li>
			</c:if>
			<c:if test="${bean.currentPage!=bean.totalPage&&bean.totalPage!=0}">
				    <li class="disablepage_n">
						<a class="disablepage_a" href="${pageContext.request.contextPath}/ShowProductByPageServlet?currentPage=${bean.currentPage+1}&category=${bean.category}"></a>
					</li>
			</c:if>
		</ul>	
	 </div>
  </div>
	
									
<%@ include file="foot.jsp" %>

</body>
</html>