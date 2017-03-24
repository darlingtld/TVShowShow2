import model.DownLoadLink;
import model.TVShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.crawler.ShowCrawler;
import service.manager.ShowManager;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"service", "model"})
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
        List<TVShow> showList = showManager.getShowList();
        List<DownLoadLink> downLoadLinkList = showCrawler.extractDownloadLinks(showList);
        System.out.println(downLoadLinkList);
    }

}