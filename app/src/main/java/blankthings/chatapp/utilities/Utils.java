package blankthings.chatapp.utilities;

import android.app.Activity;
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


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();

        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
