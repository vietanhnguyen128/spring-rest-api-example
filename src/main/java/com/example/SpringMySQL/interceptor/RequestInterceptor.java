package com.example.SpringMySQL.interceptor;

import com.example.SpringMySQL.model.Login;
import com.example.SpringMySQL.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginServiceImpl loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Will reuse later
        String uri = request.getPathInfo();
        String token = request.getParameter("token"); //get parameter from url: ex: /user?token=ABCXYZ

        if (token != null) {
            Optional<Login> tokenData = Optional.ofNullable(loginService.findByToken(token));
            if (tokenData.isPresent()) {
                return true;
            }
        }
        response.setStatus(HttpStatus.FORBIDDEN.value());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}