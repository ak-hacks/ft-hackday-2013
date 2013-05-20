package com.ft.hack.dynamite;

import java.io.*;
import java.util.StringTokenizer;

/**
 * User: anuragkapur
 * Date: 20/05/2013 15:07
 */

public class WeblogProcessor {

    private String filePath = "/Users/anuragkapur/Tech_Stuff/ft/weblog_data/stats.ft.com.20130508";

    public void processFile(String filePath) {
        String strLine = "";

        try {
            FileInputStream fstream = new FileInputStream(filePath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            while ((strLine = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(strLine,"=");
                int uuidStartIndex = strLine.indexOf(".uuid=");

                if(uuidStartIndex > 0) {
                    System.out.println(strLine);
                    String uuid = strLine.substring(uuidStartIndex,uuidStartIndex+6+36);
                    System.out.println(uuid);
                }
            }
        } catch (IOException e) {
            System.out.println(strLine);
            e.printStackTrace();
        }
    }
}