package kosien.procon.application.matatabidb.mydatabase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by i15317 on 2017/09/16.
 */

public class RecordTimeOpenHelper extends SQLiteOpenHelper {
    //データベース名
    private static final String DB_NAME = "MATATABI";

    //データベースバージョン
    private static final int DB_VERSION = 1;

    //コンストラクタ

    public RecordTimeOpenHelper(Context context){
        //オブジェクトを作成する
        super(context,DB_NAME,null,DB_VERSION);
    }

    //データベース
    @Override
    public void onCreate(SQLiteDatabase db){
        db.beginTransaction();

        try{
            //データベース作成用SQL
            StringBuilder createsql = MAKE_SQL.createDiaryUpdateTime();
            db.execSQL(createsql.toString());
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    @Override

    //データベース更新（空実装）
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }

}
