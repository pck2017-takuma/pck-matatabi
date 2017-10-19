package kosien.procon.application;

import android.graphics.Bitmap;

/**
 * Created by procon-kyougi on 2017/10/20.
 */

public class train_timetable_list {
    private String arriveTime = null;
    private String departTime = null;
    private String stationName = null;
    private Bitmap stationIcon = null;


    //セッター
    public void setArriveTime(String _arriveTime){
        this.arriveTime = _arriveTime;
    }

    public void setDepartTime(String _departTime){
        this.departTime = _departTime;
    }

    public void setStationName(String _stationName){
        this.stationName = _stationName;
    }

    public void setStationIcon(Bitmap _stationIcon){
        this.stationIcon = _stationIcon;
    }

    public String getArriveTime(){
        return this.arriveTime;
    }

    public String getDepartTime(){
        return this.departTime;
    }

    public String getStationName(){
        return this.stationName;
    }

    public Bitmap getStationIcon(){
        return this.stationIcon;
    }



}
