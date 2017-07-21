package lingda.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import lingda.service.crawler.tvshow.ShowCrawler;

import java.io.IOException;

/**
 * Created by lingda on 11/11/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShowCrawlerTest {

    @Autowired
    private ShowCrawler showCrawler;

    @Test
    public void testFetchHTML() throws IOException {
//        showCrawler.fetchHTML();
    }
}
