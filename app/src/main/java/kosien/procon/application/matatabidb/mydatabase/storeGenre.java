package kosien.procon.application.matatabidb.mydatabase;

import java.io.Serializable;

/**
 * Created by procon-kyougi on 2017/10/17.
 */

/*ジャンル番号割り当て

１・・・
２・・・



 */


public class storeGenre implements Serializable {

    //テーブル名
    public static final String TABLE_NAME = "store_genre";

    //カラムID
    public static final String COLUMN_ID = "column_id";
    //ジャンル番号
    public static final String GENRE_ID = "genre_id";
    //ジャンル名
    public static final String GENRE_NAME = "genre_name";


    //プロパティ
    private long rowId = 0;
    private String genreId = null;
    private String genreName = null;

    //ゲッター
    public long getRowId(){
        return this.rowId;
    }
    public String getGenreId(){
        return this.genreId;
    }
    public String getGenreName(){return this.genreName;}

    //セッター
    public void setRowId(long _rowId){this.rowId = _rowId;}
    public void setGenreId(String _genreId){this.genreId = _genreId;}
    public void setGenreName(String _genreName){this.genreName = _genreName;}



}
