package kosien.procon.application.matatabidb.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by procon-kyougi on 2017/09/30.
 */

public class travelScheduleDao {



    private travelScheduleOpenHelper helper = null;

    //コンストラクタ
    public travelScheduleDao(Context context){
        helper = new travelScheduleOpenHelper(context);
    }
    //レコードの保存
    public travelSchedule sava_diary(travelSchedule item){
        //書き込み可能でデータベースを読み出し
        SQLiteDatabase db = helper.getWritableDatabase();
        //結果を格納するやつ
        travelSchedule result = null;
        try{
            //データベースに値を格納するやつ
            ContentValues values = new ContentValues();
            values.put( travelSchedule.TRAVEL_NUM, item.getTravelNum());
            values.put( travelSchedule.PLACE_NAME, item.getPlaceName());
            values.put( travelSchedule.ROUTE_NUM, item.getRouteNum());

            int rowId = item.getRowid();

            //idが初期値なら
            if(rowId == 0){
                rowId = (int)db.insert(travelSchedule.TABLE_NAME,null,values);
            }else{
                //そうでないなら既存データが存在するのデータベースの更新処理を行う
                db.update(travelSchedule.TABLE_NAME,values,travelSchedule.COLUMN_ID + "=?", new String[]{String.valueOf(rowId)});

            }
            result = load_item(rowId);

        }finally{
            //処理が終了したらデータベースを閉じる
            db.close();
        }
        return result;
    }

    //レコードの削除
    public void delete_item(travelSchedule item){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(travelSchedule.TABLE_NAME,travelSchedule.COLUMN_ID + "=?",new String[]{String.valueOf(item.getRowid())});

        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }
    }

    //レコードの全件削除
    public void deleteall_item(){
        SQLiteDatabase db = helper.getWritableDatabase();
        RouteInfo number = null;
        try{
            //削除のSQL文生成
            MAKE_SQL sql = new MAKE_SQL();
            String query = sql.query_deleteAlldata(travelSchedule.TABLE_NAME,travelSchedule.COLUMN_ID);
        }finally {
            db.close();
        }
    }


    //idを指定してロード
    public travelSchedule load_item(int itemId){
        SQLiteDatabase db = helper.getReadableDatabase();
        travelSchedule number = null;

        try{
            String query = MAKE_SQL.query_load(travelSchedule.TABLE_NAME,travelSchedule.COLUMN_ID,itemId);
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
    private travelSchedule getItem(Cursor cursor){
        travelSchedule item = new travelSchedule();
        item.setRowid((int)cursor.getLong(0));
        item.setTravelNum((int)cursor.getLong(1));
        item.setPlaceName(cursor.getString(2));
        item.setRouteNum((int)cursor.getLong(3));
        return item;
    }






}
