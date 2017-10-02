package kosien.procon.application.matatabidb;

import android.app.Application;

/**
 * Created by procon-kyougi on 2017/09/14.
 */


public class Matatabi extends Application {
    private static Matatabi instance = null;
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
    public static Matatabi getInstance() {
        return instance;
    }
}