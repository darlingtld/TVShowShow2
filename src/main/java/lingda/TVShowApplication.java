package lingda;

import lingda.service.crawler.ShowCrawler;
import lingda.service.manager.ShowManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TVShowApplication /*implements CommandLineRunner*/ {

    private static final Logger logger = LoggerFactory.getLogger(TVShowApplication.class);

    @Autowired
    private ShowCrawler showCrawler;

    @Autowired
    private ShowManager showManager;


    public static void main(String[] args) {
        SpringApplication.run(TVShowApplication.class, args);
    }

//    @Override
//    public void run(String... args) {
//        logger.info("Get all the latest episodes per show I am now watching");
//        try {
//            List<TVShow> showList = showManager.getLatestListPerShow();
//            showList.forEach(System.out::println);
//            for (TVShow show : showList) {
//                List<DownLoadLink> downLoadLinkList = showCrawler.getDownloadLinks(show);
//                System.out.println(downLoadLinkList);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//    }


}