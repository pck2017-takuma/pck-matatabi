package kosien.procon.application.matatabidb.mydatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by i15317 on 2017/09/16.
 */


//日記保存記録データベース
public class RecordTimeDao {

    private RecordTimeOpenHelper helper = null;

    //コンストラクタ
    public RecordTimeDao(Context context){
        helper = new RecordTimeOpenHelper(context);
    }

    //レコードの保存

    public RecordTime save_time(RecordTime item){
        //データベースオープン
        SQLiteDatabase db = helper.getWritableDatabase();
        //結果を格納するやつ
        RecordTime result = null;

        try{
            //データベースに値を格納するやつ
            ContentValues values = new ContentValues();
            values.put(RecordTime.DIARY_ID,item.getdiaryID());
            values.put(RecordTime.UPDATE_TIME,item.getUpdateTime());
            values.put(RecordTime.UPDATE_YEAR,item.getUpdateYear());
            values.put(RecordTime.UPDATE_DATE,item.getUpdateDate());
            values.put(RecordTime.UPDATE_DAY,item.getUpdateDay());


            //行番号を取得
            int rowId = item.getRowid();

            //idが初期値ならば
            if(rowId == 0){
                rowId = (int)db.insert(RecordTime.TABLE_NAME,null,values);
            }else{
                db.update(RecordTime.TABLE_NAME,values,RecordTime.COLUMN_ID + "=?",new String[]{String.valueOf(rowId)});

            }
            result = load_item(rowId);
        }finally {
            //処理が終了したらデータベースを閉じる
            db.close();
        }
        return result;
    }


    //レコードの削除
    public void delete_item(RecordTime item){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(RecordTime.TABLE_NAME,RecordTime.COLUMN_ID + "=?",new String[]{String.valueOf(item.getRowid())});
        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }
    }

    public void deleteall_item(){
        SQLiteDatabase db = helper.getWritableDatabase();
        RecordTime number = null;
        try{
            //削除のSQL文生成
            MAKE_SQL sql = new MAKE_SQL();
            String query = sql.query_deleteAlldata(RecordTime.TABLE_NAME,RecordTime.COLUMN_ID);
        }finally {
            db.close();
        }
    }

    //idを指定してロード
    public RecordTime load_item(int itemId){
        SQLiteDatabase db = helper.getReadableDatabase();
        RecordTime number = null;

        try{
            String query = MAKE_SQL.query_load(RecordTime.TABLE_NAME,RecordTime.COLUMN_ID,itemId);
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
    private RecordTime getItem(Cursor cursor){
        RecordTime item = new RecordTime();
        item.setRowId((int)cursor.getLong(0));
        item.setDiaryId((int)cursor.getLong(1));
        item.setDiaryUpdateTime((int)cursor.getLong(2));
        item.setUpdateYear((int)cursor.getLong(3));
        item.setUpdateDate((int)cursor.getLong(4));
        item.setUpdateDay((int)cursor.getLong(5));
        item.setUpdateDayTime((int)cursor.getLong(6));

        return item;

    }



}
