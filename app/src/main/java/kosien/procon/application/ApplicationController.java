package kosien.procon.application;

import android.app.Application;

/**
 * Created by kokor on 2017/09/17.
 */

public class ApplicationController extends Application {
    private static ApplicationController sInstance;

    @Override

    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static synchronized ApplicationController getsInstance() {
        return sInstance;
    }
}
