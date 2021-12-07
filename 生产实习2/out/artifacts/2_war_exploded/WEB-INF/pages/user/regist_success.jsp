<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//	1request.getSchema();可以返回当前页面所使用的协议，就是 http
//	request.getServerName();返回当前页面所在服务器的名字，就是上面例子中的 localhost
//	request.getServerPort();返回当前页面所在服务器的端口号，就是上面例子中的 8080
//	request.getContextPath();返回当前页面所在的应用的名字，就是上面例子中的 ooo
//	当这四个拼装起来，就是当前应用的跟路径
%>
<html>
<head>
<meta charset="UTF-8">
<title>天亮会员注册页面</title>
<link type="text/css" rel="stylesheet" href="<%=basePath2%>static/css/style.css" >
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
				<img class="logo_img" alt="" src="<%=basePath2%>static/img/logo.gif" >
				<span class="wel_word"></span>
				<%--被提取的区域--%>
			    <%@ include file="../common/user_common.jsp"%>
		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="<%=basePath2%>index">转到主页</a></h1>
	
		</div>
		
		<div id="bottom">
			<span>
				天亮书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>