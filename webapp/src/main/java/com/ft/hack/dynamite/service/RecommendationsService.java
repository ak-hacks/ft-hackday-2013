package com.ft.hack.dynamite.service;

import com.ft.hack.dynamite.model.Recommendation;

import java.util.List;
import java.util.Map;

/**
 * User: anuragkapur
 * Date: 20/05/2013 12:37
 */

public interface RecommendationsService {

    public Map<String, List<Recommendation>> getRecommendationsForUser(String userId);
}
