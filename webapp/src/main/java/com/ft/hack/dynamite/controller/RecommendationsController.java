package com.ft.hack.dynamite.controller;

import com.ft.hack.dynamite.model.Query;
import com.ft.hack.dynamite.service.RecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * User: anuragkapur
 * Date: 20/05/2013 10:49
 */

@Controller
@RequestMapping("/recommend")
public class RecommendationsController {

    @Autowired
    private View jsonView;

    private RecommendationsService recommendationsService;

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ModelAndView getRecommendationsForUser(@PathVariable("userId") String userId) {

        Map<String, Object> results = recommendationsService.getRecommendationsForUser(userId);
        return new ModelAndView(jsonView, "recommendationForUser", results);
    }

    @RequestMapping(value = "/company/{companyName}", method = RequestMethod.GET)
    public ModelAndView getRecommendationsForCompany(@PathVariable("companyName") String companyName) {

        Map<String, Object> results = recommendationsService.getRecommendationsForCompany(companyName);
        return new ModelAndView(jsonView, "recommendationForUser", results);
    }

    @RequestMapping(value = { "/query/" }, method = { RequestMethod.POST })
    public ModelAndView getRecommendationForQuery(@RequestBody Query query,
                                                  HttpServletResponse httpResponse, WebRequest request){

        Map<String, Object> results = recommendationsService.getRecommendationsForQuery(query);
        return new ModelAndView(jsonView, "recommendationsForUser", results);
    }

    public RecommendationsService getRecommendationsService() {
        return recommendationsService;
    }

    public void setRecommendationsService(RecommendationsService recommendationsService) {
        this.recommendationsService = recommendationsService;
    }
}
