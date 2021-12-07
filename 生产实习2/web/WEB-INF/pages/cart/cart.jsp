<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

<link type="text/css" rel="stylesheet" href="<%=basePath%>static/css/style.css" >
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="<%=basePath%>static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<div>
				<span>欢迎<span class="um_span">Mark</span>光临天亮书城</span>
				<a href="../order/order.jsp">我的订单</a>
				<a href="index.html">注销</a>&nbsp;&nbsp;
				<a href="index.html">返回</a>
			</div>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${sessionScope.carlist}" var="b">
			<tr>
				<td>${b.name}</td>
				<td><button onclick="deletecount(${b.id},${b.count},${b.stock})">-</button>
						${b.count}
					<button onclick="addcount(${b.id},${b.count},${b.stock})">+</button>
				</td>
				<td>${b.price}</td>
				<td>${b.totalPrice}</td>
				<td><a href="<%=basePath%>manager/deleteCarById?id=${b.id}">删除</a></td>
			</tr>
			</c:forEach>
		</table>
		
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.num}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${total}</span>元</span>
			<span class="cart_span"><a href="<%=basePath%>manager/deleteCarAll">清空购物车</a></span>
			<span class="cart_span"><a href="<%=basePath%>manager/checkOut">去结账</a></span>
		</div>
	
	</div>
	
	<div id="bottom">
		<span>
			天亮书城.Copyright &copy;2015
		</span>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>static/js/jquery-1.7.2.js"></script>
<script type="text/javascript" >
	function deletecount(id,count,stock){
		count = count - 1;
		if(count == 0){
			alert("再减就没有了哦");
		}else{
			location.href = "<%=basePath%>manager/changeCarCount?id="+id+"&count="+count;
		}
	}
	function addcount(id,count,stock){
		count = count + 1;
		if(count > stock){
			alert("购买数量不能大于总库存，总库存为"+stock);
		}else{
			location.href = "<%=basePath%>manager/changeCarCount?id="+id+"&count="+count;
		}
	}
</script>
</html>