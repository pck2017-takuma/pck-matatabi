package kosien.procon.application.matatabidb.mydatabase;

import java.io.Serializable;

/**
 * Created by procon-kyougi on 2017/09/30.
 */

public class travelSchedule implements Serializable {

    //テーブルネーム
    public static final String TABLE_NAME = "travel_schedule";
    //カラムID
    public static final String COLUMN_ID = "_id";
    //旅行番号
    public static final String TRAVEL_NUM = "travel_num";
    //訪れる場所
    public static final String PLACE_NAME = "place_name";
    //訪れる順番
    public static final String ROUTE_NUM = "route_num";
    //現在訪れているところ
    public static final String FLAG = "schedule_flag";


    //プロパティ

    private int rowid = 0;
    private int travelNum = -1;
    private String placeName = null;
    private int routeNum = -1;
    private int flag = 0;

    //ゲッター
    public int getRowid() {
        return this.rowid;
    }
    public void setRowid(int _rowid) {
        this.rowid = _rowid;
    }
    public int getTravelNum() {
        return this.travelNum;
    }
    public void setTravelNum(int _travelNum) {
        this.travelNum = _travelNum;
    }

    //セッター
    public int getRouteNum() {
        return this.routeNum;
    }
    public void setRouteNum(int _routeNum) {
        this.routeNum = _routeNum;
    }
    public String getPlaceName() {
        return this.placeName;
    }
    public void setPlaceName(String _placeName) {
        this.placeName = _placeName;
    }
    public int getFlag() {
        return this.flag;
    }
    public void setFlag(int _flag) {
        this.flag = _flag;
    }




}
