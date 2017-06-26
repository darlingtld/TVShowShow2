package lingda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lingda on 24/06/2017.
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String readersBooks() {
        return "Rest API works!";
    }
}
