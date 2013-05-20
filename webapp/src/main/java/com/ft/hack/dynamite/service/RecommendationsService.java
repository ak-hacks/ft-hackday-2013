package com.ft.hack.dynamite.service;

import com.ft.hack.dynamite.model.Query;

import java.util.Map;

/**
 * User: anuragkapur
 * Date: 20/05/2013 12:37
 */

public interface RecommendationsService {

    public Map<String, Object> getRecommendationsForUser(String userId);
    public Map<String, Object> getRecommendationsForCompany(String companyName);
    public Map<String, Object> getRecommendationsForSector(String sectorName);
    public Map<String, Object> getRecommendationsForPosition(String positionName);
    public Map<String, Object> getRecommendationsForQuery(Query query);
}