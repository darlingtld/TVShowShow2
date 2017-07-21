package lingda.controller;

import com.google.common.collect.ImmutableMap;
import lingda.model.dto.DownLoadLink;
import lingda.model.dto.TVShowSearchResult;
import lingda.model.pojo.TVShow;
import lingda.service.crawler.tvshow.ShowCrawler;
import lingda.service.manager.impl.TVShowManagerDB;
import lingda.service.search.impl.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lingda on 25/06/2017.
 */
@RestController
@RequestMapping("show")
public class ShowController {

    @Autowired
    private TVShowManagerDB tvShowManagerDB;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ShowCrawler meijuttShowCrawler;

    @GetMapping
    public List<TVShow> getShowList() {
        return tvShowManagerDB.getShowList();
    }

    @GetMapping("/downloadlinks")
    public List<DownLoadLink> getDownloadLinks(@RequestParam("detailUrl") String detailUrl) {
        return meijuttShowCrawler.searchDownloadLinks(detailUrl);
    }

    @GetMapping("/tvshow")
    public TVShowSearchResult getTVShow(@RequestParam("detailUrl") String detailUrl) {
        return tvShowManagerDB.searchTVShowSearchResult(ImmutableMap.of("detailUrl", detailUrl));
    }
}
