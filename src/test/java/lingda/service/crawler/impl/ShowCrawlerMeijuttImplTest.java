package lingda.service.crawler.impl;

import lingda.model.dto.TVShowSearchResult;
import lingda.model.pojo.TVShow;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lingda on 12/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShowCrawlerMeijuttImplTest {

    @Autowired
    private ShowCrawlerMeijuttImpl showCrawlerMeijuttImpl;

    @Test
    public void shouldGetSomethingFromSearchShow() {
        List<TVShowSearchResult> tvShowSearchResultList = showCrawlerMeijuttImpl.searchShow(new TVShow(null, "实习医生格蕾", "Grey's Anatomy", "", 1, 1));
        tvShowSearchResultList.forEach(System.out::println);
        assertThat(tvShowSearchResultList, not(IsEmptyCollection.empty()));
//        check the first item
        TVShowSearchResult result = tvShowSearchResultList.get(0);
        assertThat(result.getName(), startsWith("实习医生格蕾"));
        assertThat(result.getEnglishName(), startsWith("Grey‘s Anatomy"));
    }

    @Test
    public void shouldGetDownloadLinks() {
        showCrawlerMeijuttImpl.getDownloadLinks(new TVShow(null, "实习医生格蕾", "Grey's Anatomy", "", 1, 1));
    }

    @Test
    public void test() {
        Optional<String> stringOptional = Optional.ofNullable("hi");
        System.out.println(stringOptional.flatMap(s -> Optional.of(s.toUpperCase())));
    }
}
