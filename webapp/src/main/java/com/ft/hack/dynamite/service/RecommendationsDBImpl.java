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
        List<Recommendation> byCompany = UserActivityDAO.getUserActivityByCompany(companyName,null);
        Map<String, Object> results = new HashMap<String, Object>();

        results.put("companyName", companyName);
        results.put("byCompany", byCompany);

        return results;
    }

    @Override
    public Map<String, Object> getRecommendationsForSector(String sectorName) {
        List<Recommendation> bySector = UserActivityDAO.getUserActivityBySector(sectorName,null);
        Map<String, Object> results = new HashMap<String, Object>();

        results.put("sectorName", sectorName);
        results.put("bySector", bySector);

        return results;
    }

    @Override
    public Map<String, Object> getRecommendationsForPosition(String positionName) {
        List<Recommendation> byPosition = UserActivityDAO.getUserActivityBySector(positionName,null);
        Map<String, Object> results = new HashMap<String, Object>();

        results.put("sectorName", positionName);
        results.put("bySector", byPosition);

        return results;
    }

    @Override
    public Map<String, Object> getRecommendationsForQuery(Query query) {
        List<Recommendation> byCompany = UserActivityDAO.getUserActivityByCompany(query.getCompanyName(),null);
        List<Recommendation> bySector = UserActivityDAO.getUserActivityBySector(query.getSectorName(),null);
        List<Recommendation> byPosition = UserActivityDAO.getUserActivityByPosition(query.getPositionName(),null);

        Map<String, Object> results = new HashMap<String, Object>();
        results.put("byCompany",byCompany);
        results.put("bySector",bySector);
        results.put("byPosition",byPosition);
        results.put("companyName",query.getCompanyName());
        results.put("positionName",query.getPositionName());
        results.put("sectorName",query.getSectorName());

        return results;
    }
}
