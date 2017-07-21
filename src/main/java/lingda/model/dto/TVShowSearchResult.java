package lingda.model.dto;


/**
 * Created by lingda on 28/06/2017.
 */
public class TVShowSearchResult extends SearchResult {

    private Integer season;
    private Integer episode;
    private String tvSource;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TVShowSearchResult)) return false;
        if (!super.equals(o)) return false;

        TVShowSearchResult that = (TVShowSearchResult) o;

        if (getSeason() != null ? !getSeason().equals(that.getSeason()) : that.getSeason() != null) return false;
        if (getEpisode() != null ? !getEpisode().equals(that.getEpisode()) : that.getEpisode() != null) return false;
        return getTvSource() != null ? getTvSource().equals(that.getTvSource()) : that.getTvSource() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getSeason() != null ? getSeason().hashCode() : 0);
        result = 31 * result + (getEpisode() != null ? getEpisode().hashCode() : 0);
        result = 31 * result + (getTvSource() != null ? getTvSource().hashCode() : 0);
        return result;
    }

    public String getTvSource() {

        return tvSource;
    }

    public void setTvSource(String tvSource) {
        this.tvSource = tvSource;
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
