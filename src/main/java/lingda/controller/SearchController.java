package lingda.controller;

import lingda.model.SearchTerm;
import lingda.model.TVShow;
import lingda.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lingda on 25/06/2017.
 */
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/show")
    public List<TVShow> searchTVShow(@RequestBody SearchTerm searchTerm) {
        return null;
    }

}
