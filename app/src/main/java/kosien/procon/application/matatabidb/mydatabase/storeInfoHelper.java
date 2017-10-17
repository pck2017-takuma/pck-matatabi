package kosien.procon.application.matatabidb.mydatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by procon-kyougi on 2017/10/17.
 */

//ヘルパークラス

public class storeInfoHelper extends SQLiteOpenHelper{

    //データベース名
    private static final String DB_NAME = "MATATABI";

    //データベースバージョン
    private static final int DB_VERSION = 1;

    //コンストラクタ
    public storeInfoHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    //初回データベース生成時のみ呼び出しされる
    @Override
    public void onCreate(SQLiteDatabase db){
        db.beginTransaction();


        try{
            //データベース作成
            StringBuilder builder = MAKE_SQL.createStoreInfo();
            db.execSQL(builder.toString());
            db.setTransactionSuccessful();
        }finally{
            db.endTransaction();
        }


    }

    //ヘルパー起動時に呼ばれるメソッド
    @Override
    public void onOpen(SQLiteDatabase db){
        db.beginTransaction();


        try{
            //データベース作成
            StringBuilder builder = MAKE_SQL.createStoreInfo();
            db.execSQL(builder.toString());
            db.setTransactionSuccessful();
        }finally{
            db.endTransaction();
        }

    }

    //データベース更新時（空実装）
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //何も書かない
    }



}
