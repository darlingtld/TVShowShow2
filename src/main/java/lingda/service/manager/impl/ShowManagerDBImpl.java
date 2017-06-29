package lingda.service.manager.impl;

import lingda.dao.TVShowRepository;
import lingda.model.pojo.TVShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lingda.service.manager.ShowManager;

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
}
