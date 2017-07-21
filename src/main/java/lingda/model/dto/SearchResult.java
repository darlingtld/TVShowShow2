package lingda.model.dto;

import io.searchbox.annotations.JestId;

/**
 * Created by lingda on 21/07/2017.
 */
public abstract class SearchResult {

    @JestId
    private String id;
    private String name;
    private String description;
    private String englishName;
    private Integer year;
    private String status;
    private String category;
    private String detailUrl;
    private String imgUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchResult)) return false;

        SearchResult that = (SearchResult) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getEnglishName() != null ? !getEnglishName().equals(that.getEnglishName()) : that.getEnglishName() != null)
            return false;
        if (getYear() != null ? !getYear().equals(that.getYear()) : that.getYear() != null) return false;
        if (getStatus() != null ? !getStatus().equals(that.getStatus()) : that.getStatus() != null) return false;
        if (getCategory() != null ? !getCategory().equals(that.getCategory()) : that.getCategory() != null)
            return false;
        if (getDetailUrl() != null ? !getDetailUrl().equals(that.getDetailUrl()) : that.getDetailUrl() != null)
            return false;
        return getImgUrl() != null ? getImgUrl().equals(that.getImgUrl()) : that.getImgUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getEnglishName() != null ? getEnglishName().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getDetailUrl() != null ? getDetailUrl().hashCode() : 0);
        result = 31 * result + (getImgUrl() != null ? getImgUrl().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", englishName='" + englishName + '\'' +
                ", year=" + year +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
