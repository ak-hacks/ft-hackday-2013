package com.ft.hack.dynamite.db;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.ft.hack.dynamite.model.Recommendation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: anuragkapur
 * Date: 20/05/2013 17:52
 */

public class UserActivityDAO {

    public static List<Recommendation> getUserActivityByCompany(String companyName, String timeFilter) {

        String query = "";
        return null;
    }

    public static List<Recommendation> getUserActivityBySector(String sectorName, String timeFilter) {

        List<Recommendation> bySector = new ArrayList<Recommendation>();

        String query = String.format("SELECT * FROM sectors WHERE sector_name='%s'",sectorName);
        Session session = SimpleClient.getSession();
        Row row = session.execute(query).one();
        if(row != null) {
            // Info for this sector exists
            query = String.format("SELECT * FROM useractivity_sector_%s",sectorName);
            ResultSet results = session.execute(query);
            for (Row row1 : results) {
                Recommendation recommendation = ArticleDAO.getArticle(row1.getString("article_id"));
                row1.getString("article_id");

                //TODO sort by count and use that to return top 5 or 10
                row1.getLong("count");
                bySector.add(recommendation);
            }
        }else {
            // no info exists
        }
        return bySector;
    }

    public static List<Recommendation> getUserActivityByPosition(String companyName, String timeFilter) {

        String query = "";
        return null;
    }

    public static void processTimeFilter(String timeFilter) {

        Date fromDate = null;
        Date toDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if(timeFilter!= null && timeFilter.equals("last5mins")) {
            int newMinute = minute - minute%5;

            //hack in absence of enough data
            calendar.set(Calendar.YEAR, 2013);
            calendar.set(Calendar.DATE, 8);
            calendar.set(Calendar.MONTH, Calendar.MAY);
            calendar.set(Calendar.HOUR_OF_DAY, 12);

            calendar.set(Calendar.MINUTE, newMinute);
            calendar.set(Calendar.SECOND, 0);
            toDate = calendar.getTime();

            calendar.add(Calendar.MINUTE, -5);
            fromDate = calendar.getTime();

            System.out.println(toDate.toString() + " :: " + fromDate.toString() );

        } else if(timeFilter!= null && timeFilter.equals("last1hour")) {
            //hack in absence of enough data
            calendar.set(Calendar.YEAR, 2013);
            calendar.set(Calendar.DATE, 8);
            calendar.set(Calendar.MONTH, Calendar.MAY);
            calendar.set(Calendar.HOUR_OF_DAY, 12);

            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            toDate = calendar.getTime();

            calendar.add(Calendar.HOUR, -1);
            fromDate = calendar.getTime();
            System.out.println(toDate.toString() + " :: " + fromDate.toString() );
        } else {
            //hack in absence of enough data
            calendar.set(Calendar.YEAR, 2013);
            calendar.set(Calendar.DATE, 8);
            calendar.set(Calendar.MONTH, Calendar.MAY);
            calendar.set(Calendar.HOUR_OF_DAY, 23);

            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            toDate = calendar.getTime();
            calendar.add(Calendar.HOUR, -23);

            fromDate=calendar.getTime();
            System.out.println(toDate.toString() + " :: " + fromDate.toString() );
        }
    }

    public static void main(String args[]) {
        UserActivityDAO.getUserActivityByCompany("apple", null);
    }
}
