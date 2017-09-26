package su.heartlove.matatabi.matatabidb.mydatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.ArrayAdapter;;
import android.widget.Toast;


import su.heartlove.matatabi.matatabidb.Matatabi;

/**
 * Created by procon-kyougi on 2017/09/14.
 */

//  C++でいう基底クラス？
abstract public class findSQL {
    //変数とか？
    String DB_NAME;
    ArrayAdapter<String> adapter;
    stationInfoDBOpenHelper stationInfoDB;         //データベース
    SQLiteDatabase db;

    //データベースからデータ文字データで引っ張る
    public ArrayAdapter<String> toSQL(String search_data, String column_name) {

        //データベースオープン
        try{
            db = stationInfoDB.getReadableDatabase();
        }catch(SQLiteException e){
            Toast.makeText(Matatabi.getInstance(),"ンアッー！データベース開けない",Toast.LENGTH_LONG).show();

            //ここのエラー処理が未実装
        }

        //アダプターの初期化
        adapter.clear();

        Cursor cursor = null;

        //データの取得

        try{
            //SQL文生成
            String sql = "select " + column_name + "from " + DB_NAME + "while " + column_name + " == " +search_data;
            //カーソル代入
            cursor = db.rawQuery(sql,null);
            //カーソルの行数を取得
            int count = cursor.getCount();
            //カーソルの行数だけループ
            for(int i = 0; i < count;i++){
                //データベースから取得した情報がカーソルに入っている
                String data = cursor.getString(0) + "\n";
                //アダプターにこの情報を追加
                adapter.add(data);
                //カーソルを次に移動
                cursor.moveToNext();
            }
        }catch(Exception e){
            Log.e("SQL Error",e.toString());
            Toast.makeText(Matatabi.getInstance(),"もうこれわかんねぇな",Toast.LENGTH_LONG).show();
        }finally{
            //カーソルを閉じる
            if(cursor != null){
                cursor.close();
            }
        }
        //デバックメッセージ
        Toast.makeText(Matatabi.getInstance(),"データベースからデータの取得が完了",Toast.LENGTH_LONG).show();
        //アダプターの内容を返す
        return adapter;

    }

}
