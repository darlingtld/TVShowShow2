package lingda.service.crawler;

import lingda.model.dto.DoubanDTO;

import java.util.Optional;

/**
 * Created by lingda on 18/07/2017.
 */
public interface RatingCrawler {

    Optional<DoubanDTO> searchRatingByName(String name);

}
