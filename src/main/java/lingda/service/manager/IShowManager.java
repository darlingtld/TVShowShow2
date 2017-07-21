package lingda.service.manager;

import lingda.model.dto.SearchResult;
import lingda.model.dto.SearchTerm;
import lingda.model.pojo.Show;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by lingda on 24/03/2017.
 * T is the type mapped to DB
 * U is the type mapped to search result, which is persisted to elasticsearch
 */
public interface IShowManager<T extends Show, U extends SearchResult> {

    Logger logger = LoggerFactory.getLogger(IShowManager.class);

    List<T> getShowList(String name);

    List<T> getShowList();

    List<T> getLatestListPerShow();

    T addNew(T t);

    U saveToES(U result);

    void deleteFromES(U result);

    U getFromES(String id);

    List<U> searchBySearchTermFromES(SearchTerm searchTerm);

    U searchTVShowSearchResult(Map<String, String> fieldValueMap);
}
