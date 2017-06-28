package lingda.service.crawler.impl;

import lingda.model.DownLoadLink;
import lingda.model.TVShow;
import lingda.util.NumericMapperUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lingda.service.crawler.ShowCrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Override
    protected Map<String, String> searchShow(TVShow show) {
        try {
            Document doc = Jsoup.connect(searchUrl)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .data("searchword", removeIllegalString(show.getEnglishName()))
                    .post();
            return buildShowToUrlMap(doc, show);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyMap();
        }
    }

    @Override
    protected List<DownLoadLink> findDownloadLinks(final String showUrl, final TVShow show) {
        try {
            Document doc = Jsoup.connect(site + showUrl).get();
            Elements elements = doc.getElementsByAttributeValue("class", "down_url");
            List<DownLoadLink> allDownLoadLinks = elements.stream().map(element -> element.attr("value"))
                    .collect(ArrayList::new,(list, url)-> list.add(new DownLoadLink(url, show)), ArrayList::addAll);
            return allDownLoadLinks;
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
    protected Optional<String> findMatchedShowUrl(TVShow show, Map<String, String> showToUrlMap) {
        Optional<Map.Entry<String, String>> showToUrlEntry = showToUrlMap.entrySet().stream()
                .filter(entry -> entry.getKey().contains(String.format("第%s季", NumericMapperUtil.getChineseNumber(show.getSeason()))))
                .findFirst();
        return showToUrlEntry.flatMap(entry -> Optional.ofNullable(showToUrlEntry.get().getValue()));
    }

    private Map<String, String> buildShowToUrlMap(Document document, TVShow show) {
        Elements elements = document.getElementsByAttributeValueMatching("title", show.getName());
        Map<String, String> showToUrlMap = new HashMap<>();
        elements.forEach(element -> showToUrlMap.putIfAbsent(element.attr("title"), element.attr("href")));
        return showToUrlMap;
    }

    private String removeIllegalString(String name) {
//        remove `'`
        String[] elems = name.split("\\s+");
        for (int i = 0; i < elems.length; i++) {
            if (elems[i].contains("'")) {
                elems[i] = "";
            }
        }
        return String.join(" ", elems);
    }
}
