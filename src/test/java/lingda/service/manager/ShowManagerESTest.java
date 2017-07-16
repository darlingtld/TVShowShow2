package lingda.service.manager;

import com.google.common.collect.ImmutableMap;
import io.searchbox.client.JestResult;
import io.searchbox.core.SearchResult;
import lingda.model.dto.TVShowSearchResult;
import lingda.service.elasticsearch.JestClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShowManagerESTest {

    @Autowired
    private JestClientService jestClientService;

    @Before
    public void before() {
    }

    @Test
    public void testCreateIndex() throws IOException {
        JestResult jestResult = jestClientService.createIndex("test_index");
        System.out.println(jestResult);
    }

    @Test
    public void testIndexDocument() throws IOException {
        TVShowSearchResult result = new TVShowSearchResult();
        result.setName("test");
        JestResult jestResult = jestClientService.index(result, "test_index", "showshow");
        System.out.println(jestResult.getErrorMessage());
    }

    @Test
    public void testSearchDocuments() throws IOException {
        List<SearchResult.Hit<TVShowSearchResult, Void>> resultList = jestClientService.searchFuzzy(TVShowSearchResult.class, ImmutableMap.of("englishName", "hero"), "searchresult", "tvshowsearchresult");
        for (SearchResult.Hit<TVShowSearchResult, Void> result : resultList) {
            System.out.println(result.id);
            System.out.println(result.source.getName());
        }
    }

    @Test
    public void testGetDocument() throws IOException {
        TVShowSearchResult result = jestClientService.get(TVShowSearchResult.class, "test_index", "AV0rB64nqI2Cg47fRtxw", "showshow");
        System.out.println(result.getName());
    }

    @Test
    public void testSearchShowResultByDetailUrl() throws IOException {
        List<SearchResult.Hit<TVShowSearchResult, Void>> resultList = jestClientService.search(TVShowSearchResult.class, ImmutableMap.of("detailUrl", "http://www.meijutt.com/content/meiju20884.html"), "searchresult", "tvshowsearchresult");
        for (SearchResult.Hit<TVShowSearchResult, Void> result : resultList) {
            System.out.println(result.id);
            System.out.println(result.source.getName());
        }
    }

    @Test
    public void testSearchShowResultUsingChinsese() throws IOException {
        List<SearchResult.Hit<TVShowSearchResult, Void>> resultList = jestClientService.search(TVShowSearchResult.class, ImmutableMap.of("name", "血族"), "searchresult", "tvshowsearchresult");
        for (SearchResult.Hit<TVShowSearchResult, Void> result : resultList) {
            System.out.println(result.id);
            System.out.println(result.source.getName());
        }
    }
//
//    @Test
//    public void testFindByTitle() {
//
//        Book book = new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
//        showManager.save(book);
//
//        List<Book> byTitle = showManager.findByTitle(book.getTitle());
//        assertThat(byTitle.size(), is(1));
//    }
//
//    @Test
//    public void testFindByAuthor() {
//
//        List<Book> bookList = new ArrayList<>();
//
//        bookList.add(new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
//        bookList.add(new Book("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
//        bookList.add(new Book("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
//        bookList.add(new Book("1007", "Spring Data + ElasticSearch", "Rambabu Posa", "01-APR-2017"));
//        bookList.add(new Book("1008", "Spring Boot + MongoDB", "Mkyong", "25-FEB-2017"));
//
//        for (Book book : bookList) {
//            showManager.save(book);
//        }
//
//        Page<Book> byAuthor = showManager.findByAuthor("Rambabu Posa", new PageRequest(0, 10));
//        assertThat(byAuthor.getTotalElements(), is(4L));
//
//        Page<Book> byAuthor2 = showManager.findByAuthor("Mkyong", new PageRequest(0, 10));
//        assertThat(byAuthor2.getTotalElements(), is(1L));
//
//    }
//
//    @Test
//    public void testDelete() {
//
//        Book book = new Book("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
//        showManager.save(book);
//        showManager.delete(book);
//        Book testBook = showManager.findOne(book.getId());
//        assertNull(testBook);
//    }

}