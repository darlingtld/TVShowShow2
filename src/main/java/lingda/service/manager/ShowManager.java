package lingda.service.manager;

import lingda.model.dto.TVShowSearchResult;
import lingda.model.pojo.TVShow;

import java.awt.print.Book;
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
//    TVShow getShowByDetailUrl(String detailUrl);
//
//    TVShowSearchResult save(TVShowSearchResult result);
//
//    void delete(TVShowSearchResult result);
//
//    TVShowSearchResult findOne(String id);
}
