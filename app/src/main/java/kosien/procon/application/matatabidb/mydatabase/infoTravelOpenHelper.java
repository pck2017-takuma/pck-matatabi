package kosien.procon.application.matatabidb.mydatabase;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by procon-kyougi on 2017/09/23.
 */

public class infoTravelOpenHelper extends SQLiteOpenHelper {


    //データベース名称
    private static final String DB_NAME = "MATATABI";

    //データベースバージョン
    private static final int DB_VERSION = 1;

    //コンストラクタ
    public infoTravelOpenHelper(Context context){
        //オブジェクトを作成する
        super(context,DB_NAME,null,DB_VERSION);
    }

    //データベース生成
    @Override
    public void onCreate(SQLiteDatabase db){
        db.beginTransaction();
        try{

            //テーブル作成用SQL生成
            StringBuilder createsql = MAKE_SQL.createtravelInfo();

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

    @Override
    public void onOpen(SQLiteDatabase db){
        db.beginTransaction();
        try{

            //テーブル作成用SQL生成
            StringBuilder createsql = MAKE_SQL.createtravelInfo();

            db.execSQL(createsql.toString());
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

}
