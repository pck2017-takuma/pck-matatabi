package kosien.procon.application.matatabidb.mydatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kosien.procon.application.matatabidb.ConnectServer;
import kosien.procon.application.matatabidb.basicTimeSearch;
import kosien.procon.application.parseSearchData;

/**
 * Created by Owner on 2017/09/15.
 */


//日記データベースアクセスクラス
public class RouteInfoDaoItem {

    private RouteInfoOpenHelper helper = null;

    //現在の行程を格納する
    public RouteInfo nowRouteInfo = new RouteInfo();

    //時刻検索結果
    ArrayList<ArrayList<RouteInfo>>getRouteList;
    //採用データ
    ArrayList<RouteInfo>nowRoute;


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
            values.put(RouteInfo.ROUTE_DEPARTURE, item.getRouteDeparture());
            values.put(RouteInfo.ROUTE_DEPTTIME, item.getrouteDepttime());
            values.put(RouteInfo.ROUTE_DESTINATION, item.getRouteDestination());
            values.put(RouteInfo.ROUTE_ARVTIME, item.getRouteArvtime());
            values.put(RouteInfo.ROUTE_TRAIN, item.getRouteTrain());
            values.put(RouteInfo.TRAVEL_NUM,item.getTravelNum());
            values.put(RouteInfo.ROUTE_FLAG,item.getRouteFlag());
            values.put(RouteInfo.SCHEDULE_NUM,item.getScheduleNum());
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

    //現在の行程を確認する
    public void getNowRoute(int nowSchedule){
        //SQL文生成
        String query = "select * from " + RouteInfo.TABLE_NAME + " where " + RouteInfo.SCHEDULE_NUM + " = " + nowSchedule + " AND" + RouteInfo.ROUTE_FLAG + " = " + "1;";
        SQLiteDatabase db = helper.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery(query,null);
            nowRouteInfo = getItem(cursor);
        }finally{
            db.close();
        }
    }

    //現在の行程を取得する
    public RouteInfo getNowRouteInfo(){
        return nowRouteInfo;
    }

    //現在の行程を完了し、次の行程に移る
    public void moveRouteNext(int nowSchedule){
        SQLiteDatabase db = helper.getWritableDatabase();
        nowRoute = new ArrayList<>();
        //SQL文生成
        //現在の行程の路線かつ、ある旅行の行程であるものを操作する
        String querybefore = "update " + RouteInfo.TABLE_NAME + " set " + RouteInfo.ROUTE_FLAG + " = 0 " + "where " + nowRouteInfo.getRowid() + " = " + RouteInfo.COLUMN_ID + " AND " + nowSchedule + " = " + RouteInfo.SCHEDULE_NUM + " ;";
        String queryafter = "update " + RouteInfo.TABLE_NAME + " set " + RouteInfo.ROUTE_FLAG + " = 1 " + "where " + (nowRouteInfo.getRowid() + 1) + " = " + RouteInfo.COLUMN_ID  +" AND " + nowSchedule + " = " + RouteInfo.SCHEDULE_NUM + " ;";
        String queryget = "select * from " + RouteInfo.TABLE_NAME  + " where " + nowRouteInfo.getRowid()+1 + " = " + RouteInfo.COLUMN_ID + " AND " + nowSchedule + " = " + RouteInfo.SCHEDULE_NUM + ";";

        try{
            db.rawQuery(querybefore,null);
            db.rawQuery(queryafter,null);
            Cursor cursor = db.rawQuery(queryget,null);
            RouteInfo tmp  = getItem(cursor);
            if(cursor.getCount() != 0) {

                nowRouteInfo = tmp;


            }
        }finally {
            db.close();
        }

    }

    //次の場所への行程を組み、出発地点のフラグをONにする

    public void defalut(int num){


        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "update " + RouteInfo.TABLE_NAME + " set " + RouteInfo.ROUTE_FLAG + " = 0" + " where " + RouteInfo.SCHEDULE_NUM  + " = " + num + ";";
        String query2 = "select * from " + RouteInfo.TABLE_NAME + " where " + RouteInfo.SCHEDULE_NUM  + " = " + num + " AND " + RouteInfo.COLUMN_ID + " = 1;";

        try {
            db.rawQuery(query, null);
            Cursor cursor = db.rawQuery(query2, null);
            nowRouteInfo = getItem(cursor);

            nowRouteInfo.setRouteFlag(1);
            this.sava_diary(nowRouteInfo);
        } finally{

            db.close();
        }


    }



    //現在の行程をキャンセルし、前の行程に戻る
    public void moveRouteBefore(int nowSchedule) {
        SQLiteDatabase db = helper.getWritableDatabase();
        //SQL文生成
        //現在の行程の路線かつ、ある旅行の行程であるものを操作する
        String querybefore = "update " + RouteInfo.TABLE_NAME + " set " + RouteInfo.ROUTE_FLAG + " = 1 " + "where " + (nowRouteInfo.getRowid() - 1) + " = " + RouteInfo.COLUMN_ID + " AND " + nowSchedule + " = " + RouteInfo.SCHEDULE_NUM + " ;";
        String queryafter = "update " + RouteInfo.TABLE_NAME + " set " + RouteInfo.ROUTE_FLAG + " = 0 " + "where " + nowRouteInfo.getRowid() + " = " + RouteInfo.COLUMN_ID + " AND " + nowSchedule + " = " + RouteInfo.SCHEDULE_NUM + " ;";
        String queryget = "select * from " + RouteInfo.TABLE_NAME + " where " + nowRouteInfo.getRowid() + 1 + " = " + RouteInfo.COLUMN_ID + " AND " + nowSchedule + " = " + RouteInfo.SCHEDULE_NUM + ";";
        try {
            db.rawQuery(querybefore, null);
            db.rawQuery(queryafter, null);
            Cursor cursor = db.rawQuery(queryget, null);
            nowRouteInfo = getItem(cursor);

            //こ↑こ↓で時間セット







        } finally {
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
        item.setRouteFlag((int)cursor.getLong(7));
        item.setScheduleNum(cursor.getInt(8));
        return item;
    }



    private ArrayList<ArrayList<RouteInfo>> getJsonFromAsync(String url) {

        ConnectServer asyncGet = new ConnectServer(new ConnectServer.AsyncCallback() {
            //非同期通信が開始される前に呼び出される
            public void onPreExecute() {}
            //非同期通信が更新されたと時に呼び出される
            public void onProgressUpdate(int progress) {}
            //非同期通信がキャンセルされたt気に呼び出される
            public void onCancelled() {}
            //非同期通信が完了した時点で呼び出される
            public void onPostExecute(String result) {

                try {
                    JSONObject json = new JSONObject(result);
                    parseSearchData resultParse = new parseSearchData(json.getJSONObject("ResultSet"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        asyncGet.execute(url);
        return asyncGet.getParseData();
    }



}
