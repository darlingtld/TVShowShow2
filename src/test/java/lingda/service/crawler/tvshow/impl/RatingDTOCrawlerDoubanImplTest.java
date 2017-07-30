package lingda.service.crawler.tvshow.impl;

import lingda.model.dto.DoubanDTO;
import lingda.service.crawler.rating.RatingCrawler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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
        Optional<DoubanDTO> doubanDTO =  ratingCrawlerDoubanImpl.searchRatingByName("The+Mummy+Returns");
        System.out.println(doubanDTO);
    }

}
