package lingda.service.crawler.impl;

import lingda.model.TVShow;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public void shouldGetSomethingFromSearchShow() {
        Map<String, String> showToUrlMap = showCrawlerMeijutt.searchShow(new TVShow(null, "实习医生格蕾", "Grey's Anatomy", "", 1, 1));
        for (Map.Entry<String, String> entry : showToUrlMap.entrySet()) {
            System.out.println(entry);
        }
        Assert.assertTrue(showToUrlMap.size() > 0);
    }

    @Test
    public void shouldGetDownloadLinks() {
        showCrawlerMeijutt.getDownloadLinks(new TVShow(null, "实习医生格蕾", "Grey's Anatomy", "", 1, 1));
    }

    @Test
    public void test() {
        Optional<String> stringOptional = Optional.ofNullable("hi");
        System.out.println(stringOptional.flatMap(s -> Optional.of(s.toUpperCase())));
    }
}
