package lingda.controller;

import lingda.model.TVShow;
import lingda.service.manager.ShowManager;
import lingda.service.manager.impl.ShowManagerDBImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lingda on 25/06/2017.
 */
@RestController
@RequestMapping("show")
public class ShowController {

    @Autowired
    private ShowManager showManager;

    @GetMapping
    public List<TVShow> getShowList(){
        return showManager.getShowList();
    }
}