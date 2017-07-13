package lingda.service.manager.impl;

import com.google.common.collect.ImmutableMap;
import io.searchbox.client.JestResult;
import io.searchbox.core.SearchResult;
import lingda.dao.TVShowRepository;
import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.model.pojo.TVShow;
import lingda.service.elasticsearch.JestClientService;
import lingda.service.manager.ShowManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lingda on 24/03/2017.
 */
@Service
public class ShowManagerDBImpl implements ShowManager {

    private static final String INDEX_NAME_TVSHOWSEARCHRESULT = "searchresult";
    private static final String TYPE_NAME_TVSHOWSEARCHRESULT = "tvshowsearchresult";

    private static final Logger logger = LoggerFactory.getLogger(ShowManagerDBImpl.class);

    @Autowired
    private TVShowRepository tvShowRepository;

    @Autowired
    private JestClientService jestClientService;

    @Override
    public List<TVShow> getShowList(String name) {
        return tvShowRepository.findByName(name);
    }

    @Override
    public List<TVShow> getShowList() {
        return tvShowRepository.findAll();
    }

    @Override
    public List<TVShow> getLatestListPerShow() {
        return tvShowRepository.findLatestListPerShow();
    }

    @Override
    public TVShow addNew(TVShow tvShow) {
        return tvShowRepository.save(tvShow);
    }

    @Override
    public TVShowSearchResult searchByDetailUrlFromES(String detailUrl) {
        try {
            List<SearchResult.Hit<TVShowSearchResult, Void>> resultList = jestClientService.search(TVShowSearchResult.class, ImmutableMap.of("detailUrl", detailUrl), INDEX_NAME_TVSHOWSEARCHRESULT, TYPE_NAME_TVSHOWSEARCHRESULT);
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

    @Override
    public TVShowSearchResult saveToES(TVShowSearchResult result) {
        try {
            logger.debug("save result={} to elasticsearch with index={} type={}", result, INDEX_NAME_TVSHOWSEARCHRESULT, TYPE_NAME_TVSHOWSEARCHRESULT);
            JestResult jestResult = jestClientService.index(result, INDEX_NAME_TVSHOWSEARCHRESULT, TYPE_NAME_TVSHOWSEARCHRESULT);
            return jestResult.getSourceAsObject(TVShowSearchResult.class);
        } catch (IOException e) {
            logger.error("error occurs in saving result to ES.  reason is {}", e.getMessage(), e);
            return null;
        }

    }

    @Override
    public void deleteFromES(TVShowSearchResult result) {

    }

    @Override
    public TVShowSearchResult getFromES(String id) {
        try {
            return jestClientService.get(TVShowSearchResult.class, id, INDEX_NAME_TVSHOWSEARCHRESULT, TYPE_NAME_TVSHOWSEARCHRESULT);
        } catch (Exception e) {
            logger.error("error in getting from ES by id={}.  reason is {}", id, e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<TVShowSearchResult> searchBySearchTermFromES(String key, SearchTerm searchTerm) {
        try {
            List<SearchResult.Hit<TVShowSearchResult, Void>> resultList = jestClientService.search(TVShowSearchResult.class, ImmutableMap.of(key, searchTerm.getTerm()), INDEX_NAME_TVSHOWSEARCHRESULT, TYPE_NAME_TVSHOWSEARCHRESULT);
            List<TVShowSearchResult> tvShowSearchResultList = new ArrayList<>();
            for (SearchResult.Hit<TVShowSearchResult, Void> result : resultList) {
                logger.info("[name]:{} [socre]:{}", result.source.getName(), result.score);
                tvShowSearchResultList.add(result.source);
            }
            return tvShowSearchResultList;
        } catch (Exception e) {
            logger.error("error in searching by searchTerm={} from ES.  reason is {}", searchTerm, e.getMessage(), e);
            return Collections.emptyList();
        }
    }

}
