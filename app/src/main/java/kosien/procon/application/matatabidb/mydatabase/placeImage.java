package kosien.procon.application.matatabidb.mydatabase;


/**
 * Created by procon-kyougi on 2017/09/16.
 */

public class placeImage {

    //テーブル名
    public static final String TABLE_NAME = "place_image";


    //カラムID
    public static final String COLUMN_ID = "_id";
    //日記のカラム番号
    public static final String PLACE_ID = "Place_id";
    //写真の保存場所
    public static final String SAVE_IMAGE = "Place_image";

    //自分自身のテーブルID
    private int rowid = 0;
    //日記テーブルID
    private int place_id = 0;
    //ローカルの画像の保存場所
    private String image_pass = null;

    public int getRowID() {
        return rowid;
    }

    public int getPlaceId() {
        return place_id;
    }

    public String getImagePass() {
        return image_pass;
    }

    public void setRowid(int _rowId) {
        this.rowid = _rowId;
    }

    public void setPlaceId(int _placeId) {
        this.place_id = _placeId;
    }

    public void setImagePass(String _imagePass) {
        this.image_pass = _imagePass;
    }

}
