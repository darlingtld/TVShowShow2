package lingda.service.crawler.impl;

import lingda.model.dto.DownLoadLink;
import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.model.pojo.TVShow;
import lingda.service.crawler.ShowCrawler;
import lingda.util.NumericMapperUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by lingda on 11/11/16.
 */
@Service
public class ShowCrawlerMeijuttImpl extends ShowCrawler {

    private static final Logger logger = LoggerFactory.getLogger(ShowCrawlerMeijuttImpl.class);

    @Value("${site.meijutt}")
    private String site;

    @Value("${site.meijutt.searchurl}")
    private String searchUrl;

    //    the search is done by the source site, no need to filter again
    @Override
    public List<TVShowSearchResult> search(SearchTerm searchTerm) {
        Document doc = this.getDocument(searchTerm.getTerm());
        return parseDocumentIntoSearchResultMatchingTerm(doc, "");
    }

    @Override
    protected List<TVShowSearchResult> searchShow(TVShow show) {
        Document doc = this.getDocument(show.getEnglishName());
        return parseDocumentIntoSearchResultMatchingTerm(doc, show.getEnglishName());
    }

    @Override
    protected List<DownLoadLink> findDownloadLinks(final String showUrl, final TVShow show) {
        try {
            Document doc = Jsoup.connect(site + showUrl).get();
            Elements elements = doc.getElementsByAttributeValue("class", "down_url");
            return elements.stream().map(element -> element.attr("value"))
                    .collect(ArrayList::new, (list, url) -> list.add(new DownLoadLink(url, show)), ArrayList::addAll);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    protected List<DownLoadLink> getMatchedDownloadLinksForWatching(List<DownLoadLink> downLoadLinkList, TVShow show) {
        return null;
    }

    @Override
    protected Optional<String> findMatchedShowUrl(TVShow show, List<TVShowSearchResult> tvShowSearchResultList) {
        Optional<TVShowSearchResult> tvShowSearchResultOptional = tvShowSearchResultList.stream()
                .filter(result -> result.getName().contains(String.format("第%s季", NumericMapperUtil.getChineseNumber(show.getSeason()))))
                .findFirst();

        return tvShowSearchResultOptional.flatMap(tvShowSearchResult -> Optional.ofNullable(tvShowSearchResult.getDetailUrl()));
    }


//    analyze the document and get the matching result into a list of TVShowSearchResult
    private List<TVShowSearchResult> parseDocumentIntoSearchResultMatchingTerm(Document document, String term) {
        Elements elements = document.getElementsByClass("list_20");
        List<Element> matchingElements = elements.stream()
                .filter(element ->
                        removeIllegalString(element.getElementsByTag("li").get(0).text().toLowerCase()).contains(removeIllegalString(term.toLowerCase()))
                                || removeIllegalString(element.getElementsByTag("li").get(1).text().toLowerCase()).contains(removeIllegalString(term.toLowerCase()))).collect(Collectors.toList());
        logger.debug("get elements matching term {}", matchingElements);
        List<TVShowSearchResult> searchResultList = new ArrayList<>();
//        parse the elements into a TVShowSearchResult
        matchingElements.forEach(element -> {
            TVShowSearchResult searchResult = new TVShowSearchResult();
            Elements liElements = element.getElementsByTag("li");
            searchResult.setName(liElements.get(0).text());
            searchResult.setEnglishName(liElements.get(1).children().get(1).text());
            searchResult.setDetailUrl(this.site + liElements.get(0).getElementsByTag("a").attr("href"));
            searchResult.setTvSource(liElements.get(2).children().get(1).text());
            searchResult.setStatus(liElements.get(4).children().get(1).text());
            searchResult.setYear(Integer.parseInt(liElements.get(5).children().get(1).text().substring(0,4)));
            searchResult.setCategory(liElements.get(6).children().get(1).text());
            searchResultList.add(searchResult);
        });
        logger.debug("search result for {} is {}", term, matchingElements);
        return searchResultList;
    }

    private String removeIllegalString(String name) {
//        remove `'`
        String[] elems = name.split("\\s+");
        for (int i = 0; i < elems.length; i++) {
            if (elems[i].contains("'") || elems[i].contains("`")) {
                elems[i] = "";
            }
        }
        return String.join(" ", elems);
    }

    private Document getDocument(String searchText) {
        try {
            return Jsoup.connect(searchUrl)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .data("searchword", removeIllegalString(searchText))
                    .post();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}