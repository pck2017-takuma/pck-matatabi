package kosien.procon.application.matatabidb.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.mydatabase.MAKE_SQL;
import kosien.procon.application.matatabidb.mydatabase.placeInfomation;
import kosien.procon.application.matatabidb.mydatabase.storeGenre;
import kosien.procon.application.matatabidb.mydatabase.storeGenreOpenHelper;

/**
 * Created by Owner on 2017/10/22.
 */

public class storeGenreDao{


    private storeGenreOpenHelper helper = null;

    //コンストラクタ
    public storeGenreDao(Context context) {
        helper = new storeGenreOpenHelper(context);
    }

    //レコードの保存

    public storeGenre save_time(storeGenre item) {
        //データベースオープン
        SQLiteDatabase db = helper.getWritableDatabase();
        //結果を格納するやつ
        storeGenre result = null;

        try {
            //データベースに値を格納するやつ
            ContentValues values = new ContentValues();
            values.put(storeGenre.GENRE_ID, item.getGenreId());
            values.put(storeGenre.GENRE_NAME, item.getGenreName());
            //行番号を取得

            long rowId = item.getRowId();

            //idが初期値ならば
            if (rowId == 0) {
                rowId = (int) db.insert(storeGenre.TABLE_NAME, null, values);
            } else {
                db.update(storeGenre.TABLE_NAME, values, storeGenre.COLUMN_ID + "=?", new String[]{String.valueOf(rowId)});
            }
            result = load_item(rowId);
        } finally {
            //処理が終了したらデータベースを閉じる
            db.close();
        }
        return result;
    }


    //レコードの削除
    public void delete_item(storeGenre item) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.delete(storeGenre.TABLE_NAME, storeGenre.COLUMN_ID + "=?", new String[]{String.valueOf(item.getRowId())});
        } finally {
            //処理が終わったらデータベースを閉じる
            db.close();
        }
    }

//    public void deleteall_item() {
//        SQLiteDatabase db = helper.getWritableDatabase();
//        storeGenre number = null;
//        try {
//            //削除のSQL文生成
//            MAKE_SQL sql = new MAKE_SQL();
//            String query = sql.query_deleteAlldata(storeGenre.TABLE_NAME, storeGenre.COLUMN_ID);
//        } finally {
//            db.close();
//        }
//    }




    //idを指定してロード
    public storeGenre load_item(long itemId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        storeGenre number = null;
        try {
            Cursor cursor = db.query(storeGenre.TABLE_NAME,null, storeGenre.GENRE_ID + " = ?",new String[]{String.valueOf(itemId)},null,null,null);
            cursor.moveToFirst();
            number = getItem(cursor);
        } finally {
            //処理が終わったらデータベースを閉じる
            db.close();
        }

        return number;
    }


    //カーソルからオブジェクトに変換
    private storeGenre getItem(Cursor cursor) {

        storeGenre item = new storeGenre();
        item.setRowId(cursor.getLong(0));
        item.setGenreId(cursor.getString(1));
        item.setGenreName(cursor.getString(2));

        return item;

    }

}
