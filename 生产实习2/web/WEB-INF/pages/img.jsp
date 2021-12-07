<%--
  Created by IntelliJ IDEA.
  User: 86152
  Date: 2021/11/14
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>图片列表</title>
</head>
<body>
<img src="<%=basePath%>${imgPath}" alt="暂无图片"  width="200px" height="200px">
</body>
</html>
