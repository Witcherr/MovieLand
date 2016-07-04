package com.potopalskyi.movieland.controller;

import com.potopalskyi.movieland.entity.User;
import com.potopalskyi.movieland.entity.UserCredential;
import com.potopalskyi.movieland.entity.exception.NoDataFoundException;
import com.potopalskyi.movieland.service.AuthorizationService;
import com.potopalskyi.movieland.service.UserService;
import com.potopalskyi.movieland.util.ConverterJson;
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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConverterJson converterJson;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> authorizeUser(@RequestBody String json) {
        logger.info("Start process of getting movies with search params  = {}", json);
        long startTime = System.currentTimeMillis();
        UserCredential userCredential = converterJson.toUserCredential(json);
        User user;
        try {
            user = userService.getUserByName(userCredential.getName());
        }catch (NoDataFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if( authorizationService.checkUserCredential(userCredential, user)){
            String token = authorizationService.generateToken(user);
            logger.info("For user = {} was generated token = {}. It took {} ms", userCredential.getName(), token, System.currentTimeMillis() - startTime);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
