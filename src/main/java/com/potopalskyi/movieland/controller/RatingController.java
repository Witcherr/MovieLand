package com.potopalskyi.movieland.controller;

import com.potopalskyi.movieland.entity.param.RatingParam;
import com.potopalskyi.movieland.security.entity.RoleTypeRequired;
import com.potopalskyi.movieland.entity.enums.RoleType;
import com.potopalskyi.movieland.security.SecurityService;
import com.potopalskyi.movieland.service.RatingService;
import com.potopalskyi.movieland.util.ConverterJson;
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

    @Autowired
    private ConverterJson converterJson;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RatingService ratingService;

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addReview(@RequestBody String json, HttpServletRequest request) {
        RatingParam ratingParam = converterJson.toRatingParam(json);
        String token = request.getHeader("token");
        if (!securityService.checkAlterPermission(token, ratingParam.getAuthorId())) {
            return new ResponseEntity<>("You can not add rating for other user", HttpStatus.FORBIDDEN);
        }
        if(ratingService.addRating(ratingParam)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
