package com.ft.hack.dynamite.db;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.ft.hack.dynamite.model.Recommendation;

/**
 * User: anuragkapur
 * Date: 21/05/2013 08:44
 */

public class ArticleDAO {

    public static Recommendation getArticle(String articleId) {
        String query = String.format("SELECT * FROM ftdynamite.articles WHERE article_id='%s'",articleId);
        Session session = SimpleClient.getSession();

        Row row = session.execute(query).one();
        Recommendation recommendation = null;

        if(row != null) {
            recommendation = new Recommendation();
            recommendation.setArticleTitle(row.getString("title"));
            recommendation.setArticleUrl(row.getString("url"));
            recommendation.setPublishedDate(row.getString("published_date"));
        }

        return recommendation;
    }
}
