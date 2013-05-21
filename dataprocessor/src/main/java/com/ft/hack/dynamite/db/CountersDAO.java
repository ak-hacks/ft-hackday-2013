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

    public static void updateCompanyCounter(String companyName, String uuid, String timestamp) {

        companyName = tableNameCleanup(companyName);

        Session session = SimpleClient.getSession();
        String companyCounterTableName = null;
        String query = "SELECT * FROM ftdynamite.companies WHERE company_name='" + companyName + "'";
        Row row = session.execute(query).one();
        if (row != null) {
            // company exists
            long count = row.getLong("count");
            if (count >= 1) {
                companyCounterTableName = "ftdynamite.useractivity_company_" + companyName;
            }
        } else {
            // New company record
            session.execute("UPDATE ftdynamite.companies SET count=count+1 WHERE company_name='" + companyName + "'");
            // create company_companyName table
            companyCounterTableName = "ftdynamite.useractivity_company_" + companyName;
            query = String.format("CREATE TABLE %s (article_id varchar, date bigint, count counter, PRIMARY KEY(article_id, date))", companyCounterTableName);
            session.execute(query);
        }

        if (companyCounterTableName != null) {
            // Update counters

            // day counter
            query = String.format("UPDATE %s SET count=count+1 WHERE article_id='%s' AND date=%d", companyCounterTableName, uuid, DateHelper.getDayBucket(timestamp));
            session.execute(query);

            // hour counter
            query = String.format("UPDATE %s SET count=count+1 WHERE article_id='%s' AND date=%d", companyCounterTableName, uuid, DateHelper.getHourBucket(timestamp));
            session.execute(query);

            // 5 min bucket counter
            //TODO
        }
    }

    public static void updatePositionCounter(String positionName, String uuid, String timestamp) {

        positionName = tableNameCleanup(positionName);

        Session session = SimpleClient.getSession();
        String positionCounterTableName = null;
        String query = "SELECT * FROM ftdynamite.positions WHERE position_name='" + positionName + "'";
        Row row = session.execute(query).one();
        if (row != null) {
            // position exists
            long count = row.getLong("count");
            if (count >= 1) {
                positionCounterTableName = "ftdynamite.useractivity_position_" + positionName;
            }
        } else {
            // New position record
            session.execute("UPDATE ftdynamite.position SET count=count+1 WHERE position_name='" + positionName + "'");
            // create position_positionName table
            positionCounterTableName = "ftdynamite.useractivity_position_" + positionName;
            query = String.format("CREATE TABLE %s (article_id varchar, date bigint, count counter, PRIMARY KEY(article_id, date))", positionCounterTableName);
            session.execute(query);
        }

        if (positionCounterTableName != null) {
            // Update counters

            // day counter
            query = String.format("UPDATE %s SET count=count+1 WHERE article_id='%s' AND date=%d", positionCounterTableName, uuid, DateHelper.getDayBucket(timestamp));
            session.execute(query);

            // hour counter
            query = String.format("UPDATE %s SET count=count+1 WHERE article_id='%s' AND date=%d", positionCounterTableName, uuid, DateHelper.getHourBucket(timestamp));
            session.execute(query);

            // 5 min bucket counter
            //TODO
        }
    }

    private static String tableNameCleanup(String tableName) {
        return tableName.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
    }
}
