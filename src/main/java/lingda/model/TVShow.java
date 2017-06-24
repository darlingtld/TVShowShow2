package lingda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lingda on 11/11/16.
 */
@Entity
@Table(name = "tvshow")
public class TVShow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "english_name")
    private String englishName;
    private String description;
    private Integer season;
    private Integer episode;

    public TVShow(Long id, String name, String englishName, String description, Integer season, Integer episode) {
        this.id = id;
        this.name = name;
        this.englishName = englishName;
        this.description = description;
        this.season = season;
        this.episode = episode;
    }

    public TVShow() {
    }

    @Override
    public String toString() {
        return "TVShow{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", description='" + description + '\'' +
                ", season=" + season +
                ", episode=" + episode +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
