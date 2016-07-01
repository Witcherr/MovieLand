package com.potopalskyi.movieland.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/v1/review")
public class ReviewController {

    @RequestMapping( method = RequestMethod.POST)
    public void addReview(){

    }

    @RequestMapping( method = RequestMethod.DELETE)
    public void removeReview(){

    }

}
