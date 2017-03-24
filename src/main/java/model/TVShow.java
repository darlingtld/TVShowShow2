package model;

/**
 * Created by lingda on 11/11/16.
 */
public class TVShow {
    private String name;
    private String englishName;
    private String description;
    private Integer season;
    private Integer episode;

    @Override
    public String toString() {
        return "TVShow{" +
                "name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", description='" + description + '\'' +
                ", season=" + season +
                ", episode=" + episode +
                '}';
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
