package kosien.procon.application;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by i15317 on 2017/10/13.
 */


//駅情報と距離を格納したクラス
class stationData{
        public String stationName;
        public double distance;
}





//緯度経度を用いた駅情報APIのパース処理
public class parseSearchStation  {

    //JSONのパース

    private final String point = "Point";
    private final String distance = "Distance";

    private JSONObject srcObject = new JSONObject();

    //取得データをこ↑こ↓に
    ArrayList<stationData>stationInfo;

    public JSONObject getSrcObject() {
        return srcObject;
    }
    //取得データこ↑こ↓まで


    //コンストラクタ
    public parseSearchStation(JSONObject object){
        srcObject = object;
    }

    public void parseData(){

        //Pointオブジェクト？を取得
        try {
            JSONObject pointJSON = srcObject.getJSONObject(point);
        }catch(JSONException e){
            System.out.println("parseSearchStationのpointのパース処理が失敗したよ");
        }





    }


    //Distance基準で駅情報をソートする関数
    private void sort(){



    }



}
