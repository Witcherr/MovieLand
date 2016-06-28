package com.potopalskyi.movieland.controller;

import com.potopalskyi.movieland.entity.UserCredential;
import com.potopalskyi.movieland.entity.exception.NoDataFoundException;
import com.potopalskyi.movieland.service.AuthorizationService;
import com.potopalskyi.movieland.util.ConvertJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1")
public class AuthorizationController {

    private Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    @Autowired
    private ConvertJson convertJson;

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> authorizateUser(@RequestBody String json){
        UserCredential userCredential = convertJson.toUserCredential(json);
        boolean isCorrectUserCredential;
        try {
           isCorrectUserCredential = authorizationService.checkUserCredential(userCredential);
        }catch (NoDataFoundException e){
            logger.warn("Incorrect credential :" + json);
            return new ResponseEntity<>("Incorrect credential :", HttpStatus.BAD_REQUEST);
        }
        if(isCorrectUserCredential){
            return new ResponseEntity<>(authorizationService.generateToken(userCredential), HttpStatus.OK);
        }
        return new ResponseEntity<>("Problems with logging", HttpStatus.BAD_REQUEST);
    }
}
