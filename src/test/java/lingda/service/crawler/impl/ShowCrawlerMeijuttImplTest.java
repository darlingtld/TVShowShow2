package lingda.service.crawler.impl;

import lingda.model.dto.DownLoadLink;
import lingda.model.dto.TVShowSearchResult;
import lingda.model.pojo.TVShow;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;

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
        List<TVShowSearchResult> tvShowSearchResultList = showCrawlerMeijuttImpl.searchShow(new TVShow(null, "Grey's Anatomy", "Grey's Anatomy", "", 1, 1));
        assertThat(tvShowSearchResultList, not(IsEmptyCollection.empty()));
//        check the first item
        TVShowSearchResult result = tvShowSearchResultList.get(0);
        assertThat(result.getName(), startsWith("实习医生格蕾"));
        assertThat(result.getEnglishName(), startsWith("Grey‘s Anatomy"));
        assertThat(result.getImgUrl(), is(notNullValue()));
        assertThat(result.getDetailUrl(), is(notNullValue()));
        assertThat(result.getStatus(), is(notNullValue()));
        assertThat(result.getTvSource(), is(notNullValue()));
        assertThat(result.getYear(), is(greaterThan(2000)));
        assertThat(result.getCategory(), is(notNullValue()));
    }

    @Test
    public void shouldGetSomethingFromSearchUsingChinese() throws UnsupportedEncodingException {
        List<TVShowSearchResult> tvShowSearchResultList = showCrawlerMeijuttImpl.searchShow(new TVShow(null, "实习医生格蕾", "实习医生格蕾", "", 1, 1));
        tvShowSearchResultList.forEach(System.out::println);
        assertThat(tvShowSearchResultList, not(IsEmptyCollection.empty()));
//        check the first item
        TVShowSearchResult result = tvShowSearchResultList.get(0);
        assertThat(result.getName(), startsWith("实习医生格蕾"));
        assertThat(result.getEnglishName(), startsWith("Grey‘s Anatomy"));
        assertThat(result.getDetailUrl(), is(notNullValue()));
        assertThat(result.getStatus(), is(notNullValue()));
        assertThat(result.getTvSource(), is(notNullValue()));
        assertThat(result.getYear(), is(greaterThan(2000)));
        assertThat(result.getCategory(), is(notNullValue()));
    }

    @Test
    public void shouldGetDownloadLinks() {
        List<DownLoadLink> downloadLinkList = showCrawlerMeijuttImpl.searchDownloadLinks("http://www.meijutt.com/content/meiju131.html");
        assertThat(downloadLinkList, not(IsEmptyCollection.empty()));
    }
}
