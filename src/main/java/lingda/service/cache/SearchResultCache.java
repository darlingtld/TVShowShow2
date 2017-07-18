package lingda.service.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.service.crawler.ShowCrawler;
import lingda.service.crawler.impl.ShowCrawlerMeijuttImpl;
import lingda.service.elasticsearch.JestClientService;
import lingda.service.manager.ShowManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lingda on 30/06/2017.
 */
@Service
public class SearchResultCache {

    private static final Logger logger = LoggerFactory.getLogger(SearchResultCache.class);

    private static LoadingCache<SearchTerm, List<TVShowSearchResult>> searchTermLoadingCache;

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 10,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10));

    @Autowired
    private ShowCrawler showCrawlerMeijutt;

    @Autowired
    private ShowManager showManagerDBImpl;

    @PostConstruct
    private void postConstruct() {
        searchTermLoadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<SearchTerm, List<TVShowSearchResult>>() {
                            public List<TVShowSearchResult> load(SearchTerm searchTerm) {
                                List<TVShowSearchResult> resultList = new ArrayList<>();
//                                searchFuzzy from elasticsearch
                                List<TVShowSearchResult> searchResultESList = showManagerDBImpl.searchBySearchTermFromES(searchTerm);
                                searchResultESList.forEach(searchResult -> logger.info("[result from ES]:{}", searchResult));
                                resultList.addAll(searchResultESList);
                                if (!resultList.isEmpty()) {
                                    logger.info("elasticsearch returns some results.  spawn a thread to update the search result");
//                                    return the result and spawn a thread to update the search result
                                    threadPoolExecutor.submit(() -> {
                                        List<TVShowSearchResult> meijuttSearchResultList = showCrawlerMeijutt.search(searchTerm);
//                                diff the searchFuzzy result from internet and elasticsearch.  save the new result to ES.
                                        meijuttSearchResultList.removeAll(searchResultESList);
                                        meijuttSearchResultList.forEach(searchResult -> showManagerDBImpl.saveToES(searchResult));
                                        resultList.addAll(meijuttSearchResultList);
                                    });
                                    return resultList;
                                } else {
                                    logger.info("elasticsearch returns empty results.  try search online");
//                                    a synchronous operation
                                    List<TVShowSearchResult> meijuttSearchResultList = showCrawlerMeijutt.search(searchTerm);
                                    meijuttSearchResultList.forEach(searchResult -> showManagerDBImpl.saveToES(searchResult));
//                                merge the two result list
                                    resultList.addAll(meijuttSearchResultList);
                                    return resultList;
                                }
                            }
                        });
    }

    public List<TVShowSearchResult> getOrSearchOnline(SearchTerm searchTerm) {
        return searchTermLoadingCache.getUnchecked(searchTerm);
    }
}
