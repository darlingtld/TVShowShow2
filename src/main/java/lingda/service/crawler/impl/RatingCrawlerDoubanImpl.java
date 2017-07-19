package lingda.service.crawler.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lingda.model.dto.DoubanDTO;
import lingda.model.dto.RatingDTO;
import lingda.service.crawler.RatingCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lingda on 18/07/2017.
 */
@Service
public class RatingCrawlerDoubanImpl implements RatingCrawler {

    private static final Logger logger = LoggerFactory.getLogger(RatingCrawlerDoubanImpl.class);

    @Value("${douban.movie.searchurl}")
    private String DOUBAN_SEARCHURL;

    @Value("${douban.movie.api}")
    private String DOUBAN_MOVIE_API;

    @Override
    public DoubanDTO searchRatingByName(String name) {
        try {
            logger.info("searching douban rating for {}", name);
            Document document = Jsoup.connect(DOUBAN_SEARCHURL + name).get();
            Elements elements = document.getElementsByClass("nbg");
            for (Element element : elements) {
                String title = element.attr("title").replaceAll("\\s+", "");
                String href = element.attr("href");
                if (title.contains(name.replaceAll("\\s+", ""))) {
                    Pattern p = Pattern.compile("\\d+");
                    Matcher m = p.matcher(href);
                    if (m.find()) {
                        String id = m.group();
                        String json = Jsoup.connect(DOUBAN_MOVIE_API + id).ignoreContentType(true).execute().body();
                        ObjectMapper objectMapper = new ObjectMapper();
                        //convert json string to object
                        JsonNode jsonNode = objectMapper.readTree(json);
                        RatingDTO ratingDTO = objectMapper.readValue(jsonNode.get("rating").toString(), RatingDTO.class);
                        return new DoubanDTO(id, ratingDTO);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("error occurs in searching douban rating for {}.  reason is {}", name, e.getMessage(), e);
        }
        return null;
    }
}
