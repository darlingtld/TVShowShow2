package lingda.service.crawler.tvshow.impl;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import lingda.model.dto.DownLoadLink;
import lingda.model.dto.MovieSearchResult;
import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.model.pojo.TVShow;
import lingda.service.crawler.movie.impl.MovieCrawlerDygod;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
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
public class MovieCrawlerDygodImplTest {

    @Autowired
    private MovieCrawlerDygod movieCrawlerDygod;

    @Test
    public void shouldGetSomethingFromSearchingMovie() throws Exception {
        List<MovieSearchResult> searchResultList = movieCrawlerDygod.search(new SearchTerm("mummy"));
        assertThat(searchResultList, not(IsEmptyCollection.empty()));
//        check the first item
        searchResultList.forEach(System.out::println);
//        MovieSearchResult result = searchResultList.get(0);
//        System.out.println(result);
//        assertThat(result.getName(), containsString("木乃伊"));
//        assertThat(result.getEnglishName(), containsString("Mummy"));
//        assertThat(result.getImgUrl(), is(notNullValue()));
//        assertThat(result.getDetailUrl(), is(notNullValue()));
//        assertThat(result.getYear(), is(equalTo(2017)));
//        assertThat(result.getCategory(), is(notNullValue()));
    }

    @Test
    public void submittingFormMeijutt() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);

            // Get the first page
            final HtmlPage page1 = webClient.getPage("http://www.meijutt.com/");

            // Get the form that we are dealing with and within that form,
            // find the submit button and the field that we want to change.
            final HtmlForm form = page1.getFormByName("site_search");

            final HtmlSubmitInput button = form.getInputByValue("提交");
            final HtmlTextInput textField = form.getInputByName("searchword");

            // Change the value of the text field
            textField.setValueAttribute("hero");

            // Now submit the form by clicking the button and get back the second page.
            final HtmlPage page2 = button.click();
            System.out.println(page2);
        }
    }
}
