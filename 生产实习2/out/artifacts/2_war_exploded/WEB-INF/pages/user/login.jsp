<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>天亮会员登录页面</title>
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<link type="text/css" rel="stylesheet" href="<%=basePath%>static/css/style.css" >
</head>
<body>
<div id="login_header">
	<img class="logo_img" alt="" src="<%=basePath%>static/img/logo.gif" >
</div>

<div class="login_banner">

	<div id="l_content">
		<span class="login_word">欢迎登录</span>
		<video id="video" width="150px" height="150px" autoplay="autoplay"></video>
		<canvas id="canvas" width="150px" height="150px" style="display: none"></canvas>
		<img id="imgTag" src="" alt="..." width="150px" height="150px" style="display: none">
	</div>

	<div id="content">
		<div class="login_form">
			<div class="login_box">
				<div class="tit">
					<h1>天亮会员</h1>
					<a href="<%=basePath%>dispatch/toRegist">立即注册</a>
				</div>
				<div class="msg_cont">
					<b></b>
					<span class="errorMsg"></span>
				</div>
				<div class="form">
					<form action="login_success.jsp">
						<label>用户名称：</label>
						<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" id="username" name="username" />
						<br />
						<br />
						<label>用户密码：</label>
						<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"  id="password" name="password" />
						<br />
						<br />
						<label>人脸登录：</label>
						<button type="button" onclick="openMedia()">开启摄像头</button>
						<button type="button" onclick="takePhoto('login')">人脸检索</button>
						<br />
						<br />
						<input type="button" value="登录" id="sub_btn" />
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
<script type="text/javascript" >
	let mediaStreamTrack=null; // 视频对象(全局)
	let video ;
	$(function (){
		$("#sub_btn").click(function (){
			var username=$("#username").val();
			var password=$("#password").val();
			// alert(username +"="+ password);
			$.ajax({
				url:"<%=basePath%>user/login",
				type:"post",
				data:{'username':username,'password':password},
				success:function (obj){
					if(obj.success){
						location.href="<%=basePath%>user/tologinSuccess";
					}else {
						$(".errorMsg").text(obj.mesg);
					}
				},
				dataType: "json"
			})
		})

	})
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
					if(str == 'login'){
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
												alert("登录成功");
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