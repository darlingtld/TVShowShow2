package lingda.controller;

import lingda.model.dto.MovieSearchResult;
import lingda.model.dto.SearchTerm;
import lingda.model.dto.TVShowSearchResult;
import lingda.service.search.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Created by lingda on 25/06/2017.
 */
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping({"", "/show"})
    public List<TVShowSearchResult> searchTVShow(@RequestBody SearchTerm searchTerm) {
        return searchService.searchTVShow(searchTerm);
    }

    @PostMapping("/movie")
    public List<MovieSearchResult> searchMovie(@RequestBody SearchTerm searchTerm) {
        return searchService.searchMovie(searchTerm);
    }

    @PostMapping("/variety")
    public List<TVShowSearchResult> searchVariety(@RequestBody SearchTerm searchTerm) {
        return Collections.emptyList();
    }

}
