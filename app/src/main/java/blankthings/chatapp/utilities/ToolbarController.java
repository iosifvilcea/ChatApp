package blankthings.chatapp.utilities;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by iosif on 5/21/17.
 */

public class ToolbarController {


    private AppCompatActivity activity;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;


    public ToolbarController(
            @NonNull AppCompatActivity activity,
            @NonNull Toolbar toolbar,
            @NonNull AppBarLayout appBarLayout) {
        this.activity = Utils.checkNotNull(activity);
        this.toolbar = Utils.checkNotNull(toolbar);
        this.appBarLayout = Utils.checkNotNull(appBarLayout);
    }


    /**
     * Allows / Disallows toolbar to scroll.
     * @param enable - true or false.
     */
    public void enableToolbarScroll(final boolean enable) {
        appBarLayout.setExpanded(true);
        final AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        if (enable) {
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
        } else {
            params.setScrollFlags(0);
        }

        toolbar.setLayoutParams(params);
    }


    public void hideToolbar() {
        if (toolbar != null) {
            toolbar.setVisibility(View.GONE);
        }
    }


    public void showToolbar() {
        if (toolbar != null) {
            toolbar.setVisibility(View.VISIBLE);
        }
    }


    public ActionBar getSupportActionBar() {
        return activity.getSupportActionBar();
    }

}
