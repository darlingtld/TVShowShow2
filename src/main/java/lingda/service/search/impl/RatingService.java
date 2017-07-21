package lingda.service.search.impl;

import lingda.dao.RatingRepository;
import lingda.model.dto.DoubanDTO;
import lingda.model.dto.RatingDTO;
import lingda.model.pojo.Rating;
import lingda.service.crawler.RatingCrawler;
import lingda.service.search.IRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

/**
 * Created by lingda on 21/07/2017.
 */
@Service
public class RatingService implements IRatingService {

    private static final Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingCrawler ratingCrawler;

    @Override
    public RatingDTO getRatingFromDouban(String showName, String englishName) {
        logger.info("get rating from douban for {} {}", showName, englishName);
//        check if it already exists and the lastUpdate is reasonable(within 24 hours)
        Rating rating = ratingRepository.findByName(showName);
        if (rating != null && rating.getLastUpdate().toInstant().plus(1, ChronoUnit.DAYS).isAfter(Instant.now())) {
            return new RatingDTO(rating);
        } else {
//        insert or update the rating if necessary
            Optional<DoubanDTO> doubanDTO = ratingCrawler.searchRatingByName(showName);
            if (doubanDTO.isPresent()) {
                RatingDTO ratingDTO = doubanDTO.get().getRatingDTO();
                if (rating == null) {
                    rating = new Rating(showName, englishName, doubanDTO.get().getDoubanId(), ratingDTO.getMax(), ratingDTO.getAverage(), ratingDTO.getMin(), ratingDTO.getStars(), new Date());
                } else {
                    rating.setMax(ratingDTO.getMax());
                    rating.setAverage(ratingDTO.getAverage());
                    rating.setStars(ratingDTO.getStars());
                    rating.setMin(ratingDTO.getMin());
                    rating.setLastUpdate(new Date());
                }
                ratingRepository.save(rating);
                return ratingDTO;
            } else {
                return null;
            }
        }
    }
}
