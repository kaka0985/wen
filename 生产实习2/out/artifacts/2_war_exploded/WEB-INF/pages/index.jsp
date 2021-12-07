<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>书城首页</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>static/css/style.css" >
</head>
<body>

<div id="header">
	<img class="logo_img" alt="" src="<%=basePath%>static/img/logo.gif" >
	<span class="wel_word">网上书城</span>
	<div>
		<a href="<%=basePath%>user/tologin">登录</a> |
		<a href="<%=basePath%>user/toRegist">注册</a> &nbsp;&nbsp;
		<a href="<%=basePath%>manager/toShopCar">购物车</a>
		<a href="<%=basePath%>manager/toManager">后台管理</a>
	</div>
</div>
<div id="main">
	<div id="book">
		<div class="book_cond">
			<form action="<%=basePath%>user/toindex" method="post">
				价格：<input id="min" type="text" name="min" value="${min}"> 元 -
				<input id="max" type="text" name="max" value="${max}"> 元
				<input type="submit" value="查询" />
			</form>
		</div>
		<div style="text-align: center">
			<span>您的购物车中有${sessionScope.num}件商品</span>
			<div>
				您刚刚将<span style="color: red">${sessionScope.name}</span>加入到了购物车中
			</div>
		</div>
		<c:forEach items="${blist}" var="b">
		<div class="b_list">
			<div class="img_div">
				<img class="book_img" alt="" src="<%=basePath%>${b.img_path}" />
			</div>
			<div class="book_info">
				<div class="book_name">
					<span class="sp1">书名:</span>
					<span class="sp2">${b.name}</span>
				</div>
				<div class="book_author">
					<span class="sp1">作者:</span>
					<span class="sp2">${b.author}</span>
				</div>
				<div class="book_price">
					<span class="sp1">价格:</span>
					<span class="sp2">￥${b.price}</span>
				</div>
				<div class="book_sales">
					<span class="sp1">销量:</span>
					<span class="sp2">${b.sales}</span>
				</div>
				<div class="book_amount">
					<span class="sp1">库存:</span>
					<span class="sp2">${b.stock}</span>
				</div>
				<div class="book_add">
					<button onclick="addCar(${b.id})">加入购物车</button>
				</div>
			</div>
		</div>
		</c:forEach>

	</div>



</div>

<div id="page_nav">
	<a href="<%=basePath%>user/toindex?cpage=1&min=${min}&max=${max}">首页</a>
	<a href="<%=basePath%>user/toindex?cpage=${cpage==1?1:(cpage-1)}&min=${min}&max=${max}">上一页</a>
	<c:if test="${cpage != 1}">
		<a href="<%=basePath%>user/toindex?cpage=${(cpage-1)}&min=${min}&max=${max}"></a>
	</c:if>
	【${cpage}】
	<c:if test="${cpage != totalpage}">
		<a href="<%=basePath%>user/toindex?cpage=${(cpage+1)}&min=${min}&max=${max}">${(cpage+1)}</a>
	</c:if>
	<a href="#"></a>
	<a href="<%=basePath%>user/toindex?cpage=${cpage==totalpage?totalpage:(cpage+1)}&min=${min}&max=${max}">下一页</a>
	<a href="<%=basePath%>user/toindex?cpage=${totalpage}&min=${min}&max=${max}">末页</a>
	共${totalpage}页，${total}条记录 到第<input value="" name="pn" id="pn_input"/>页
	<input type="button" onclick="Jump()" value="确定">
</div>
<br/>
<div id="bottom">
		<span>
			天亮书城.Copyright &copy;2021
		</span>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>static/js/jquery-1.7.2.js"></script>
<script type="text/javascript" >
	function addCar(id){
		$.ajax({
			url:"<%=basePath%>manager/addCar?id="+id,
			type:"post",
			dataType:"json",
			success:function (obj){
				if(obj.success){
					alert(obj.mesg);
					location.reload();
				}else{
					alert(obj.mesg);
				}

			}
		})
	}
	function Jump() {
		var val = document.getElementById('pn_input').value.trim()
		if (val<1||val>${totalpage}){
			alert("请输入正确的页数");
		}else {
		location.href = "<%=basePath%>user/toindex?cpage="+val;
	}}

</script>
</html>