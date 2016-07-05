package com.potopalskyi.movieland.controller;

import com.potopalskyi.movieland.entity.param.ReviewAlterParam;
import com.potopalskyi.movieland.security.entity.RoleTypeRequired;
import com.potopalskyi.movieland.entity.enums.RoleType;
import com.potopalskyi.movieland.security.SecurityService;
import com.potopalskyi.movieland.service.ReviewService;
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
@RequestMapping(value = "/v1/review")
public class ReviewController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConverterJson converterJson;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ReviewService reviewService;

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addReview(@RequestBody String json, HttpServletRequest request) {
        logger.info("Start process of adding review = {} ", json);
        long startTime = System.currentTimeMillis();
        ReviewAlterParam reviewAlterParam = converterJson.toReviewAlterParam(json);
        if (!reviewAlterParam.isCorrectParams()) {
            return new ResponseEntity<>("You should send correct review", HttpStatus.BAD_REQUEST);
        }
        String token = request.getHeader("token");
        if (!securityService.checkAlterPermission(token, reviewAlterParam.getAuthorId())) {
            return new ResponseEntity<>("You can not add review for other user", HttpStatus.FORBIDDEN);
        }
        reviewService.addReview(reviewAlterParam);
        logger.info("End process of adding review = {}. It took {} ms", System.currentTimeMillis() - startTime);
        return new ResponseEntity<>("Your review was successfully added", HttpStatus.OK);
    }

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteReview(@RequestBody String json, HttpServletRequest request) {
        ReviewAlterParam reviewAlterParam = converterJson.toReviewAlterParam(json);
        if (!reviewAlterParam.isCorrectParams()) {
            return new ResponseEntity<>("You should send correct review", HttpStatus.BAD_REQUEST);
        }
        String token = request.getHeader("token");
        if (!securityService.checkAlterPermission(token, reviewAlterParam.getAuthorId())) {
            return new ResponseEntity<>("You can not delete review of other user", HttpStatus.FORBIDDEN);
        }
        reviewService.deleteReview(reviewAlterParam);
        return new ResponseEntity<>("Your review was successfully added", HttpStatus.OK);
    }
}
