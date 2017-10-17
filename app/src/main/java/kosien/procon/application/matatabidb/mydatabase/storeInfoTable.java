package kosien.procon.application.matatabidb.mydatabase;

import java.io.Serializable;

/**
 * Created by procon-kyougi on 2017/10/17.
 */


//飲食店情報管理データベース
public class storeInfoTable implements Serializable {

    //テーブル名
    public static final String TABLE_NAME = "store_table";

    //カラムID
    public static final String COLUMN_ID = "_id";

    //こ↑こ↓からてーぶるめい

    //飲食店名
    public static final String STORE_NAME = "store_name";
    //最寄り駅番号
    public static final String STORE_STATION = "store_station";
    //緯度
    public static final String LATITUDE = "store_latitude";
    //経度
    public static final String LONGITUDE = "store_longitude";
    //住所
    public static final String ADDRESS = "store_address";
    //紹介
    public static final String COLUMN = "store_column";
    //電話番号
    public static final String PHONE_NUM = "store_phone";
    //郵便番号
    public static final String POST_NUM = "post_num";
    //営業開始時間
    public static final String OPEN_TIME = "open_time";
    //営業終了時間
    public static final String CLOSE_TIME = "close_time";
    //お店のジャンル
    public static final String STORE_GENRE = "store_genre";


    //ここからプロパティ
    private int rowId = 0;
    //お店の名前
    private String storeName = null;
    //最寄り駅番号
    private String stationNumber = null;
    //緯度
    private String storeLatitude = null;
    //経度
    private String storeLongitude = null;
    //住所
    private String placeAddress = null;
    //紹介
    private String storeColumn = null;
    //電話番号
    private String storePhoneNum = null;
    //郵便番号
    private String storePostNum = null;
    //営業開始時間
    private String storeOpenTime = null;
    //営業終了時間
    private String storeCloseTime = null;
    //genre
    private int storeGenre = 0;


    //ゲッター
    public int getRowId(){
        return this.rowId;
    }
    public String getStoreName(){
        return this.storeName;
    }

    public String getStoreStation(){
        return this.stationNumber;
    }
    public String getStoreLatitude(){
        return this.storeLatitude;
    }
    public String getStoreLongitude(){
        return this.storeLongitude;
    }
    public String getPlaceAddress(){
        return this.placeAddress;
    }
    public String getStoreColumn(){
        return this.storeColumn;
    }
    public String getStorePhoneNum(){
        return this.storePhoneNum;
    }
    public String getStorePostNum(){
        return this.storePostNum;
    }
    public String getStoreOpenTime(){
        return this.storeOpenTime;
    }
    public String getStoreCloseTime(){
        return this.storeCloseTime;
    }
    public int getStoreGenre(){
        return this.storeGenre;
    }


    //セッター
    public void setRowId(int _rowId){this.rowId = _rowId;}
    public void setStoreName(String _storeName){this.storeName = _storeName;}
    public void setStoreStation(String _stationNumber){this.stationNumber = _stationNumber;}
    public void setStoreLatitude(String _storeLatitude){this.storeLatitude = _storeLatitude;}
    public void setStoreLongitude(String _storeLongitude){this.storeLongitude = _storeLongitude;}
    public void setStoreAddress(String _storeAddress){this.placeAddress = _storeAddress;}
    public void setStoreColumn(String _storeColumn){this.storeColumn = _storeColumn;}
    public void setStorePhoneNum(String _storePhoneNum){this.storePhoneNum = _storePhoneNum;}
    public void setStorePostNum(String _storePostNum){this.storePhoneNum = _storePostNum;}
    public void setStoreOpenTime(String _storeOpenTime){this.storeOpenTime = _storeOpenTime;}
    public void setStoreCloseTime(String _storeCloseTime){this.storeCloseTime = _storeCloseTime;}
    public void setStoreGenre(int _storeGenre){this.storeGenre = _storeGenre;}










}
