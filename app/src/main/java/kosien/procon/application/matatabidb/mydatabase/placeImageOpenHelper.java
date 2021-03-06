package kosien.procon.application.matatabidb.mydatabase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by procon-kyougi on 2017/09/16.
 */

public class placeImageOpenHelper extends SQLiteOpenHelper {

    //データベース名
    private static final String DB_NAME = "MATATABI";

    //データベースバージョン
    private static final int DB_VERSION = 1;

    //コンストラクタ
    public placeImageOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    //データベース作成
    @Override
    public void onCreate(SQLiteDatabase db){
        db.beginTransaction();

        try{
            //テーブル作成用SQL
            StringBuilder createsql = MAKE_SQL.createPlaceImage();
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
            //テーブル作成用SQL
            StringBuilder createsql = MAKE_SQL.createPlaceImage();
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
