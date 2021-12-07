<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<link type="text/css" rel="stylesheet" href="<%=basePath%>static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="<%=basePath%>static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<div>
				<span>欢迎<span class="um_span">韩总</span>光临天亮书城</span>
				<a href="<%=basePath%>order/order.jsp">我的订单</a>
				<a href="<%=basePath%>user/logout">注销</a>&nbsp;&nbsp;
				<a href="<%=basePath%>user/toindex">返回</a>
			</div>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${orderlist}" var="o">
				<tr>
					<td>
						<fmt:formatDate value="${o.createdate}" pattern="yyyy-MM-dd" />
					</td>
					<td>${o.sumprice}</td>
					<td>
						<c:if test="${o.status == 0}">
							未发货
						</c:if>
						<c:if test="${o.status == 1}">
							已发货
						</c:if>
					</td>
					<td><a href="<%=basePath%>shop/toOrderDetails?oid=${o.oid}">查看详情</a></td>
				</tr>
			</c:forEach>
		</table>
		
	
	</div>
	
	<div id="bottom">
		<span>
			天亮书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>