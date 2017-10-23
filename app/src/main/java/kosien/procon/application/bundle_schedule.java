package kosien.procon.application;

import java.io.Serializable;

/**
 * Created by procon-kyougi on 2017/10/23.
 */

public class bundle_schedule implements Serializable {

    //スケジュール詳細画面にバンドルスルーデータ
    private int rowId;
    private int travelNum;

    //観光地の名前と種別さえ分かれば詳細はデータベースでオープン出来る
    private String placeName;
    private String placeCategory;


    public bundle_schedule(){}

    public void setRowId(int _rowId){
        this.rowId = _rowId;
    }
    public void setTravelNum(int _travelNum){this.travelNum = _travelNum;}
    public void setPlaceName(String _placeName){this.placeName = _placeName;}
    public void setPlaceCategory(String _placeCategory){this.placeCategory = _placeCategory;}

    public int getTravelNum(){
        return this.travelNum;
    }
    public int getRowId(){return this.rowId;}
    public String getPlaceName(){return this.placeName;}
    public String getPlaceCategory(){return this.placeCategory;}
}
