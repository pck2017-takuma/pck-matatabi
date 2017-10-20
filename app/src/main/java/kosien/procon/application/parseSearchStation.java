package kosien.procon.application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

/**
 * Created by i15317 on 2017/10/13.
 */


//駅情報と距離を格納したクラス
class stationData {
    public String stationName;
    public double distance;
}


//緯度経度を用いた駅情報APIのパース処理
public class parseSearchStation {

    //JSONのパース

    private final String point = "Point";
    private final String distance = "Distance";

    private JSONObject srcObject = new JSONObject();

    //取得データをこ↑こ↓に
    ArrayList<stationData> stationInfo;

    public JSONObject getSrcObject() {
        return srcObject;
    }
    //取得データこ↑こ↓まで


    //コンストラクタ
    public parseSearchStation(JSONObject object) {
        srcObject = object;
    }

    public void parseData() {

        JSONArray parsePoint = new JSONArray();

        try {
            //ポイントデータは複数あるものとする
            parsePoint = srcObject.getJSONArray(point);

        } catch (JSONException e) {
            try {

                //ルートがオブジェクトなら配列で包む
                parsePoint.put(srcObject.getJSONObject(point));

            } catch (JSONException f) {
                //それでもだめなら
                System.out.println("失敗");
            }
        }

        //配列データから一つ一つのオブジェクトに変換する

        ArrayList<JSONObject> pointObject = new ArrayList<JSONObject>();

        for (int i = 0; i < parsePoint.length(); i++) {
            try {

                pointObject.add(parsePoint.getJSONObject(i));

            } catch (JSONException e) {
                System.out.println("JSONArrayからJSONObjectへの変換処理が失敗しました");
            }

        }


    }


    //Distance基準で駅情報をソートする関数
    private void sort() {


    }


}
