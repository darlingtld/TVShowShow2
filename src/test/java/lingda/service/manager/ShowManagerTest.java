package lingda.service.manager;

import lingda.model.TVShow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lingda on 05/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShowManagerTest {

    @Autowired
    private ShowManager showManager;

    @Test
    public void shouldSuccessfullyAddNewTVShow(){
        TVShow tvShow = new TVShow();
        tvShow.setName("实习医生格蕾");
        tvShow.setDescription("anything");
        tvShow.setEnglishName("asdf");
        tvShow.setSeason(3);
        tvShow.setEpisode(12);
        showManager.addNew(tvShow);
    }
}
