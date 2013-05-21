package com.ft.hack.dynamite.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: anuragkapur
 * Date: 21/05/2013 04:49
 */

public class DateHelper {

    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyy:HH:mm:ss +0100");

    public static int getDayBucket(String timestamp) {
        int result = -1;

        try {
            Date date = (Date)formatter.parse(timestamp);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            String monthStr = (month < 10)?"0"+month:""+month;
            int day = calendar.get(Calendar.DATE);
            String dayStr = (day < 10)?"0"+day:""+day;

            result = Integer.parseInt(year + monthStr + dayStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result ;
    }

    public static int getHourBucket(String timestamp) {
        int result = -1;

        try {
            Date date = (Date)formatter.parse(timestamp);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            String monthStr = (month < 10)?"0"+month:""+month;
            int day = calendar.get(Calendar.DATE);
            String dayStr = (day < 10)?"0"+day:""+day;
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            String hourStr = (hour < 10)?"0"+hour:""+hour;

            result = Integer.parseInt(year + monthStr + dayStr + hourStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result ;
    }

    public static void main(String args[]) {

        System.out.println(DateHelper.getDayBucket("08/May/2013:01:24:07 +0100"));
        System.out.println(DateHelper.getHourBucket("08/May/2013:01:24:07 +0100"));
    }
}
