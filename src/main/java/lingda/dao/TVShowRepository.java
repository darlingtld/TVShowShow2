package lingda.dao;

import lingda.model.pojo.TVShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lingda on 24/03/2017.
 */
@Transactional
public interface TVShowRepository extends JpaRepository<TVShow, Long> {

    List<TVShow> findByName(String name);

    @Query("select new lingda.model.pojo.TVShow(max(id), name, max(englishName), max(description), season, max(episode)) " +
            "from TVShow tv group by tv.name, tv.season")
    List<TVShow> findLatestListPerShow();
}
