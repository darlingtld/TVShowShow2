package lingda.model.pojo;

import lingda.model.dto.TVShowSearchResult;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tvshow")
public class TVShow extends Show{

    private Integer season;
    private Integer episode;

    public TVShow(Long id, String name, String englishName, String description, Integer season, Integer episode) {
        super(id, name, englishName, description);
        this.season = season;
        this.episode = episode;
    }

    public TVShow() {
    }

    public TVShow(TVShowSearchResult searchResult) {
        if (searchResult != null) {
            setName(searchResult.getName());
            setEnglishName(searchResult.getEnglishName());
            setDescription(searchResult.getDescription());
            setSeason(searchResult.getSeason());
            setEpisode(searchResult.getEpisode());
        }
    }

    @Override
    public String toString() {
        return "TVShow{" +
                "season=" + season +
                ", episode=" + episode +
                "} " + super.toString();
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

}
