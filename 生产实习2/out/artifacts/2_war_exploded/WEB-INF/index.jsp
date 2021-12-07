
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//  request.getSchema()可以返回当前页面使用的协议，就是上面例子中的“http”
//  request.getServerName()可以返回当前页面所在的服务器的名字，就是上面例子中的“localhost"
//  request.getServerPort()可以返回当前页面所在的服务器使用的端口,就是8080，
//  request.getContextPath()可以返回当前页面所在的应用的名字，就是上面例子中的myblog
%>
<html>
<head>
  <title>$Title$</title>
</head>
<script type="text/javascript" src="<%=basepath%>static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">

   function upload(){
     console.log("文件上传")
     var  file=$("#file").prop("files");
     if(file.length==0){
       alert("请上传文件");
       return;
     }
     var  file=$("#file").prop("files")[0];
     var formData = new FormData();
     formData.append("file",file);
     formData.append("name", "tom");
     $.ajax({
       url: "<%=basepath%>file/upload2",
       type: "POST",
       data: formData,
       // 告诉jQuery不要去设置Content-Type请求头
       contentType: false,
       // 告诉jQuery不要去处理发送的数据
       processData: false,
       success:function (res) {
         console.log(res);
         $("#imageBox").append(
                    <div>
                        <img src="<%=basepath%>' + res.mesg + '"/>
                    </div>
         )
       }
     });
   }
</script>
<body>
<form action="file/upload" method="post" enctype="multipart/form-data">
  文件：<input type="file" name="file" id="file">
    <input type="submit">
    <input type="button" value="ajax上传" onclick="upload()">
</form>
<div id="imageBox"></div>
</body>
</html>