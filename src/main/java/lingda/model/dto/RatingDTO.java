package lingda.model.dto;

import lingda.model.pojo.Rating;

/**
 * Created by lingda on 18/07/2017.
 */
public class RatingDTO {
    private Double max;
    private Double average;
    private Integer stars;
    private Double min;

    public RatingDTO() {
    }

    public RatingDTO(Double max, Double average, Integer stars, Double min) {

        this.max = max;
        this.average = average;
        this.stars = stars;
        this.min = min;
    }

    public RatingDTO(Rating rating) {
        setMax(rating.getMax());
        setAverage(rating.getAverage());
        setMin(rating.getMin());
        setStars(rating.getStars());
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

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "max=" + max +
                ", average=" + average +
                ", stars=" + stars +
                ", min=" + min +
                '}';
    }
}
