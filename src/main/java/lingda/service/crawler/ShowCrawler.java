package lingda.service.crawler;

import lingda.model.dto.DownLoadLink;
import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.model.pojo.TVShow;

import java.util.List;
import java.util.Optional;

/**
 * Created by lingda on 11/11/16.
 */
public interface ShowCrawler {

    /**
     * @param searchTerm the searchFuzzy term
     * @return a map containing the show name and the url, null if nothing is found
     */
    List<TVShowSearchResult> search(SearchTerm searchTerm);

    /**
     * @param show the latest show that is now being watched
     * @return a map containing the show name and the url, null if nothing is found
     */
    List<TVShowSearchResult> searchShow(TVShow show);

    /**
     * get all the download links matching the url
     *
     * @param detailUrl
     * @return all the download links matching the url
     */
    List<DownLoadLink> searchDownloadLinks(String detailUrl);

    /**
     * find the reasonable show url
     *
     * @param show
     * @param showToUrlMap
     * @return
     */
    Optional<String> findMatchedShowUrl(TVShow show, List<TVShowSearchResult> showToUrlMap);

}
