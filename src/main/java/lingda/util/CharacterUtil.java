package lingda.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

/**
 * Created by lingda on 30/06/2017.
 */
public class CharacterUtil {

    public static String removeBadCharacter(String raw) {
        try {
            ByteBuffer input = ByteBuffer.wrap(raw.getBytes("GBK"));
            CharsetDecoder utf8Decoder = Charset.forName("UTF-8").newDecoder();
            utf8Decoder.onMalformedInput(CodingErrorAction.REPLACE);
            utf8Decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
            utf8Decoder.replaceWith("?");
            CharBuffer output = utf8Decoder.decode(input);
            String outputString = output.toString();
            return outputString.replaceAll("\\?", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
