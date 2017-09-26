package kosien.procon.application.matatabidb.mydatabase;


import android.widget.ArrayAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
;
import android.widget.Toast;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.Matatabi;


/**
 * Created by procon-kyougi on 2017/09/13.
 * データベースアクセス用クラス
 */

public class StationInfoDao{

    private stationInfoDBOpenHelper helper = null;

    public StationInfoDao(Context context) {
        helper = new stationInfoDBOpenHelper(context);
    }

    public Station_Infomation save(Station_Infomation stationInfo) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Station_Infomation result = null;
        try {
            ContentValues values = new ContentValues();
            values.put(Station_Infomation.STATION_NAME, stationInfo.getStationname());
            values.put(Station_Infomation.KANA_NAME, stationInfo.getKananame());
            values.put(Station_Infomation.BET_NAME, stationInfo.getBetname());
            values.put(Station_Infomation.STATION_CODE,stationInfo.getStationCode());
            values.put(Station_Infomation.STATION_NUMBER, stationInfo.getStationNumber());
            values.put(Station_Infomation.STATION_POST, stationInfo.getStationpost());
            values.put(Station_Infomation.STATION_ADDRESS, stationInfo.getStationaddress());
            values.put(Station_Infomation.STATION_LATITUDE, stationInfo.getStationlatitude());
            values.put(Station_Infomation.STATION_LONGITUDE, stationInfo.getStationlongitude());
            values.put(Station_Infomation.TOURIST_FLAG, stationInfo.gettouristFlag());
            values.put(Station_Infomation.STATION_PICTURE1, stationInfo.getstationPicture1());
            values.put(Station_Infomation.STATION_PICTURE2, stationInfo.getstationPicture2());
            values.put(Station_Infomation.STATION_PICTURE3, stationInfo.getstationPicture3());
            values.put(Station_Infomation.STATION_PICTURE4, stationInfo.getstationPicture4());
            values.put(Station_Infomation.STATION_PICTURE5, stationInfo.getstationPicture5());
            values.put(Station_Infomation.STATION_PICTURE6, stationInfo.getstationPicture6());
            values.put(Station_Infomation.STATION_PICTURE7, stationInfo.getstationPicture7());
            values.put(Station_Infomation.STATION_PICTURE8, stationInfo.getstationPicture8());
            values.put(Station_Infomation.STATION_PICTURE9, stationInfo.getstationPicture9());
            values.put(Station_Infomation.CITY_EVA_VALUE, stationInfo.getCityEva());
            Long rowId = stationInfo.getRowid();

            if (rowId == null) {
                rowId = db.insert(Station_Infomation.TABLE_NAME, null, values);
            } else {
                db.update(Station_Infomation.TABLE_NAME, values, Station_Infomation.COLUMN_ID + "=?", new String[]{String.valueOf(rowId)});
            }
            result = load(rowId);
        } finally {
            db.close();
        }
        return result;
    }




    /**
     * レコードの削除
     *
     * @param stationInfo 削除対象のオブジェクト
     */

    public void delete(Station_Infomation stationInfo) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.delete(Station_Infomation.TABLE_NAME, Station_Infomation.COLUMN_ID + "=?", new String[]{String.valueOf(stationInfo.getRowid())});
        } finally {
            db.close();
        }
    }

    /**
     * idでBizCardをロードする
     *
     * @param rowId PK
     * @return ロード結果
     */
    public Station_Infomation load(Long rowId) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Station_Infomation bizCard = null;
        try {
            Cursor cursor = db.query(Station_Infomation.TABLE_NAME, null, Station_Infomation.COLUMN_ID + "=?", new String[]{String.valueOf(rowId)}, null, null, null);
            cursor.moveToFirst();
            bizCard = getStationInfo(cursor);
        } finally {
            db.close();
        }
        return bizCard;
    }

    /**
     * 一覧を取得する
     *
     * @return 検索結果
     */

/*    public List<Station_Infomation> list() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<Station_Infomation> bizCardList;
        try {
            Cursor cursor = db.query(Station_Infomation.TABLE_NAME, null, null, null, null, null, Station_Infomation.COLUMN_ID);
            bizCardList = new ArrayAdapter<Station_Infomation>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                bizCardList.add(getStationInfo(cursor));
                cursor.moveToNext();
            }
        } finally {
            db.close();
        }
        return bizCardList;
    }*/

    //指定条件のデータを文字列として返す

