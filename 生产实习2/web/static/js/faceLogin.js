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
    ctx.drawImage(video, 0, 0, 150, 150);


    // toDataURL  ---  可传入'image/png'---默认, 'image/jpeg'
    let img = document.getElementById('canvas').toDataURL();
    // 这里的img就是得到的图片
    console.log('img-----', img);
    document.getElementById('imgTag').src=img;
    alert(img);
    alert(str);
    //上传
    var flagStr="登录";
    if(str=="register"){
        flagStr="注册";
    }

    /**
     * 开始
     */
    var formdata = new FormData();//创建一个新的FormData对象
    //Ajax中的 data 属性就是 formdata
    formdata.append('api_key','PXdmpcuX59A1NAdsmlknPxOJLcLoVzxJ'); //使用append的方法为 formdata 对象赋值
    formdata.append('api_secret','0Q21pyqcRB1Rqi-ZslX51BvZiJZ9qDix');
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
            alert(123)
            //判断被识别的照片是一个人脸，不能是动物或多个脸
            if(res.error_message == undefined & res.face_num == 1){
                //获取返回的face_token字符串
                var face_token = res.faces[0].face_token;
                if(str == 'login'){
                    //获取数据库中的face_token与当前的face_token进行对比
                    $.ajax({
                        url:"<%=basePath%>user/login",
                        type: 'get',
                        async: false,
                        success: function (data){
                            var ulist = data.obj;
                            for(var i = 0;i<ulist.length;i++){
                                var u = ulist[i];
                                var token1 = u.face_token;
                                //传入face++比对接口进行比对
                                var formdata2 = new FormData();
                                formdata2.append('api_key','PXdmpcuX59A1NAdsmlknPxOJLcLoVzxJ'); //使用append的方法为 formdata 对象赋值
                                formdata2.append('api_secret','0Q21pyqcRB1Rqi-ZslX51BvZiJZ9qDix');
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
                                        //alert(res.confidence);
                                        if(res.confidence > 85){
                                            alert("登录成功");
                                            closeMedia();
                                            location.href = "http://localhost:8080/book/dispatch/faceLogin?id="+u.id;
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