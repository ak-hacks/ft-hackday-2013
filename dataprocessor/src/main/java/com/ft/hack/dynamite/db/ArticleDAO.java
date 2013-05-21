package com.ft.hack.dynamite.db;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.ft.hack.dynamite.domain.Story;
import com.ft.hack.dynamite.service.FTContentService;

/**
 * User: anuragkapur
 * Date: 21/05/2013 09:23
 */

public class ArticleDAO {

    public static void addArticle(String articleId) {

        // check if the article record already exists
        Session session = SimpleClient.getSession();
        String query = String.format("SELECT * FROM ftdynamite.articles WHERE article_id='%s'",articleId);
        Row row = session.execute(query).one();
        if(row == null) {
            // add if it doesn't
            Story story = FTContentService.getContent(articleId);
            query = String.format("INSERT INTO ftdynamite.articles (article_id, title, url, published_date) VALUES ('%s','%s','%s','%s')",story.getUuid(), story.getTitle(), story.getUrl(), story.getPublishedDate());
            session.execute(query);
        }
    }
}
