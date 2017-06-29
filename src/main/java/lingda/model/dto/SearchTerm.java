package lingda.model.dto;

/**
 * Created by lingda on 28/06/2017.
 */
public class SearchTerm {
    private String term;

    public SearchTerm() {
    }

    public SearchTerm(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "SearchTerm{" +
                "term='" + term + '\'' +
                '}';
    }
}
