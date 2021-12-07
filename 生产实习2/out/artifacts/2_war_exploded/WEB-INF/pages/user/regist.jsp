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
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>

<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="<%=basePath%>static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
					<video id="video" width="150px" height="150px" autoplay="autoplay"></video>
					<canvas id="canvas" width="150px" height="150px" style="display: none"></canvas>
					<img id="imgTag" src="" alt="..." width="150px" height="150px" style="display: none">
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册天亮会员</h1>
								<span class="errorMsg"></span>
							</div>
							<div class="form">
								<form action="">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" id="code"/>
									<img alt="" src="<%=basePath%>static/code.jsp" id="imgCode" style="float: right; margin-right: 40px">
									<br />
									<br />
									<label>人脸登录：</label>
									<button type="button" onclick="openMedia()">开启摄像头</button>
									<button type="button" onclick="takePhoto('register')">人脸检索</button>
									<br />
									<br />
									<br/>
									<input type="button" value="注册" id="sub_btn" onclick="regist()" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				天亮书城.Copyright &copy;2015
			</span>
		</div>
</body>
<script type="text/javascript" src="<%=basePath%>static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">

	//给验证码绑定点击事件
	$(function (){
		$("#imgCode").click(function (){
			var url = '<%=basePath%>static/code.jsp?tm='+Math.random();
			$("#imgCode").attr("src",url);
		})
	})

	// 注册页面按如下要求实现功能
	// 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
	// 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
	// 验证确认密码：和密码相同
	// 邮箱验证：xxxxx@xxx.com
	function regist(){
		//获取表单的每一项值
		var username = $("#username").val();
		var password = $("#password").val();
		var repwd = $("#repwd").val();
		var email = $("#email").val();
		var code = $("#code").val();
		//正则表达式
		var user_pwd_parrten = /^\w{5,12}$/;
		var email_parrten = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;

		//验证用户名的格式是否正确
		if(!user_pwd_parrten.test(username)){
			$(".errorMsg").text("用户名格式不正确");
			$("#username").focus();
			return;
		}else{
			//验证用户名唯一
			var flag = true;
			$.ajax({
				url:"<%=basePath%>user/checkUserNameUnique",
				type:"post",
				async:false, //ajax默认是异步请求，我们设置为同步
				data:{'username':username},
				success:function (obj){
					if(!obj.success){
						flag = false;
					}
				},
				dataType:"json"
			})
			if(!flag){
				$(".errorMsg").text("用户名不唯一");
				$("#username").focus();
				return;
			}
		}
		//验证密码格式
		if(!user_pwd_parrten.test(password)){
			$(".errorMsg").text("密码格式不正确");
			$("#password").focus();
			return;
		}
		//重复密码与密码是否一致
		if(repwd != password){
			$(".errorMsg").text("重复密码不正确");
			$("#repwd").focus();
			return;
		}
		//验证邮箱格式
		if(!email_parrten.test(email)){
			$(".errorMsg").text("邮箱格式不正确");
			$("#email").focus();
			return;
		}
		//验证码不为空
		if(code == ""){
			$(".errorMsg").text("验证码不能为空");
			$("#code").focus();
			return;
		}

		//给后台提交注册的信息
		$.ajax({
			url:"<%=basePath%>user/regist",
			type:"post",
			data:{"username":username,"password":password,"email":email,"code":code},
			success:function (obj){
				if(obj.success){
					location.href="<%=basePath%>user/toRegistSuccess";
				}else{
					$(".errorMsg").text(obj.mesg);
				}
			},
			dataType: "json"
		})


	}

	let mediaStreamTrack=null; // 视频对象(全局)
	let video ;
	//开启摄像头
	function openMedia() {
		let constraints = {
			video: { width: 500, height: 500 },
			audio: false
		};
		//获得video摄像头
		video = document.getElementById('video');
		let promise = navigator.mediaDevices.getUserMedia(constraints);
		promise.then((mediaStream) => {
			// mediaStreamTrack = typeof mediaStream.stop === 'function' ? mediaStream : mediaStream.getTracks()[1];
			mediaStreamTrack=mediaStream.getVideoTracks()
			video.srcObject = mediaStream;
			video.play();
		});
	}

	// 拍照
	function takePhoto(str) {

		//获得Canvas对象
		let video = document.getElementById('video');
		let canvas = document.getElementById('canvas');
		let ctx = canvas.getContext('2d');
		ctx.drawImage(video, 0, 0, 150, 150);


		// toDataURL  ---  可传入'image/png'---默认, 'image/jpeg'
		let img = document.getElementById('canvas').toDataURL();
		// 这里的img就是得到的图片
		console.log('img-----', img);
		document.getElementById('imgTag').src=img;
		// alert(img);
		// alert(str);
		//上传
		/**
		 * 开始
		 */
		var formdata = new FormData();//创建一个新的FormData对象
		//Ajax中的 data 属性就是 formdata
		formdata.append('api_key','LfOHefo9aJhRWluxgS9KkSkXmuTcuyMQ'); //使用append的方法为 formdata 对象赋值
		formdata.append('api_secret','giLWl2AvTyz7WdDXlSchz7WqZQwEQ7a9');
		formdata.append('image_base64',img);
		$.ajax({
			type:'post',
			url:'https://api-cn.faceplusplus.com/facepp/v3/detect',
			async:false,
			contentType:false,
			processData:false,
			cache:false, //根据需要定义
			data:formdata,
			success:function(res){
				//判断被识别的照片是一个人脸，不能是动物或多个脸
				if(res.error_message == undefined & res.face_num == 1){
					//获取返回的face_token字符串
					var face_token = res.faces[0].face_token;
					if(str == 'register'){
						//获取数据库中的face_token与当前的face_token进行对比
						$.ajax({
							url:"<%=basePath%>user/getFaceTokenUser",
							type: 'get',
							async: false,
							success: function (data){
								alert(data.obj)
								var ulist = data.obj;
								for(var i = 0;i<ulist.length;i++){
									var u = ulist[i];
									var token1 = u.face_token;
									//传入face++比对接口进行比对
									var formdata2 = new FormData();
									formdata2.append('api_key','LfOHefo9aJhRWluxgS9KkSkXmuTcuyMQ'); //使用append的方法为 formdata 对象赋值
									formdata2.append('api_secret','giLWl2AvTyz7WdDXlSchz7WqZQwEQ7a9');
									formdata2.append('face_token1',token1);
									formdata2.append('face_token2',face_token);
									$.ajax({
										type:'post',
										url:'https://api-cn.faceplusplus.com/facepp/v3/compare',
										async:false,
										contentType:false,
										processData:false,
										data:formdata2,
										success:function(res){
											alert(res.confidence);
											if(res.confidence > 85){
												alert("已经注册");
												closeMedia();
												location.href = "<%=basePath%>user/tologinSuccess?id="+u.id;
											}
										}
									})
								}
							}
						})

					}else{
						$("#face_token").val(face_token);
						alert("人脸检索成功!");
						closeMedia();
					}

				}else{
					alert("人脸检索失败!");
				}
			}
		})
		/**
		 * 结束
		 */

		/*$.ajax({
            url:"faceController",
            type:"POST",
            data:{"imgData":img,"type":str},
            dataType: "json",
            success:function(data){
                var flag = data.success;
                if (flag) {
                    alert(flagStr+"成功");
                } else {
                    alert(flagStr+"失败!");
                }
            }
            ,error:function(){
                alert("服务器内部错误！"+flagStr+"失败")
            }
        });*/

	}

	// 关闭摄像头
	function closeMedia() {
		let stream = document.getElementById('video').srcObject;
		let tracks = stream.getTracks();

		tracks.forEach(function(track) {
			track.stop();
		});

		document.getElementById('video').srcObject = null;
	}
</script>

</html>