package kosien.procon.application.matatabidb.mydatabase;


import java.io.Serializable;

/**
 * Created by procon-kyougi on 2017/09/16.
 */

public class placeInfomation implements Serializable{
    //テーブル名
    public static final String TABLE_NAME = "place_infomation";
    //ここからカラム名
    //カラムID
    public static final String COLUMN_ID = "_id";

    //観光地名
    public static final String PLACE_NAME = "Place_Name";
    //最寄り駅番号
    public static final String PLACE_STATION = "Place_Station";
    //緯度
    public static final String LATITUDE = "Place_Latitude";
    //経度
    public static final String LONGITUDE = "Place_Longitude";
    //住所
    public static final String ADDRESS = "Place_Address";
    //紹介
    public static final String COLUMN = "Place_Column";
    //電話番号
    public static final String PHONE_NUMBER = "Phone_Number";
    //郵便番号
    public static final String POST_NUMBER = "Post_Number";
    //営業開始時間
    public static final String OPEN_TIME = "Open_Time";
    //営業終了時間
    public static final String CLOSE_TIME = "Close_Time";
    //評価値
    public static final String PLACE_VALUE = "Place_Value";

    //ここからプロパティ
    private int rowId = 0;
    //観光地名
    private String placeName = null;
    //最寄り駅
    private String placeStation = null;
    //緯度
    private String placeLatitude = null;
    //経度
    private String placeLongitude = null;
    //住所
    private String placeAddress = null;
    //紹介
    private String placeColumn = null;
    //電話番号
    private String placePhoneNumber = null;
    //郵便番号
    private String placePostNumber = null;
    //営業開始時間
    private String placeOpenTime = null;
    //営業終了時間
    private String placeCloseTime = null;
    //評価値
    private int placeValue = 0;

    //ゲッター
    public int getRowId(){
        return rowId;
    }
    public String getPlaceName(){
        return placeName;
    }
    public String getPlaceStation(){return placeStation;}
    public String getPlaceLatitude(){
        return placeLatitude;
    }
    public String getPlaceLongitude(){
        return placeLongitude;
    }
    public String getPlaceAddress(){
        return placeAddress;
    }
    public String getPlaceColumn(){
        return placeColumn;
    }
    public String getPlacePhoneNumber(){
        return placePhoneNumber;
    }
    public String getPlacePostNumber(){
        return placePostNumber;
    }
    public String getPlaceOpenTime(){
        return placeOpenTime;
    }
    public String getCloseTime(){
        return placeCloseTime;
    }
    public int getPlaceValue(){
        return placeValue;
    }

    //セッター
    public void setRowId(int _rowId){
        this.rowId = _rowId;
    }
    public void setPlaceName(String _placeName){
        this.placeName = _placeName;
    }
    public void setPlaceStation(String _placeStation){this.placeStation = _placeStation;}
    public void setPlaceLatitude(String _latitude){
        this.placeLatitude = _latitude;
    }
    public void setPlaceLongitude(String _longitude){
        this.placeLongitude = _longitude;
    }
    public void setPlaceAddress(String _address){
        this.placeAddress = _address;
    }
    public void setPlaceColumn(String _column){
        this.placeColumn = _column;
    }
    public void setPlacePhoneNumber(String _phoneNumber){
        this.placePhoneNumber = _phoneNumber;
    }
    public void setPlacePostNumber(String _postNumber){
        this.placePostNumber = _postNumber;
    }
    public void setPlaceOpenTime(String _openTime){
        this.placeOpenTime = _openTime;
    }
    public void setPlaceCloseTime(String _closeTime){
        this.placeCloseTime = _closeTime;
    }
    public void setPlaceValue(int _setValue){
        this.placeValue  = _setValue;
    }

}
