package com.potopalskyi.movieland.interceptor;

import com.potopalskyi.movieland.service.AuthorizationService;
import com.potopalskyi.movieland.util.ConverterJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    ConverterJson converterJson;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        int i = 0;
        String token = request.getHeader("token");
        if(!authorizationService.checkRightsForRequest(request.getHeader("token"))){
            //response;
            return false;
        }
        i++;
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}
