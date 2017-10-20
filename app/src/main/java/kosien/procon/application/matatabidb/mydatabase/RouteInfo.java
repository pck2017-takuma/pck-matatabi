package kosien.procon.application.matatabidb.mydatabase;


import java.io.Serializable;

/**
 * Created by Owner on 2017/09/15.
 */

public class RouteInfo implements Serializable {


    //添付画像は別テーブルに作成しているので注意

    //テーブル名

    public static final String TABLE_NAME = "Route_Info";

    //ここからカラム名

    //カラムID
    public static final String COLUMN_ID = "_id";
    //////////////////////////////////////////テーブル名////////////////////////////////////////////////////////////
    //出発駅
    public static final String ROUTE_DEPARTURE = "route_departure";
    //出発時刻
    public static final String ROUTE_DEPTTIME = "route_depttime";
    //到着駅
    public static final String ROUTE_DESTINATION = "route_destination";
    //到着時刻
    public static final String ROUTE_ARVTIME = "route_arvtime";
    //列車名
    public static final String ROUTE_TRAIN = "route_train";
    //番号
    public static final String TRAVEL_NUM = "travel_num";
    //訪れたかどうかフラグを立てる
    public static final String ROUTE_FLAG = "route_flag";
    //どの行程かを割り当てる
    public static final String SCHEDULE_NUM = "schedule_num";

    //プロパティ
    //行番号
    private int rowid = 0;
    //出発駅
    private String routeDeparture = null;
    //出発時刻
    private String routeDepttime = null;
    //到着駅
    private String routeDestination = null;
    //到着時刻
    private String routeArvtime = null;
    //列車名
    private String routeTrain = null;
    //番号
    private int travelNum = 0;
    //フラグ
    private int routeFlag = 0;
    //行程番号
    private int scheduleNum = -1;

    //ゲッター
    public int getRowid() {
        return rowid;
    }

    public String getRouteDeparture() {
        return this.routeDeparture;
    }

    public String getrouteDepttime() {
        return this.routeDepttime;
    }

    public String getRouteDestination() {
        return this.routeDestination;
    }

    public String getRouteArvtime() {
        return this.routeArvtime;
    }

    public String getRouteTrain() {
        return this.routeTrain;
    }

    public int getTravelNum() {
        return this.travelNum;
    }

    public int getRouteFlag() {
        return this.routeFlag;
    }

    public int getScheduleNum() {
        return this.scheduleNum;
    }

    //セッター
    public void setRowid(int _rowId) {
        this.rowid = _rowId;
    }

    public void setRouteDeparture(String _routeDeparture) {
        this.routeDeparture = _routeDeparture;
    }

    public void setRouteDepttime(String _routeDepttime) {
        this.routeDepttime = _routeDepttime;
    }

    public void setRouteDestination(String _routeDestination) {
        this.routeDestination = _routeDestination;
    }

    public void setRouteArvtime(String _routeArvtime) {
        this.routeArvtime = _routeArvtime;
    }

    public void setRouteTrain(String _routeTrain) {
        this.routeTrain = _routeTrain;
    }

    public void setTravelNum(int _travelNum) {
        this.travelNum = _travelNum;
    }

    public void setRouteFlag(int _routeFlag) {
        this.routeFlag = _routeFlag;
    }

    public void setScheduleNum(int _scheduleNum) {
        this.scheduleNum = _scheduleNum;
    }

}
