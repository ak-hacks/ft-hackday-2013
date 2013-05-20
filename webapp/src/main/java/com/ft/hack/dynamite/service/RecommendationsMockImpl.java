package com.ft.hack.dynamite.service;

import com.ft.hack.dynamite.model.Recommendation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: anuragkapur
 * Date: 20/05/2013 12:44
 */

public class RecommendationsMockImpl implements RecommendationsService {

    @Override
    public Map<String, List<Recommendation>> getRecommendationsForUser(String userId) {
        List<Recommendation> byCompany = new ArrayList<Recommendation>();
        Recommendation recommendation1 = new Recommendation();
        recommendation1.setArticleTitle("Test article 1");
        recommendation1.setArticleUrl("http://www.ft.com/bla1");
        recommendation1.setPublishedDate("2013:05:20 12:00");

        Recommendation recommendation2 = new Recommendation();
        recommendation2.setArticleTitle("Test article 2");
        recommendation2.setArticleUrl("http://www.ft.com/bla1");
        recommendation2.setPublishedDate("2013:05:20 12:00");

        byCompany.add(recommendation1);
        byCompany.add(recommendation2);

        List<Recommendation> bySector = new ArrayList<Recommendation>();
        bySector.add(recommendation1);
        bySector.add(recommendation2);

        List<Recommendation> byPosition = new ArrayList<Recommendation>();
        byPosition.add(recommendation1);
        byPosition.add(recommendation2);

        Map<String, List<Recommendation>> results = new HashMap<String, List<Recommendation>>();
        results.put("byCompany",byCompany);
        results.put("bySector",bySector);
        results.put("byPosition",byPosition);

        return results;
    }
}
