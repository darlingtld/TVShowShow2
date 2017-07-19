package lingda.service.crawler;

import lingda.model.dto.DoubanDTO;

/**
 * Created by lingda on 18/07/2017.
 */
public interface RatingCrawler {

    DoubanDTO searchRatingByName(String name);

}
