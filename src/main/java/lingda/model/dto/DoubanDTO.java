package lingda.model.dto;

/**
 * Created by lingda on 19/07/2017.
 */
public class DoubanDTO {

    private String doubanId;
    private RatingDTO ratingDTO;

    public String getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(String doubanId) {
        this.doubanId = doubanId;
    }

    public RatingDTO getRatingDTO() {
        return ratingDTO;
    }

    public void setRatingDTO(RatingDTO ratingDTO) {
        this.ratingDTO = ratingDTO;
    }

    public DoubanDTO() {

    }

    @Override
    public String toString() {
        return "DoubanDTO{" +
                "doubanId='" + doubanId + '\'' +
                ", ratingDTO=" + ratingDTO +
                '}';
    }

    public DoubanDTO(String doubanId, RatingDTO ratingDTO) {

        this.doubanId = doubanId;
        this.ratingDTO = ratingDTO;
    }
}
