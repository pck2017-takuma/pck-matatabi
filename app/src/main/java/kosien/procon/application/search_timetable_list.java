package kosien.procon.application;

/**
 * Created by procon-kyougi on 2017/10/20.
 */

public class search_timetable_list {
    private String trainTime = null;
    private String trainDetail = null;
    private String trainDepature = null;

    //コンストラクタ
    public search_timetable_list() {
    }

    //ゲッター
    public void setTrainTime(String _trainName) {
        this.trainTime = _trainName;
    }

    public void setTrainDetail(String _trainDetail) {
        this.trainDetail = _trainDetail;
    }

    public void setTrainDepature(String _trainDepature) {
        this.trainDepature = _trainDepature;
    }

    public String getTrainTime() {
        return this.trainTime;
    }

    public String getTrainDetail() {
        return this.trainDetail;
    }

    public String getTrainDepature() {
        return this.trainDepature;
    }

}
