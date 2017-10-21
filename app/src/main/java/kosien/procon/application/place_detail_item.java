package kosien.procon.application;

import android.graphics.Bitmap;
import android.view.LayoutInflater;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Owner on 2017/10/21.
 */

public class place_detail_item implements Serializable {

    private String placeTitle;
    private String placeColumn;
    private Bitmap placeImage;
    private String placeAction;
    private String placeCategory;

    void setPlaceTitle(String _placeTitle){
        this.placeTitle = _placeTitle;
    }
    void setPlaceColumn(String _placeTitle){
        this.placeColumn = _placeTitle;
    }

    void setPlaceAction(String _placeAction){
        this.placeAction = _placeAction;
    }
    void setPlaceCategory(String _placeCategory){
        this.placeCategory = _placeCategory;
    }
    void setPlaceImage(Bitmap _placeImage){this.placeImage = _placeImage;}
    String getPlaceTitle(){
        return this.placeTitle;
    }


    String getPlaceColumn(){return this.placeColumn;}

    String getPlaceAction(){return this.placeAction;}
    String getPlaceCategory(){return this.placeCategory;}
    Bitmap getPlaceImage(){return this.placeImage;}

}
