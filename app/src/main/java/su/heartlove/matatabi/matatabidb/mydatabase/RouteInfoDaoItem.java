package su.heartlove.matatabi.matatabidb.mydatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 2017/09/15.
 */


//日記データベースアクセスクラス
public class RouteInfoDaoItem {


    private RouteInfoOpenHelper helper = null;

    //コンストラクタ
    public RouteInfoDaoItem(Context context){
        helper = new RouteInfoOpenHelper(context);
    }
    //レコードの保存
    public RouteInfo sava_diary(RouteInfo item){
        //書き込み可能でデータベースを読み出し
        SQLiteDatabase db = helper.getWritableDatabase();
        //結果を格納するやつ
        RouteInfo result = null;
        try{
            //データベースに値を格納するやつ
            ContentValues values = new ContentValues();
            values.put( RouteInfo.ROUTE_DEPARTURE, item.getRouteDeparture());
            values.put(RouteInfo.ROUTE_DEPTTIME, item.getrouteDepttime());
            values.put(RouteInfo.ROUTE_DESTINATION, item.getRouteDestination());
            values.put(RouteInfo.ROUTE_ARVTIME, item.getRouteArvtime());
            values.put(RouteInfo.ROUTE_TRAIN, item.getRouteTrain());
            values.put(RouteInfo.TRAVEL_NUM,item.getTravelNum());

            int rowId = item.getRowid();

            //idが初期値なら
            if(rowId == 0){
                rowId = (int)db.insert(RouteInfo.TABLE_NAME,null,values);
            }else{
                //そうでないなら既存データが存在するのデータベースの更新処理を行う
                db.update(RouteInfo.TABLE_NAME,values,RouteInfo.COLUMN_ID + "=?", new String[]{String.valueOf(rowId)});

            }
            result = load_item(rowId);

            }finally{
            //処理が終了したらデータベースを閉じる
            db.close();
        }
        return result;
    }

    //レコードの削除
    public void delete_item(RouteInfo item){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(RouteInfo.TABLE_NAME,RouteInfo.COLUMN_ID + "=?",new String[]{String.valueOf(item.getRowid())});

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
            String query = sql.query_deleteAlldata(RouteInfo.TABLE_NAME,RouteInfo.COLUMN_ID);
        }finally {
            db.close();
        }
    }


    //idを指定してロード
    public RouteInfo load_item(int itemId){
        SQLiteDatabase db = helper.getReadableDatabase();
        RouteInfo number = null;

        try{
            String query = MAKE_SQL.query_load(RouteInfo.TABLE_NAME,RouteInfo.COLUMN_ID,itemId);
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
    private RouteInfo getItem(Cursor cursor){
        RouteInfo item = new RouteInfo();
        item.setRowid((int)cursor.getLong(0));
        item.setRouteDeparture(cursor.getString(1));
        item.setRouteDepttime(cursor.getString(2));
        item.setRouteDestination(cursor.getString(3));
        item.setRouteArvtime(cursor.getString(4));
        item.setRouteTrain(cursor.getString(5));
        item.setTravelNum((int)cursor.getLong(6));

        return item;
    }










}
