package lingda;

import lingda.service.elasticsearch.JestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class TVShowApplication {

    @Value("${elasticsearch.index.searchresult}")
    private String INDEX_NAME_TVSHOWSEARCHRESULT;

    @Value("${elasticsearch.type.searchresult}")
    private String TYPE_NAME_TVSHOWSEARCHRESULT;

    @Autowired
    private JestClientService jestClientService;

    private static final Logger logger = LoggerFactory.getLogger(TVShowApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TVShowApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void createElasticsearchIndexIfNotExists() throws IOException, URISyntaxException {
        logger.info("set up the elasticsearch indices and mappings");
        jestClientService.createIndexIfNotExists(INDEX_NAME_TVSHOWSEARCHRESULT);
        jestClientService.createIndexMapping(INDEX_NAME_TVSHOWSEARCHRESULT, TYPE_NAME_TVSHOWSEARCHRESULT);
    }

}