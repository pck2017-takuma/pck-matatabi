package su.heartlove.matatabi.matatabidb.mydatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by procon-kyougi on 2017/09/16.
 */

public class StationImageDao {

    private StationImageOpenHelper helper = null;

    //コンストラクタ
    public StationImageDao(Context context){
        helper = new StationImageOpenHelper(context);

    }

    public StationImage save_image(StationImage item){
        //書き込み可能でデータベースを呼び出す
        SQLiteDatabase db = helper.getWritableDatabase();

        //結果を格納するやつ
        StationImage result  = null;

        try{
            //データベースからの値を格納するやつ
            ContentValues values = new ContentValues();
            //画像の駅番号
            values.put(StationImage.STATION_NUMBER,item.getStationNumber());
            //画像の保存先を参照
            values.put(StationImage.STATION_PICTURE,item.getStationPicuture());
            //カラムIDを取得
            int rowId = item.getRowId();
            //idが初期値なら
            if(rowId == 0){
                rowId = (int)db.insert(StationImage.TABLE_NAME,null,values);
            }else{
                db.update(StationImage.TABLE_NAME,values,StationImage.COLUMN_ID + "=?",new String[]{String.valueOf(rowId)});
            }
            result = load_item(rowId);

        }finally{
            //処理が終了したらデータベースを閉じる
            db.close();
        }
        return result;
    }

    //レコード削除
    public void delete_item(StationImage item){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(StationImage.TABLE_NAME,StationImage.COLUMN_ID + "=?",new String[]{String.valueOf(item.getRowId())});
        }finally{
            db.close();
        }
    }


    //レコードの全件削除
    //後で実装　≒（やらない）

    //idを指定してロード

    public StationImage load_item(int itemId){

        SQLiteDatabase db = helper.getReadableDatabase();
        StationImage number = null;

        try{
            String query = MAKE_SQL.query_load(StationImage.TABLE_NAME,StationImage.COLUMN_ID,itemId);
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToFirst();
            number = getItem(cursor);
        }finally{
            db.close();
        }

        return number;
    }

    //カーソルからオブジェクトに変換

    private StationImage getItem(Cursor cursor){
        StationImage item = new StationImage();
        item.setRowId((int)cursor.getLong(0));
        item.setStationNumber(cursor.getString(1));
        item.setStationPicture(cursor.getString(2));

        return item;
    }


}
