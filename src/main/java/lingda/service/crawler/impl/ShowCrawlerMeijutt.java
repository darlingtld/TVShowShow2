package lingda.service.crawler.impl;

import lingda.model.DownLoadLink;
import lingda.model.TVShow;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lingda.service.crawler.ShowCrawler;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by lingda on 11/11/16.
 */
@Service
public class ShowCrawlerMeijutt extends ShowCrawler {

    @Value("${site.meijutt}")
    private String site;

    @Value("${site.meijutt.searchurl}")
    private String searchUrl;

    @Override
    public String searchShow(TVShow show) {
        try {
            Document doc = Jsoup.connect(searchUrl)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .data("searchword", removeIllegalString(show.getEnglishName()))
                    .post();
            extractElementsMatchingShow(doc, show);
            return doc.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    private void extractElementsMatchingShow(Document document, TVShow show) {
        Elements elements = document.getElementsByClass("box_bg_c");
        System.out.println(elements);
    }

    @Override
    public List<URL> getDownloadLinks() {
        return null;
    }

    @Override
    public List<DownLoadLink> extractDownloadLinks(TVShow show) {
        return null;
    }
}
