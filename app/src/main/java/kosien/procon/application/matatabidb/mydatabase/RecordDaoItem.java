package kosien.procon.application.matatabidb.mydatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kosien.procon.application.matatabidb.Matatabi;

/**
 * Created by Owner on 2017/09/15.
 */


//日記データベースアクセスクラス
public class RecordDaoItem {


    private RecordOpenHelper helper = null;

    //コンストラクタ
    public RecordDaoItem(Context context){
        helper = new RecordOpenHelper(context);
    }
    //レコードの保存
    public RecordItem sava_diary(RecordItem item){
        //書き込み可能でデータベースを読み出し
        SQLiteDatabase db = helper.getWritableDatabase();
        //結果を格納するやつ
        RecordItem result = null;
        try{
            //データベースに値を格納するやつ
            ContentValues values = new ContentValues();
            values.put( RecordItem.DIARY_TITLE, item.getDiaryTitle());
            values.put(RecordItem.DIARY_RECORD,item.getDiaryRecord());
            values.put(RecordItem.DIARY_YEAR,item.getDiaryYear());
            values.put(RecordItem.DIARY_MON,item.getDiaryMon());
            values.put(RecordItem.DIARY_Day,item.getDiaryDay());
            values.put(RecordItem.DIARY_TIME,item.getDiaryTime());
            values.put(RecordItem.TRAVEL_NUM,item.gettravelNum());
            int rowId = item.getRowid();

            //idが初期値なら
            if(rowId == 0){
                rowId = (int)db.insert(RecordItem.TABLE_NAME,null,values);
            }else{
                //そうでないなら既存データが存在するのデータベースの更新処理を行う
                db.update(RecordItem.TABLE_NAME,values,RecordItem.COLUMN_ID + "=?", new String[]{String.valueOf(rowId)});

            }
            result = load_item(rowId);

            }finally{
            //処理が終了したらデータベースを閉じる
            db.close();
        }
        return result;
    }

    //レコードの削除
    public void delete_item(RecordItem item){
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            db.delete(RecordItem.TABLE_NAME,RecordItem.COLUMN_ID + "=?",new String[]{String.valueOf(item.getRowid())});

        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }
    }

    //レコードの全件削除
    public void deleteall_item(){
        SQLiteDatabase db = helper.getWritableDatabase();
        RecordItem number = null;
        try{
            //削除のSQL文生成
            MAKE_SQL sql = new MAKE_SQL();
            String query = sql.query_deleteAlldata(RecordItem.TABLE_NAME,RecordItem.COLUMN_ID);
        }finally {
            db.close();
        }
    }


    //idを指定してロード
    public RecordItem load_item(int itemId){
        SQLiteDatabase db = helper.getReadableDatabase();
        RecordItem number = null;
        try{
            String query = MAKE_SQL.query_load(RecordItem.TABLE_NAME,RecordItem.COLUMN_ID,itemId);
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToFirst();
            number = getItem(cursor);
        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }

        return number;
    }

    public ArrayList<RecordItem> loadRecord(int travelnum){
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<RecordItem> number = null;

        try{
            String query = MAKE_SQL.query_load(RecordItem.TABLE_NAME,RecordItem.COLUMN_ID,travelnum);
            Cursor cursor = db.rawQuery(query,null);
            int count = cursor.getCount();
            //カーソルの行数だけループ
            for(int i = 0; i < count;i++){

                number.add(getItem(cursor));
                //カーソルを次に移動
                cursor.moveToNext();
            }
        }finally{
            //処理が終わったらデータベースを閉じる
            db.close();
        }

        return number;
    }




    public List list_search_item(RecordItem record, boolean searchWord ) {
        SQLiteDatabase db = helper.getReadableDatabase();

        List itemList;
        String searchCulmn = null;

        /* 検索する条件を設定 */
        String searchNikki = RecordItem.getItemSearchRecord();
        int searchNen = RecordItem.getDiarySearchyear();
        int searchTuki = RecordItem.getItemSearchMon();
        int searchHi = RecordItem.getItemSearchDay();

        if( searchWord == true ){
            if( searchNikki.length() > 0 ){
                searchCulmn = (RecordItem.DIARY_RECORD + " like '%" + searchNikki + "%' ").toString();
            }
        }
        else{
            if( searchHi == 0 ){
                searchCulmn = RecordItem.DIARY_YEAR + " = '" + searchNen + "' AND " +
                        RecordItem.DIARY_MON + " = '" + searchTuki + "' ";
            }
            else {
                searchCulmn = RecordItem.DIARY_YEAR + " = '" + searchNen + "' AND " +
                        RecordItem.DIARY_MON + " = '" + searchTuki + "' AND " +
                        RecordItem.DIARY_Day + " = '" + searchHi + "' ";
            }
        }

        /* where句の条件を得たら検索 */
        try {
            String query = null;
            query = "select * " +
                    " from " + RecordItem.TABLE_NAME +
                    " where " + searchCulmn + ";";

            Cursor cursor = db.rawQuery(query, null);

            itemList = new ArrayList();
            cursor.moveToFirst();
            while( !cursor.isAfterLast()){
                itemList.add( getItem( cursor ) );
                cursor.moveToNext();
            }
        } finally {
            db.close();
        }
        return itemList;
    }







    //カーソルからオブジェクトに変換
    private RecordItem getItem(Cursor cursor){
        RecordItem item = new RecordItem();
        item.setRowid((int)cursor.getLong(0));
        item.setDiaryTitle(cursor.getString(1));
        item.setDiaryRecord(cursor.getString(2));
        return item;
    }










}
