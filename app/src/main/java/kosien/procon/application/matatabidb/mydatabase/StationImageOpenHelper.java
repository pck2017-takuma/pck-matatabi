package kosien.procon.application.matatabidb.mydatabase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by procon-kyougi on 2017/09/16.
 */

public class StationImageOpenHelper extends SQLiteOpenHelper {

    //データベース名
    private static final String DB_NAME = "MATATABI";
    //データベースバージョン
    private static final int DB_VERSION = 1;
    //コンストラクタ

    public StationImageOpenHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);

    }

    //テーブル作成
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            //テーブル作成用SQL文
            StringBuilder createsql = MAKE_SQL.createStationImage();
            db.execSQL(createsql.toString());
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //テーブル作成
    @Override
    public void onOpen(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            //テーブル作成用SQL文
            StringBuilder createsql = MAKE_SQL.createStationImage();
            db.execSQL(createsql.toString());
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //テーブル更新
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
