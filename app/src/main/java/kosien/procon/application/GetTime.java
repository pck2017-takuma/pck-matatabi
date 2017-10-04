package kosien.procon.application;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by procon-kyougi1 on 2017/09/15.
 */

public class GetTime {
    public static String getNowDate() {
        final DateFormat df = new SimpleDateFormat("HH:mm:ss");
        final Date date = new Date(System.currentTimeMillis());
        return df.format(date);
    }

    public static String convertNowTime(String _time){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

        Date date = new Date();
        try {
            date = df.parse(_time);
        }catch(ParseException e){


        }
        String strDate = new SimpleDateFormat("HH:mm:ss", Locale.JAPAN).format(date);


        return strDate;
    }





}
