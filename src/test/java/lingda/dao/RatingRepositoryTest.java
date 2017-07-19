package lingda.dao;

import lingda.model.pojo.Rating;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 *
 * Created by lingda on 19/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingRepositoryTest {

    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void shouldAddNewRating() {
        Rating rating = ratingRepository.save(new Rating("格蕾", "Grey's Anatomy", "123456", 10.0, 8.2, 0.0, 845, new Date()));
        assertThat(rating.getId(), is(notNullValue()));
    }

    @Test
    public void shouldGetRatingByName() {
        Rating rating = ratingRepository.findByName("格蕾");
        assertThat(rating.getId(), is(notNullValue()));
    }
}
