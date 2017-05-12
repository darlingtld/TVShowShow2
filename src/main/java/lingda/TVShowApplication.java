package lingda;

import lingda.model.DownLoadLink;
import lingda.model.TVShow;
import lingda.service.crawler.ShowCrawler;
import lingda.service.manager.ShowManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TVShowApplication implements CommandLineRunner {

    @Autowired
    private ShowCrawler showCrawler;

    @Autowired
    private ShowManager showManager;


    public static void main(String[] args) {
        SpringApplication.run(TVShowApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        get all the tv show list that I am now watching
        List<TVShow> showList = showManager.getLatestListPerShow();
        for (TVShow show : showList) {
            List<DownLoadLink> downLoadLinkList = showCrawler.extractDownloadLinks(show);
            System.out.println(downLoadLinkList);
        }
    }


}