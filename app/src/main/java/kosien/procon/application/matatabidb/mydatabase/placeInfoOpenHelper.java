package su.heartlove.matatabi.matatabidb.mydatabase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by procon-kyougi on 2017/09/16.
 */

public class placeInfoOpenHelper extends SQLiteOpenHelper{

    //データベース名
    private static final String DB_NAME = "MATATABI";
    //データベースバージョン
    private static final int DB_VERSION = 1;
    //コンストラクタ
    public placeInfoOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    //テーブル作成
    @Override
    public void onCreate(SQLiteDatabase db){
        db.beginTransaction();

        try{
            //データベース作成用SQL
            StringBuilder createsql = MAKE_SQL.createPlaceInfo();
            db.execSQL(createsql.toString());
            db.setTransactionSuccessful();
        }finally{
            db.endTransaction();
        }
    }

    @Override
    //データベース更新（空実装）
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }

}
