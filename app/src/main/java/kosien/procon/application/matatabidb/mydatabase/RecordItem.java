package kosien.procon.application.matatabidb.mydatabase;


import java.io.Serializable;

/**
 * Created by Owner on 2017/09/15.
 */

public class RecordItem implements Serializable {


    //添付画像は別テーブルに作成しているので注意

    //テーブル名
    public static final String TABLE_NAME = "matatabi_diary";
    //ここからカ
    //カラムID
    public static final String COLUMN_ID = "_id";
    //////////////////////////////////////////テーブル名////////////////////////////////////////////////////////////
    //日記タイトル
    public static final String DIARY_TITLE = "diary_title";
    //日記本文を記録
    public static final String DIARY_RECORD = "diary_record";
    //旅行番号（これが何の旅行か識別する用）
    public static final String TRAVEL_NUM = "travel_num";
    //日記最終更新日の年数
    public static final String DIARY_YEAR = "diary_year";
    //日記最終更新日の月
    public static final String DIARY_MON = "diary_mon";
    //日記最終更新日の日にち
    public static final String DIARY_Day = "diary_data";
    //日記最終更新日の時間（秒単位）
    public static final String DIARY_TIME = "diary_time";
    //スケジュール割り当て
    public static final String SCHEDULE_NUM = "schedule_num";


    //プロパティ
    //行番号
    private int rowid = 0;
    //日記タイトル
    private String diaryTitle = null;
    //日記本文
    private String diaryRecord = null;
    //日記最終更新日の年数
    private int diaryYear = -1;
    //日記最終更新日の月
    private int diaryMon = -1;
    //日記最終更新日の日
    private int diaryDay = -1;
    //日記最終更新日の時間
    private int diaryTime = -1;
    //旅番号
    private int travelNum = 0;
    //スケジュール
    private int scheduleNum = 0;

    private static String itemsearchrecord = null;
    private static int itemsearchyear = 0;
    private static int itemsearchmon = 0;
    private static int itemsearchday = 0;


    //ゲッター
    public int getRowid(){
        return rowid;
    }
    public String getDiaryTitle(){
        return diaryTitle;
    }
    public int getDiaryYear(){
        return diaryYear;
    }
    public int getDiaryMon(){
        return diaryMon;
    }
    public int getDiaryDay(){
        return diaryDay;
    }
    public int getDiaryTime(){
        return diaryTime;
    }
    public int gettravelNum(){return travelNum;}
    public String getDiaryRecord(){
         return diaryRecord;
     }
    public int getScheduleNum(){return scheduleNum;}

    //セッター
    public void setRowid(int _rowId){
        this.rowid = _rowId;
    }

    public void setDiaryTitle(String _diaryTitle){
        this.diaryTitle = _diaryTitle;
    }
    public void setTravelNum(int _travelNum){this.travelNum = _travelNum;}
    public void setDiaryYear(int _diaryYear){
        this.diaryYear = _diaryYear;
    }
    public void setDiaryMon(int _diaryMon){
        this.diaryMon = _diaryMon;
    }
    public void setDiaryDay(int _diaryDay){
        this.diaryDay = _diaryDay;
    }
    public void setDiaryTime(int _diaryTime){
        this.diaryTime = _diaryTime;
    }
    public void setScheduleNum(int _scheduleNum){this.scheduleNum = _scheduleNum;}

    // //セッターについては年数とかを個別に設定することは想定していない
    // public void setDiaryTime(int _time){
    //     //この時間を年、月、日、秒（時間）に分解する
    //     int month = 0;
    //     int data = 0;
    //     int year = 0;
    //     int time = 0;

    //     //この先はまだ空実装
    // }

     public void setDiaryRecord(String _diaryRecord){
         this.diaryRecord = _diaryRecord;
     }



    public static int getDiarySearchyear() { return itemsearchyear; }
    public void setItemSearchYear(int paramYear) { RecordItem.itemsearchyear = paramYear; }

    public static int getItemSearchMon() { return itemsearchmon; }
    public void setItemSearchMon(int paramMon) { RecordItem.itemsearchmon = paramMon; }

    public static int getItemSearchDay() { return itemsearchday; }
    public void setItemSearchHi(int paramDay) { RecordItem.itemsearchday = paramDay; }

    public static String getItemSearchRecord() { return itemsearchrecord; }
    public void setItemSearchRecord(String paramNikki) { RecordItem.itemsearchrecord = paramNikki; }


    //ListViewで内容を表示（デバック用？）、アプリ完成時にはコメントアウト推奨
    // @Override
    // public String toString(){
    //     表示文字数
    //     final int diary_length = 10;

    //     String diary = getDiaryRecord();
    //     なぜか改行を削除しないとダメみたい
    //     diary = diary.replaceAll("\n","");
    //     //日付を生成するのかな
    //     StringBuilder builder = new StringBuilder();
    //     //年数を取得
    //     builder.append(getDiaryYear());
    //     builder.append("年");
    //     //月を取得
    //     builder.append(getDiaryMon());
    //     builder.append("月");
    //     //日にちを取得
    //     builder.append(getDiaryDay());
    //     builder.append("日");

    //     //出だしのx文字まで表示するよ
    //     if(diary.length() > diary_length){
    //         builder.append(" : " + diary.substring(0,diary_length));
    //     }else{
    //         builder.append(" : " + diary);
    //     }

    //     return builder.toString();

    // }






}
