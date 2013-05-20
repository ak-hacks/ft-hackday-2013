package test.com.ft.hack.dynamite.db;

import com.ft.hack.dynamite.db.SimpleClient;
import com.ft.hack.dynamite.db.UserActivityDAO;
import org.junit.Test;

/**
 * User: anuragkapur
 * Date: 20/05/2013 18:09
 */

public class UserActivityDAOTest {
    @Test
    public void testGetUserActivityByCompany() throws Exception {
        SimpleClient client = new SimpleClient();
        UserActivityDAO dao = new UserActivityDAO();
        dao.getUserActivityByCompany("financial_times","last5mins");
        dao.getUserActivityByCompany("financial_times","last1hour");
    }
}
