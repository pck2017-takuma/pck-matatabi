package kosien.procon.application.matatabidb.mydatabase;


import java.io.Serializable;

/**
 * Created by i15317 on 2017/09/16.
 */

public class RecordTime implements Serializable {

    //テーブル名
    public static final String TABLE_NAME = "diaryUpdateTime";

    //ここからカラム名

    //カラムID
    public static final String COLUMN_ID = "_id";
    //日記のカラムID
    public static final String DIARY_ID = "diary_Id";
    //時間（すべて秒）
    public static final String UPDATE_TIME = "update_Time";
    //時間（年）
    public static final String UPDATE_YEAR = "update_Year";
    //時間（月）
    public static final String UPDATE_DATE = "update_Date";
    //時間（日）
    public static final String UPDATE_DAY = "update_Day";
    //時間（その日の時間）:単位は秒
    public static final String UPDATE_DAYTIME = "update_dayTime";

    //ここからプロパティ
    //カラムID
    private int rowid = 0;
    //日記のカラムID
    private int diaryId = 0;
    //時間（すべて秒）
    private int update_time = 0;
    //時間（年）
    private int update_year = 0;
    //時間（月）
    private int update_date = 0;
    //時間（日）
    private int update_day = 0;
    //時間（その日の時間）：単位は秒
    private int update_dayTime = 0;


    public int getRowid() {
        return rowid;
    }

    public int getdiaryID() {
        return diaryId;
    }

    public int getUpdateTime() {
        return update_time;
    }

    public int getUpdateYear() {
        return update_year;
    }

    public int getUpdateDate() {
        return update_date;
    }

    public int getUpdateDay() {
        return update_day;
    }

    public int getUpdateDayTime() {
        return update_dayTime;
    }


    public void setRowId(int _rowId) {
        this.rowid = _rowId;
    }

    public void setDiaryId(int _diaryId) {
        this.diaryId = _diaryId;
    }

    public void setDiaryUpdateTime(int _diaryupdatetime) {
        this.update_time = _diaryupdatetime;
    }

    public void setUpdateYear(int _updateYear) {
        this.update_year = _updateYear;
    }

    public void setUpdateDate(int _updateDate) {
        this.update_date = _updateDate;
    }

    public void setUpdateDay(int _updateDay) {
        this.update_day = _updateDay;
    }

    public void setUpdateDayTime(int _updatedayTime) {
        this.update_dayTime = _updatedayTime;
    }


}
