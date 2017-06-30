package lingda.service.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.service.crawler.impl.ShowCrawlerMeijuttImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lingda on 30/06/2017.
 */
@Service
public class SearchResultCache {

    private static final Logger logger = LoggerFactory.getLogger(SearchResultCache.class);

    private static LoadingCache<SearchTerm, List<TVShowSearchResult>> searchTermLoadingCache;

    @Autowired
    private ShowCrawlerMeijuttImpl showCrawlerMeijutt;

    @PostConstruct
    private void postConstruct() {
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

    public List<TVShowSearchResult> get(SearchTerm searchTerm) {
        return searchTermLoadingCache.getUnchecked(searchTerm);
    }
}
