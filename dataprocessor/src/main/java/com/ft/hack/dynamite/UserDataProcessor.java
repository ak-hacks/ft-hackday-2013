package com.ft.hack.dynamite;

import java.io.*;
import java.util.StringTokenizer;

/**
 * User: anuragkapur
 * Date: 21/05/2013 03:37
 */

public class UserDataProcessor {

    private static final String filePath = "/Users/anuragkapur/Tech_Stuff/ft/user_data/hackdayusers.txt";

    public void processFile(String filePath) {
        String strLine = "";

        try {
            FileInputStream fstream = new FileInputStream(filePath);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            while ((strLine = br.readLine()) != null) {
                //System.out.println(strLine);
                StringTokenizer tokenizer = new StringTokenizer(strLine,"   ");
                System.out.println(" >> " + tokenizer.nextToken());
                System.out.println(" >>>> " + tokenizer.nextToken());
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        UserDataProcessor processor = new UserDataProcessor();
        processor.processFile(filePath);
    }
}
