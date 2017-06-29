package lingda.model.dto;

/**
 * Created by lingda on 28/06/2017.
 */
public class TVShowSearchResult {

    private String name;
    private String description;
    private String englishName;
    private String tvSource;
    private Integer year;
    private String status;
    private Integer season;
    private Integer episode;
    private String detailUrl;
    private String imgUrl;

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getTvSource() {
        return tvSource;
    }

    public void setTvSource(String tvSource) {
        this.tvSource = tvSource;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "TVShowSearchResult{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", englishName='" + englishName + '\'' +
                ", tvSource='" + tvSource + '\'' +
                ", year=" + year +
                ", status='" + status + '\'' +
                ", season=" + season +
                ", episode=" + episode +
                ", detailUrl='" + detailUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
