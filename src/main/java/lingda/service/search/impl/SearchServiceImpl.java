package lingda.service.search.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.service.crawler.impl.ShowCrawlerMeijuttImpl;
import lingda.service.search.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lingda on 28/06/2017.
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

    private static LoadingCache<SearchTerm, List<TVShowSearchResult>> searchTermLoadingCache;

    @Autowired
    private ShowCrawlerMeijuttImpl showCrawlerMeijutt;

    @PostConstruct
    public void postConstruct() {
        searchTermLoadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<SearchTerm, List<TVShowSearchResult>>() {
                            public List<TVShowSearchResult> load(SearchTerm searchTerm) {
                                return showCrawlerMeijutt.search(searchTerm);
                            }
                        });
    }

    @Override
    public List<TVShowSearchResult> searchTVShow(SearchTerm searchTerm) {
        logger.debug("search tv show with search term {}", searchTerm);
//        get the search result list from meijutt
        return searchTermLoadingCache.getUnchecked(searchTerm);
    }
}
