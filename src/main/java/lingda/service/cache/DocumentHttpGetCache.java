package lingda.service.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lingda on 30/06/2017.
 */
@Service
public class DocumentHttpGetCache {

    private static final Logger logger = LoggerFactory.getLogger(DocumentHttpGetCache.class);

    private static LoadingCache<String, Document> documentLoadingCache;

    @PostConstruct
    private void postConstruct() {
        documentLoadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, Document>() {
                            public Document load(String url) throws IOException {
                                return Jsoup.connect(url).get();
                            }
                        });
    }


    public Document get(String url) {
        try {
            return documentLoadingCache.get(url);
        } catch (ExecutionException e) {
            logger.error("error getting Document from cache.  reason is {}", e.getMessage(), e);
            return null;
        }
    }

}
