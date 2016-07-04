package com.potopalskyi.movieland.controller;

import com.potopalskyi.movieland.entity.param.ReviewAlterParam;
import com.potopalskyi.movieland.entity.annotation.RoleTypeRequired;
import com.potopalskyi.movieland.entity.enums.RoleType;
import com.potopalskyi.movieland.service.SecurityService;
import com.potopalskyi.movieland.service.ReviewService;
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
@RequestMapping(value = "/v1/review")
public class ReviewController {

    @Autowired
    private ConverterJson converterJson;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ReviewService reviewService;

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addReview(@RequestBody String json, HttpServletRequest request) {
        ReviewAlterParam reviewAlterParam = converterJson.toReviewAddParam(json);
        String token = request.getHeader("token");
        if (!securityService.checkUserForAltering(token, reviewAlterParam.getAuthorId())) {
            return new ResponseEntity<>("You cann't add review for other user", HttpStatus.FORBIDDEN);
        }
        if (reviewService.addReview(reviewAlterParam)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteReview(@RequestBody String json, HttpServletRequest request) {
        ReviewAlterParam reviewAlterParam = converterJson.toReviewAddParam(json);
        String token = request.getHeader("token");
        if (!securityService.checkUserForAltering(token, reviewAlterParam.getAuthorId())) {
            return new ResponseEntity<>("You cann't delete review of other user", HttpStatus.FORBIDDEN);
        }
        if (reviewService.deleteReview(reviewAlterParam)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
