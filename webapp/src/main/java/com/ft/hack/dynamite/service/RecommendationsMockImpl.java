package com.ft.hack.dynamite.service;

import com.ft.hack.dynamite.model.Query;
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
    public Map<String, Object> getRecommendationsForUser(String userId) {

        String companyName = "Financial Times";
        String sectorName = "Media";
        String positionName = "Editor";

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

        Map<String, Object> results = new HashMap<String, Object>();
        results.put("companyName",companyName);
        results.put("byCompany",byCompany);
        results.put("sectorName",sectorName);
        results.put("bySector",bySector);
        results.put("positionName",positionName);
        results.put("byPosition",byPosition);

        return results;
    }

    @Override
    public Map<String, Object> getRecommendationsForCompany(String companyName) {
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

        Map<String, Object> results = new HashMap<String, Object>();
        results.put("byCompany",byCompany);
        results.put("companyName",companyName);

        return results;
    }

    @Override
    public Map<String, Object> getRecommendationsForSector(String sectorName) {
        List<Recommendation> bySector = new ArrayList<Recommendation>();
        Recommendation recommendation1 = new Recommendation();
        recommendation1.setArticleTitle("Test article 1");
        recommendation1.setArticleUrl("http://www.ft.com/bla1");
        recommendation1.setPublishedDate("2013:05:20 12:00");

        Recommendation recommendation2 = new Recommendation();
        recommendation2.setArticleTitle("Test article 2");
        recommendation2.setArticleUrl("http://www.ft.com/bla1");
        recommendation2.setPublishedDate("2013:05:20 12:00");

        bySector.add(recommendation1);
        bySector.add(recommendation2);

        Map<String, Object> results = new HashMap<String, Object>();
        results.put("bySector",bySector);
        results.put("sectorName",sectorName);

        return results;
    }

    @Override
    public Map<String, Object> getRecommendationsForPosition(String positionName) {
        List<Recommendation> byPosition = new ArrayList<Recommendation>();
        Recommendation recommendation1 = new Recommendation();
        recommendation1.setArticleTitle("Test article 1");
        recommendation1.setArticleUrl("http://www.ft.com/bla1");
        recommendation1.setPublishedDate("2013:05:20 12:00");

        Recommendation recommendation2 = new Recommendation();
        recommendation2.setArticleTitle("Test article 2");
        recommendation2.setArticleUrl("http://www.ft.com/bla1");
        recommendation2.setPublishedDate("2013:05:20 12:00");

        byPosition.add(recommendation1);
        byPosition.add(recommendation2);

        Map<String, Object> results = new HashMap<String, Object>();
        results.put("byPosition",byPosition);
        results.put("positionName",positionName);

        return results;
    }

    public Map<String, Object> getRecommendationsForQuery(Query query) {
        Map<String, Object> results = getRecommendationsForUser(null);
        results.put("query", query);
        return results;
    }
}
