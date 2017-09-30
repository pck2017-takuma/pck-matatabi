package kosien.procon.application.matatabidb.mydatabase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by i15317 on 2017/09/15.
 */


//レコードクラス処理クラス
public class RecordOpenHelper extends SQLiteOpenHelper {

    //データベース名称
    private static final String DB_NAME = "MATATABI";

    //データベースバージョン
    private static final int DB_VERSION = 1;

    //コンストラクタ
    public RecordOpenHelper(Context context){
        //オブジェクトを作成する
        super(context,DB_NAME,null,DB_VERSION);
    }

    //データベース生成
    @Override
    public void onCreate(SQLiteDatabase db){
        db.beginTransaction();

        try{
            //テーブル作成用SQL生成
            StringBuilder createsql = MAKE_SQL.createDiary();
            db.execSQL(createsql.toString());
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    @Override
    //データベース更新（空実装）
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        try{
            //テーブル作成用SQL生成
            StringBuilder createsql = MAKE_SQL.createDiary();
            db.execSQL(createsql.toString());
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        db.beginTransaction();
        try{
            //テーブル作成用SQL生成
            StringBuilder createsql = MAKE_SQL.createDiary();
            db.execSQL(createsql.toString());
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }



}
