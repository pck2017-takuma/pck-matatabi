package kosien.procon.application.matatabidb.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by procon-kyougi on 2017/10/17.
 */

public class storeInfoDao {

    private storeInfoHelper helper = null;

    //コンストラクタ
    public storeInfoDao(Context context){
        helper = new storeInfoHelper(context);
    }

    //レコードの保存

    public storeInfoTable save_time(storeInfoTable item){
        //データベースオープン
        SQLiteDatabase db = helper.getWritableDatabase();
        //結果を格納するやつ
        storeInfoTable result = null;

        try{
            //データベースに値を格納するやつ
            ContentValues values = new ContentValues();
            values.put(storeInfoTable.STORE_NAME,item.getStoreName());
            values.put(storeInfoTable.STORE_STATION,item.getStoreStation());
            values.put(storeInfoTable.LATITUDE,item.getStoreLatitude());
            values.put(storeInfoTable.LONGITUDE,item.getStoreLongitude());
            values.put(storeInfoTable.ADDRESS,item.getPlaceAddress());
            values.put(storeInfoTable.COLUMN,item.getStoreColumn());
            values.put(storeInfoTable.PHONE_NUM,item.getStorePhoneNum());
            values.put(storeInfoTable.POST_NUM,item.getStorePostNum());
            values.put(storeInfoTable.OPEN_TIME,item.getStoreOpenTime());
            values.put(storeInfoTable.CLOSE_TIME,item.getStoreCloseTime());
            values.put(storeInfoTable.STORE_GENRE,item.getStoreGenre());
            //行番号を取得
            int rowId = item.getRowId();

            //idが初期値ならば
            if(rowId == 0){
                rowId = (int)db.insert(storeInfoTable.TABLE_NAME,null,values);
            }else{
                db.update(storeInfoTable.TABLE_NAME,values,storeInfoTable.COLUMN_ID + "=?",new String[]{String.valueOf(rowId)});
            }
            result = load_item(rowId);
        }finally {
            //処理が終了したらデータベースを閉じる
            db.close();
        }
        return result;
    }


    //レコードの削除
    public void delete_item(storeInfoTable item){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(storeInfoTable.TABLE_NAME,storeInfoTable.COLUMN_ID + "=?",new String[]{String.valueOf(item.getRowId())});
        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }
    }

    public void deleteall_item(){
        SQLiteDatabase db = helper.getWritableDatabase();
        storeInfoTable number = null;
        try{
            //削除のSQL文生成
            MAKE_SQL sql = new MAKE_SQL();
            String query = sql.query_deleteAlldata(storeInfoTable.TABLE_NAME,storeInfoTable.COLUMN_ID);
        }finally {
            db.close();
        }
    }


    //idを指定してロード
    public storeInfoTable load_item(int itemId){
        SQLiteDatabase db = helper.getReadableDatabase();
        storeInfoTable number = null;
        try{
            String query = MAKE_SQL.query_load(storeInfoTable.TABLE_NAME,storeInfoTable.COLUMN_ID,itemId);
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
    private storeInfoTable getItem(Cursor cursor){

        storeInfoTable item = new storeInfoTable();
        item.setRowId((int)cursor.getLong(0));
        item.setStoreName(cursor.getString(1));
        item.setStoreStation(cursor.getString(2));
        item.setStoreLatitude(cursor.getString(3));
        item.setStoreLongitude(cursor.getString(4));
        item.setStoreAddress(cursor.getString(5));
        item.setStoreColumn(cursor.getString(6));
        item.setStorePhoneNum(cursor.getString(7));
        item.setStorePostNum(cursor.getString(8));
        item.setStoreOpenTime(cursor.getString(9));
        item.setStoreCloseTime(cursor.getString(10));
        item.setStoreGenre((int)cursor.getLong(11));
        return item;

    }

}
