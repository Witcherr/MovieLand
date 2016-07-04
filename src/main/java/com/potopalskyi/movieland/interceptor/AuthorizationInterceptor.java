package com.potopalskyi.movieland.interceptor;

import com.potopalskyi.movieland.entity.annotation.RoleTypeRequired;
import com.potopalskyi.movieland.service.AuthorizationService;
import com.potopalskyi.movieland.util.ConverterJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RoleTypeRequired roleTypeRequired = handlerMethod.getMethod().getAnnotation(RoleTypeRequired.class);
        return roleTypeRequired == null || authorizationService.checkRightsForRequest(request.getHeader("token"), roleTypeRequired);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}
