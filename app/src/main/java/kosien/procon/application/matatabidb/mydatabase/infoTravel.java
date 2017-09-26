package kosien.procon.application.matatabidb.mydatabase;

import java.io.Serializable;

/**
 * Created by procon-kyougi on 2017/09/23.
 */

public class infoTravel implements Serializable{
    //テーブル名
    public static final String TABLE_NAME = "travel_info";
    //カラムID
    public static final String COLUMN_ID = "_id";
    //////////////////////////////////////////テーブル名////////////////////////////////////////////////////////////
    //日記タイトル
    public static final String TRAVEL_NUM = "travel_num";

    //プロパティ
    //行番号
    private int rowid = 0;
    //旅番号
    private int travelNum = 0;

    //ゲッター
    public int getRowid(){
        return rowid;
    }
    public int gettravelNum(){return travelNum;}


    public void setRowid(int _rowId){
        this.rowid = _rowId;
    }

    public void setTravelNum(int _travelnum){
        this.travelNum = _travelnum;
    }


}
