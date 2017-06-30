package lingda.service.search.impl;

import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.service.cache.SearchResultCache;
import lingda.service.search.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lingda on 28/06/2017.
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private SearchResultCache searchResultCache;

    @Override
    public List<TVShowSearchResult> searchTVShow(SearchTerm searchTerm) {
        logger.debug("search tv show with search term {}", searchTerm);
//        get the search result list from meijutt
        return searchResultCache.get(searchTerm);
    }
}
