package com.potopalskyi.movieland.controller;

import com.potopalskyi.movieland.entity.annotation.RoleTypeRequired;
import com.potopalskyi.movieland.entity.enums.RoleType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/v1/review")
public class ReviewController {

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping( method = RequestMethod.POST)
    public void addReview(){

    }

    @RoleTypeRequired(role = RoleType.USER)
    @RequestMapping( method = RequestMethod.DELETE)
    public void removeReview(){

    }

}
