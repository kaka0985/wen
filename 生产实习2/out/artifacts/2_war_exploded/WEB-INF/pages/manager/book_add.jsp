<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<link type="text/css" rel="stylesheet" href="<%=basePath%>static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="<%=basePath%>static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
			<%@include file="../common/manager_common.jsp" %>
		</div>
		
		<div id="main">
			<form action="<%=basePath%>manager/addBook" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>名称</td>
						<td><input name="name" type="text" /></td>
						<td style="text-align: left">
							选择图片：<br/>
							<input type="file" id="xdaTanFileImg" name="file" onchange="xmTanUploadImg(this)" accept="image/*"/>
						</td>
						<td rowspan="2">
							预&nbsp;&nbsp;&nbsp;&nbsp;览:<br/>
							<img id="xmTanImg"  class="img-circle" style="width: 150px; height: 150px;" />
						</td>
					</tr>
					<tr>
						<td>价格</td>
						<td><input name="price" type="text" /></td>
						<td></td>
					</tr>
					<tr>
						<td>作者</td>
						<td><input name="author" type="text" /></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>销量</td>
						<td><input name="sales" type="text" /></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>库存</td>
						<td><input name="stock" type="text" /></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: right"><input type="submit" value="提交"/></td>
					</tr>
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
			<span>
				天亮书城.Copyright &copy;2015
			</span>
		</div>
</body>
<script type="text/javascript" src="<%=basePath%>static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("form").submit(function(){
			var filePath = $("input[name='file']").val();
			if(filePath!=""&&filePath!=null){
				var extStart = filePath.lastIndexOf(".");
				var ext = filePath.substring(extStart,filePath.length).toUpperCase();
				if(ext!=".png" && ext!=".PNG" && ext!=".jpg" && ext!=".JPG"){
					alert("必须是图片格式！");
					return false;
				}
			}else{
				alert("请上传图书图片！");
				return false;
			};
		})
	})
	//选择图片，马上预览
	function xmTanUploadImg(obj) {
		var file = obj.files[0];

		console.log(obj);
		console.log(file);
		console.log("file.size = " + file.size); //file.size 单位为byte

		var reader = new FileReader();
		//读取文件过程方法
		reader.onloadstart = function(e) {
			console.log("开始读取....");
		}

		reader.onprogress = function(e) {
			console.log("正在读取中....");
		}
		reader.onabort = function(e) {
			console.log("中断读取....");
		}
		reader.onerror = function(e) {
			console.log("读取异常....");
		}
		reader.onload = function(e) {
			console.log("成功读取....");
			var img = document.getElementById("xmTanImg");
			img.src = e.target.result;
			//或者 img.src = this.result;  //e.target == this
		}
		reader.readAsDataURL(file)
	}
</script>
</html>