package com.ft.hack.dynamite.service;

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
}