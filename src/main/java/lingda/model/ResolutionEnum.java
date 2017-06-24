package lingda.model;

/**
 * Created by lingda on 23/06/2017.
 */
public enum ResolutionEnum {
    HIGH("1024X576"), MEDIUM("720X396");

    private String resolution;

    ResolutionEnum(String resolution) {
        this.resolution = resolution;
    }
}
