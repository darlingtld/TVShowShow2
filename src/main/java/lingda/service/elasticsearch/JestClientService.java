package lingda.service.elasticsearch;

import com.google.common.io.ByteStreams;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;
import io.searchbox.indices.mapping.PutMapping;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.mapper.DocumentMapper;
import org.elasticsearch.index.mapper.RootObjectMapper;
import org.elasticsearch.index.mapper.StringFieldMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.fuzzyQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
public class JestClientService implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(JestClientService.class);
    private JestClient client = null;

    @Value("${spring.elasticsearch.jest.uris}")
    String serverUri;

    @Value("${spring.elasticsearch.jest.proxy.host}")
    String host;

    @Value("${spring.elasticsearch.jest.proxy.port}")
    Integer port;

    @Value("${spring.elasticsearch.jest.username}")
    String username;

    @Value("${spring.elasticsearch.jest.password}")
    String password;

    @PostConstruct
    private void postconstruct() {
        // Construct a new Jest client according to configuration via factory
        BasicCredentialsProvider customCredentialsProvider = new BasicCredentialsProvider();
        customCredentialsProvider.setCredentials(
                new AuthScope(host, port),
                new UsernamePasswordCredentials(username, password)
        );
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(serverUri)
                .credentialsProvider(customCredentialsProvider)
                .multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                .defaultMaxTotalConnectionPerRoute(2)
                // and no more 20 connections in total
                .maxTotalConnection(10)
                .build());
        this.client = factory.getObject();
    }

    /**
     * @param indexName
     * @return
     * @throws IOException
     */
    public void createIndexIfNotExists(String indexName) throws IOException {
//        this configuration is to use the ik and pinyin analyzer
        /*
        "index": {
            "analysis": {
                "analyzer": {
                    "ik_pinyin_analyzer": {
                        "type": "custom",
                                "tokenizer": "ik_smart",
                                "filter": ["my_pinyin", "word_delimiter"]
                    }
                },
                "filter": {
                    "my_pinyin": {
                        "type": "pinyin",
                                "first_letter": "prefix",
                                "padding_char": " "
                    }
                }
            }
        }*/
        boolean indexExists = client.execute(new IndicesExists.Builder(indexName).build()).isSucceeded();
        if (!indexExists) {
            logger.info("create elasticsearch index");
            Settings.Builder settingsBuilder = Settings.builder();
            settingsBuilder.put("index.analysis.analyzer.ik_pinyin_analyzer.type", "custom");
            settingsBuilder.put("index.analysis.analyzer.ik_pinyin_analyzer.tokenizer", "ik_smart");
            settingsBuilder.putArray("index.analysis.analyzer.ik_pinyin_analyzer.filter", "my_pinyin", "word_delimiter");
            settingsBuilder.put("index.analysis.filter.my_pinyin.type", "pinyin");
            settingsBuilder.put("index.analysis.filter.my_pinyin.first_letter", "prefix");
            settingsBuilder.put("index.analysis.filter.my_pinyin.padding_char", " ");
            JestResult jestResult = client.execute(new CreateIndex.Builder(indexName).settings(settingsBuilder.build().getAsMap()).build());
            if (jestResult.getResponseCode() != 200) {
                logger.error("fail to create elasticsearch index={}.  reason is {}", indexName, jestResult.getErrorMessage());
            } else {
                logger.info("succeed in creating elasticsearch index={}", indexName);
            }
        } else {
            logger.info("index={} already exists in elasticsearch", indexName);
        }
    }

    public void createIndexMapping(String indexName, String typeName) throws IOException, URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("es_config_json/mapping_searchresult_tvshow.json").toURI());
        String mappingJson = new String(ByteStreams.toByteArray(new FileInputStream(path.toFile())));
        JestResult result = client.execute(new PutMapping.Builder(indexName, typeName, mappingJson).build());
        if (result.getResponseCode() != 200) {
            logger.error("fail to create elasticsearch mapping for index={} type={}.  reason is {}", indexName, typeName, result.getErrorMessage());
        } else {
            logger.info("succeed in creating elasticsearch mapping for index={} type={}", indexName, typeName);
        }
    }

    public JestResult index(Object source, String indexName, String typeName) throws IOException {
        Index index = new Index.Builder(source).index(indexName).type(typeName).build();
        return client.execute(index);
    }

    public <T> List<SearchResult.Hit<T, Void>> searchBoolShouldQueryFuzzy(Class<T> clazz, Map<String, String> fieldValueMap, String indexName, String typeName) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = boolQuery();

        for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
            boolQueryBuilder = boolQueryBuilder.should(fuzzyQuery(entry.getKey(), entry.getValue()).fuzziness(Fuzziness.TWO).prefixLength(2));
        }
        searchSourceBuilder.query(boolQueryBuilder);
        logger.info("[elasticsearch query]:{}", searchSourceBuilder.toString());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(indexName)
                .addType(typeName)
                .build();

        SearchResult result = client.execute(search);

        return result.getHits(clazz);
    }

    public <T> List<SearchResult.Hit<T, Void>> searchBoolShouldQueryMatchPhrase(Class<T> clazz, Map<String, String> fieldValueMap, String indexName, String typeName) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = boolQuery();

        for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
            boolQueryBuilder = boolQueryBuilder.should(matchPhraseQuery(entry.getKey(), entry.getValue()));
        }
        searchSourceBuilder.query(boolQueryBuilder);
        logger.info("[elasticsearch query]:{}", searchSourceBuilder.toString());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(indexName)
                .addType(typeName)
                .build();

        SearchResult result = client.execute(search);

        return result.getHits(clazz);
    }

    public <T> List<SearchResult.Hit<T, Void>> searchBoolShouldQueryMatch(Class<T> clazz, Map<String, String> fieldValueMap, String indexName, String typeName) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = boolQuery();

        for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
            boolQueryBuilder = boolQueryBuilder.should(matchQuery(entry.getKey(), entry.getValue()));
        }
        searchSourceBuilder.query(boolQueryBuilder);
        logger.info("[elasticsearch query]:{}", searchSourceBuilder.toString());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(indexName)
                .addType(typeName)
                .build();

        SearchResult result = client.execute(search);

        return result.getHits(clazz);
    }

    public <T> T get(Class<T> clazz, String id, String indexName, String typeName) throws IOException {
        Get get = new Get.Builder(indexName, id).type(typeName).build();

        JestResult result = client.execute(get);

        return result.getSourceAsObject(clazz);
    }

}