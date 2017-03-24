package lingda.service.crawler.impl;

import lingda.model.DownLoadLink;
import lingda.model.TVShow;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lingda.service.crawler.ShowCrawler;

import java.io.IOException;
import java.net.URL;
import java.util.List;

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
    public String searchShow(String showName, int season, int episode) {
        try {
            Document doc = Jsoup.connect(searchUrl).data("searchword", showName).post();
            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<URL> getDownloadLinks() {
        return null;
    }

    @Override
    public List<DownLoadLink> extractDownloadLinks(List<TVShow> showList) {
        return null;
    }
}
