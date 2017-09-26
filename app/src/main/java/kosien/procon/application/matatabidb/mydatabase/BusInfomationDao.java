package kosien.procon.application.matatabidb.mydatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import android.widget.Adapter;
import android.widget.ArrayAdapter;
import java.util.List;

import kosien.procon.application.matatabidb.mydatabase.BUS_INFOMATION;
import kosien.procon.application.matatabidb.mydatabase.busInfoDBOpenHelper;

/**
 * Created by procon-kyougi on 2017/09/13.
 */

public class BusInfomationDao {

    private busInfoDBOpenHelper helper = null;
    public BusInfomationDao(Context context) {
        helper = new busInfoDBOpenHelper(context);
    }

    /**
     * BizCardの保存
     * rowidがnullの場合はinsert、rowidが!nullの場合はupdate
     * @param busInfo 保存対象のオブジェクト
     * @return 保存結果
     */
    public BUS_INFOMATION save(BUS_INFOMATION busInfo){
        SQLiteDatabase db = helper.getWritableDatabase();
        BUS_INFOMATION result = null;
        try {
            ContentValues values = new ContentValues();
            values.put( BUS_INFOMATION.BUS_STOPNAME, busInfo.getBusStopName());
            values.put( BUS_INFOMATION.KANA_NAME, busInfo.getBusStopName());
            values.put( BUS_INFOMATION.BET_NAME, busInfo.getBusStopName());
            values.put( BUS_INFOMATION.BUS_NUMBER, busInfo.getBusStopName());
            values.put( BUS_INFOMATION.STATION_NUMBER, busInfo.getBusStopName());
            Long rowId = busInfo.getRowid();
            // IDがnullの場合はinsert
            if( rowId == null){
                rowId = db.insert( BUS_INFOMATION.TABLE_NAME, null, values);
            }
            else{
                db.update( BUS_INFOMATION.TABLE_NAME, values, BUS_INFOMATION.COLUMN_ID + "=?", new String[]{ String.valueOf( rowId)});
            }
            result = load( rowId);
        } finally {
            db.close();
        }
        return result;
    }

    /**
     * レコードの削除
     * @param bizCard 削除対象のオブジェクト
     */
    public void delete(BUS_INFOMATION bizCard) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.delete( BUS_INFOMATION.TABLE_NAME, BUS_INFOMATION.COLUMN_ID + "=?", new String[]{ String.valueOf( bizCard.getRowid())});
        } finally {
            db.close();
        }
    }

    /**
     * idでBizCardをロードする
     * @param rowId PK
     * @return ロード結果
     */
    public BUS_INFOMATION load(Long rowId) {
        SQLiteDatabase db = helper.getReadableDatabase();

        BUS_INFOMATION bizCard = null;
        try {
            Cursor cursor = db.query( BUS_INFOMATION.TABLE_NAME, null, BUS_INFOMATION.COLUMN_ID + "=?", new String[]{ String.valueOf( rowId)}, null, null, null);
            cursor.moveToFirst();
            bizCard = getbusInfo( cursor);
        } finally {
            db.close();
        }
        return bizCard;
    }

    /**
     * 一覧を取得する
     * @return 検索結果
     */
/*
    public ArrayAdapter<BUS_INFOMATION> list() {
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayAdapter<BUS_INFOMATION> bizCardList;
        try {
            Cursor cursor = db.query( BUS_INFOMATION.TABLE_NAME, null, null, null, null, null, BUS_INFOMATION.COLUMN_ID);
            bizCardList = new ArrayAdapter<BUS_INFOMATION>();
            cursor.moveToFirst();
            while( !cursor.isAfterLast()){
                bizCardList.add( getbusInfo( cursor));
                cursor.moveToNext();
            }
        } finally {
            db.close();
        }
        return bizCardList;
    }
*/

    /**
     * カーソルからオブジェクトへの変換
     * @param cursor カーソル
     * @return 変換結果
     */
    private BUS_INFOMATION getbusInfo( Cursor cursor){
        BUS_INFOMATION bizCard = new BUS_INFOMATION();

        bizCard.setRowid( cursor.getLong(0));
        bizCard.setBusStopName( cursor.getString(1));
        bizCard.setkanaName( cursor.getString(2));
        bizCard.setbetName( cursor.getString(3));
        bizCard.setBusNumber( cursor.getString(4));
        bizCard.setstationNumber( cursor.getString(5));
        return bizCard;
    }

}
