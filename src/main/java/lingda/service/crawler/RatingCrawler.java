package lingda.service.crawler;

import lingda.model.dto.Rating;

/**
 * Created by lingda on 18/07/2017.
 */
public interface RatingCrawler {

    Rating searchRatingByName(String name);

}
