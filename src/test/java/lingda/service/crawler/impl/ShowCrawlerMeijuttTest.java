package lingda.service.crawler.impl;

import lingda.model.TVShow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;

/**
 * Created by lingda on 12/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShowCrawlerMeijuttTest {

    @Autowired
    private ShowCrawlerMeijutt showCrawlerMeijutt;

    @Test
    public void searchShow(){
        String doc = showCrawlerMeijutt.searchShow(new TVShow("实习医生格蕾", "Grey's Anatomy", "", 1, 1));
    }
}
