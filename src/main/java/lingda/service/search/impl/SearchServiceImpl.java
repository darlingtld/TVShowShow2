package lingda.service.search.impl;

import lingda.model.dto.MovieSearchResult;
import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.service.cache.SearchResultCache;
import lingda.service.search.SearchService;
import lingda.util.StringSimilarityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
        logger.info("search tvshow with search term {}", searchTerm);
//        search result list from meijutt
//        use 'new ArrayList()' to avoid ConcurrentModificationException when sorting a list in Java 8
        List<TVShowSearchResult> tvShowSearchResultList = new ArrayList<>(searchResultCache.getTVShowOrSearchOnline(searchTerm));
        tvShowSearchResultList.sort(searchResultComparator(searchTerm));
        return tvShowSearchResultList;
    }

    @Override
    public List<MovieSearchResult> searchMovie(SearchTerm searchTerm) {
        logger.info("search movie with search term {}", searchTerm);

        return null;
    }

    //    TODO optimize the algorithm to order the searchFuzzy results
    Comparator<TVShowSearchResult> searchResultComparator(SearchTerm searchTerm) {
        return (o1, o2) -> {
            double o1Sim = StringSimilarityUtil.sim(o1.getName().toLowerCase(), searchTerm.getTerm().toLowerCase()) + StringSimilarityUtil.sim(o1.getEnglishName().toLowerCase(), searchTerm.getTerm().toLowerCase());
            double o2Sim = StringSimilarityUtil.sim(o2.getName().toLowerCase(), searchTerm.getTerm().toLowerCase()) + StringSimilarityUtil.sim(o1.getEnglishName().toLowerCase(), searchTerm.getTerm().toLowerCase());
            if ((o1.getName().toLowerCase().contains(searchTerm.getTerm().toLowerCase()) || o1.getEnglishName().toLowerCase().contains(searchTerm.getTerm().toLowerCase()))
                    && !(o2.getName().toLowerCase().contains(searchTerm.getTerm().toLowerCase()) || o2.getEnglishName().toLowerCase().contains(searchTerm.getTerm().toLowerCase()))) {
                return -1;
            } else if (!(o1.getName().toLowerCase().contains(searchTerm.getTerm().toLowerCase()) || o1.getEnglishName().toLowerCase().contains(searchTerm.getTerm().toLowerCase()))
                    && (o2.getName().toLowerCase().contains(searchTerm.getTerm().toLowerCase()) || o2.getEnglishName().toLowerCase().contains(searchTerm.getTerm().toLowerCase()))) {
                return 1;
            } else {
                if (o1Sim == o2Sim) {
                    return o2.getYear() - o1.getYear();
                } else {
                    return o2Sim > o1Sim ? 1 : -1;
                }
            }

        };
    }
}
