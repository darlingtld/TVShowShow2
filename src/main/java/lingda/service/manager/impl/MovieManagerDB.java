package lingda.service.manager.impl;

import com.google.common.collect.ImmutableMap;
import io.searchbox.client.JestResult;
import io.searchbox.core.SearchResult;
import lingda.dao.MovieRepository;
import lingda.dao.RatingRepository;
import lingda.model.dto.MovieSearchResult;
import lingda.model.dto.SearchTerm;
import lingda.model.pojo.Movie;
import lingda.service.crawler.rating.RatingCrawler;
import lingda.service.elasticsearch.JestClientService;
import lingda.service.manager.IShowManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by lingda on 21/07/2017.
 */
@Service("movieManagerDB")
@Transactional
public class MovieManagerDB implements IShowManager<Movie, MovieSearchResult> {

    private static final Logger logger = LoggerFactory.getLogger(TVShowManagerDB.class);

    @Value("${elasticsearch.index.searchresult}")
    private String INDEX_NAME_SEARCHRESULT;

    @Value("${elasticsearch.type.moviesearchresult}")
    private String TYPE_NAME_MOVIESEARCHRESULT;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private JestClientService jestClientService;

    @Autowired
    private RatingCrawler ratingCrawlerDoubanImpl;

    @Override
    public List<Movie> getShowList(String name) {
        return movieRepository.findByName(name);
    }

    @Override
    public List<Movie> getShowList() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getLatestListPerShow() {
        return null;
    }

    @Override
    public Movie addNew(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public MovieSearchResult saveToES(MovieSearchResult result) {
        try {
            result.setId(String.valueOf(result.hashCode()));
            logger.info("save to ES result={} index={} type={}", result, INDEX_NAME_SEARCHRESULT, TYPE_NAME_MOVIESEARCHRESULT);
//            use the hashcode of the moviesearchresult to avoid duplication
            JestResult jestResult = jestClientService.index(result, INDEX_NAME_SEARCHRESULT, TYPE_NAME_MOVIESEARCHRESULT);
            return jestResult.getSourceAsObject(MovieSearchResult.class);
        } catch (IOException e) {
            logger.error("error occurs in saving result to ES.  reason is {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void deleteFromES(MovieSearchResult result) {

    }

    @Override
    public MovieSearchResult getFromES(String id) {
        try {
            return jestClientService.get(MovieSearchResult.class, id, INDEX_NAME_SEARCHRESULT, TYPE_NAME_MOVIESEARCHRESULT);
        } catch (Exception e) {
            logger.error("error in getting from ES by id={}.  reason is {}", id, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<MovieSearchResult> searchBySearchTermFromES(SearchTerm searchTerm) {
        try {
            List<SearchResult.Hit<MovieSearchResult, Void>> resultList = jestClientService.searchBoolShouldQueryFuzzy(MovieSearchResult.class, ImmutableMap.of("name", searchTerm.getTerm(), "englishName", searchTerm.getTerm()), INDEX_NAME_SEARCHRESULT, TYPE_NAME_MOVIESEARCHRESULT);
            List<SearchResult.Hit<MovieSearchResult, Void>> pinyinResultList = jestClientService.searchBoolShouldQueryMatchPhrase(MovieSearchResult.class, ImmutableMap.of("name.pinyin", searchTerm.getTerm()), INDEX_NAME_SEARCHRESULT, TYPE_NAME_MOVIESEARCHRESULT);
            resultList.addAll(pinyinResultList);
            resultList.sort((o1, o2) -> {
                if (o1.score > o2.score) {
                    return -1;
                } else {
                    return 1;
                }
            });
            List<MovieSearchResult> movieSearchResultList = new ArrayList<>();
            for (SearchResult.Hit<MovieSearchResult, Void> result : resultList) {
                logger.info("[name]:{} [score]:{}", result.source.getName(), result.score);
                movieSearchResultList.add(result.source);
            }
            return movieSearchResultList;
        } catch (Exception e) {
            logger.error("error in searching by searchTerm={} from ES.  reason is {}", searchTerm, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public MovieSearchResult searchTVShowSearchResult(Map<String, String> fieldValueMap) {
        try {
            List<SearchResult.Hit<MovieSearchResult, Void>> resultList = jestClientService.searchBoolShouldQueryMatchPhrase(MovieSearchResult.class, fieldValueMap, INDEX_NAME_SEARCHRESULT, TYPE_NAME_MOVIESEARCHRESULT);
            if (!resultList.isEmpty()) {
                return resultList.get(0).source;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("error occurs in searching detailUrl From ES.  reason is {}", e.getMessage(), e);
            return null;
        }
    }
}
