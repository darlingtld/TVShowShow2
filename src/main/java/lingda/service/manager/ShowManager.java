package lingda.service.manager;

import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.model.pojo.TVShow;

import java.util.List;

/**
 * Created by lingda on 24/03/2017.
 */
public interface ShowManager {

    List<TVShow> getShowList(String name);

    List<TVShow> getShowList();

    List<TVShow> getLatestListPerShow();

    TVShow addNew(TVShow tvShow);

    //    fetch data from elasticsearch
    TVShowSearchResult searchByDetailUrlFromES(String detailUrl);

    TVShowSearchResult saveToES(TVShowSearchResult result);

    void deleteFromES(TVShowSearchResult result);

    TVShowSearchResult getFromES(String id);

    List<TVShowSearchResult> searchBySearchTermFromES(String key, SearchTerm searchTerm);
}
