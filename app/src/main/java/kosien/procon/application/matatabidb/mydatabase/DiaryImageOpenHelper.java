package kosien.procon.application.matatabidb.mydatabase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by procon-kyougi on 2017/09/16.
 */

public class DiaryImageOpenHelper extends SQLiteOpenHelper {

    //データベース名
    private static final String DB_NAME = "MATATABI";

    //データベースバージョン
    private static final int DB_VERSION = 1;

    //コンストラクタ
    public DiaryImageOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    //データベース作成
    public void onCreate(SQLiteDatabase db){
        db.beginTransaction();

        try{
            //テーブル作成用SQL

            StringBuilder createsql = MAKE_SQL.createDiaryImage();
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
