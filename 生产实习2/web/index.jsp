
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//  request.getSchema()可以返回当前页面使用的协议，就是上面例子中的“http”
//  request.getServerName()可以返回当前页面所在的服务器的名字，就是上面例子中的“localhost"
//  request.getServerPort()可以返回当前页面所在的服务器使用的端口,就是8080，
//  request.getContextPath()可以返回当前页面所在的应用的名字，就是上面例子中的myblog
%>
<!DOCTYPE html>
<html >
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta charset="utf-8"/>
  <title>温佳诺</title>

  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

  <!-- bootstrap & fontawesome -->
  <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css"/>

  <!-- page specific plugin styles -->

  <!-- text fonts -->
  <link rel="stylesheet" href="assets/css/fonts.googleapis.com.css"/>

  <!-- ace styles -->
  <link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style"/>

  <!--[if lte IE 9]>
  <link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet"/>
  <![endif]-->
  <link rel="stylesheet" href="assets/css/ace-skins.min.css"/>
  <link rel="stylesheet" href="assets/css/ace-rtl.min.css"/>

  <!--[if lte IE 9]>
  <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
  <![endif]-->

  <!-- inline styles related to this page -->

  <!-- ace settings handler -->
  <script src="assets/js/ace-extra.min.js"></script>

  <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

  <!--[if lte IE 8]>
  <script src="assets/js/html5shiv.min.js"></script>
  <script src="assets/js/respond.min.js"></script>
  <![endif]-->

  <!--[if !IE]> -->
  <script src="assets/js/jquery-2.1.4.min.js"></script>

  <!-- <![endif]-->

  <!--[if IE]>
  <script src="assets/js/jquery-1.11.3.min.js"></script>
  <![endif]-->
  <script src="assets/js/bootstrap.min.js"></script>

  <!-- page specific plugin scripts -->
  <script src="assets/js/jquery.dataTables.min.js"></script>
  <script src="assets/js/jquery.dataTables.bootstrap.min.js"></script>
  <script src="assets/js/dataTables.buttons.min.js"></script>
  <script src="assets/js/buttons.flash.min.js"></script>
  <script src="assets/js/buttons.html5.min.js"></script>
  <script src="assets/js/buttons.print.min.js"></script>
  <script src="assets/js/buttons.colVis.min.js"></script>
  <script src="assets/js/dataTables.select.min.js"></script>

  <!-- ace scripts -->
  <script src="assets/js/ace-elements.min.js"></script>
  <script src="assets/js/ace.min.js"></script>

</head>

<body class="no-skin">

<div class="main-container ace-save-state" id="main-container">

  <div class="main-content">
    <div class="main-content-inner">

      <div class="page-content">

        <div style="padding: 10px;">
          <table>
            <tr>
              <td colspan="2">
                <button class="btn btn-sm btn-default" onclick="openMedia()">开启摄像头</button>
                <button class="btn btn-sm btn-default" onclick="closeMedia()">关闭摄像头</button>
                <button class="btn btn-sm btn-default" onclick="takePhoto('login')">登录</button>
                <button class="btn btn-sm btn-default" onclick="takePhoto('register')">注册</button>
              </td>
            </tr>
          </table>
          <table>
            <tr>
              <td>
                <video id="video" width="500px" height="500px" autoplay="autoplay"></video>
                <canvas id="canvas" width="500px" height="500px" style="display: none"></canvas>
              </td>
              <td>
                <img id="imgTag" src="" alt="..." width="500px" height="500px"><br>
              </td>
            </tr>
          </table>
        </div>

      </div><!-- /.page-content -->
    </div>
  </div><!-- /.main-content -->

</div><!-- /.main-container -->

</body>
<script>
  let mediaStreamTrack=null; // 视频对象(全局)
  let video ;
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
    ctx.drawImage(video, 0, 0, 500, 500);
    // toDataURL  ---  可传入'image/png'---默认, 'image/jpeg'
    let img = document.getElementById('canvas').toDataURL();
    // 这里的img就是得到的图片
    console.log('img-----', img);
    document.getElementById('imgTag').src=img;
    // alert(img);
    // alert(str);
    //上传
    var flagStr="登录";
    if(str=="register"){
      flagStr="注册";
    }
    $.ajax({
      url:"<%=basepath%>faceController",
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
    });

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
