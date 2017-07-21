package lingda.service.crawler.movie;

import lingda.model.dto.DownLoadLink;
import lingda.model.dto.MovieSearchResult;
import lingda.model.dto.SearchTerm;

import java.util.List;

/**
 * Created by lingda on 21/07/2017.
 */
public interface IMovieCrawler {
    List<MovieSearchResult> search(SearchTerm searchTerm);

    List<DownLoadLink> searchDownloadLinks(String detailUrl);
}
