package lingda.controller;

import com.google.common.collect.ImmutableMap;
import lingda.model.dto.DownLoadLink;
import lingda.model.dto.MovieSearchResult;
import lingda.model.pojo.Movie;
import lingda.service.crawler.movie.impl.MovieCrawlerDygod;
import lingda.service.manager.impl.MovieManagerDB;
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
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private MovieManagerDB movieManagerDB;

    @Autowired
    private MovieCrawlerDygod movieCrawlerDygod;

    @GetMapping
    public List<Movie> getShowList() {
        return movieManagerDB.getShowList();
    }

    @GetMapping("/downloadlinks")
    public List<DownLoadLink> getDownloadLinks(@RequestParam("detailUrl") String detailUrl) {
        return movieCrawlerDygod.searchDownloadLinks(detailUrl);
    }

    @GetMapping("/movie")
    public MovieSearchResult getTVShow(@RequestParam("detailUrl") String detailUrl) {
        return movieManagerDB.searchTVShowSearchResult(ImmutableMap.of("detailUrl", detailUrl));
    }
}
