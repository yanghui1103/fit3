package com.bw.fit.common.interceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bw.fit.system.dao.SystemDao;

import static com.bw.fit.common.util.PubFun.*;

public class ControllerAuthInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private SystemDao systemDao ;
    @Override
    public boolean preHandle(HttpServletRequest request, 
            HttpServletResponse response, Object handler)throws Exception {
        boolean flag= true;
        
        return flag;
    }
}
