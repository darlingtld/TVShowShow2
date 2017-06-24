package lingda.util;

import com.google.common.base.Preconditions;

/**
 * Created by lingda on 23/06/2017.
 */
public class SeasonEpisodeJoinerUtil {

    public static String buildSeaonEpisodeString(int season, int episode) {
        Preconditions.checkArgument(season > 0 && season < 100, "season should be greater than 0 and less than 100");
        return String.format("S%02dE%02d", season, episode);
    }
}
