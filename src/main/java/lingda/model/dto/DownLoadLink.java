package lingda.model.dto;

import lingda.model.pojo.TVShow;

/**
 * Created by lingda on 24/03/2017.
 */
public class DownLoadLink {

    private String link;
    private TVShow tvShow;
    private LinkType linkType;

    @Override
    public String toString() {
        return "DownLoadLink{" +
                "link=" + link +
                ", tvShow=" + tvShow +
                '}';
    }

    public DownLoadLink(String link, TVShow tvShow) {
        this.link = link;
        this.tvShow = tvShow;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public TVShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TVShow tvShow) {
        this.tvShow = tvShow;
    }

    public LinkType getLinkType() {
        if (this.link.startsWith("ed2k")) {
            return LinkType.EMULE;
        } else {
            return linkType.THUNDER;
        }
    }

    enum LinkType {
        THUNDER, EMULE
    }
}
