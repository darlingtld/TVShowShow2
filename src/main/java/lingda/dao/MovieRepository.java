package lingda.dao;

import lingda.model.pojo.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByName(String name);

}
