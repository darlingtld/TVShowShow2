package lingda.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lingda on 23/06/2017.
 */
public class SeasonEpisodeJoinerUtilTest {

    @Test
    public void shouldBuildSeasonEpisodeString() {
        Assert.assertEquals(SeasonEpisodeJoinerUtil.buildSeaonEpisodeString(1, 1), "S01E01");
        Assert.assertEquals(SeasonEpisodeJoinerUtil.buildSeaonEpisodeString(1, 11), "S01E11");
        Assert.assertEquals(SeasonEpisodeJoinerUtil.buildSeaonEpisodeString(10, 10), "S10E10");
        Assert.assertEquals(SeasonEpisodeJoinerUtil.buildSeaonEpisodeString(20, 3), "S20E03");
    }
}
