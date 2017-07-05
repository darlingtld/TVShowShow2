package lingda.model.dto;

public class TVShowDTO {
    private String name;
    private String englishName;
    private String description;
    private Integer season;
    private Integer episode;
    private String url;

    public TVShowDTO(String name, String englishName, String description, Integer season, Integer episode, String url) {
        this.name = name;
        this.englishName = englishName;
        this.description = description;
        this.season = season;
        this.episode = episode;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "TVShowDTO{" +
                "name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", description='" + description + '\'' +
                ", season=" + season +
                ", episode=" + episode +
                ", url='" + url + '\'' +
                '}';
    }
}
