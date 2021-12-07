package com.tledu.interceptor;

import com.tledu.model.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter  {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = (User) request.getSession().getAttribute("loginUser");
        String requestType = request.getHeader("X-Requested-With");
        if(user == null){
            if("XMLHttpRequest".equals(requestType)){
                response.getWriter().write("{\"isLogin\":\"false\"}");
            }else{
                request.getRequestDispatcher("/WEB-INF/pages/user/login.jsp").forward(request, response);
            }
            return false;
        }
        return true;

    }
}
