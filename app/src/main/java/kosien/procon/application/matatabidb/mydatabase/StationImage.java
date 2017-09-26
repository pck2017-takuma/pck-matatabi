package kosien.procon.application.matatabidb.mydatabase;


import java.io.Serializable;

/**
 * Created by procon-kyougi on 2017/09/16.
 */

public class StationImage implements Serializable{

    //テーブル名
    public static final String TABLE_NAME = "station_image";

    //ここからカラム名

    //カラムID
    public static final String COLUMN_ID = "_id";
    //駅番号
    public static final String STATION_NUMBER = "_id";
    //画像保存場所
    public static final String STATION_PICTURE = "station_picture";

    //ここからプロパティ
    private int rowId = 0;
    private String stationNumber = null;
    private String stationPicture = null;

    //ここからゲッター
    public int getRowId(){
        return rowId;
    }
    public String getStationNumber(){
        return stationNumber;
    }
    public String getStationPicuture(){
        return stationPicture;
    }
    //ここからセッター

    public void setRowId(int _rowId){
        this.rowId = _rowId;
    }
    public void setStationNumber(String _stationNumber){
        this.stationNumber = _stationNumber;
    }
    public void setStationPicture(String _stationPicture){
        this.stationPicture = _stationPicture;
    }
}
