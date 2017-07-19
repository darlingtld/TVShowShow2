package lingda.model.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lingda on 19/07/2017.
 */
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "english_name")
    private String englishName;
    @Column(name = "douban_id")
    private String doubanId;
    private Double max;
    private Double average;
    private Double min;
    private Integer stars;
    @Column(name = "last_update")
    private Date lastUpdate;

    public Rating() {
    }

    public Rating(String name, String englishName, String doubanId, Double max, Double average, Double min, Integer stars, Date lastUpdate) {

        this.name = name;
        this.englishName = englishName;
        this.doubanId = doubanId;
        this.max = max;
        this.average = average;
        this.min = min;
        this.stars = stars;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                ", doubanId='" + doubanId + '\'' +
                ", max=" + max +
                ", average=" + average +
                ", min=" + min +
                ", stars=" + stars +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public String getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(String doubanId) {
        this.doubanId = doubanId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}
