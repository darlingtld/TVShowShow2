package lingda.util;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lingda on 22/06/2017.
 * <p>
 * translate the digit into Chinese characters
 */
public class NumericMapperUtil {

    private static final Map<Integer, String> singleDigitMap = new HashMap<Integer, String>() {{
        put(1, "一");
        put(2, "二");
        put(3, "三");
        put(4, "四");
        put(5, "五");
        put(6, "六");
        put(7, "七");
        put(8, "八");
        put(9, "九");
        put(10, "十");
    }};

    public static String getChineseNumber(int num) {
        Preconditions.checkArgument(num > 0 && num < 100, "number should greater than 0 and less than 100");
        StringBuilder chNumber = new StringBuilder();
        int num1P10 = num / 10;
        if (num1P10 > 0) {
            if (num1P10 == 1) {
                chNumber.append(singleDigitMap.get(10));
                if (num % 10 > 0) {
                    chNumber.append(singleDigitMap.get(num % 10));
                }
            } else {
                chNumber.append(singleDigitMap.get(num1P10));
                chNumber.append(singleDigitMap.get(10));
                if (num % 10 > 0) {
                    chNumber.append(singleDigitMap.get(num % 10));
                }
            }
        } else {
            chNumber.append(singleDigitMap.get(num));
        }
        return chNumber.toString();
    }
}


