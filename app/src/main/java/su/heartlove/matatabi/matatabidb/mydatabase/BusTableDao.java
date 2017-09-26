package su.heartlove.matatabi.matatabidb.mydatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.widget.ArrayAdapter;
import java.util.List;

import su.heartlove.matatabi.matatabidb.mydatabase.BUS_TABLE;
import su.heartlove.matatabi.matatabidb.mydatabase.busDBOpenHelper;

/**
 * Created by procon-kyougi on 2017/09/13.
 */

public class BusTableDao {

    private busDBOpenHelper helper = null;

    public BusTableDao(Context context) {
        helper = new busDBOpenHelper(context);
    }

    /**
     * BizCardの保存
     * rowidがnullの場合はinsert、rowidが!nullの場合はupdate
     * @param busTable 保存対象のオブジェクト
     * @return 保存結果
     */

    public BUS_TABLE save(BUS_TABLE busTable){
        SQLiteDatabase db = helper.getWritableDatabase();
        BUS_TABLE result = null;
        try {
            ContentValues values = new ContentValues();
            values.put( BUS_TABLE.LOAD_NUMBER, busTable.getloadNumber());
            values.put( BUS_TABLE.BUS_AWAY, busTable.getloadNumber());
            values.put( BUS_TABLE.DEPARTING_TIME, busTable.getloadNumber());
            values.put( BUS_TABLE.ARRIVING_TIME, busTable.getloadNumber());
            values.put( BUS_TABLE.STATION_NUMBER, busTable.getloadNumber());
            values.put( BUS_TABLE.PLACE_NUMBER, busTable.getloadNumber());

            Long rowId = busTable.getRowid();
            // IDがnullの場合はinsert
            if( rowId == null){
                rowId = db.insert( BUS_TABLE.TABLE_NAME, null, values);
            }
            else{
                db.update( BUS_TABLE.TABLE_NAME, values, BUS_TABLE.COLUMN_ID + "=?", new String[]{ String.valueOf( rowId)});
            }
            result = load( rowId);
        } finally {
            db.close();
        }
        return result;
    }

    /**
     * レコードの削除
     * @param busTable 削除対象のオブジェクト
     */
    public void delete(BUS_TABLE busTable) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.delete( BUS_TABLE.TABLE_NAME, BUS_TABLE.COLUMN_ID + "=?", new String[]{ String.valueOf( busTable.getRowid())});
        } finally {
            db.close();
        }
    }

    /**
     * idでBizCardをロードする
     * @param rowId PK
     * @return ロード結果
     */
    public BUS_TABLE load(Long rowId) {
        SQLiteDatabase db = helper.getReadableDatabase();

        BUS_TABLE bizCard = null;
        try {
            Cursor cursor = db.query( BUS_TABLE.TABLE_NAME, null, BUS_TABLE.COLUMN_ID + "=?", new String[]{ String.valueOf( rowId)}, null, null, null);
            cursor.moveToFirst();
            bizCard = getBusTable(cursor);
        } finally {
            db.close();
        }
        return bizCard;
    }

    /**
     * 一覧を取得する
     * @return 検索結果
     */
/*    public List<BUS_TABLE> list() {
        SQLiteDatabase db = helper.getReadableDatabase();

        List<BUS_TABLE> bizCardList;
        try {
            Cursor cursor = db.query( BUS_TABLE.TABLE_NAME, null, null, null, null, null, BUS_TABLE.COLUMN_ID);
            bizCardList = new ArrayAdapter<BUS_TABLE>();
            cursor.moveToFirst();
            while( !cursor.isAfterLast()){
                bizCardList.add( getBusTable( cursor));
                cursor.moveToNext();
            }
        } finally {
            db.close();
        }
        return bizCardList;
    }*/

    /**
     * カーソルからオブジェクトへの変換
     * @param cursor カーソル
     * @return 変換結果
     */
    private BUS_TABLE getBusTable( Cursor cursor){
        BUS_TABLE bizCard = new BUS_TABLE();

        bizCard.setRowid( cursor.getLong(0));
        bizCard.setloadNumber( cursor.getString(1));
        bizCard.setbusAway( cursor.getString(2));
        bizCard.setdepatingTime( cursor.getString(3));
        bizCard.setarrivingTime( cursor.getString(4));
        bizCard.setstationNumber( cursor.getString(5));
        bizCard.setplaceNumber( cursor.getString(6));
        return bizCard;
    }

}
