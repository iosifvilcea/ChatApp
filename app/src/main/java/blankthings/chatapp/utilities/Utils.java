package blankthings.chatapp.utilities;

/**
 * Utils
 *
 * Created by iosif on 5/20/17.
 */

public class Utils {


    private Utils() {}


    public static <T> T checkNotNull(T object) {
        if (object == null) {
            throw new NullPointerException("Object cannot be null.");
        }

        return object;
    }
}
