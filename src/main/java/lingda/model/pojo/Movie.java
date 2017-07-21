package lingda.model.pojo;

import lingda.model.dto.MovieSearchResult;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie extends Show{

    public Movie(Long id, String name, String englishName, String description) {
        super(id, name, englishName, description);
    }

    public Movie() {
        super();
    }

    public Movie(MovieSearchResult searchResult) {
        if (searchResult != null) {
            setName(searchResult.getName());
            setEnglishName(searchResult.getEnglishName());
            setDescription(searchResult.getDescription());
        }
    }


}
