package kosien.procon.application.matatabidb.mydatabase;


import java.io.Serializable;

/**
 * Created by procon-kyougi on 2017/09/12.
 */

public class Train_Info implements Serializable {


    //テーブル名
    public static final String TABLE_NAME = "Train_Infomation";

    //ここからカラム名
    //カラム名に追加
    public static final String COLUMN_ID = "_id";
    //列車番号
    public static final String TRAIN_NUMBER = "Train_Number";
    //始発駅
    public static final String STARTING_STATION = "Starting_Station";
    //終着駅
    public static final String TERMINAL_STATION = "Terminal_Station";
    //列車種別
    public static final String TRAIN_ASSORTMENT = "Train_Assortment";
    //列車の運行条件的な？
    public static final String TRAIN_CONDITION = "Train_Condition";
    //備考欄
    public static final String TRAIN_COLUMN = "Train_Column";

    //プロパティ
    //private String trainName = null;
    private String trainNumber = null;
    private String startingStation = null;
    private String terminalStation = null;
    private String trainAssortment = null;
    private String trainCondition = null;
    private String trainColumn = null;
    //プロパティに追加
    private Long rowid = null;


    //ここからゲッター実装

    // public String getTrainName(){
    //     return trainName;
    // }


    //ゲッターに追加
    public Long getRowid() {
        return rowid;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getstatingStation() {
        return startingStation;
    }

    public String getTerminalStation() {
        return terminalStation;
    }

    public String getTrainAssortment() {
        return trainAssortment;
    }

    public String getTrainCondition() {
        return trainCondition;
    }

    public String getTrainColumn() {
        return trainColumn;
    }

    //ここからセッター実装

    // public void setTrainName(String _trainName){
    //     this.trainName = _trainName;
    // }


    //セッターに追加
    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public void setTrainNumber(String _trainNumber) {
        this.trainNumber = _trainNumber;
    }

    public void setstatingStation(String _statingStation) {
        this.startingStation = _statingStation;
    }

    public void setTerminalStaiton(String _terminalStation) {
        this.terminalStation = _terminalStation;
    }

    public void setTrainAssortment(String _trainAssortment) {
        this.trainAssortment = _trainAssortment;
    }

    public void setTrainCondition(String _trainCondition) {
        this.trainCondition = _trainCondition;
    }

    public void setTrainColumn(String _trainColumn) {
        this.trainColumn = _trainColumn;
    }

}
