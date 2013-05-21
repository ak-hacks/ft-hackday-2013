package com.ft.hack.dynamite.db;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.ft.hack.dynamite.util.DateHelper;

import java.util.ResourceBundle;

/**
 * User: anuragkapur
 * Date: 20/05/2013 22:51
 */

public class CountersDAO {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("db");

    public static void updateSectorCounter(String sectorName, String uuid, String timestamp) {

        sectorName = tableNameCleanup(sectorName);

        Session session = SimpleClient.getSession();
        String sectorCounterTableName = null;
        String query = "SELECT * FROM ftdynamite.sectors WHERE sector_name='" + sectorName + "'";
        Row row = session.execute(query).one();
        if (row != null) {
            // Sector exists
            long count = row.getLong("count");
            if (count >= 1) {
                sectorCounterTableName = "ftdynamite.useractivity_sector_" + sectorName;
            }
        } else {
            // New sector record
            session.execute("UPDATE ftdynamite.sectors SET count=count+1 WHERE sector_name='" + sectorName + "'");
            // create sector_sectorName table
            sectorCounterTableName = "ftdynamite.useractivity_sector_" + sectorName;
            query = String.format("CREATE TABLE %s (article_id varchar, date bigint, count counter, PRIMARY KEY(article_id, date))",sectorCounterTableName);
            session.execute(query);
        }

        if (sectorCounterTableName != null) {
            // Update counters

            // day counter
            query = String.format("UPDATE %s SET count=count+1 WHERE article_id='%s' AND date=%d", sectorCounterTableName, uuid, DateHelper.getDayBucket(timestamp));
            session.execute(query);

            // hour counter
            query = String.format("UPDATE %s SET count=count+1 WHERE article_id='%s' AND date=%d", sectorCounterTableName, uuid, DateHelper.getHourBucket(timestamp));
            session.execute(query);

            // 5 min bucket counter
            //TODO
        }
    }

    private static String tableNameCleanup(String tableName) {
        return tableName.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
    }
}
