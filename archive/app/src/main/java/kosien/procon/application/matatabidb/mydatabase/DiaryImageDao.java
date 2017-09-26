package kosien.procon.application.matatabidb.mydatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by procon-kyougi on 2017/09/16.
 */

public class DiaryImageDao {

    private DiaryImageOpenHelper helper = null;

    //コンストラクタ
    public DiaryImageDao(Context context){
        helper = new DiaryImageOpenHelper(context);

    }

    public Diary_Image save_image(Diary_Image item){
        //書き込み可能でデータベースを呼び出す
        SQLiteDatabase db = helper.getWritableDatabase();

        //結果を格納するやつ
        Diary_Image result  = null;

        try{
            //データベースからの値を格納するやつ
            ContentValues values = new ContentValues();
            //日記のカラムIDを取得
            values.put(Diary_Image.DIARY_ID,item.getDiaryId());
            //画像の保存先を参照
            values.put(Diary_Image.SAVE_IMAGE,item.getImagePass());
            //カラムIDを取得
            int rowId = item.getRowID();
            //idが初期値なら
            if(rowId == 0){
                rowId = (int)db.insert(Diary_Image.TABLE_NAME,null,values);
            }else{
                db.update(Diary_Image.TABLE_NAME,values,Diary_Image.COLUMN_ID + "=?",new String[]{String.valueOf(rowId)});
            }
            result = load_item(rowId);

        }finally{
            //処理が終了したらデータベースを閉じる
            db.close();
        }
        return result;
    }

    //レコード削除
    public void delete_item(Diary_Image item){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(Diary_Image.TABLE_NAME,Diary_Image.COLUMN_ID + "=?",new String[]{String.valueOf(item.getRowID())});
        }finally{
            db.close();
        }
    }


    //レコードの全件削除
    //後で実装　≒（やらない）

    //idを指定してロード

    public Diary_Image load_item(int itemId){

        SQLiteDatabase db = helper.getReadableDatabase();
        Diary_Image number = null;

        try{
            String query = MAKE_SQL.query_load(Diary_Image.TABLE_NAME,Diary_Image.COLUMN_ID,itemId);
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToFirst();
            number = getItem(cursor);
        }finally{
            db.close();
        }

        return number;
    }

    //カーソルからオブジェクトに変換

    private Diary_Image getItem(Cursor cursor){
        Diary_Image item = new Diary_Image();
        item.setRowid((int)cursor.getLong(0));
        item.setDiaryId((int)cursor.getLong(1));
        item.setImagePass(cursor.getString(2));

        return item;
    }


}
