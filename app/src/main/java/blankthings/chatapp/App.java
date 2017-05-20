package blankthings.chatapp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by iosif on 5/20/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }

}
