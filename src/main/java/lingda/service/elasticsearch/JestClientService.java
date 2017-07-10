package lingda.service.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Service
public class JestClientService implements Serializable {
    private static final long serialVersionUID = 1L;
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
     * Settings.Builder settingsBuilder = Settings.builder();
     * settingsBuilder.put("number_of_shards",5);
     * settingsBuilder.put("number_of_replicas",1);
     * client.execute(new CreateIndex.Builder("articles").settings(settingsBuilder.build().getAsMap()).build());
     *
     * @param indexName
     * @return
     * @throws IOException
     */
    public JestResult createIndex(String indexName) throws IOException {
        return client.execute(new CreateIndex.Builder(indexName).build());
    }

//    public JestResult createIndexMapping(String indexName){
//        RootObjectMapper.Builder rootObjectMapperBuilder = new RootObjectMapper.Builder("my_mapping_name").add(
//                new StringFieldMapper.Builder("message").store(true)
//        );
//        DocumentMapper documentMapper = new DocumentMapper.Builder("my_index", null, rootObjectMapperBuilder).build(null);
//        String expectedMappingSource = documentMapper.mappingSource().toString();
//        PutMapping putMapping = new PutMapping.Builder(
//                "my_index",
//                "my_type",
//                expectedMappingSource
//        ).build();
//        client.execute(putMapping);
//
//    }

    public JestResult index(Object source, String indexName, String typeName) throws IOException {
        Index index = new Index.Builder(source).index(indexName).type(typeName).build();
        return client.execute(index);
    }

    public <T> List<SearchResult.Hit<T, Void>> search(Class<T> clazz, String key, String value, String indexName, String typeName) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(key, value));

        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex(indexName)
                .addType(typeName)
                .build();

        SearchResult result = client.execute(search);

        return result.getHits(clazz);
    }

    public <T> T get(Class<T> clazz, String indexName, String id, String typeName) throws IOException {
        Get get = new Get.Builder(indexName, id).type(typeName).build();

        JestResult result = client.execute(get);

        return result.getSourceAsObject(clazz);
    }

}