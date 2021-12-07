<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path2 = request.getContextPath();
    String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path2+"/";
%>
<div>
    <a href="<%=basePath2%>manager/toBookManager">图书管理</a>
    <a href="<%=basePath2%>manager/order_manager.jsp">订单管理</a>
    <a href="<%=basePath2%>user/toindex">返回商城</a>
</div>