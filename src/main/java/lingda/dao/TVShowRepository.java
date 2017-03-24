package lingda.dao;

import lingda.model.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lingda on 24/03/2017.
 */
@Transactional
public interface TVShowRepository extends JpaRepository<TVShow, Long> {

    List<TVShow> findByName(String name);
}
