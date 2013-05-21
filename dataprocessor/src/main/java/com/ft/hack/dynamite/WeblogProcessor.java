package com.ft.hack.dynamite;

import com.ft.hack.dynamite.db.ArticleDAO;
import com.ft.hack.dynamite.db.CountersDAO;
import com.ft.hack.dynamite.db.UserDAO;

import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * User: anuragkapur
 * Date: 20/05/2013 15:07
 */

public class WeblogProcessor {

    private static final String filePath = "/Users/anuragkapur/Tech_Stuff/ft/weblog_data/stats.ft.com.20130508";

    public void processFile(String filePath) {
        String strLine = "";

        try {
            FileInputStream fstream = new FileInputStream(filePath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            while ((strLine = br.readLine()) != null) {
                try {
                    String uuid = "";
                    String pid = "";

                    StringTokenizer tokenizer = new StringTokenizer(strLine,"=");
                    int uuidStartIndex = strLine.indexOf(".uuid=");
                    int pid1StartIndex = strLine.indexOf(".pid_s=");
                    int pid2StartIndex = strLine.indexOf("_PID=");

                    boolean meaningfulRecord = false;

                    if(uuidStartIndex > 0) {
                        uuid = strLine.substring(uuidStartIndex+6,uuidStartIndex+6+36);

                        // check if we got a meaningful uuid
                        if (!uuid.startsWith("undefined")) {
                            if(pid1StartIndex > 0) {
                                pid = strLine.substring(pid1StartIndex+7, strLine.indexOf("&",pid1StartIndex));
                                if(!pid.equalsIgnoreCase("undefined") && !pid.equals("")) {
                                    meaningfulRecord = true;
                                }
                            }

                            if(pid2StartIndex > 0 && !meaningfulRecord) {
                                pid = strLine.substring(pid2StartIndex+5, strLine.indexOf("_TIME", pid2StartIndex));
                                if(!pid.equalsIgnoreCase("undefined") && !pid.equals("")) {
                                    meaningfulRecord = true;
                                }
                            }

                            if (meaningfulRecord) {
                                String timestamp = strLine.substring(strLine.indexOf("[")+1,strLine.indexOf("]"));
                                processTuple(timestamp, uuid, pid);
                            }

                        }
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processTuple(String timestamp, String uuid, String pid) {
        System.out.println("Processing :: " + timestamp);

        // Lookup user info based on uuid
        Map<String,String> userData = UserDAO.getUser(pid);


        if(userData != null) {
            // Update "sector" demographic counters
            String sectorName = userData.get("sectorName");
            if(sectorName != null && !sectorName.equalsIgnoreCase("N/A") && !sectorName.equals("")) {
                CountersDAO.updateSectorCounter(sectorName, uuid, timestamp);
            }

            // Update "company" demographic counters
            String companyName = userData.get("companyName");


            // Update "position" demographic counters

            // Update article content table
            ArticleDAO.addArticle(uuid);
        }
    }

    public static void main(String args[]) {
        WeblogProcessor processor = new WeblogProcessor();
        processor.processFile(filePath);
    }
}