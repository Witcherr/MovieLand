package com.potopalskyi.movieland.controller;

import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.security.entity.RoleTypeRequired;
import com.potopalskyi.movieland.entity.enums.RoleType;
import com.potopalskyi.movieland.security.SecurityService;
import com.potopalskyi.movieland.service.RatingService;
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

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/v1/rate")
public class RatingController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConverterJson converterJson;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RatingService ratingService;

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addRating(@RequestBody String json, HttpServletRequest request) {
        logger.info("Start process of adding rating = {} ", json);
        long startTime = System.currentTimeMillis();
        RatingParam ratingParam = converterJson.toRatingParam(json);
        if(!ratingParam.isCorrectParams()){
            return new ResponseEntity<>("Please check authorId, movieId and don't forget, rating should be between 1 and 10", HttpStatus.BAD_REQUEST);
        }
        String token = request.getHeader("token");
        if (!securityService.checkAlterPermission(token, ratingParam.getAuthorId())) {
            return new ResponseEntity<>("You can not add rating for other user", HttpStatus.FORBIDDEN);
        }
        ratingService.addRating(ratingParam);
        logger.info("End process of adding rating = {}. It took {} ms", json, System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
