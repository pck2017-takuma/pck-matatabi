package kosien.procon.application.matatabidb.mydatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by procon-kyougi on 2017/09/16.
 */

public class placeInfoDao {


    private placeInfoOpenHelper helper = null;
    private ArrayList<placeInfomation> returnData = new ArrayList<>();
    //コンストラクタ
    public placeInfoDao(Context context){
        helper = new placeInfoOpenHelper(context);
    }

    //レコードの保存

    public placeInfomation save_time(placeInfomation item){
        //データベースオープン
        SQLiteDatabase db = helper.getWritableDatabase();
        //結果を格納するやつ
        placeInfomation result = null;

        try{
            //データベースに値を格納するやつ
            ContentValues values = new ContentValues();
            values.put(placeInfomation.PLACE_NAME,item.getPlaceName());
            values.put(placeInfomation.PLACE_STATION,item.getPlaceStation());
            values.put(placeInfomation.LATITUDE,item.getPlaceLatitude());
            values.put(placeInfomation.LONGITUDE,item.getPlaceLongitude());
            values.put(placeInfomation.ADDRESS,item.getPlaceAddress());
            values.put(placeInfomation.COLUMN,item.getPlaceColumn());
            values.put(placeInfomation.PHONE_NUMBER,item.getPlacePhoneNumber());
            values.put(placeInfomation.POST_NUMBER,item.getPlacePostNumber());
            values.put(placeInfomation.OPEN_TIME,item.getPlaceOpenTime());
            values.put(placeInfomation.CLOSE_TIME,item.getCloseTime());
            values.put(placeInfomation.PLACE_VALUE,item.getPlaceValue());
            //行番号を取得
            int rowId = item.getRowId();

            //idが初期値ならば
            if(rowId == 0){
                rowId = (int)db.insert(placeInfomation.TABLE_NAME,null,values);
            }else{
                db.update(placeInfomation.TABLE_NAME,values,placeInfomation.COLUMN_ID + "=?",new String[]{String.valueOf(rowId)});
            }
            result = load_item(rowId);
        }finally {
            //処理が終了したらデータベースを閉じる
            db.close();
        }
        return result;
    }


    //レコードの削除
    public void delete_item(placeInfomation item){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(placeInfomation.TABLE_NAME,placeInfomation.COLUMN_ID + "=?",new String[]{String.valueOf(item.getRowId())});
        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }
    }

    public void deleteall_item(){
        SQLiteDatabase db = helper.getWritableDatabase();
        placeInfomation number = null;
        try{
            //削除のSQL文生成
            MAKE_SQL sql = new MAKE_SQL();
            String query = sql.query_deleteAlldata(placeInfomation.TABLE_NAME,placeInfomation.COLUMN_ID);
        }finally {
            db.close();
        }
    }

    public boolean findPlaceInfo(String search_data, String column_name){
        SQLiteDatabase db = helper.getReadableDatabase();
        //データを空にする。
        if(!returnData.isEmpty()) {
            returnData.clear();
        }

        try{
            String query = "select * from " + placeInfomation.TABLE_NAME + " where " + column_name + " == " + "'" + search_data + "'" + ";";
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToFirst();
            //カーソルの行数だけループ
            for(boolean next = cursor.moveToFirst();next;next= cursor.moveToNext()){
                returnData.add(getItem(cursor));
            }
        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }

        if(returnData.size() == 0){
            return false;
        }else{
            return true;
        }

    }
    public ArrayList<placeInfomation> getSearchResult(){
        return returnData;
    }


    //idを指定してロード
    public placeInfomation load_item(int itemId){
        SQLiteDatabase db = helper.getReadableDatabase();
        placeInfomation number = null;
        try{
            String query = MAKE_SQL.query_load(placeInfomation.TABLE_NAME,placeInfomation.COLUMN_ID,itemId);
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToFirst();
            number = getItem(cursor);
        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }

        return number;
    }




    //カーソルからオブジェクトに変換
    private placeInfomation getItem(Cursor cursor){

        placeInfomation item = new placeInfomation();
        item.setRowId((int)cursor.getLong(0));
        item.setPlaceName(cursor.getString(1));
        item.setPlaceStation(cursor.getString(2));
        item.setPlaceLatitude(cursor.getString(3));
        item.setPlaceLongitude(cursor.getString(4));
        item.setPlaceAddress(cursor.getString(5));
        item.setPlaceColumn(cursor.getString(6));
        item.setPlacePhoneNumber(cursor.getString(7));
        item.setPlacePostNumber(cursor.getString(8));
        item.setPlaceOpenTime(cursor.getString(9));
        item.setPlaceCloseTime(cursor.getString(10));
        item.setPlaceValue((int)cursor.getLong(11));
        return item;

    }




}
