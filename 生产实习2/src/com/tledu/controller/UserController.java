package com.tledu.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tledu.QRcode.QRCodeUtil;
import com.tledu.commom.AjaxResult;
import com.tledu.model.Book;
import com.tledu.model.User;
import com.tledu.service.IBookService;
import com.tledu.service.IUserService;
import com.tledu.util.FaceUtil;
import com.tledu.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IBookService bookService;

    @RequestMapping("/toRegist")
    public String toRegist(){

        return "user/regist";
    }
    @RequestMapping("/checkUserNameUnique")
    @ResponseBody
    public AjaxResult checkUserNameUnique(String username){
        User user=userService.checkUserNameUnique(username);
        AjaxResult result=new AjaxResult();
        result.setSuccess(true);
        if(user !=null){
            result.setSuccess(false);
        } return result;
    }
    //注册成功
    @RequestMapping("/toRegistSuccess")
    public String toRegistSuccesss(){
        return "user/regist_success";
    }
    @RequestMapping("/regist")
    @ResponseBody
    public AjaxResult regist(User user, HttpSession session){
        AjaxResult result=new AjaxResult();
        if(user.getCode().equals(session.getAttribute("key"))){//二维码数值
            try {
                userService.add(user);
                session.setAttribute("loginUser",user);
                result.setSuccess(true);
            }catch (Exception e){
                result.setSuccess(false);
                result.setMesg(e.getMessage());
            }
        }else {
            result.setSuccess(false);
            result.setMesg("验证码不正确");
        }
        return result;
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "index";
    }

    @RequestMapping("/tologin")
    public String indext( ){
        return "user/login";
    }
    //到图书信息首页
    //cpage当前页
    @RequestMapping("/toindex")
    public String toindex(Integer cpage,Integer min,Integer max,Model model){
        cpage=cpage==null?1:cpage;
        Integer start=(cpage-1)*4;
        PageHelper.offsetPage(start,4);//8=pagesize
        Map<String,Object> map=new HashMap<>();
        map.put("min",min);
        map.put("max",max);
        List<Book> blist=bookService.getBookList(map);

        PageInfo info=new PageInfo(blist);
        long total = info.getTotal();//总条数
        Long totalpage=(total/4)+(total%4==0?0:1);
        model.addAttribute("blist",blist);
        model.addAttribute("totalpage",totalpage);
        model.addAttribute("cpage",cpage);
        model.addAttribute("total",total);
        model.addAttribute("min",min);
        model.addAttribute("max",max);
        return "index";
    }
    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(User user,HttpSession session){
        AjaxResult result=new AjaxResult();
       User u=userService.grtUserByUserName(user);
        System.out.println(u);
        if(u !=null){

            if(u.getPassword().equals(user.getPassword())) {
                System.out.println(u);
                session.setAttribute("loginUser",u);
                result.setSuccess(true);
                result.setMesg("登录成功");
            }else {
                result.setSuccess(false);
                result.setMesg("密码不正确");
        }}else {
            result.setSuccess(false);
            result.setMesg("用户名不存在");
        }return result;
        }
//    tologinSuccess
    @RequestMapping("/tologinSuccess")
    public String  tologinSuccess( ){
        return "user/login_success";
    }
    @RequestMapping("/tologinSuccess2")
    public String  tologinSuccess2( ){

        return "user/login_success";
    }

    /**
     * 获取所有的face_token用户
     */
    @RequestMapping("/getFaceTokenUser")
    @ResponseBody
    public AjaxResult getFaceTokenUser(){
        AjaxResult result = new AjaxResult();
        try {
            List<User> ulist = userService.getUserList();
            result.setObj(ulist);
            System.out.println(ulist.get(0));
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMesg(e.getMessage());
        }
        return result;
    }
}


