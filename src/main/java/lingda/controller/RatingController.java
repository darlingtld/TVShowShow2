package lingda.controller;

import lingda.model.dto.RatingDTO;
import lingda.service.search.impl.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lingda on 25/06/2017.
 */
@RestController
@RequestMapping("rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/douban")
    public RatingDTO getRatingFromDouban(@RequestParam("name") String showName, @RequestParam(value = "englishName", required = false) String englishName) {
        return ratingService.getRatingFromDouban(showName, englishName);
    }
}