//    public ArrayList<String> findStationInfo(String search_data, String column_name) {
//        String sql = "SELECT " + column_name + " FROM " + Station_Infomation.TABLE_NAME + " WHERE " + column_name + " == " +search_data;
//        SQLiteDatabase db = helper.getReadableDatabase();
//        ArrayList<String> bizCardList = null;
//        Cursor cursor = null;
//
//        //アダプター初期化
//        bizCardList.clear();
//        try {
//            cursor = db.rawQuery(sql,null);
//            int count = cursor.getCount();
//            //カーソルの行数だけループ
//            for(int i = 0; i < count;i++){
//                //データベースから取得した情報がカーソルに入っている
//                String data = cursor.getString(0) + "\n";
//                //アダプターにこの情報を追加
//                bizCardList.add(data);
//                //カーソルを次に移動
//                cursor.moveToNext();
//            }
//        }catch(Exception e){
//            Log.e("SQL Error",e.toString());
//            Toast.makeText(Matatabi.getInstance(),"もうこれわかんねぇな",Toast.LENGTH_LONG).show();
//        }finally{
//            //カーソルを閉じる
//            if(cursor != null){
//                cursor.close();
//            }
//        }
//
//        return bizCardList;
//    }




    public ArrayList<Station_Infomation> findStationInfo(String search_data, String column_name){
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Station_Infomation> number = null;

        try{
            String query = "SELECT " + column_name + " FROM " + Station_Infomation.TABLE_NAME + " WHERE " + column_name + " == " +search_data;
            Cursor cursor = db.rawQuery(query,null);
            int count = cursor.getCount();
            //カーソルの行数だけループ
            for(int i = 0; i < count;i++){
                number.add(getStationInfo(cursor));
                //カーソルを次に移動
                cursor.moveToNext();
            }
        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }

        return number;
    }





    //指定条件のデータに検索SQLをかけて条件に合うやつを返す(第２引数・・・要素数)
    public ArrayList<String> findStationInfo(String sql,int num) {
      //  String sql = "select " + column_name + "from " + Station_Infomation.TABLE_NAME + "while " + column_name + " == " +search_data;
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<String> bizCardList = null;
        Cursor cursor = null;

        //アダプター初期化
        bizCardList.clear();
        try {
            cursor = db.rawQuery(sql,null);
            int count = cursor.getCount();
            //カーソルの行数だけループ
            for(int i = 0; i < count;i++){
                //データベースから取得した情報がカーソルに入っている
                
                String data = cursor.getString(0) + "\n";
                //アダプターにこの情報を追加
                bizCardList.add(data);
                //カーソルを次に移動
                cursor.moveToNext();
            }
        }catch(Exception e){
            Log.e("SQL Error",e.toString());
            Toast.makeText(Matatabi.getInstance(),"もうこれわかんねぇな",Toast.LENGTH_LONG).show();
        }finally{
            //カーソルを閉じる
            if(cursor != null){
                cursor.close();
            }
        }

        return bizCardList;
    }

    /**
     * カーソルからオブジェクトへの変換
     *
     * @param cursor カーソル
     * @return 変換結果
     */

    private Station_Infomation getStationInfo(Cursor cursor) {
        Station_Infomation staInfo = new Station_Infomation();
        staInfo.setRowid(cursor.getLong(0));
        staInfo.setStationName(cursor.getString(1));
        staInfo.setKanaName(cursor.getString(2));
        staInfo.setBetName(cursor.getString(3));
        staInfo.setStationCode(cursor.getLong(4));
        staInfo.setStationNumber(cursor.getString(5));
        staInfo.setStationPost(cursor.getString(6));
        staInfo.setStationAddress(cursor.getString(7));
        staInfo.setStationLatitude(cursor.getDouble(8));
        staInfo.setStationLongitude(cursor.getDouble(9));
        staInfo.setTouristFlag(cursor.getString(10));
        staInfo.setStationPicture1(cursor.getString(11));
        staInfo.setStationPicture2(cursor.getString(12));
        staInfo.setStationPicture3(cursor.getString(13));
        staInfo.setStationPicture4(cursor.getString(14));
        staInfo.setStationPicture5(cursor.getString(15));
        staInfo.setStationPicture6(cursor.getString(16));
        staInfo.setStationPicture7(cursor.getString(17));
        staInfo.setStationPicture8(cursor.getString(18));
        staInfo.setStationPicture9(cursor.getString(19));
        staInfo.setCITY_EVA(cursor.getString(20));
        return staInfo;
    }
}
