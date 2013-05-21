package com.ft.hack.dynamite.db;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * User: anuragkapur
 * Date: 21/05/2013 04:05
 */

public class UserDAO {

    public static Map<String, String> getUser(String pid) {

        Session session = SimpleClient.getSession();
        ResultSet results = session.execute("SELECT * FROM  ftdynamite.user_demographics WHERE passport_id = " + pid);
        Row row = results.one();

        if(row != null) {
            String companyName = row.getString("business_nm");
            String sectorName = row.getString("industry");
            String position = row.getString("position");

            Map<String, String> userData = new HashMap<String, String>();
            userData.put("companyName",companyName);
            userData.put("sectorName", sectorName);
            userData.put("position", position);

            return userData;
        }else {
            return null;
        }
    }
}
