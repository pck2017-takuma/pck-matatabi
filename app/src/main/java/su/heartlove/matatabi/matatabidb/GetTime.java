package su.heartlove.matatabi.matatabidb;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import java.util.Date;

/**
 * Created by procon-kyougi1 on 2017/09/15.
 */

public class GetTime {
    public static String getNowDate() {
        final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        return df.format(date);
    }
}
