package lingda.service.search;

import lingda.model.dto.MovieSearchResult;
import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;

import java.util.List;

/**
 * Created by lingda on 28/06/2017.
 */
public interface SearchService {

    List<TVShowSearchResult> searchTVShow(SearchTerm searchTerm);

    List<MovieSearchResult> searchMovie(SearchTerm searchTerm);
}
