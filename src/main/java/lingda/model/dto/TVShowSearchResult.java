package lingda.model.dto;


import io.searchbox.annotations.JestId;

/**
 * Created by lingda on 28/06/2017.
 */
public class TVShowSearchResult {

    @JestId
    private String id;
    private String name;
    private String description;
    private String englishName;
    private String tvSource;
    private Integer year;
    private String status;
    private String category;
    private Integer season;
    private Integer episode;
    private String detailUrl;
    private String imgUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", englishName='" + englishName + '\'' +
                ", tvSource='" + tvSource + '\'' +
                ", year=" + year +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                ", season=" + season +
                ", episode=" + episode +
                ", detailUrl='" + detailUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TVShowSearchResult)) return false;

        TVShowSearchResult that = (TVShowSearchResult) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getEnglishName() != null ? !getEnglishName().equals(that.getEnglishName()) : that.getEnglishName() != null)
            return false;
        if (getTvSource() != null ? !getTvSource().equals(that.getTvSource()) : that.getTvSource() != null)
            return false;
        if (getYear() != null ? !getYear().equals(that.getYear()) : that.getYear() != null) return false;
        if (getStatus() != null ? !getStatus().equals(that.getStatus()) : that.getStatus() != null) return false;
        if (getCategory() != null ? !getCategory().equals(that.getCategory()) : that.getCategory() != null)
            return false;
        if (getSeason() != null ? !getSeason().equals(that.getSeason()) : that.getSeason() != null) return false;
        if (getEpisode() != null ? !getEpisode().equals(that.getEpisode()) : that.getEpisode() != null) return false;
        if (getDetailUrl() != null ? !getDetailUrl().equals(that.getDetailUrl()) : that.getDetailUrl() != null)
            return false;
        return getImgUrl() != null ? getImgUrl().equals(that.getImgUrl()) : that.getImgUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getEnglishName() != null ? getEnglishName().hashCode() : 0);
        result = 31 * result + (getTvSource() != null ? getTvSource().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getSeason() != null ? getSeason().hashCode() : 0);
        result = 31 * result + (getEpisode() != null ? getEpisode().hashCode() : 0);
        result = 31 * result + (getDetailUrl() != null ? getDetailUrl().hashCode() : 0);
        result = 31 * result + (getImgUrl() != null ? getImgUrl().hashCode() : 0);
        return result;
    }
}
