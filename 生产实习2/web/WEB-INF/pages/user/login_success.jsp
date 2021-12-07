<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta charset="UTF-8">
<title>天亮会员注册页面</title>
<link type="text/css" rel="stylesheet" href="<%=basePath%>static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="<%=basePath%>static/img/logo.gif" >
			<%--被提取的区域--%>
			<%@ include file="../common/user_common.jsp"%>
		</div>
		
		<div id="main">
		
			<h1>欢迎回来 <a href="<%=basePath%>user/toindex">转到主页</a></h1>
	
		</div>
		
		<div id="bottom">
			<span>
				天亮书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>