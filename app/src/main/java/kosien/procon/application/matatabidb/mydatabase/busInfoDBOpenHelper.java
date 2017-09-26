package kosien.procon.application.matatabidb.mydatabase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by procon-kyougi on 2017/09/14.
 */

public class busInfoDBOpenHelper extends SQLiteOpenHelper{

    //データベース名
    private static final String DATABASE_NAME = "MATATABI";

    //データベースのバージョン
    static final int DB_VERSION = 1;

    //コンストラクタ
    public busInfoDBOpenHelper(Context context) {
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
            StringBuilder StationInfo = su.heartlove.matatabi.matatabidb.mydatabase.MAKE_SQL.createBusInfomation();
            db.execSQL(StationInfo.toString());
        } finally {
            db.endTransaction();

        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
