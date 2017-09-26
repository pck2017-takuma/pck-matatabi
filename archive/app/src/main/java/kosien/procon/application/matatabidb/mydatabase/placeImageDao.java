package kosien.procon.application.matatabidb.mydatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by procon-kyougi on 2017/09/16.
 */

public class placeImageDao {

    private placeImageOpenHelper helper = null;

    //コンストラクタ
    public placeImageDao(Context context){

        helper = new placeImageOpenHelper(context);

    }

    public placeImage save_image(placeImage item){
        //書き込み可能でデータベースを呼び出す
        SQLiteDatabase db = helper.getWritableDatabase();

        //結果を格納するやつ
        placeImage result  = null;

        try{
            //データベースからの値を格納するやつ
            ContentValues values = new ContentValues();
            //日記のカラムIDを取得
            values.put(placeImage.PLACE_ID,item.getPlaceId());
            //画像の保存先を参照
            values.put(placeImage.SAVE_IMAGE,item.getImagePass());
            //カラムIDを取得
            int rowId = item.getRowID();
            //idが初期値なら
            if(rowId == 0){
                rowId = (int)db.insert(placeImage.TABLE_NAME,null,values);
            }else{
                db.update(placeImage.TABLE_NAME,values,placeImage.COLUMN_ID + "=?",new String[]{String.valueOf(rowId)});
            }
            result = load_item(rowId);

        }finally{
            //処理が終了したらデータベースを閉じる
            db.close();
        }
        return result;
    }

    //レコード削除
    public void delete_item(placeImage item){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(placeImage.TABLE_NAME,placeImage.COLUMN_ID + "=?",new String[]{String.valueOf(item.getRowID())});
        }finally{
            db.close();
        }
    }


    //レコードの全件削除
    //後で実装　≒（やらない）

    //idを指定してロード

    public placeImage load_item(int itemId){

        SQLiteDatabase db = helper.getReadableDatabase();
        placeImage number = null;

        try{
            String query = MAKE_SQL.query_load(placeImage.TABLE_NAME,placeImage.COLUMN_ID,itemId);
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToFirst();
            number = getItem(cursor);
        }finally{
            db.close();
        }

        return number;
    }

    //カーソルからオブジェクトに変換

    private placeImage getItem(Cursor cursor){
        placeImage item = new placeImage();
        item.setRowid((int)cursor.getLong(0));
        item.setPlaceId((int)cursor.getLong(1));
        item.setImagePass(cursor.getString(2));

        return item;
    }


}
