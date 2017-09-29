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

    //
    public static final String TRAVEL_TITEL = "travel_title";
    //プロパティ
    //行番号
    private int rowid = 0;
    //旅番号
    private int travelNum = 0;
    //日記タイトル
    private String travelTitle = null;

    //ゲッター
    public int getRowid(){
        return rowid;
    }
    public int gettravelNum(){return travelNum;}
    public String getTravelTitle(){return travelTitle;}
    public void setRowid(int _rowId){
        this.rowid = _rowId;
    }
    public void setTravelNum(int _travelnum){
        this.travelNum = _travelnum;
    }

    public void setTravelTitle(String _travelTitle){
        this.travelTitle = _travelTitle;
    }


}
