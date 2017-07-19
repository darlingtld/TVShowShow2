package lingda.model.dto;

/**
 * Created by lingda on 18/07/2017.
 */
public class Rating {
    private double max;
    private double average;
    private int stars;
    private int min;

    public Rating() {
    }

    public Rating(double max, double average, int stars, int min) {

        this.max = max;
        this.average = average;
        this.stars = stars;
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
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
