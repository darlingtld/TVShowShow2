package lingda.service.search;

import lingda.model.dto.RatingDTO;

/**
 * Created by lingda on 21/07/2017.
 */
public interface IRatingService {
    RatingDTO getRatingFromDouban(String showName, String englishName);
}
