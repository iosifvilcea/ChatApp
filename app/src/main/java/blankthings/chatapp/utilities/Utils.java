package blankthings.chatapp.utilities;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Calendar;

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


    public static String getTodaysFormattedDate() {
        final String daysArray[] = {"Sun","Mon","Tues", "Wed","Thurs","Fri", "Sat"};
        final Calendar c = Calendar.getInstance();
        final int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        final int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        return String.format("%s, %s", daysArray[dayOfWeek], String.valueOf(dayOfMonth));
    }


    public static void hideKeyboard(final View view, final Context context) {
            final InputMethodManager imm =
                    (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
