package lingda.service.manager;

import lingda.model.TVShow;

import java.util.List;

/**
 * Created by lingda on 24/03/2017.
 */
public interface ShowManager {

    List<TVShow> getShowList(String name);

    List<TVShow> getShowList();

    List<TVShow> getLatestListPerShow();

    TVShow addNew(TVShow tvShow);
}
