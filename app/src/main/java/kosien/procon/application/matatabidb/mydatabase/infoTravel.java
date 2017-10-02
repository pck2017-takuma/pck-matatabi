package kosien.procon.application.matatabidb.mydatabase;

import java.io.Serializable;

import static java.sql.Types.NULL;

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
    //
    public static final String TRAVEL_FLAG = "travel_flag";




    //行番号
    private Integer rowid = NULL;
    //旅番号
    private int travelNum = 0;
    //日記タイトル
    private String travelTitle = null;
    //旅の実行状態
    private int travelFlag = 0;


    //ゲッター
    public int getRowid(){
        return rowid;
    }
    public int gettravelNum(){return travelNum;}
    public int getTravelNum(){return travelNum;}

    public String getTravelTitle(){return travelTitle;}
    public int getTravelFlag(){return travelFlag;}

    public void setRowid(int _rowId){
        this.rowid = _rowId;
    }
    public void setTravelNum(int _travelnum){
        this.travelNum = _travelnum;
    }
    public void setTravelFlag(int _travelFlag){this.travelFlag = _travelFlag;}
    public void setTravelTitle(String _travelTitle){
        this.travelTitle = _travelTitle;
    }


}
