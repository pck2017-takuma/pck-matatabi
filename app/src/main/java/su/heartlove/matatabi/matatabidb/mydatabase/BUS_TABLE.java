package su.heartlove.matatabi.matatabidb.mydatabase;


import java.io.Serializable;

/**
 * Created by i15317 on 2017/09/11.
 */

public class BUS_TABLE implements Serializable {

    //テーブル名
    public static final String TABLE_NAME = "Bus_Table";

    //カラム名
    public static final String COLUMN_ID = "_id";

    //路線・停留所記号
    public static final String LOAD_NUMBER = "load_number";
    //上りか下りか
    public static final String BUS_AWAY = "bus_away";
    //発車時刻
    public static final String DEPARTING_TIME = "departing_time";
    //到着時刻
    public static final String ARRIVING_TIME = "arriving_time";
    //駅ナンバー
    public static final String STATION_NUMBER = "station_number";
    //観光地記号
    public static final String PLACE_NUMBER = "place_number";

    //ここからプロパティ
    private String loadNumber = null;
    private String busAway = null;
    private String depatingTime = null;
    private String arrivingTime = null;
    private String stationNumber = null;
    private String placeNumber = null;
    private Long rowid = null;

    //ここからゲッター
    public String getloadNumber() {
        return loadNumber;
    }

    public String getbusAway() {
        return busAway;
    }

    public String getdepatingTime() {
        return depatingTime;
    }

    public String getarrivingTime() {
        return arrivingTime;
    }

    public String getstationNumber() {
        return stationNumber;
    }

    public String getplaceNumber() {
        return placeNumber;
    }

    public Long getRowid() {
        return rowid;
    }

    //ここからセッター
    public void setloadNumber(String _loadNumber) {
        this.loadNumber = _loadNumber;
    }

    public void setbusAway(String _busAway) {
        this.busAway = _busAway;
    }

    public void setdepatingTime(String _depatingTime) {
        this.depatingTime = _depatingTime;
    }

    public void setarrivingTime(String _arrivingTime) {
        this.arrivingTime = _arrivingTime;
    }

    public void setstationNumber(String _stationNumber) {
        this.stationNumber = _stationNumber;
    }

    public void setplaceNumber(String _placeNumber) {
        this.placeNumber = _placeNumber;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }
}
