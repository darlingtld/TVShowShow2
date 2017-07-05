package lingda.service.crawler.impl;

import lingda.model.dto.DownLoadLink;
import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.model.pojo.TVShow;
import lingda.service.cache.DocumentHttpGetCache;
import lingda.service.crawler.ShowCrawler;
import lingda.util.NumericMapperUtil;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static lingda.util.CharacterUtil.removeBadCharacter;

/**
 * Created by lingda on 11/11/16.
 */
@Service
public class ShowCrawlerMeijuttImpl implements ShowCrawler {

    private static final Logger logger = LoggerFactory.getLogger(ShowCrawlerMeijuttImpl.class);

    @Value("${site.meijutt}")
    private String site;

    @Value("${site.meijutt.searchurl}")
    private String searchUrl;

    @Autowired
    private DocumentHttpGetCache documentHttpGetCache;

    //    the search is done by the source site, no need to filter again
    @Override
    public List<TVShowSearchResult> search(final SearchTerm searchTerm) {
        Document doc = this.searchDocumentUsingHttpPost(removeBadCharacter(searchTerm.getTerm()));
        return parseDocumentIntoSearchResultMatchingTerm(doc, "");
    }

    @Override
    public List<TVShowSearchResult> searchShow(final TVShow show) {
        Document doc = this.searchDocumentUsingHttpPost(removeBadCharacter(show.getName()));
        return parseDocumentIntoSearchResultMatchingTerm(doc, show.getName());
    }

    @Override
    public List<DownLoadLink> searchDownloadLinks(final String showUrl, final TVShow show) {
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
    public Optional<String> findMatchedShowUrl(final TVShow show, List<TVShowSearchResult> tvShowSearchResultList) {
        Optional<TVShowSearchResult> tvShowSearchResultOptional = tvShowSearchResultList.stream()
                .filter(result -> result.getName().contains(String.format("第%s季", NumericMapperUtil.getChineseNumber(show.getSeason()))))
                .findFirst();

        return tvShowSearchResultOptional.flatMap(tvShowSearchResult -> Optional.ofNullable(tvShowSearchResult.getDetailUrl()));
    }

    //    analyze the document and get the matching result into a list of TVShowSearchResult
    private List<TVShowSearchResult> parseDocumentIntoSearchResultMatchingTerm(Document document, String term) {
        Elements elements = document.getElementsByClass("cn_box2");
        List<Element> matchingElements = elements.stream()
                .filter(element ->
                        removeIllegalString(element.getElementsByTag("li").get(0).text().toLowerCase()).contains(removeIllegalString(term.toLowerCase()))
                                || removeIllegalString(element.getElementsByTag("li").get(1).text().toLowerCase()).contains(removeIllegalString(term.toLowerCase()))).collect(Collectors.toList());
        logger.debug("get elements matching term {}", matchingElements);
        List<TVShowSearchResult> searchResultList = new ArrayList<>();
//        parse the elements into a TVShowSearchResult
        matchingElements.forEach(element -> {
            Element picElement = element.getElementsByClass("cn_box_box3").get(0);
            TVShowSearchResult searchResult = new TVShowSearchResult();
            Elements liElements = element.getElementsByClass("list_20").get(0).getElementsByTag("li");
            searchResult.setName(liElements.get(0).text());
            searchResult.setEnglishName(liElements.get(1).children().get(1).text());
            searchResult.setImgUrl(picElement.getElementsByTag("img").attr("src"));
            String detailUrl = this.site + liElements.get(0).getElementsByTag("a").attr("href");
            searchResult.setDetailUrl(detailUrl);
            searchResult.setTvSource(liElements.get(2).children().get(1).text());
            searchResult.setStatus(liElements.get(4).children().get(1).text());
            searchResult.setYear(Integer.parseInt(liElements.get(5).children().get(1).text().substring(0, 4)));
            searchResult.setCategory(liElements.get(6).children().get(1).text());
            searchResult.setDescription(fetchDescriptionForTheShow(detailUrl));
            searchResultList.add(searchResult);
        });
        logger.debug("search result for {} is {}", term, matchingElements);
        return searchResultList;
    }

    private String fetchDescriptionForTheShow(String detailUrl) {
        try {
            Document doc = documentHttpGetCache.get(detailUrl);
            return doc.getElementsByClass("des").text().trim();
        } catch (Exception e) {
            logger.error("failed to fetch the description from {}.  cause is {}", detailUrl, e.getMessage(), e);
        }
        return "";
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

    private Document searchDocumentUsingHttpPost(String searchText) {
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
