package kosien.procon.application.matatabidb.mydatabase;


import java.io.Serializable;

/**
 * Created by procon-kyougi on 2017/09/08.
 */

public class Station_Infomation implements Serializable {

    //テーブル名
    public static final String TABLE_NAME = "station_infomation";

    //カラム名
    public static final String COLUMN_ID = "_id";
    public static final String STATION_NAME = "station_name";
    public static final String STATION_CODE = "station_code";
    public static final String KANA_NAME = "kana_station_name";
    public static final String BET_NAME = "bet_station_name";
    public static final String STATION_NUMBER = "station_number";
    public static final String STATION_POST = "station_post";
    public static final String STATION_ADDRESS = "station_address";
    public static final String STATION_LATITUDE = "station_latitude";
    public static final String STATION_LONGITUDE = "station_longitude";
    public static final String TOURIST_FLAG = "tourist_flag";
    public static final String CITY_EVA_VALUE = "city_evalution_value";
    public static final String COLUMN_id = "_id";

    //プロパティ
    private Long rowid = null;
    private String stationName = null;
    private long stationCode = 0;
    private String kanaName = null;
    private String betName = null;
    private String stationNumber = null;
    private String stationPost = null;
    private String stationAddress = null;
    private double stationLatitude = 0.0;
    private double stationLongitude = 0.0;
    private String touristFlag = null;

    private String City_Eva_Value = null;

    //ゲッター
    public Long getRowid() {
        return rowid;
    }

    public String getStationname() {
        return stationName;
    }

    public Long getStationCode() {
        return stationCode;
    }

    public String getKananame() {
        return kanaName;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public String getStationpost() {
        return stationPost;
    }

    public String getStationaddress() {
        return stationAddress;
    }

    public String getBetname() {
        return betName;
    }

    public double getStationlatitude() {
        return stationLatitude;
    }

    public double getStationlongitude() {
        return stationLongitude;
    }

    public String gettouristFlag() {
        return touristFlag;
    }


    public String getCityEva() {
        return City_Eva_Value;
    }

    //セッター
    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public void setStationName(String _stationName) {
        this.stationName = _stationName;
    }

    public void setStationCode(Long _stationCode) {
        this.stationCode = _stationCode;
    }

    public void setKanaName(String _kanaName) {
        this.kanaName = _kanaName;
    }

    public void setBetName(String betName) {
        this.kanaName = betName;
    }

    public void setStationNumber(String _stationNumber) {
        this.stationNumber = _stationNumber;
    }

    public void setStationPost(String _stationPost) {
        this.stationPost = _stationPost;
    }

    public void setStationAddress(String _stationAddress) {
        this.stationAddress = _stationAddress;
    }

    public void setStationLatitude(double _stationLatitude) {
        this.stationLatitude = _stationLatitude;
    }

    public void setStationLongitude(double _stationLongitude) {
        this.stationLongitude = _stationLongitude;
    }

    public void setTouristFlag(String _touristFlag) {
        this.touristFlag = _touristFlag;
    }


    public void setCITY_EVA(String _city_eva) {
        this.City_Eva_Value = _city_eva;
    }


}
