package lingda.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lingda on 22/06/2017.
 */
public class NumericMapperUtilTest {

    @Test
    public void shouldGetValidChineseNumber() {
        Assert.assertEquals(NumericMapperUtil.getChineseNumber(1), "一");
        Assert.assertEquals(NumericMapperUtil.getChineseNumber(10), "十");
        Assert.assertEquals(NumericMapperUtil.getChineseNumber(13), "十三");
        Assert.assertEquals(NumericMapperUtil.getChineseNumber(20), "二十");
        Assert.assertEquals(NumericMapperUtil.getChineseNumber(21), "二十一");
    }
}
