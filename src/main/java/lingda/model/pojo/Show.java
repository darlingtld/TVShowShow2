package lingda.model.pojo;

import lingda.model.dto.MovieSearchResult;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "english_name")
    private String englishName;
    private String description;

    public Show(Long id, String name, String englishName, String description) {
        this.id = id;
        this.name = name;
        this.englishName = englishName;
        this.description = description;
    }

    public Show() {
    }

    public Show(MovieSearchResult searchResult) {
        if (searchResult != null) {
            setName(searchResult.getName());
            setEnglishName(searchResult.getEnglishName());
            setDescription(searchResult.getDescription());
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
