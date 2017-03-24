package service.crawler;

import model.DownLoadLink;
import model.TVShow;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by lingda on 11/11/16.
 */
public abstract class ShowCrawler {

    String fetchHTML(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.html();
    }

    public abstract String searchShow(String showName, int season, int episode);

    public abstract List<URL> getDownloadLinks();

    public abstract List<DownLoadLink> extractDownloadLinks(List<TVShow> showList);
}
