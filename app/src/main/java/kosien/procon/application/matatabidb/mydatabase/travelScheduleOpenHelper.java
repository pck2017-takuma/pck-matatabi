package kosien.procon.application.matatabidb.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by procon-kyougi on 2017/09/30.
 */

public class travelScheduleOpenHelper extends SQLiteOpenHelper {

    //データベース名称
    private static final String DB_NAME = "MATATABI";

    //データベースバージョン
    private static final int DB_VERSION = 1;

    //コンストラクタ
    public travelScheduleOpenHelper(Context context){
        //オブジェクトを作成する
        super(context,DB_NAME,null,DB_VERSION);
    }


    private String[][] datas = new String[][]{
            {"99999","テスト旅行1","0","0"},
            {"99999","テスト旅行2","1","0"},
            {"99999","テスト旅行3","2","0"},
            {"99999","テスト旅行4","3","0"},
            {"99999","テスト旅行5","4","0"},
            {"99999","テスト旅行6","5","0"},
            {"99999","テスト旅行7","6","0"}
    };


    //データベース生成
    @Override
    public void onCreate(SQLiteDatabase db){
        db.beginTransaction();

        try{
            //テーブル作成用SQL生成
            StringBuilder createsql = MAKE_SQL.createtravelSchedule();
            db.execSQL(createsql.toString());

            for (String[] data : datas) {
                ContentValues values = new ContentValues();
                values.put(travelSchedule.TRAVEL_NUM, data[0]);
                values.put(travelSchedule.PLACE_NAME,data[1]);
                values.put(travelSchedule.ROUTE_NUM,data[2]);
                values.put(travelSchedule.FLAG,data[3]);
                db.insert(travelSchedule.TABLE_NAME, null, values);
            }

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
            StringBuilder createsql = MAKE_SQL.createtravelSchedule();
            db.execSQL(createsql.toString());


            for (String[] data : datas) {
                ContentValues values = new ContentValues();
                values.put(travelSchedule.TRAVEL_NUM, data[0]);
                values.put(travelSchedule.PLACE_NAME,data[1]);
                values.put(travelSchedule.ROUTE_NUM,data[2]);
                values.put(travelSchedule.FLAG,data[3]);
                db.insert(travelSchedule.TABLE_NAME, null, values);
            }

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
