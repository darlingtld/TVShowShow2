package lingda.service.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lingda.model.dto.MovieSearchResult;
import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.model.pojo.Movie;
import lingda.model.pojo.TVShow;
import lingda.service.crawler.movie.IMovieCrawler;
import lingda.service.crawler.tvshow.ShowCrawler;
import lingda.service.manager.IShowManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lingda on 30/06/2017.
 */
@Service
public class SearchResultCache {

    private static final Logger logger = LoggerFactory.getLogger(SearchResultCache.class);

    private static LoadingCache<SearchTerm, List<TVShowSearchResult>> tvShowSearchTermLoadingCache;

    private static LoadingCache<SearchTerm, List<MovieSearchResult>> movieSearchTermLoadingCache;

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 10,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10));

    @Autowired
    private ShowCrawler showCrawlerMeijutt;

    @Autowired
    private IMovieCrawler movieCrawlerDygod;

    @Autowired
    @Qualifier("tvShowManagerDB")
    private IShowManager<TVShow, TVShowSearchResult> ShowManagerDB;

    @Autowired
    @Qualifier("movieManagerDB")
    private IShowManager<Movie, MovieSearchResult> movieManagerDB;

    @PostConstruct
    private void postConstruct() {
//        initialize tvshow cache
        tvShowSearchTermLoadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<SearchTerm, List<TVShowSearchResult>>() {
                            public List<TVShowSearchResult> load(SearchTerm searchTerm) {
                                List<TVShowSearchResult> resultList = new ArrayList<>();
//                                search from elasticsearch
                                List<TVShowSearchResult> searchResultESList = ShowManagerDB.searchBySearchTermFromES(searchTerm);
                                searchResultESList.forEach(searchResult -> logger.info("[result from ES]:{}", searchResult));
                                resultList.addAll(searchResultESList);
                                if (!resultList.isEmpty()) {
                                    logger.info("elasticsearch returns some results.  spawn a thread to update the search result");
//                                    return the result and spawn a thread to update the search result
                                    threadPoolExecutor.submit(() -> {
                                        List<TVShowSearchResult> meijuttSearchResultList = showCrawlerMeijutt.search(searchTerm);
//                                diff the search result from internet and elasticsearch.  save the new result to ES.
                                        meijuttSearchResultList.removeAll(searchResultESList);
                                        meijuttSearchResultList.forEach(searchResult -> ShowManagerDB.saveToES(searchResult));
                                        resultList.addAll(meijuttSearchResultList);
                                    });
                                    return resultList;
                                } else {
                                    logger.info("elasticsearch returns empty results.  try search online");
//                                    a synchronous operation
                                    List<TVShowSearchResult> meijuttSearchResultList = showCrawlerMeijutt.search(searchTerm);
                                    meijuttSearchResultList.forEach(searchResult -> ShowManagerDB.saveToES(searchResult));
//                                merge the two result list
                                    resultList.addAll(meijuttSearchResultList);
                                    return resultList;
                                }
                            }
                        });

        //        initialize movie cache
        movieSearchTermLoadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<SearchTerm, List<MovieSearchResult>>() {
                            public List<MovieSearchResult> load(SearchTerm searchTerm) {
                                List<MovieSearchResult> resultList = new ArrayList<>();
//                                search from elasticsearch
                                List<MovieSearchResult> searchResultESList = movieManagerDB.searchBySearchTermFromES(searchTerm);
                                searchResultESList.forEach(searchResult -> logger.info("[result from ES]:{}", searchResult));
                                resultList.addAll(searchResultESList);
                                if (!resultList.isEmpty()) {
                                    logger.info("elasticsearch returns some results.  spawn a thread to update the search result");
//                                    return the result and spawn a thread to update the search result
                                    threadPoolExecutor.submit(() -> {
                                        List<MovieSearchResult> dygodSearchResultList = movieCrawlerDygod.search(searchTerm);
//                                diff the search result from internet and elasticsearch.  save the new result to ES.
                                        dygodSearchResultList.removeAll(searchResultESList);
                                        dygodSearchResultList.forEach(searchResult -> movieManagerDB.saveToES(searchResult));
                                        resultList.addAll(dygodSearchResultList);
                                    });
                                    return resultList;
                                } else {
                                    logger.info("elasticsearch returns empty results.  try search online");
//                                    a synchronous operation
                                    List<MovieSearchResult> dygodSearchResultList = movieCrawlerDygod.search(searchTerm);
                                    dygodSearchResultList.forEach(searchResult -> movieManagerDB.saveToES(searchResult));
//                                merge the two result list
                                    resultList.addAll(dygodSearchResultList);
                                    return resultList;
                                }
                            }
                        });
    }

    public List<TVShowSearchResult> getTVShowOrSearchOnline(SearchTerm searchTerm) {
        return tvShowSearchTermLoadingCache.getUnchecked(searchTerm);
    }

    public List<MovieSearchResult> getMovieOrSearchOnline(SearchTerm searchTerm){
        return movieSearchTermLoadingCache.getUnchecked(searchTerm);
    }
}
