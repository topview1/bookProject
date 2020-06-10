<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/client/css/style.css">    
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
//鼠标点击搜索框时执行
function my_click(){
	//点击时，如果取得的值和搜索框默认value值相同，则将搜索框清空
	  document.getElementById("search_key").value = '';
	  document.getElementById("search_key").style.color = 'black';
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/MenuSearchServlet" id="searchform">
        <div class="search">
            <input class="search_box" onclick ="my_click()" type="text" id="search_key" name="search_key"  style="color:#ccc" value="请输入书名">
            <input class="search_img" type="submit" name="search" id="" value="提交">

        </div>
    </form>
</body>
</html>