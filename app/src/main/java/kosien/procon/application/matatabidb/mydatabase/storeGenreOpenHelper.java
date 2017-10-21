package kosien.procon.application.matatabidb.mydatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by procon-kyougi on 2017/10/17.
 */

public class storeGenreOpenHelper extends SQLiteOpenHelper {
    //データベース名
    public static final String DB_NAME = "MATATABI";
    //データベースバージョン
    public static final int DB_VERSION = 1;

    //コンストラクタ
    public storeGenreOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.beginTransaction();

        try{
            //テーブル作成SQL生成
            StringBuilder builder = MAKE_SQL.createStoreGenre();
            db.execSQL(builder.toString());
            db.setTransactionSuccessful();
        }finally{
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }

    @Override
    public void onOpen(SQLiteDatabase db){
        db.beginTransaction();
        try{
            //テーブル作成SQL生成
            StringBuilder builder = MAKE_SQL.createStoreGenre();
            db.execSQL(builder.toString());
            db.setTransactionSuccessful();
        }finally{
            db.endTransaction();
        }
    }

}
