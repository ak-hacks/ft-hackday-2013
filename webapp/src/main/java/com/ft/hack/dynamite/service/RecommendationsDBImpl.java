package com.ft.hack.dynamite.service;

import com.ft.hack.dynamite.db.UserActivityDAO;
import com.ft.hack.dynamite.db.UserDAO;
import com.ft.hack.dynamite.model.Query;
import com.ft.hack.dynamite.model.Recommendation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: anuragkapur
 * Date: 21/05/2013 08:27
 */

public class RecommendationsDBImpl implements RecommendationsService{

    @Override
    public Map<String, Object> getRecommendationsForUser(String userId) {

        Map<String, Object> results = new HashMap<String, Object>();

        Map<String, String> userData = UserDAO.getUser(userId);

        List<Recommendation> byCompany = UserActivityDAO.getUserActivityByCompany(userData.get("companyName"),null);
        List<Recommendation> bySector = UserActivityDAO.getUserActivityBySector(userData.get("sectorName"), null);
        List<Recommendation> byPosition = UserActivityDAO.getUserActivityByPosition(userData.get("positionName"), null);

        results.put("companyName",userData.get("companyName"));
        results.put("sectorName",userData.get("sectorName"));
        results.put("positionName",userData.get("positionName"));
        results.put("byCompany",byCompany);
        results.put("bySector",bySector);
        results.put("byPosition",byPosition);

        return results;
    }

    @Override
    public Map<String, Object> getRecommendationsForCompany(String companyName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, Object> getRecommendationsForSector(String sectorName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, Object> getRecommendationsForPosition(String positionName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, Object> getRecommendationsForQuery(Query query) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
