package lingda.dao;

import lingda.model.pojo.Rating;
import lingda.model.pojo.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lingda on 24/03/2017.
 */
@Transactional
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating findByName(String name);

    Rating findByEnglishName(String englishName);

}
