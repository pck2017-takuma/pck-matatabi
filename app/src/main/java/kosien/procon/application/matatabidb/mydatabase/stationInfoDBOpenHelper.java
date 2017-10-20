package kosien.procon.application.matatabidb.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by procon-kyougi on 2017/09/14.
 */

//このクラス用SQL文生成ツール


public class stationInfoDBOpenHelper extends SQLiteOpenHelper {

    //データベース名
    private static final String DATABASE_NAME = "MATATABI";

    //駅情報データベース用サンプルデータ
    private String[][] datas = new String[][]{
            {"田所fdfsa", "デバックエキ", "Debug_Station", "114514", "DEBUG99", "114-514", "香川県三豊市下北沢551", "81.05897", "174.514", "0", "114514"},
            {"田所dsf23432", "デバックエキ", "Debug_Station", "114514", "DEBUG99", "114-514", "香川県三豊市下北沢551", "81.05897", "174.514", "0", "114514"},
            {"田所あああああ", "デバックエキ", "Debug_Station", "114514", "DEBUG99", "114-514", "香川県三豊市下北沢551", "81.05897", "174.514", "0", "114514"},
            {"田所", "デバックエキ", "Debug_Station", "114514", "DEBUG99", "114-514", "香川県三豊市下北沢551", "81.05897", "174.514", "0", "114514"},
            {"田所", "デバックエキ", "Debug_Station", "114514", "DEBUG99", "114-514", "香川県三豊市下北沢551", "81.05897", "174.514", "0", "114514"},
            {"田所", "デバックエキ", "Debug_Station", "114514", "DEBUG99", "114-514", "香川県三豊市下北沢551", "81.05897", "174.514", "0", "114514"},
            {"田所", "デバックエキ", "Debug_Station", "114514", "DEBUG99", "114-514", "香川県三豊市下北沢551", "81.05897", "174.514", "0", "114514"},
            {"田所", "デバックエキ", "Debug_Station", "114514", "DEBUG99", "114-514", "香川県三豊市下北沢551", "81.05897", "174.514", "0", "114514"},
            {"遠野", "デバックエキ", "Debug_Station", "114514", "DEBUG98", "114-514", "香川県三豊市下北沢551", "52.04322", "164.514", "0", "114514"},
            {"KMR", "デバックエキ", "Debug_Station", "114514", "DEBUG98", "114-514", "香川県三豊市下北沢551", "52.04322", "164.514", "0", "114514"},
            {"MUR", "デバックエキ", "Debug_Station", "114514", "DEBUG98", "114-514", "香川県三豊市下北沢551", "52.04322", "164.514", "0", "114514"},
            {"TNOK", "デバックエキ", "Debug_Station", "114514", "DEBUG98", "114-514", "香川県三豊市下北沢551", "52.04322", "164.514", "0", "114514"},
            {"TDN", "デバックエキ", "Debug_Station", "114514", "DEBUG98", "114-514", "香川県三豊市下北沢551", "52.04322", "164.514", "0", "114514"}
    };

    //データベースのバージョン
    static final int DB_VERSION = 1;

    //コンストラクタ
    public stationInfoDBOpenHelper(Context context) {
        //指定したデータベース名が存在しないときは、新たに作成されonCreate()が実行される
        //バージョンを変更するとonUpgrade()が呼ばれるよ
        super(context, DATABASE_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();

        //列車時刻表テーブルの作成
        try {
            //SQL取得
            MAKE_SQL tmp = new MAKE_SQL();
            StringBuilder StationInfo = tmp.createStationInfomation();
            db.execSQL(StationInfo.toString());

            // サンプルデータの投入
            for (String[] data : datas) {
                ContentValues values = new ContentValues();
                values.put(Station_Infomation.STATION_NAME, data[0]);
                values.put(Station_Infomation.KANA_NAME, data[1]);
                values.put(Station_Infomation.BET_NAME, data[2]);
                values.put(Station_Infomation.STATION_CODE, data[3]);
                values.put(Station_Infomation.STATION_NUMBER, data[4]);
                values.put(Station_Infomation.STATION_POST, data[5]);
                values.put(Station_Infomation.STATION_ADDRESS, data[6]);
                values.put(Station_Infomation.STATION_LATITUDE, Double.parseDouble(data[7]));
                values.put(Station_Infomation.STATION_LONGITUDE, Double.parseDouble(data[8]));
                values.put(Station_Infomation.TOURIST_FLAG, data[9]);
                values.put(Station_Infomation.CITY_EVA_VALUE, data[10]);

                db.insert(Station_Infomation.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {

        db.beginTransaction();

        //列車時刻表テーブルの作成
        try {
            //SQL取得
            MAKE_SQL tmp = new MAKE_SQL();
            StringBuilder StationInfo = tmp.createStationInfomation();
            db.execSQL(StationInfo.toString());

//            // サンプルデータの投入
//            for (String[] data : datas) {
//                ContentValues values = new ContentValues();
//                values.put(Station_Infomation.STATION_NAME, data[0]);
//                values.put(Station_Infomation.KANA_NAME, data[1]);
//                values.put(Station_Infomation.BET_NAME, data[2]);
//                values.put(Station_Infomation.STATION_CODE,data[3]);
//                values.put(Station_Infomation.STATION_NUMBER, data[4]);
//                values.put(Station_Infomation.STATION_POST, data[5]);
//                values.put(Station_Infomation.STATION_ADDRESS, data[6]);
//                values.put(Station_Infomation.STATION_LATITUDE, Double.parseDouble(data[7]));
//                values.put(Station_Infomation.STATION_LONGITUDE, Double.parseDouble(data[8]));
//                values.put(Station_Infomation.TOURIST_FLAG, data[9]);
//                values.put(Station_Infomation.CITY_EVA_VALUE, data[10]);
//
//                db.insert(Station_Infomation.TABLE_NAME, null, values);
//            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();

        }
    }


}
