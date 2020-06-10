<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/style.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/cart.css" type="text/css">
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
	function changeProductNum(count,totalCount,id){
		count = parseInt(count);
		totalCount = parseInt(totalCount);
		if(count == 0){
			var flag = window.confirm("确认删除商品吗?");
			if(!flag){
				count =1;
			}
		}
		if(count>totalCount){
			alert("已达到商品最大购买量");
			count = totalCount;
		}
		//跳转到修改Num的servlet
		window.location.href = "${pageContext.request.contextPath}/ChangeProductNumServlet?id="
			+ id + "&count=" + count;
	}

</script>
<meta charset="UTF-8">
<title>结账页面</title>
</head>
<body>
<%@ include file="head.jsp" %>
<%@ include file="menu.jsp" %>
<%@ include file="search.jsp" %>
  <form  action="${pageContext.request.contextPath}/CreateOrderServlet" onsubmit="return validateForm()" method="post">
<div class="infocontent">
	<h1>购物清单</h1>
 	<table id="customers">
		<tr>
			<th width="10%">序号</th>
			<th width="30%">商品名称</th>
			<th width="10%">价格</th>
			<th width="20%">数量</td>
			<th width="10%">库存</th>
			<th width="10%">小计</th>
		</tr>
		<c:set var="total" value="0" /> 
		<c:forEach items="${sessionScope.cart}" var="cart" varStatus="vs">
			<table id="customers" style="margin-top: 0">
			<tr>
				<th width="10%">${vs.count} </th>
				<th width="30%">${cart.key.name}</th>
				<th width="10%">${cart.key.price}</th>
				<th width="20%">
					${cart.value}</th>
				<th width="10%">${cart.key.pnum}</th>
				<th width="10%">${cart.key.price*cart.value}</th>
			</tr>
			
			</table>
			<c:set value="${total+cart.key.price*cart.value}" var="total" />
		</c:forEach>
	</table>
	<div  style="text-align:right; padding: 20px 53px 10px 0;">
			<font style="color:#FF6600; font-weight:bold" >合计：&nbsp;&nbsp;${total}元</font>
	</div>
	<div class="option" style="height:120px">
		<input type="hidden" name="money" value="${total}">
		收货地址：<input id="receiverAddress" name="receiverAddress" type="text" value=""style="width:350px" />
		<span id="receiverAddressMsg" style="color:red;font-size:14px"></span>
		<br/>
		收货人：&nbsp;&nbsp;&nbsp;&nbsp;<input id="receiverName" name="receiverName" type="text" value="${user.username}" style="width:150px"  />
		<span id="receiverNameMsg"></span>
		
		<br/> 
		联系方式：<input type="text" id="receiverPhone" name="receiverPhone" value="${user.telephone}" style="width:150px"  />
		<span id="receiverPhoneMsg"></span>
		
		<br/> 
		<a class="button" style="float: left" href="${pageContext.request.contextPath}/ShowProductByPageServlet">继续购物</a>
		 <input class="button" style="float: right;border-width:0;outline:none"  type="submit" value="结账">
	</div>
</div>
</form>
<%@ include file="foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/order.js"></script>

</body>
</html>