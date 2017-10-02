package kosien.procon.application.matatabidb.mydatabase;


import android.content.ContentValues;
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

    //駅情報データベース用サンプルデータ
    private String[][] datas = new String[][]{
            {"TDN","Y19","34.132046","133.64343","観音寺市有明町","日本さくら名所100選に選定されている。砂で作られた寛永通宝の銭形砂絵は金運スポットとして有名である。","0875-23-3933","768-0062","24時間営業","24時間営業","4"},
            {"琴弾公園","Y19","34.132046","133.64343","観音寺市有明町","日本さくら名所100選に選定されている。砂で作られた寛永通宝の銭形砂絵は金運スポットとして有名である。","0875-23-3933","768-0062","24時間営業","4"},

    };




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


            // サンプルデータの投入
            for (String[] data : datas) {
                ContentValues values = new ContentValues();
                values.put(placeInfomation.PLACE_NAME, data[0]);
                values.put(placeInfomation.PLACE_STATION,data[1]);
                values.put(placeInfomation.LATITUDE,data[2]);
                values.put(placeInfomation.LONGITUDE,data[3]);
                values.put(placeInfomation.ADDRESS,data[4]);
                values.put(placeInfomation.COLUMN,data[5]);
                values.put(placeInfomation.PHONE_NUMBER,data[6]);
                values.put(placeInfomation.POST_NUMBER,data[7]);
                values.put(placeInfomation.OPEN_TIME,data[8]);
                values.put(placeInfomation.CLOSE_TIME,data[9]);
                values.put(placeInfomation.PLACE_VALUE,data[10]);
                db.insert(placeInfomation.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        }finally{
            db.endTransaction();
        }
    }


    //テーブル作成
    @Override
    public void onOpen(SQLiteDatabase db){
        db.beginTransaction();
        try{
            //データベース作成用SQL
            StringBuilder createsql = MAKE_SQL.createPlaceInfo();
            db.execSQL(createsql.toString());


            // サンプルデータの投入
            for (String[] data : datas) {
                ContentValues values = new ContentValues();
                values.put(placeInfomation.PLACE_NAME, data[0]);
                values.put(placeInfomation.PLACE_STATION,data[1]);
                values.put(placeInfomation.LATITUDE,data[2]);
                values.put(placeInfomation.LONGITUDE,data[3]);
                values.put(placeInfomation.ADDRESS,data[4]);
                values.put(placeInfomation.COLUMN,data[5]);
                values.put(placeInfomation.PHONE_NUMBER,data[6]);
                values.put(placeInfomation.POST_NUMBER,data[7]);
                values.put(placeInfomation.OPEN_TIME,data[8]);
                values.put(placeInfomation.CLOSE_TIME,data[9]);
                values.put(placeInfomation.PLACE_VALUE,data[10]);
                db.insert(placeInfomation.TABLE_NAME, null, values);
            }


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
