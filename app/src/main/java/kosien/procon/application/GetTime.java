package kosien.procon.application;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import java.util.Date;

/**
 * Created by procon-kyougi1 on 2017/09/15.
 */

public class GetTime {
    public static String getNowDate() {
        final DateFormat df = new SimpleDateFormat("HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis() + 32400000L);
        return df.format(date);
    }
}
