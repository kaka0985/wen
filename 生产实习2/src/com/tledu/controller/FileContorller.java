package com.tledu.controller;

import com.tledu.commom.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RequestMapping("/file")
@Controller
public class FileContorller {

    @RequestMapping("/upload")
    public String upload(MultipartFile file, Model model) throws Exception {
//        System.out.println(file.getOriginalFilename());
        String filename=file.getOriginalFilename();
        String filePaeh="D:\\idea\\生产实习2\\web\\static\\update\\";
        filePaeh+=filename;
//        InputStream inputStream = file.getInputStream();
//        OutputStream out=new FileOutputStream(new File(filePaeh));
//        int len=0;
//        byte[] bytes=new byte[1024];
//        while ((len=inputStream.read(bytes))!=-1){
//            out.write(bytes,0,len);
//        }
//        out.flush();
//        out.close();
//        inputStream.close();
        file.transferTo(new File(filePaeh));
        String imgPath="\\update\\"+filename;
        model.addAttribute("imgPath",imgPath);
        return "img";
    }
    @RequestMapping("/upload2")
    @ResponseBody
    public AjaxResult upload2(MultipartFile file, String name) throws Exception {
        String filename=file.getOriginalFilename();
        String filePaeh="D:\\idea\\生产实习2\\web\\static\\update\\";
        filePaeh+=filename;
        System.out.println("姓名="+name);
        file.transferTo(new File(filePaeh));
        AjaxResult result= new AjaxResult();
        String imgPath="\\update\\"+filename;
        result.setMesg(imgPath);
        return  result;
    }
    @RequestMapping("/downLoad")
    public void downLoad(String filename, HttpServletResponse response) throws Exception {
        String filePath="D:\\idea\\生产实习2\\web\\static\\update"+"\\"+filename;
        File file=new File(filePath);
        InputStream in=new BufferedInputStream(new FileInputStream(file));
        byte[] buffer=new byte[in.available()];
        in.read(buffer);
        in.close();
        response.reset();
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        os.write(buffer);// 输出文件
        os.flush();
        os.close();
    }
}
