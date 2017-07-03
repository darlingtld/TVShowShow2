package lingda.service.search.impl;

import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lingda on 01/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SearchServiceImplTest {

    @Autowired
    private SearchServiceImpl searchService;

    @Test
    public void shouldGetTVShowSearchResults() {
        List<TVShowSearchResult> tvShowSearchResultList = searchService.searchTVShow(new SearchTerm("hero"));
        tvShowSearchResultList.forEach(result -> System.out.println(result.getName() + " " + result.getYear()));
        assertThat(tvShowSearchResultList, not(IsEmptyCollection.empty()));
    }

    @Test
    public void shouldGetTVShowSearchResultsInCorrectOrder() {
        TVShowSearchResult result1 = new TVShowSearchResult();
        result1.setName("实习医生格蕾第十季");
        result1.setEnglishName("Grey‘s Anatomy Season 10");
        result1.setYear(2013);
        TVShowSearchResult result2 = new TVShowSearchResult();
        result2.setName("实习医生格蕾第十二季");
        result2.setEnglishName("Grey‘s Anatomy Season 12");
        result2.setYear(2015);
        TVShowSearchResult result3 = new TVShowSearchResult();
        result3.setName("实习医生风云第九季");
        result3.setEnglishName("Scrubs Season 9");
        result3.setYear(2009);
        List<TVShowSearchResult> tvShowSearchResultList = Arrays.asList(
                result1, result2, result3
        );
        tvShowSearchResultList.sort(searchService.searchResultComparator(new SearchTerm("实习医生格蕾")));
        tvShowSearchResultList.forEach(result -> System.out.println(result.getName() + " " + result.getYear()));
        assertThat(tvShowSearchResultList, not(IsEmptyCollection.empty()));
    }
}
