package lingda.service.manager;

import lingda.model.dto.RatingDTO;
import lingda.model.dto.TVShowSearchResult;
import lingda.model.pojo.TVShow;
import lingda.service.search.IRatingService;
import lingda.service.search.impl.RatingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lingda on 05/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@Transactional
public class TVShowManagerTest {

    @Autowired
    @Qualifier("tvShowManagerDB")
    private IShowManager<TVShow, TVShowSearchResult> showManagerDB;

    @Autowired
    private IRatingService ratingService;

    @Test
    public void addNewTVShow() {
        TVShow tvShow = new TVShow();
        tvShow.setName("新实习医生格蕾");
        tvShow.setDescription("anything");
        tvShow.setEnglishName("Grey's Anatomy");
        tvShow.setSeason(1);
        tvShow.setEpisode(1);
        TVShow newTVShow = showManagerDB.addNew(tvShow);
        assertThat(showManagerDB.getShowList(tvShow.getName()).size(), is(1));
        assertThat(showManagerDB.getShowList(tvShow.getName()).get(0).getName(), is(newTVShow.getName()));
    }

    @Test
    public void shouldGetLatestListPerShow() {
        List<TVShow> showList = showManagerDB.getLatestListPerShow();
        System.out.println(showList);
    }

    @Test
    public void shouldUpsertRating() {
        RatingDTO ratingDTO = ratingService.getRatingFromDouban("英雄第二季", "Hero Season 1");
        System.out.println(ratingDTO);
    }
}
