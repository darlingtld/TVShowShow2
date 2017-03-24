package model;

import java.net.URL;

/**
 * Created by lingda on 24/03/2017.
 */
public class DownLoadLink {

    private URL link;
    private TVShow tvShow;
    private LinkType linkType;

    @Override
    public String toString() {
        return "DownLoadLink{" +
                "link=" + link +
                ", tvShow=" + tvShow +
                ", linkType=" + linkType +
                '}';
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public TVShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TVShow tvShow) {
        this.tvShow = tvShow;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    enum LinkType {
        THUNDER
    }
}
