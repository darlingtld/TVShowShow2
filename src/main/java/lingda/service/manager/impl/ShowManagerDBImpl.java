package lingda.service.manager.impl;

import lingda.dao.TVShowRepository;
import lingda.model.pojo.TVShow;
import lingda.service.manager.ShowManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lingda on 24/03/2017.
 */
@Service
public class ShowManagerDBImpl implements ShowManager {

    @Autowired
    private TVShowRepository tvShowRepository;

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

//    @Override
//    public TVShow getShowByDetailUrl(String detailUrl) {
//        return null;
//    }

//    @Override
//    public TVShowSearchResult save(TVShowSearchResult result) {
//        return searchResultRepository.save(result);
//    }
//
//    @Override
//    public void delete(TVShowSearchResult result) {
//        searchResultRepository.delete(result);
//    }
//
//    @Override
//    public TVShowSearchResult findOne(String id) {
//        return searchResultRepository.findOne(id);
//    }
}
