package su.heartlove.matatabi.matatabidb.mydatabase;


import java.io.Serializable;

/**
 * Created by Owner on 2017/09/15.
 */

public class Diary_Image implements Serializable {

    //テーブル名
    public static final String TABLE_NAME = "diary_image";


    //カラムID
    public static final String COLUMN_ID = "_id";
    //日記のカラム番号
    public static final String DIARY_ID = "Diary_id";
    //写真の保存場所
    public static final String SAVE_IMAGE = "Diary_image";

    //自分自身のテーブルID
    private int rowid = 0;
    //日記テーブルID
    private int diary_id = 0;
    //ローカルの画像の保存場所
    private String image_pass = null;

    public int getRowID(){
        return rowid;
    }
    public int getDiaryId() {
        return diary_id;
    }
    public String getImagePass(){
        return image_pass;
    }

    public void setRowid(int _rowId){
        this.rowid = _rowId;
    }
    public void setDiaryId(int _diaryId){
        this.diary_id = _diaryId;
    }
    public void setImagePass(String _imagePass){
        this.image_pass = _imagePass;
    }


}
