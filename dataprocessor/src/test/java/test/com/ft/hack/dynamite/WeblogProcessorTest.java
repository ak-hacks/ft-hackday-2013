package test.com.ft.hack.dynamite;

import com.ft.hack.dynamite.WeblogProcessor;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * User: anuragkapur
 * Date: 20/05/2013 15:14
 */

public class WeblogProcessorTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testProcessFile() throws Exception {
        String filePath = "/Users/anuragkapur/Tech_Stuff/ft/weblog_data/stats.ft.com.20130508";
        WeblogProcessor processor = new WeblogProcessor();
        processor.processFile(filePath);

        Assert.assertTrue(true);
    }
}
