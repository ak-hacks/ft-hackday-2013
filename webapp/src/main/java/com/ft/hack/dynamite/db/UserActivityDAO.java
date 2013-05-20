package com.ft.hack.dynamite.db;

import java.util.Calendar;
import java.util.Date;

/**
 * User: anuragkapur
 * Date: 20/05/2013 17:52
 */

public class UserActivityDAO {

    public void getUserActivityByCompany(String companyName, String timeFilter) {

        Date fromDate = null;
        Date toDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if(timeFilter.equals("last5mins")) {
            int newMinute = minute - minute%5;

            calendar.set(Calendar.MINUTE, newMinute);
            calendar.set(Calendar.SECOND, 0);
            toDate = calendar.getTime();

            calendar.add(Calendar.MINUTE, -5);
            fromDate = calendar.getTime();

            System.out.println(toDate.toString() + " :: " + fromDate.toString() );

        }else if(timeFilter.equals("last1hour")) {
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            toDate = calendar.getTime();

            calendar.add(Calendar.HOUR, -1);
            fromDate = calendar.getTime();
            System.out.println(toDate.toString() + " :: " + fromDate.toString() );
        }
        //SimpleClient.getSession().execute("");
    }
}
