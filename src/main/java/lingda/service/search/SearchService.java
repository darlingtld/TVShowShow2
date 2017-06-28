package lingda.service.search;

import lingda.model.SearchTerm;
import lingda.model.TVShow;

import java.util.List;

/**
 * Created by lingda on 28/06/2017.
 */
public interface SearchService {

    List<TVShow> searchTVShow(SearchTerm searchTerm);
}
