package lingda.service.crawler;

import lingda.model.DownLoadLink;
import lingda.model.TVShow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by lingda on 11/11/16.
 */
public abstract class ShowCrawler {

    private static final Logger logger = LoggerFactory.getLogger(ShowCrawler.class);

    /**
     * @param show the latest show that is now being watched
     * @return a map containing the show name and the url, null if nothing is found
     */
    protected abstract Map<String, String> searchShow(TVShow show);

    /**
     * get all the download links matching the url
     * @param showUrl
     * @param show
     * @return all the download links matching the url
     */
    protected abstract List<DownLoadLink> findDownloadLinks(String showUrl, TVShow show);

    /**
     * get the download links that the user is gonna watch next.  filter out the already watched shows
     * @param downLoadLinkList
     * @param show
     * @return get the next shows waiting to be watched
     */
    protected abstract List<DownLoadLink> getMatchedDownloadLinksForWatching(List<DownLoadLink> downLoadLinkList, TVShow show);

    /**
     * find the reasonable show url
     * @param show
     * @param showToUrlMap
     * @return
     */
    protected abstract Optional<String> findMatchedShowUrl(TVShow show, Map<String, String> showToUrlMap);

    public List<DownLoadLink> getDownloadLinks(TVShow show) {

        Map<String, String> showToUrlMap = searchShow(show);

        if (Objects.nonNull(showToUrlMap)) {
            List<DownLoadLink> downLoadLinkList = new ArrayList<>();
            Optional<String> showUrl = findMatchedShowUrl(show, showToUrlMap);
            showUrl.ifPresent(s -> downLoadLinkList.addAll(findDownloadLinks(s, show)));
            return getMatchedDownloadLinksForWatching(downLoadLinkList, show);
        } else {
            return Collections.emptyList();
        }
    }

}
