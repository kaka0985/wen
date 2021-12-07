package com.tledu.controller;

import com.tledu.util.FaceUtil;
import com.tledu.util.ImageUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/faceController")
public class FaceController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //步骤：
        // 1、获取客户上传的图片--上传到指定的文件夹中upimg
        File file = ImageUtils.uploadImg(request, "imgData", "upimg");
        String faceToken2=null;
        boolean res = false;
        boolean delFlag = true;
        try {
            //2、判断是否包含人脸信息： detect
            String faceToken = FaceUtil.detect(file);
            if (faceToken != null) {// 2.1 包含：
                // 在faceset中查找是否有相似度高的人脸信息： search
                res = FaceUtil.search(faceToken);//有：登录成功；删除照片 没有：登录失 败；删除照片
                System.out.println(faceToken2);
                // 判定用户请求的类型
                String type=request.getParameter("type");
                if("register".equals(type)) {//如果是注册
                    if(res) {//有：已经注册过;删除照片
                        res=false;
                    }else {//没有：可以注册，添加facetoken到faceset中；保留照片
                        res=FaceUtil.addFace(faceToken);
                        delFlag=false;//不删除照片
                    }
                }
              }else{// 2.2 不包含：登录失败；删除照片
            }
           } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //删除照片
            if(delFlag) {
                file.delete();
            }
            //返回数据给客户端 response
            PrintWriter pw=response.getWriter();
            String msg="{\"success\":"+res+"}";
            pw.write(faceToken2);
            pw.write(msg); pw.close();
       }
    }
}
