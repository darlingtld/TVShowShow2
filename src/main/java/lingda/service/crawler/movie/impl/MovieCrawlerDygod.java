package lingda.service.crawler.movie.impl;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import lingda.model.dto.DownLoadLink;
import lingda.model.dto.MovieSearchResult;
import lingda.model.dto.SearchTerm;
import lingda.service.cache.DocumentHttpGetCache;
import lingda.service.crawler.WebClientFactory;
import lingda.service.crawler.movie.IMovieCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lingda on 21/07/2017.
 */
@Service
public class MovieCrawlerDygod implements IMovieCrawler {

    private static final Logger logger = LoggerFactory.getLogger(MovieCrawlerDygod.class);

    @Value("${site.movie.dygod}")
    private String site;

    @Value("${site.movie.dygod.searchurl}")
    private String searchUrl;

    @Autowired
    private DocumentHttpGetCache documentHttpGetCache;

    private static final String ENGLISH_NAME_REGEX = "(.*?)(片[\\s\\p{Zs}]*名|英文片名).*?(?<englishName>[\\w+\\s]+)";
    private static final Pattern ENGLISH_NAME_PATTERN = Pattern.compile(ENGLISH_NAME_REGEX);
    private static final String CATEGORY_REGEX = "(.*?)(类[\\s\\p{Zs}]*型).*?(?<category>[\\u4e00-\\u9fa5/]+)";
    private static final Pattern CATEGORY_PATTERN = Pattern.compile(CATEGORY_REGEX);
    private static final String YEAR_REGEX = "(.*?)(年[\\s\\p{Zs}]*代).*?(?<year>\\d+)";
    private static final Pattern YEAR_PATTERN = Pattern.compile(YEAR_REGEX);
    private static final String DESCRIPTION_REGEX = "(.*?)(简[\\s\\p{Zs}]*介)";

    @Override
    public List<MovieSearchResult> search(SearchTerm searchTerm) {
        logger.info("start searching from {} for term {}", site, searchTerm);
        try {
            Document doc = this.searchDocument(searchTerm.getTerm());
            return parseDocumentIntoSearchResultMatchingTerm(doc, "");
        } catch (Exception e) {
            logger.error("error in searching from dygod.  reason is {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<DownLoadLink> searchDownloadLinks(String detailUrl) {
        return null;
    }

    private Document searchDocument(String searchText) {
        try (WebClient webClient = WebClientFactory.get()) {
            final HtmlPage firstPage = webClient.getPage(site);
            final HtmlForm searchForm = firstPage.getFormByName("searchform");
            final HtmlSubmitInput button = searchForm.getInputByName("Submit");
            final HtmlTextInput textField = searchForm.getInputByName("keyboard");
            textField.setValueAttribute(searchText);
            final HtmlPage searchResultPage = button.click();
            return Jsoup.parse(searchResultPage.asXml());
        } catch (Exception e) {
            logger.error("error in searching dygod.  cause={}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    //    analyze the document and search the matching result into a list of MovieSearchResult
    private List<MovieSearchResult> parseDocumentIntoSearchResultMatchingTerm(Document document, String term) {
        Elements elements = document.getElementsByClass("tbspan");
        List<MovieSearchResult> searchResultList = new ArrayList<>();
//        parse the elements into a MovieSearchResult
        elements.forEach(element -> {
            try {
                MovieSearchResult searchResult = new MovieSearchResult();
                Element aElement = element.getElementsByClass("ulink").get(0);
                searchResult.setName(aElement.attr("title"));
                String detailUrl = this.site + aElement.attr("href");
                searchResult.setDetailUrl(detailUrl);
                populateRemainingFieldsByDrillingDown(searchResult, detailUrl);
                searchResultList.add(searchResult);
            } catch (Exception e) {
                logger.error("error in parsing document.  cause={}", e.getMessage(), e);
            }
        });
        logger.info("finish search from {}.  result count is {}", site, searchResultList.size());
        return searchResultList;
    }

    private void populateRemainingFieldsByDrillingDown(MovieSearchResult searchResult, String detailUrl) {
        try {
            Document doc = Jsoup.connect(detailUrl).get();
            Element element = doc.getElementById("Zoom");
            Elements pElements = element.getElementsByTag("p");
            for (int i = 0; i < pElements.size(); i++) {
                String text = pElements.get(i).text();
                if (text.matches(ENGLISH_NAME_REGEX)) {
                    Matcher matcher = ENGLISH_NAME_PATTERN.matcher(text);
                    if (matcher.find()) {
                        searchResult.setEnglishName(matcher.group("englishName").trim());
                    } else {
                        searchResult.setEnglishName("Unknown");
                    }
                } else if (text.matches(CATEGORY_REGEX)) {
                    Matcher matcher = CATEGORY_PATTERN.matcher(text);
                    if (matcher.find()) {
                        searchResult.setCategory(matcher.group("category").trim());
                    } else {
                        searchResult.setCategory("未知");
                    }
                } else if (text.matches(YEAR_REGEX)) {
                    Matcher matcher = YEAR_PATTERN.matcher(text);
                    if (matcher.find()) {
                        searchResult.setYear(Integer.parseInt(matcher.group("year").trim()));
                    } else {
                        searchResult.setYear(0);
                    }
                } else if (text.matches(DESCRIPTION_REGEX)) {
                    searchResult.setDescription(pElements.get(i + 1).text().trim());
                } else if (!pElements.get(i).children().isEmpty() && pElements.get(i).children().first().hasAttr("src")) {
                    searchResult.setImgUrl(pElements.get(i).children().first().attr("src"));
                }
            }

        } catch (IOException e) {
            logger.error("error in populating remaining fields for {} from {}", searchResult.getName(), detailUrl);
        }
    }
}
