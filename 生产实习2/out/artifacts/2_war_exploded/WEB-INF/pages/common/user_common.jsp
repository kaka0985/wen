<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path2 = request.getContextPath();
    String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div>
    <span>欢迎<span class="um_span">${sessionScope.loginUser.username}</span>光临天亮书城</span>
    <a href="../order/order.jsp">我的订单</a>
    <a href="<%=basePath2%>user/logout">注销</a>&nbsp;&nbsp;
    <a href="<%=basePath2%>user/toindex">返回</a>
</div>