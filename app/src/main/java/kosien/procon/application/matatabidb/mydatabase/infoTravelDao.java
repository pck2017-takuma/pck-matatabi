package kosien.procon.application.matatabidb.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by procon-kyougi on 2017/09/23.
 */

public class infoTravelDao {


    private infoTravelOpenHelper helper = null;

    //コンストラクタ
    public infoTravelDao(Context context){
        helper = new infoTravelOpenHelper(context);
    }

    //レコードの保存

    public infoTravel save_time(infoTravel item){
        //データベースオープン
        SQLiteDatabase db = helper.getWritableDatabase();
        //結果を格納するやつ
        infoTravel result = null;

        try{
            //データベースに値を格納するやつ
            ContentValues values = new ContentValues();
            values.put(infoTravel.TRAVEL_NUM,item.gettravelNum());
            //行番号を取得
            int rowId = item.getRowid();

            //idが初期値ならば
            if(rowId == 0){
                rowId = (int)db.insert(infoTravel.TABLE_NAME,null,values);
            }else{
                db.update(infoTravel.TABLE_NAME,values,infoTravel.COLUMN_ID + "=?",new String[]{String.valueOf(rowId)});
            }
            result = load_item(rowId);

        }finally {
            //処理が終了したらデータベースを閉じる
            db.close();
        }
        return result;
    }


    //レコードの削除
    public void delete_item(infoTravel item){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(infoTravel.TABLE_NAME,infoTravel.COLUMN_ID + "=?",new String[]{String.valueOf(item.getRowid())});
        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }
    }

    public void deleteall_item(){
        SQLiteDatabase db = helper.getWritableDatabase();
        infoTravel number = null;
        try{
            //削除のSQL文生成
            MAKE_SQL sql = new MAKE_SQL();
            String query = sql.query_deleteAlldata(infoTravel.TABLE_NAME,infoTravel.COLUMN_ID);
        }finally {
            db.close();
        }
    }

    //idを指定してロード
    public infoTravel load_item(int itemId){
        SQLiteDatabase db = helper.getReadableDatabase();
        infoTravel number = null;
        try{
        //テーブル名、検索カラム、検索番号
            String query = MAKE_SQL.query_load(infoTravel.TABLE_NAME,infoTravel.TRAVEL_NUM,itemId);
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToFirst();
            number = getItem(cursor);
        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }

        return number;
    }

    //とりあえず全件取得
    public ArrayList<infoTravel> load_item(){
        ArrayList<infoTravel>returnData = new ArrayList<infoTravel>();
        SQLiteDatabase db = helper.getReadableDatabase();
        infoTravel number = null;


        try{
        //テーブル名、検索カラム、検索番号
            String query = "select * from "+ infoTravel.TABLE_NAME + ";";
            Cursor cursor = db.rawQuery(query,null);
            boolean next = cursor.moveToFirst();
            //存在数だけループする
            while(next){
                returnData.add(getItem(cursor));
                next = cursor.moveToNext();
            }

        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }

        return returnData;
    }




    //カーソルからオブジェクトに変換
    private infoTravel getItem(Cursor cursor){
        infoTravel item = new infoTravel();

        item.setRowid((int)cursor.getLong(0));
        item.setTravelNum((int)cursor.getLong(1));
        item.setTravelTitle(cursor.getString(2));
        return item;
    }
}
