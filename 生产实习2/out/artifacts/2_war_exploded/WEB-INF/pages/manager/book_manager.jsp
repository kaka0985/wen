<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<link type="text/css" rel="stylesheet" href="<%=basePath%>static/css/style.css" >
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="<%=basePath%>static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="../common/manager_common.jsp" %>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>图片</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${blist}" var="b">
			<tr>
				<td>${b.name}</td>
				<td>
					<img src="<%=basePath%>${b.img_path}" width="80px" height="80px">
				</td>
				<td>${b.price}</td>
				<td>${b.author}</td>
				<td>${b.sales}</td>
				<td>${b.stock}</td>
				<td><a href="<%=basePath%>manager/toBookEdit?id=${b.id}">修改</a></td>
				<td><a href="<%=basePath%>manager/deleteBookById?id=${b.id}">删除</a></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="999">
					<a href="<%=basePath%>manager/toBookManager?cpage=1">首页</a>
					<a href="<%=basePath%>manager/toBookManager?cpage=${cpage == 1 ? 1 : (cpage-1)}">上一页</a>
					<a href="<%=basePath%>manager/toBookManager?cpage=${cpage == totalPage ? cpage : (cpage+1)}">下一页</a>
					<a href="<%=basePath%>manager/toBookManager?cpage=${totalPage}">尾页</a>
				</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="<%=basePath%>manager/toBookEdit">添加图书</a></td>
			</tr>	
		</table>
	</div>
	
	<div id="bottom">
		<span>
			天亮书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>