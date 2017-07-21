package lingda.service.crawler.tvshow.impl;

import lingda.model.dto.DoubanDTO;
import lingda.service.crawler.RatingCrawler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by lingda on 12/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RatingDTOCrawlerDoubanImplTest {

    @Autowired
    private RatingCrawler ratingCrawlerDoubanImpl;

    @Test
    public void shouldGetRating() {
        Optional<DoubanDTO> doubanDTO =  ratingCrawlerDoubanImpl.searchRatingByName("英雄第一季");
        System.out.println(doubanDTO);
    }

}
