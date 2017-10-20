package kosien.procon.application;

import android.graphics.Bitmap;

/**
 * Created by procon-kyougi on 2017/10/20.
 */


public class timetable_list_item {

    /*こ↑こ↓表示アイテム*/
    //発駅画像
    private Bitmap startStationImage = null;
    //着駅画像
    private Bitmap endStationImage = null;
    //列車情報表示
    private String trainInfo = null;
    //発駅
    private String startStation = null;
    //着駅
    private String endStation = null;
    //発駅時刻
    private String startTime = null;
    //着駅時刻
    private String endTime = null;

    //一応ダイレクトに値を入れられるコンストラクタは用意するけど基本はセッター関数を使ったほうが分かりやすいと思います
    public timetable_list_item(String _startStation, String _startTime, String _endStation, String _endTime, String _trainInfo) {
        this.trainInfo = _trainInfo;
        this.startStation = _startStation;
        this.startTime = _startTime;
        this.endStation = _endStation;
        this.endTime = _endTime;

        //画像読み込み


    }

    //コンストラクタ（別途セッターを読んでね）
    public timetable_list_item() {
        //画像読み込み
    }

    //発駅情報

    public void setStartStationInfo(String _startStation, String _startTime) {
        this.startStation = _startStation;
        this.startTime = _startTime;
    }

    //着駅情報
    public void setEndStationInfo(String _endStation, String _endTime) {
        this.endStation = _endStation;
        this.endTime = _endTime;

    }

    //列車情報セット
    public void setTrainInfo(String _trainInfo) {
        this.trainInfo = _trainInfo;
    }


    //ゲッター（１つずつ実装）

    public Bitmap getStartStationImage() {
        return this.startStationImage;
    }

    public Bitmap getEndStationImage() {
        return this.endStationImage;
    }

    public String getStartStation() {
        return this.startStation;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndStation() {
        return this.endStation;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getTrainInfo() {
        return this.trainInfo;
    }


}
