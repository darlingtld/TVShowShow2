package lingda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TVShowApplication {

    private static final Logger logger = LoggerFactory.getLogger(TVShowApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TVShowApplication.class, args);
    }

}