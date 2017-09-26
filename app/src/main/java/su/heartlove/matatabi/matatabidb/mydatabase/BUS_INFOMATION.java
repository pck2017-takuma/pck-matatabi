 package su.heartlove.matatabi.matatabidb.mydatabase;


import java.io.Serializable;

/**
 * Created by i15317 on 2017/09/11.
 */

public class BUS_INFOMATION implements Serializable {

//カラム名に追加
public static final String COLUMN_ID = "_id";

//プロパティに追加
private Long rowid = null;

//ゲッターに追加
public Long getRowid() {
      return rowid;
}

//セッターに追加
public void setRowid(Long rowid) {
     this.rowid = rowid;
}
    
    //テーブル名
    public static final String TABLE_NAME = "Bus_Infomation";
    //停留所
    public static final String BUS_STOPNAME = "bus_stopname";
    //停留所の読み
    public static final String KANA_NAME = "kana_name";
    //ローマ字で停留所
    public static final String BET_NAME = "bet_name";
    //路線・バス区別
    public static final String BUS_NUMBER = "bus_number";
    //駅前、構内直通の場合はその駅番号を記載する。駅前　＜　構内
    public static final String STATION_NUMBER = "station_number";


    //プロパティ
    private String busStopname = null;
    private String kanaName = null;
    private String betName = null;
    private String busNumber = null;
    private String staitonNumber = null;

    //ゲッター

    public String getBusStopName() {
        return busStopname;
    }

    public String getkanaName() {
        return kanaName;
    }

    public String getbetName() {
        return betName;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public String getstationNumber() {
        return staitonNumber;
    }

    //ここからゲッター
    public void setBusStopName(String _busStopname) {
        this.busStopname = _busStopname;
    }

    public void setkanaName(String _kanaName) {
        this.kanaName = _kanaName;
    }

    public void setbetName(String _betName) {
        this.betName = _betName;
    }

    public void setBusNumber(String _busNumber) {
        this.busNumber = _busNumber;
    }

    public void setstationNumber(String _stationNumber) {
        this.staitonNumber = _stationNumber;
    }


}
