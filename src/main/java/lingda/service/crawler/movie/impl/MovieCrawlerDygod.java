package lingda.service.crawler.movie.impl;

import lingda.model.dto.DownLoadLink;
import lingda.model.dto.MovieSearchResult;
import lingda.model.dto.SearchTerm;
import lingda.service.crawler.movie.IMovieCrawler;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by lingda on 21/07/2017.
 */
@Service
public class MovieCrawlerDygod  implements IMovieCrawler{

    @Override
    public List<MovieSearchResult> search(SearchTerm searchTerm) {
        return Collections.emptyList();
    }

    @Override
    public List<DownLoadLink> searchDownloadLinks(String detailUrl) {
        return null;
    }
}
