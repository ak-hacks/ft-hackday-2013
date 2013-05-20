package com.ft.hack.dynamite.service;


import com.ft.hack.dynamite.domain.Story;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: anuragkapur
 * Date: 20/05/2013 21:12
 */

public class FTContentService {

    private static final Logger LOGGER = Logger.getLogger(FTContentService.class);

    public static Story getContent(String uuid) {

        String response = executeGet("http://api.ft.com/content/items/v1/" + uuid + "?aspects=title,location,lifecycle&apiKey=ff7c49ce801f2c21bfa2de598a55f8c1");
        Object obj = JSONValue.parse(response);

        Story story = null;

        if(obj.getClass().equals(JSONObject.class)) {
            try {
                JSONObject jsonResponse = (JSONObject)obj;
                JSONObject jsonItem = (JSONObject)jsonResponse.get("item");
                JSONObject jsonTitle = (JSONObject)jsonItem.get("title");
                JSONObject jsonLocation = (JSONObject)jsonItem.get("location");
                JSONObject jsonLifecycle = (JSONObject)jsonItem.get("lifecycle");
                String title = (String)jsonTitle.get("title");
                String uri = (String)jsonLocation.get("uri");
                String publishedDate = (String)jsonLifecycle.get("lastPublishDateTime");

                story = new Story();
                story.setTitle(title);
                story.setUrl(uri);
                story.setPublishedDate(publishedDate);
                story.setUuid(uuid);

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return story;
    }

    private static String executeGet(String targetURL) {
        URL url;
        HttpURLConnection connection = null;
        String result = null;
        try {
            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                buffer.append(line);
            }
            rd.close();
            result = buffer.toString();
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Story story = getContent("460f0c42-b711-11e2-841e-00144feabdc0");
        System.out.println("Story :: " + story.getTitle() + " :: " + story.getPublishedDate() + " :: " + story.getUrl());
    }
}
