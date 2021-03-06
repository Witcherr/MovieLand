package com.potopalskyi.movieland.interceptor;

import com.potopalskyi.movieland.security.entity.RoleTypeRequired;
import com.potopalskyi.movieland.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RoleTypeRequired roleTypeRequired = handlerMethod.getMethod().getAnnotation(RoleTypeRequired.class);
        if(roleTypeRequired != null && !securityService.checkRightsForRequest(request.getHeader("token"), roleTypeRequired)){
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
