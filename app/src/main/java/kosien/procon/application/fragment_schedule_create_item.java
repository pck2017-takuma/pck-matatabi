package kosien.procon.application;

/**
 * Created by procon-kyougi on 2017/10/20.
 */

public class fragment_schedule_create_item {
    //観光地名
    private String placeName;


    public fragment_schedule_create_item(String _placeName){
        this.placeName = _placeName;
    }


    public void setPlaceName(String _placeName) {
        this.placeName = placeName;
    }

    public String getPlaceName() {
        return this.placeName;
    }
}
